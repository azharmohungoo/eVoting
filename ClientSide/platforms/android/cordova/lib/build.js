#!/usr/bin/env node

/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/

/* jshint sub:true */

var shell   = require('shelljs'),
    spawn   = require('./spawn'),
    Q       = require('q'),
    path    = require('path'),
    fs      = require('fs'),
    os      = require('os'),
    ROOT    = path.join(__dirname, '..', '..');
var check_reqs = require('./check_reqs');
var exec  = require('./exec');


var SIGNING_PROPERTIES = '-signing.properties';
var MARKER = 'YOUR CHANGES WILL BE ERASED!';
var TEMPLATE =
    '# This file is automatically generated.\n' +
    '# Do not modify this file -- ' + MARKER + '\n';

function findApks(directory) {
    var ret = [];
    if (fs.existsSync(directory)) {
        fs.readdirSync(directory).forEach(function(p) {
            if (path.extname(p) == '.apk') {
                ret.push(path.join(directory, p));
            }
        });
    }
    return ret;
}

function sortFilesByDate(files) {
    return files.map(function(p) {
        return { p: p, t: fs.statSync(p).mtime };
    }).sort(function(a, b) {
        var timeDiff = b.t - a.t;
        return timeDiff === 0 ? a.p.length - b.p.length : timeDiff;
    }).map(function(p) { return p.p; });
}

function isAutoGenerated(file) {
    if(fs.existsSync(file)) {
        var fileContents = fs.readFileSync(file, 'utf8');
        return fileContents.indexOf(MARKER) > 0;
    }
    return false;
}

function findOutputApksHelper(dir, build_type, arch) {
    var ret = findApks(dir).filter(function(candidate) {
        // Need to choose between release and debug .apk.
        if (build_type === 'debug') {
            return /-debug/.exec(candidate) && !/-unaligned|-unsigned/.exec(candidate);
        }
        if (build_type === 'release') {
            return /-release/.exec(candidate) && !/-unaligned/.exec(candidate);
        }
        return true;
    });
    ret = sortFilesByDate(ret);
    if (ret.length === 0) {
        return ret;
    }
    // Assume arch-specific build if newest api has -x86 or -arm.
    var archSpecific = !!/-x86|-arm/.exec(ret[0]);
    // And show only arch-specific ones (or non-arch-specific)
    ret = ret.filter(function(p) {
        /*jshint -W018 */
        return !!/-x86|-arm/.exec(p) == archSpecific;
        /*jshint +W018 */
    });
    if (arch && ret.length > 1) {
        ret = ret.filter(function(p) {
            return p.indexOf('-' + arch) != -1;
        });
    }
    return ret;
}

function hasCustomRules() {
    return fs.existsSync(path.join(ROOT, 'custom_rules.xml'));
}

function extractProjectNameFromManifest(projectPath) {
    var manifestPath = path.join(projectPath, 'AndroidManifest.xml');
    var manifestData = fs.readFileSync(manifestPath, 'utf8');
    var m = /<activity[\s\S]*?android:name\s*=\s*"(.*?)"/i.exec(manifestData);
    if (!m) {
        throw new Error('Could not find activity name in ' + manifestPath);
    }
    return m[1];
}

function findAllUniq(data, r) {
    var s = {};
    var m;
    while ((m = r.exec(data))) {
        s[m[1]] = 1;
    }
    return Object.keys(s);
}

function readProjectProperties() {
    var data = fs.readFileSync(path.join(ROOT, 'project.properties'), 'utf8');
    return {
        libs: findAllUniq(data, /^\s*android\.library\.reference\.\d+=(.*)(?:\s|$)/mg),
        gradleIncludes: findAllUniq(data, /^\s*cordova\.gradle\.include\.\d+=(.*)(?:\s|$)/mg),
        systemLibs: findAllUniq(data, /^\s*cordova\.system\.library\.\d+=(.*)(?:\s|$)/mg)
    };
}

