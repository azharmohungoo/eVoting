/**
 * Created by Azhar on 2016/10/10.
 */

angular.module('eVotingWebApp')

  .service('LoginService',  function($http, ipProvider) {
    return {
      login: function(loginRequest)
      {
        return $http({url : "http://"+ipProvider.getIP()+":8080/login" , data : loginRequest , method : "POST"})
          .then(function (result) {
            if(result.data.success == true)
            {
              alert("Successful login as : " +  result.data.name + " " + result.data.surname);

              return result.data;
            }
            else {
              alert("Unsuccessful login");}
            return result;
          }).catch(function (exception)
          {
            return exception;
          });
      },

      loginP: function(loginPRequest)
      {
        return $http({url : "http://"+ipProvider.getIP()+":8080/loginP" , data : loginPRequest , method : "POST"})
          .then(function (result) {
            if(result.data.success == true)
            {
              alert("Successful login as : " +  result.data.partyName);

              return result.data;
            }
            else {
              alert("Unsuccessful login");}
            return result;
          }).catch(function (exception)
          {
            return exception;
          });
      }
    }
  })
