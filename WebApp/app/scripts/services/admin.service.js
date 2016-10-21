/**
 * Created by Azhar on 2016/10/15.
 */

angular.module('eVotingWebApp')

.factory('AdminService', AdminService);

AdminService.$inject = ['$http'];

function  AdminService($http) {
  return {
    adminRegister : function(adminRegisterRequest){

      alert("Registering..");

      return $http({url : "http://localhost:8080/adminRegister" , data : adminRegisterRequest , method : "POST"})
        .then(function (result) {
          //alert(JSON.stringify(result)) //
          //$location.path('/');
          return result.data;
        }).catch(function (exception)
        {
          return exception;
        });
      ;
    },

    adminParty : function(adminRegisterPartyRequest){

      alert("Registering..");

      return $http({url : "http://localhost:8080/registerParty" , data : adminRegisterPartyRequest , method : "POST"})
        .then(function (result) {
          //alert(JSON.stringify(result)) //
          //$location.path('/');
          return result.data;
        }).catch(function (exception)
        {
          return exception;
        });
      ;
    },

    search: function (searchRequest) {
      return $http({url: "http://localhost:8080/search", data: searchRequest, method: "POST"})
        .then(function (result) {
          alert(JSON.stringify(result));
          // alert(result.data.success);
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
      return $http({url: "http://localhost:8080/deactivate", data: deactivateRequest, method: "POST"})
        .then(function (result) {
          alert(JSON.stringify(result));
          // alert(result.data.success);
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