var builders = {
    ant: {
        getArgs: function(cmd, opts) {
            var args = [cmd, '-f', path.join(ROOT, 'build.xml')];
            // custom_rules.xml is required for incremental builds.
            if (hasCustomRules()) {
                args.push('-Dout.dir=ant-build', '-Dgen.absolute.dir=ant-gen');
            }
            if(opts.packageInfo) {
                args.push('-propertyfile=' + path.join(ROOT, opts.buildType + SIGNING_PROPERTIES));
            }
            return args;
        },

        prepEnv: function(opts) {
            return check_reqs.check_ant()
            .then(function() {
                // Copy in build.xml on each build so that:
                // A) we don't require the Android SDK at project creation time, and
                // B) we always use the SDK's latest version of it.
                var sdkDir = process.env['ANDROID_HOME'];
                var buildTemplate = fs.readFileSync(path.join(sdkDir, 'tools', 'lib', 'build.template'), 'utf8');
                function writeBuildXml(projectPath) {
                    var newData = buildTemplate.replace('PROJECT_NAME', extractProjectNameFromManifest(ROOT));
                    fs.writeFileSync(path.join(projectPath, 'build.xml'), newData);
                    if (!fs.existsSync(path.join(projectPath, 'local.properties'))) {
                        fs.writeFileSync(path.join(projectPath, 'local.properties'), TEMPLATE);
                    }
                }
                writeBuildXml(ROOT);
                var propertiesObj = readProjectProperties();
                var subProjects = propertiesObj.libs;
                for (var i = 0; i < subProjects.length; ++i) {
                    writeBuildXml(path.join(ROOT, subProjects[i]));
                }
                if (propertiesObj.systemLibs.length > 0) {
                    throw new Error('Project contains at least one plugin that requires a system library. This is not supported with ANT. Please build using gradle.');
                }

                var propertiesFile = opts.buildType + SIGNING_PROPERTIES;
                var propertiesFilePath = path.join(ROOT, propertiesFile);
                if (opts.packageInfo) {
                    fs.writeFileSync(propertiesFilePath, TEMPLATE + opts.packageInfo.toProperties());
                } else if(isAutoGenerated(propertiesFilePath)) {
                    shell.rm('-f', propertiesFilePath);
                }
            });
        },

        /*
         * Builds the project with ant.
         * Returns a promise.
         */
        build: function(opts) {
            // Without our custom_rules.xml, we need to clean before building.
            var ret = Q();
            if (!hasCustomRules()) {
                // clean will call check_ant() for us.
                ret = this.clean(opts);
            }

            var args = this.getArgs(opts.buildType == 'debug' ? 'debug' : 'release', opts);
            return check_reqs.check_ant()
            .then(function() {
                console.log('Executing: ant ' + args.join(' '));
                return spawn('ant', args);
            });
        },

        clean: function(opts) {
            var args = this.getArgs('clean', opts);
            return check_reqs.check_ant()
            .then(function() {
                return spawn('ant', args);
            });
        },

        findOutputApks: function(build_type) {
            var binDir = path.join(ROOT, hasCustomRules() ? 'ant-build' : 'bin');
            return findOutputApksHelper(binDir, build_type, null);
        }
    },
    gradle: {
        getArgs: function(cmd, opts) {
            if (cmd == 'release') {
                cmd = 'cdvBuildRelease';
            } else if (cmd == 'debug') {
                cmd = 'cdvBuildDebug';
            }
            var args = [cmd, '-b', path.join(ROOT, 'build.gradle')];
            if (opts.arch) {
                args.push('-PcdvBuildArch=' + opts.arch);
            }

            // 10 seconds -> 6 seconds
            args.push('-Dorg.gradle.daemon=true');
            args.push.apply(args, opts.extraArgs);
            // Shaves another 100ms, but produces a "try at own risk" warning. Not worth it (yet):
            // args.push('-Dorg.gradle.parallel=true');
            return args;
        },

        // Makes the project buildable, minus the gradle wrapper.
        prepBuildFiles: function() {
            var projectPath = ROOT;
            // Update the version of build.gradle in each dependent library.
            var pluginBuildGradle = path.join(projectPath, 'cordova', 'lib', 'plugin-build.gradle');
            var propertiesObj = readProjectProperties();
            var subProjects = propertiesObj.libs;
            for (var i = 0; i < subProjects.length; ++i) {
                if (subProjects[i] !== 'CordovaLib') {
                    shell.cp('-f', pluginBuildGradle, path.join(ROOT, subProjects[i], 'build.gradle'));
                }
            }

            var subProjectsAsGradlePaths = subProjects.map(function(p) { return ':' + p.replace(/[/\\]/g, ':'); });
            // Write the settings.gradle file.
            fs.writeFileSync(path.join(projectPath, 'settings.gradle'),
                '// GENERATED FILE - DO NOT EDIT\n' +
                'include ":"\n' +
                'include "' + subProjectsAsGradlePaths.join('"\ninclude "') + '"\n');
            // Update dependencies within build.gradle.
            var buildGradle = fs.readFileSync(path.join(projectPath, 'build.gradle'), 'utf8');
            var depsList = '';
            subProjectsAsGradlePaths.forEach(function(p) {
                depsList += '    debugCompile project(path: "' + p + '", configuration: "debug")\n';
                depsList += '    releaseCompile project(path: "' + p + '", configuration: "release")\n';
            });
            // For why we do this mapping: https://issues.apache.org/jira/browse/CB-8390
            var SYSTEM_LIBRARY_MAPPINGS = [
                [/^\/?extras\/android\/support\/(.*)$/, 'com.android.support:support-$1:+'],
                [/^\/?google\/google_play_services\/libproject\/google-play-services_lib\/?$/, 'com.google.android.gms:play-services:+']
            ];
            propertiesObj.systemLibs.forEach(function(p) {
                var mavenRef;
                // It's already in gradle form if it has two ':'s
                if (/:.*:/.exec(p)) {
                    mavenRef = p;
                } else {
                    for (var i = 0; i < SYSTEM_LIBRARY_MAPPINGS.length; ++i) {
                        var pair = SYSTEM_LIBRARY_MAPPINGS[i];
                        if (pair[0].exec(p)) {
                            mavenRef = p.replace(pair[0], pair[1]);
                            break;
                        }
                    }
                    if (!mavenRef) {
                        throw new Error('Unsupported system library (does not work with gradle): ' + p);
                    }
                }
                depsList += '    compile "' + mavenRef + '"\n';
            });
            buildGradle = buildGradle.replace(/(SUB-PROJECT DEPENDENCIES START)[\s\S]*(\/\/ SUB-PROJECT DEPENDENCIES END)/, '$1\n' + depsList + '    $2');
            var includeList = '';
            propertiesObj.gradleIncludes.forEach(function(includePath) {
                includeList += 'apply from: "' + includePath + '"\n';
            });
            buildGradle = buildGradle.replace(/(PLUGIN GRADLE EXTENSIONS START)[\s\S]*(\/\/ PLUGIN GRADLE EXTENSIONS END)/, '$1\n' + includeList + '$2');
            fs.writeFileSync(path.join(projectPath, 'build.gradle'), buildGradle);
        },

        prepEnv: function(opts) {
            var self = this;
            return check_reqs.check_gradle()
            .then(function() {
                return self.prepBuildFiles();
            }).then(function() {
                // Copy the gradle wrapper on each build so that:
                // A) we don't require the Android SDK at project creation time, and
                // B) we always use the SDK's latest version of it.
                var projectPath = ROOT;
                // check_reqs ensures that this is set.
                var sdkDir = process.env['ANDROID_HOME'];
                var wrapperDir = path.join(sdkDir, 'tools', 'templates', 'gradle', 'wrapper');
                if (process.platform == 'win32') {
                    shell.cp('-f', path.join(wrapperDir, 'gradlew.bat'), projectPath);
                } else {
                    shell.cp('-f', path.join(wrapperDir, 'gradlew'), projectPath);
                }
                shell.rm('-rf', path.join(projectPath, 'gradle', 'wrapper'));
                shell.mkdir('-p', path.join(projectPath, 'gradle'));
                shell.cp('-r', path.join(wrapperDir, 'gradle', 'wrapper'), path.join(projectPath, 'gradle'));

                // If the gradle distribution URL is set, make sure it points to version we want.
                // If it's not set, do nothing, assuming that we're using a future version of gradle that we don't want to mess with.
                // For some reason, using ^ and $ don't work.  This does the job, though.
                var distributionUrlRegex = /distributionUrl.*zip/;
                var distributionUrl = 'distributionUrl=http\\://services.gradle.org/distributions/gradle-2.2.1-all.zip';
                var gradleWrapperPropertiesPath = path.join(projectPath, 'gradle', 'wrapper', 'gradle-wrapper.properties');
                shell.sed('-i', distributionUrlRegex, distributionUrl, gradleWrapperPropertiesPath);

                var propertiesFile = opts.buildType + SIGNING_PROPERTIES;
                var propertiesFilePath = path.join(ROOT, propertiesFile);
                if (opts.packageInfo) {
                    fs.writeFileSync(propertiesFilePath, TEMPLATE + opts.packageInfo.toProperties());
                } else if (isAutoGenerated(propertiesFilePath)) {
                    shell.rm('-f', propertiesFilePath);
                }
            });
        },

        /*
         * Builds the project with gradle.
         * Returns a promise.
         */
        build: function(opts) {
            var wrapper = path.join(ROOT, 'gradlew');
            var args = this.getArgs(opts.buildType == 'debug' ? 'debug' : 'release', opts);
            return Q().then(function() {
                console.log('Running: ' + wrapper + ' ' + args.join(' '));
                return spawn(wrapper, args);
            });
        },

        clean: function(opts) {
            var builder = this;
            var wrapper = path.join(ROOT, 'gradlew');
            var args = builder.getArgs('clean', opts);
            return Q().then(function() {
                console.log('Running: ' + wrapper + ' ' + args.join(' '));
                return spawn(wrapper, args);
            });
        },

        findOutputApks: function(build_type, arch) {
            var binDir = path.join(ROOT, 'build', 'outputs', 'apk');
            return findOutputApksHelper(binDir, build_type, arch);
        }
    },

    none: {
        prepEnv: function() {
            return Q();
        },
        build: function() {
            console.log('Skipping build...');
            return Q(null);
        },
        clean: function() {
            return Q();
        },
        findOutputApks: function(build_type, arch) {
            return sortFilesByDate(builders.ant.findOutputApks(build_type, arch).concat(builders.gradle.findOutputApks(build_type, arch)));
        }
    }
};

