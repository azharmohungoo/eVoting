angular.module('app.services', [])


  .service('ipProvider', function(){

    return {
       getIP : function () {
         return "192.168.1.5";
       }

  }
  })

  .service('AccountInformationService', function ($http, ipProvider) {

    return {
      getAccount: function (getAccountRequest) {

        var myUrl = "http://" + ipProvider.getIP() + ":8080/login";
        return $http({url: myUrl, data: getAccountRequest, method: "POST"})
          .then(function (result) {
            if (result.data.success == true) {
              alert("Viewing Account Information for : " + result.data.name + " " + result.data.surname);
              //var userName = result.data.name;
              return result.data;
             // $state.go('tabsController.electionInformation');

            }
            else {
              alert("Unsuccessful, please try again later.");
            }
            return result;
          }).catch(function (exception) {
            return exception;
          });
      }
    }

  })

.service('VoteNationalService', function($http, ipProvider){
  return {

    castNational: function (castNationalRequest) {

      alert("Casting vote..");
     var myUrl = "http://" + ipProvider.getIP() + ":8080/castVote";
      return $http({ url : myUrl , data : castNationalRequest , method : "POST" })
        .then(function (result){
          alert(result.data.reason);
        })

    },

  getParty: function(viewPartyRequest)
  {
    var myUrl = "http://" + ipProvider.getIP() + ":8080/getParty";
    return $http({url : myUrl , data : viewPartyRequest , method : "POST"})

  }
  }

})


.service('ViewPartyService', function($http, ipProvider){
  return {

    getParty: function(viewPartyRequest)
    {
      var myUrl = "http://" + ipProvider.getIP() + ":8080/getParty";
      return $http({url : myUrl , data : viewPartyRequest , method : "POST"})
        .then(function(result)
      {
          alert(result.data);
      })

    }

  }

})

.service('LoginService',  function($http, $state, ipProvider) {
  return {
    login: function(loginRequest) {

      var myUrl = "http://" + ipProvider.getIP() + ":8080/login";
      return $http({url : myUrl , data : loginRequest , method : "POST"})
        .then(function (result) {
          if(result.data.success == true)
          {
            alert("Successful login as : " +  result.data.name + " " + result.data.surname);
            alert("Activation Status : " + result.data.activated);
           var userName = result.data.name;

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

RegisterService.$inject = ['$http', 'ipProvider'];

function  RegisterService($http, ipProvider) {

  return {
    register : function(registerRequest){

      alert("Registering..");

      var myUrl = "http://" + ipProvider.getIP() + ":8080/register";
      return $http({url : myUrl , data : registerRequest , method : "POST"})
        .then(function (result) {
          return result.data;
        }).catch(function (exception)
        {
          return exception;
        });
      ;
    }
  }
}
