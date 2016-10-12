/**
 * Created by Azhar on 2016/10/12.
 */

'use strict';

angular.module('eVotingWebApp')

  .controller('ActivatorCtrl', function ($scope, ActivatorService, $localStorage, $location) {
    var vm = this;

    vm.idNum;
    vm.password;

    vm.search = search;
    vm.activate = activate;

    function search()
    {
      var searchRequest = {
        "idNum" : vm.idNum
      }

      ActivatorService.search(searchRequest)
        .then(function (result)
        {
          $localStorage.data = result;

          $scope.message =  { "fullName" : $localStorage.data.name + " " + $localStorage.data.surname,
                              "idNum" : "ID number : " + $localStorage.data.IDNum,
                              "email" :  "Email : " + $localStorage.data.email,
                              "cellphone" :  "Cellphone : " + $localStorage.data.cellphone,
                              "locationRegistered" :  "Location registered : " + $localStorage.data.locationRegistered,
                              "activationStatus" :  "Activation status : " + $localStorage.data.activated,
                              "votesRemaining" :  "Votes remaining : " + $localStorage.data.votes};

          document.getElementById("btnActivate").hidden = false;

          console.log(result);
        });
    };

    function activate()
    {
      var activateRequest = {
        "idNum" : vm.idNum
      }

      ActivatorService.activate(activateRequest)
        .then(function (result)
        {
          $localStorage.data = result;

          $scope.message =  { "fullName" : $localStorage.data.name + " " + $localStorage.data.surname,
            "idNum" : "ID number : " + $localStorage.data.IDNum,
            "email" :  "Email : " + $localStorage.data.email,
            "cellphone" :  "Cellphone : " + $localStorage.data.cellphone,
            "locationRegistered" :  "Location registered : " + $localStorage.data.locationRegistered,
            "activationStatus" :  "Activation status : " + $localStorage.data.activated,
            "votesRemaining" :  "Votes remaining : " + $localStorage.data.votes};

          $localStorage.data = "";

          console.log(result);
        });
    }
  });