module.exports.isBuildFlag = function(flag) {
    return /^--(debug|release|ant|gradle|nobuild|versionCode=|minSdkVersion=|gradleArg=|keystore=|alias=|password=|storePassword=|keystoreType=|buildConfig=)/.exec(flag);
};

function parseOpts(options, resolvedTarget) {
    // Backwards-compatibility: Allow a single string argument
    if (typeof options == 'string') options = [options];

    var ret = {
        buildType: 'debug',
        buildMethod: process.env['ANDROID_BUILD'] || 'gradle',
        arch: null,
        extraArgs: []
    };

    var multiValueArgs = {
      'versionCode': true,
      'minSdkVersion': true,
      'gradleArg': true,
      'keystore' : true,
      'alias' : true,
      'password' : true,
      'storePassword' : true,
      'keystoreType' : true,
      'buildConfig' : true
    };
    var packageArgs = {};
    var buildConfig;
    // Iterate through command line options
    for (var i=0; options && (i < options.length); ++i) {
        if (/^--/.exec(options[i])) {
            var keyValue = options[i].substring(2).split('=');
            var flagName = keyValue.shift();
            var flagValue = keyValue.join('=');
            if (multiValueArgs[flagName] && !flagValue) {
                flagValue = options[i + 1];
                ++i;
            }
            switch(flagName) {
                case 'debug':
                case 'release':
                    ret.buildType = flagName;
                    break;
                case 'ant':
                case 'gradle':
                    ret.buildMethod = flagName;
                    break;
                case 'device':
                case 'emulator':
                    // Don't need to do anything special to when building for device vs emulator.
                    // iOS uses this flag to switch on architecture.
                    break;
                case 'prepenv' :
                    ret.prepEnv = true;
                    break;
                case 'nobuild' :
                    ret.buildMethod = 'none';
                    break;
                case 'versionCode':
                    ret.extraArgs.push('-PcdvVersionCode=' + flagValue);
                    break;
                case 'minSdkVersion':
                    ret.extraArgs.push('-PcdvMinSdkVersion=' + flagValue);
                    break;
                case 'gradleArg':
                    ret.extraArgs.push(flagValue);
                    break;
                case 'keystore':
                    packageArgs.keystore = path.relative(ROOT, path.resolve(flagValue));
                    break;
                case 'alias':
                case 'storePassword':
                case 'password':
                case 'keystoreType':
                    packageArgs[flagName] = flagValue;
                    break;
                case 'buildConfig':
                    buildConfig = flagValue;
                    break;
                default :
                    console.warn('Build option --\'' + flagName + '\' not recognized (ignoring).');
            }
        } else {
            console.warn('Build option \'' + options[i] + '\' not recognized (ignoring).');
        }
    }

    // If some values are not specified as command line arguments - use build config to supplement them.
    // Command line arguemnts have precedence over build config.
    if (buildConfig) {
        console.log(path.resolve(buildConfig));
        if (!fs.existsSync(buildConfig)) {
            throw new Error('Specified build config file does not exist: ' + buildConfig);
        }
        console.log('Reading build config file: '+ buildConfig);
        var config = JSON.parse(fs.readFileSync(buildConfig, 'utf8'));
        if (config.android && config.android[ret.buildType]) {
            var androidInfo = config.android[ret.buildType];
            if(androidInfo.keystore) {
                packageArgs.keystore = packageArgs.keystore || path.relative(ROOT, path.join(path.dirname(buildConfig), androidInfo.keystore));
            }

            ['alias', 'storePassword', 'password','keystoreType'].forEach(function (key){
                packageArgs[key] = packageArgs[key] || androidInfo[key];
            });
        }
    }
    if (packageArgs.keystore && packageArgs.alias) {
        ret.packageInfo = new PackageInfo(packageArgs.keystore, packageArgs.alias, packageArgs.storePassword,
            packageArgs.password, packageArgs.keystoreType);
    }

    if(!ret.packageInfo) {
        if(Object.keys(packageArgs).length > 0) {
            console.warn('\'keystore\' and \'alias\' need to be specified to generate a signed archive.');
        }
    }
    ret.arch = resolvedTarget && resolvedTarget.arch;

    return ret;
}

