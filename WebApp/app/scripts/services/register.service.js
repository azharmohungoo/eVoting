/**
 * Created by Azhar on 2016/10/10.
 */

angular.module('eVotingWebApp')

.factory('RegisterService', RegisterService);

RegisterService.$inject = ['$http', 'ipProvider'];

function  RegisterService($http, ipProvider) {
  return {
    register : function(registerRequest){

      alert("Registering..");

      return $http({url : "http://"+ipProvider.getIP()+":8080/register" , data : registerRequest , method : "POST"})
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
