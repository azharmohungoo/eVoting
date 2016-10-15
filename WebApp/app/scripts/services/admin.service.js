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
    }
  }
}