/*
 * Builds the project with the specifed options
 * Returns a promise.
 */
module.exports.runClean = function(options) {
    var opts = parseOpts(options);
    var builder = builders[opts.buildMethod];
    return builder.prepEnv(opts)
    .then(function() {
        return builder.clean(opts);
    }).then(function() {
        shell.rm('-rf', path.join(ROOT, 'out'));

        ['debug', 'release'].forEach(function(config) {
            var propertiesFilePath = path.join(ROOT, config + SIGNING_PROPERTIES);
            if(isAutoGenerated(propertiesFilePath)){
                shell.rm('-f', propertiesFilePath);
            }
        });
    });
};

/*
 * Builds the project with the specifed options
 * Returns a promise.
 */
module.exports.run = function(options, optResolvedTarget) {
    var opts = parseOpts(options, optResolvedTarget);
    var builder = builders[opts.buildMethod];
    return builder.prepEnv(opts)
    .then(function() {
        if (opts.prepEnv) {
            console.log('Build file successfully prepared.');
            return;
        }
        return builder.build(opts)
        .then(function() {
            var apkPaths = builder.findOutputApks(opts.buildType, opts.arch);
            console.log('Built the following apk(s):');
            console.log('    ' + apkPaths.join('\n    '));
            return {
                apkPaths: apkPaths,
                buildType: opts.buildType,
                buildMethod: opts.buildMethod
            };
        });
    });
};

