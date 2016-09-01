angular.module('app.services', [])

.service('LoginService', function($q) {
  return {
    loginUser: function(name, pw) {
      var deferred = $q.defer();
      var promise = deferred.promise;

      if (name == 'codeX' && pw == 'password') {
        deferred.resolve('Welcome ' + name + '!');
      } else {
        deferred.reject('Wrong credentials.');
      }
      promise.success = function(fn) {
        promise.then(fn);
        return promise;
      }
      promise.error = function(fn) {
        promise.then(null, fn);
        return promise;
      }
      return promise;
    }
  }
})

.factory('RegisterService', RegisterService);

RegisterService.$inject = ['$http'];
function  RegisterService($http) {

  return {
    register : function(registerRequest){

      alert("registering");

      return $http({url : "http://127.0.0.1:8080/register" , data : registerRequest , method : "POST"})
        .then(function (result) {
          alert(JSON.stringify(result))
        return result;
      }).catch(function (exception)
      {
        return exception;
      });
      ;
    }
  }
};
