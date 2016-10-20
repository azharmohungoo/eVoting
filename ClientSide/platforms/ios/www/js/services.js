angular.module('app.services', [])


  .service('ipProvider', function(){

    return {
       getIP : function () {
         return "127.0.0.1";
       }

  }
  })

.service('VoteNationalService', function($http, ipProvider){
  return {

    castNational: function (castNationalRequest) {

      alert("Casting national vote");
     var myUrl = "http://" + ipProvider.getIP() + ":8080/castVote";
      alert(myUrl);
      return $http({ url : myUrl , data : castNationalRequest , method : "POST" })
        .then(function (result){
          alert(result.data);
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

RegisterService.$inject = ['$http', 'ipProvider'];

function  RegisterService($http, ipProvider) {

  return {
    register : function(registerRequest){

      alert("Registering..");

      var myUrl = "http://" + ipProvider.getIP() + ":8080/register";
      return $http({url : myUrl , data : registerRequest , method : "POST"})
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
