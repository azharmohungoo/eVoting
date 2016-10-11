/**
 * Created by Azhar on 2016/10/10.
 */

'use strict';

angular.module('eVotingWebApp')

  .controller('LoginCtrl', function ($scope, LoginService, $localStorage) {
    var vm = this;

    vm.idNum;
    vm.password;

    vm.login = login;

    function  login()
    {
      var loginRequest = {
        "password" : Sha256.hash(vm.password) ,
        //"password" : vm.password ,
        "idNum" : vm.idNum
      }

      LoginService.login(loginRequest)
        .then(function (result)
        {
          $scope.name = result.name;
          $localStorage.data = result;

          console.log(result);

        });
    }
  });
