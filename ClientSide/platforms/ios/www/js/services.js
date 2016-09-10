angular.module('app.services', [])


.service('VoteNationalService', function($http){
  return {
    castNational: function (castNationalRequest) {

      alert("Casting national vote");
     // alert(JSON.stringify(castNationalRequest));
      return $http({ url : "http://127.0.0.1:8080/castVote" , data : castNationalRequest , method : "POST" })
        .then(function (result){
          alert(result.data);
        })

    }

  }

})



.service('LoginService',  function($http, $state) {
  return {
    login: function(loginRequest) {

    //  alert("logging in attempt");
      return $http({url : "http://127.0.0.1:8080/login" , data : loginRequest , method : "POST"})
        .then(function (result) {
          alert(result.data);

          if(result.data == true)
          {
            alert("Successful login");
            $state.go('tabsController.electionInformation');

          }
          else {alert("Unsuccessful login");}
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

      alert("Registering..");

      return $http({url : "http://127.0.0.1:8080/register" , data : registerRequest , method : "POST"})
        .then(function (result) {
        //  alert(JSON.stringify(result))
          return result.data;
        }).catch(function (exception)
        {
          return exception;
        });
      ;
    }
  }
}
