/**
 * Created by Azhar on 2016/10/10.
 */

'use strict';

angular.module('eVotingWebApp')

  .controller('LoginCtrl', function ($scope, LoginService, $location, $localStorage) {
    var vm = this;

    vm.idNum;
    vm.password;


    vm.checkU = checkU;
    vm.loginP = loginP;
    vm.login = login;
    vm.isLoggedIn = isLoggedIn;

    function checkU()
    {
      if (document.getElementById("userT").value == "voter" || document.getElementById("userT").value == "admin" || document.getElementById("userT").value == "activator")
      {
        login();
      }
      else if (document.getElementById("userT").value == "party")
      {
        loginP();
      }
    }

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

          switch(result.userType) {
            case "Admin":
              $location.path('/admin');
              break;
            case "Activator":
              $location.path('/activator');
              break;
            case "Voter":
              $location.path('/voter');
              break;
          }


          console.log(result);
        });
    }

    function  loginP()
    {
      var loginPRequest = {
        "password" : Sha256.hash(vm.password) ,
        //"password" : vm.password ,
        "partyId" : vm.idNum
      }

      LoginService.loginP(loginPRequest)
        .then(function (result)
        {
          $scope.partyName = result.partyName;
          $localStorage.partyName = vm.idNum;

         // alert(vm.idNum);
          result.userType = "Party";
          $localStorage.data = result;
          console.log(result);

          $location.path('/party');

          console.log(result);
        });
    }

    function isLoggedIn()
    {
      if ($localStorage.data == undefined)
      {
        return false;
      }

      return true;
    }
  });
