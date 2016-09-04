angular.module('app.services', [])






.service('LoginService',  function($http) {
  return {
    login: function(loginRequest) {


      alert("logging in");
      return $http({url : "http://127.0.0.1:8080/login" , data : loginRequest , method : "POST"})
        .then(function (result) {
          alert(JSON.stringify(result))
          return result;
        }).catch(function (exception)
        {
          return exception;
        });
    }

  }
})


  .factory( 'RegisterService', RegisterService);

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
}
