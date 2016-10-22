'use strict';

/**
 * @ngdoc overview
 * @name eVotingWebApp
 * @description
 * # eVotingWebApp
 *
 * Main module of the application.
 */
angular
  .module('eVotingWebApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngStorage'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'LoginCtrl',
        controllerAs: 'vm',
        isRole: 'none'
      })
      .when('/register', {
        templateUrl: 'views/register.html',
        controller: 'RegisterCtrl',
        controllerAs: 'vm'
      })
      .when('/admin', {
        templateUrl: 'views/admin.html',
        controller: 'AdminCtrl',
        controllerAs: 'vm',
        isRole: 'Admin'
      })
      .when('/adminAddUser', {
        templateUrl: 'views/adminAddUser.html',
        controller: 'AdminCtrl',
        controllerAs: 'vm',
        isRole: 'Admin'
      })
      .when('/adminDeactivateUser', {
        templateUrl: 'views/adminDeactivateUser.html',
        controller: 'AdminCtrl',
        controllerAs: 'vm',
        isRole: 'Admin'
      })
      .when('/activator', {
        templateUrl: 'views/activator.html',
        controller: 'ActivatorCtrl',
        controllerAs: 'vm',
        isRole: 'Activator'
      })
      .when('/party', {
        templateUrl: 'views/party.html',
        controller: '',
        controllerAs: '',
        isRole: 'Party'
      })
      .when('/voter', {
        templateUrl: 'views/voter.html',
        controller: 'VoterCtrl',
        controllerAs: 'vm',
        isRole: 'Voter'
      })
      .when('/election', {
        templateUrl: 'views/election.html',
        controller: '',
        controllerAs: '',
        isRole: 'Voter'
      })
      .when('/voteNational', {
        templateUrl: 'views/voteNational.html',
        controller: 'ElectionCtrl',
        controllerAs: 'vm',
        isRole: 'Voter'
      })
      .when('/voteProvincial', {
        templateUrl: 'views/voteProvincial.html',
        controller: 'ElectionCtrl',
        controllerAs: 'vm',
        isRole: 'Voter'
      })
      .when('/viewParty', {
        templateUrl: 'views/viewParty.html',
        controller: 'ViewPartyCtrl',
        controllerAs: 'vm',
        isRole: 'Voter'
      })
      .when('/accountInfo', {
        templateUrl: 'views/accountInfo.html',
        controller: 'VoterCtrl',
        controllerAs: 'vm',
        isRole: 'Voter'
      })
      .when('/logout', {
        templateUrl: 'views/main.html',
        controller: 'LogoutCtrl'
      })
      .when('/accessdenied', {
        templateUrl: 'views/accessDenied.html',
        controller: 'AccessDeniedCtrl',
        controllerAs: 'vm'
      })
      .otherwise({
        redirectTo: '/'
      });
  })

  .run(function ($rootScope, $location, $localStorage) {
    $rootScope.$on('$routeChangeStart', function (event, next) {
      //alert(next);
      //console.log(next);

      if (next.originalPath == "/register")
      {
        $location.path(next.originalPath);
      }
      else if ($localStorage.data == undefined)
      {
        $location.path("/");
      }
      else if (next.redirectTo != undefined)
      {
        $location.path(next.redirectTo);
      }
      else if (next.isRole == "none" || $localStorage.data.userType == next.isRole)
      {
        $location.path(next.originalPath);
      }
      else
      {
        $location.path('/accessdenied');
        //$location.path(next.originalPath);
      }
    });
  });
