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

    },

  getParty: function(viewPartyRequest)
  {
    return $http({url : "http://127.0.0.1:8080/getParty" , data : viewPartyRequest , method : "POST"})
    
  }
  }

})


.service('ViewPartyService', function($http){
  return {

    getParty: function(viewPartyRequest)
    {
      return $http({url : "http://127.0.0.1:8080/getParty" , data : viewPartyRequest , method : "POST"})
        .then(function(result)
      {
          alert(result.data);
      })

    }

  }

})

.service('LoginService',  function($http, $state) {
  return {
    login: function(loginRequest) {

      return $http({url : "http://127.0.0.1:8080/login" , data : loginRequest , method : "POST"})
        .then(function (result) {
          alert(JSON.stringify(result));
         // alert(result.data.success);
          if(result.data.success == true)
          {
            alert("Successful login as : " +  result.data.name + " " + result.data.surname);
           var userName = result.data.name;
           // alert(userName);
           // var loggedInUser = {name:result.data.name, lastName:result.data.surname}

            $state.go('tabsController.electionInformation');
            return result.data;
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
