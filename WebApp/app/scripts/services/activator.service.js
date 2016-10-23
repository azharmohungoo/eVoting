/**
 * Created by Azhar on 2016/10/12.
 */

angular.module('eVotingWebApp')

  .service('ActivatorService',  function($http, ipProvider) {
      return {
        search: function (searchRequest) {
          return $http({url: "http://"+ipProvider.getIP()+":8080/search", data: searchRequest, method: "POST"})
            .then(function (result) {
              if (result.data.success == true) {
                alert("Voter found as : " + result.data.name + " " + result.data.surname);

                return result.data;
              }
              else {
                alert("Voter not found");
              }
              return result;
            }).catch(function (exception) {
              return exception;
            });
        },

        activate: function (activateRequest) {
          return $http({url: "http://"+ipProvider.getIP()+":8080/activate", data: activateRequest, method: "POST"})
            .then(function (result) {
              if (result.data.success == true) {
                alert(result.data.name + " " + result.data.surname + " activated");

                return result.data;
              }
              else {
                alert("Voter not activated");
              }
              return result;
            }).catch(function (exception) {
              return exception;
            });
        }
      }
    }
  )
