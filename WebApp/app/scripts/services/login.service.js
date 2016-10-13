/**
 * Created by Azhar on 2016/10/10.
 */

angular.module('eVotingWebApp')

  .service('LoginService',  function($http, $location) {
    return {
      login: function(loginRequest)
      {
        return $http({url : "http://localhost:8080/login" , data : loginRequest , method : "POST"})
          .then(function (result) {
            alert(JSON.stringify(result));
            // alert(result.data.success);
            if(result.data.success == true)
            {
              alert("Successful login as : " +  result.data.name + " " + result.data.surname);
              //var type = result.data.name;
              //alert(userName);
              // var loggedInUser = {name:result.data.name, lastName:result.data.surname}

              //$location.path('/voter');

              switch(result.data.userType) {
                case "Admin":
                  $location.path('/admin');
                  break;
                case "Activator":
                  $location.path('/activator');
                  break;
                case "Party":
                  $location.path('/party');
                  break;
                case "Voter":
                  $location.path('/voter');
                  break;
              }

              return result.data;
            }
            else {
              //$location.path('/');
              alert("Unsuccessful login");}
            return result;
          }).catch(function (exception)
          {
            return exception;
          });
      }

    }
  })