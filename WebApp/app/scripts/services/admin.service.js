/**
 * Created by Azhar on 2016/10/15.
 */

angular.module('eVotingWebApp')

.factory('AdminService', AdminService);

AdminService.$inject = ['$http', 'ipProvider'];

function  AdminService($http, ipProvider) {
  return {
    adminRegister : function(adminRegisterRequest){
      return $http({url : "http://"+ipProvider.getIP()+":8080/adminRegister" , data : adminRegisterRequest , method : "POST"})
        .then(function (result) {
          alert("Registered user!");
          return result.data;
        }).catch(function (exception)
        {
          return exception;
        });
      ;
    },

    adminParty : function(adminRegisterPartyRequest){
      return $http({url : "http://"+ipProvider.getIP()+":8080/registerParty" , data : adminRegisterPartyRequest , method : "POST"})
        .then(function (result) {
          alert("Registered party!");
          return result.data;
        }).catch(function (exception)
        {
          return exception;
        });
      ;
    },

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

    deactivate: function (deactivateRequest) {
      return $http({url: "http://"+ipProvider.getIP()+":8080/deactivate", data: deactivateRequest, method: "POST"})
        .then(function (result) {
          if (result.data.success == true) {
            alert(result.data.name + " " + result.data.surname + " deactivated");

            return result.data;
          }
          else {
            alert("Voter not deactivated");
          }
          return result;
        }).catch(function (exception) {
          return exception;
        });
    }
  }
}