// Called by plugman after installing plugins, and by create script after creating project.
module.exports.prepBuildFiles = function() {
    var builder = builders['gradle'];
    return builder.prepBuildFiles();
};

/*
 * Detects the architecture of a device/emulator
 * Returns "arm" or "x86".
 */
module.exports.detectArchitecture = function(target) {
    function helper() {
        return exec('adb -s ' + target + ' shell cat /proc/cpuinfo', os.tmpdir())
        .then(function(output) {
            if (/intel/i.exec(output)) {
                return 'x86';
            }
            return 'arm';
        });
    }
    // It sometimes happens (at least on OS X), that this command will hang forever.
    // To fix it, either unplug & replug device, or restart adb server.
    return helper().timeout(1000, 'Device communication timed out. Try unplugging & replugging the device.')
    .then(null, function(err) {
        if (/timed out/.exec('' + err)) {
            // adb kill-server doesn't seem to do the trick.
            // Could probably find a x-platform version of killall, but I'm not actually
            // sure that this scenario even happens on non-OSX machines.
            return exec('killall adb')
            .then(function() {
                console.log('adb seems hung. retrying.');
                return helper()
                .then(null, function() {
                    // The double kill is sadly often necessary, at least on mac.
                    console.log('Now device not found... restarting adb again.');
                    return exec('killall adb')
                    .then(function() {
                        return helper()
                        .then(null, function() {
                            return Q.reject('USB is flakey. Try unplugging & replugging the device.');
                        });
                    });
                });
            }, function() {
                // For non-killall OS's.
                return Q.reject(err);
            });
        }
        throw err;
    });
};

module.exports.findBestApkForArchitecture = function(buildResults, arch) {
    var paths = buildResults.apkPaths.filter(function(p) {
        if (buildResults.buildType == 'debug') {
            return /-debug/.exec(p);
        }
        return !/-debug/.exec(p);
    });
    var archPattern = new RegExp('-' + arch);
    var hasArchPattern = /-x86|-arm/;
    for (var i = 0; i < paths.length; ++i) {
        if (hasArchPattern.exec(paths[i])) {
            if (archPattern.exec(paths[i])) {
                return paths[i];
            }
        } else {
            return paths[i];
        }
    }
    throw new Error('Could not find apk architecture: ' + arch + ' build-type: ' + buildResults.buildType);
};

function PackageInfo(keystore, alias, storePassword, password, keystoreType) {
    this.keystore = {
        'name': 'key.store',
        'value': keystore
    };
    this.alias = {
        'name': 'key.alias',
        'value': alias
    };
    if (storePassword) {
        this.storePassword = {
            'name': 'key.store.password',
            'value': storePassword
        };
    }
    if (password) {
        this.password = {
            'name': 'key.alias.password',
            'value': password
        };
    }
    if (keystoreType) {
        this.keystoreType = {
            'name': 'key.store.type',
            'value': keystoreType
        };
    }
}

PackageInfo.prototype = {
    toProperties: function() {
        var self = this;
        var result = '';
        Object.keys(self).forEach(function(key) {
            result += self[key].name;
            result += '=';
            result += self[key].value.replace(/\\/g, '\\\\');
            result += '\n';
        });
        return result;
    }
};

module.exports.help = function() {
    console.log('Usage: ' + path.relative(process.cwd(), path.join(ROOT, 'cordova', 'build')) + ' [flags] [Signed APK flags]');
    console.log('Flags:');
    console.log('    \'--debug\': will build project in debug mode (default)');
    console.log('    \'--release\': will build project for release');
    console.log('    \'--ant\': will build project with ant');
    console.log('    \'--gradle\': will build project with gradle (default)');
    console.log('    \'--nobuild\': will skip build process (useful when using run command)');
    console.log('    \'--prepenv\': don\'t build, but copy in build scripts where necessary');
    console.log('    \'--versionCode=#\': Override versionCode for this build. Useful for uploading multiple APKs. Requires --gradle.');
    console.log('    \'--minSdkVersion=#\': Override minSdkVersion for this build. Useful for uploading multiple APKs. Requires --gradle.');
    console.log('    \'--gradleArg=<gradle command line arg>\': Extra args to pass to the gradle command. Use one flag per arg. Ex. --gradleArg=-PcdvBuildMultipleApks=true');
    console.log('');
    console.log('Signed APK flags (overwrites debug/release-signing.proprties) :');
    console.log('    \'--keystore=<path to keystore>\': Key store used to build a signed archive. (Required)');
    console.log('    \'--alias=\': Alias for the key store. (Required)');
    console.log('    \'--storePassword=\': Password for the key store. (Optional - prompted)');
    console.log('    \'--password=\': Password for the key. (Optional - prompted)');
    console.log('    \'--keystoreType\': Type of the keystore. (Optional)');
    process.exit(0);
};
