/**
 * Created by Azhar on 2016/10/14.
 */

'use strict';

angular.module('eVotingWebApp')

  .controller('AdminCtrl' , function($scope, $localStorage , AdminService) {
    var vm = this;

    vm.idNum;
    vm.password;
    vm.name;
    vm.surname;
    vm.locationRegistered;
    vm.cellphone;
    vm.email;
    vm.userType;

    vm.pIdNum;
    vm.pPassword;
    vm.pName;
    vm.pDescription;
    vm.pImgUrl;

    vm.admin = admin;
    vm.search = search;
    vm.deactivate = deactivate;
    vm.adminRegister = adminRegister;
    vm.checkUserT1 = checkUserT1;
    vm.checkUserT2 = checkUserT2;

    function checkUserT1()
    {
      if (!(document.getElementById("userT").value == "admin" || document.getElementById("userT").value == "activator"))
      {
        return false;
      }

      return true;
    };

    function checkUserT2()
    {
      if (!(document.getElementById("userT").value == "party"))
      {
        return false;
      }

      return true;
    };

    function admin()
    {
      if (document.getElementById("userT").value == "admin" || document.getElementById("userT").value == "activator")
      {
        adminRegister();
      }
      else
      {
        adminParty();
      }
    };

    function adminRegister() {

      var adminRegisterRequest = {
        "idNum" : vm.idNum,
        "password" : Sha256.hash(vm.password) ,
        //"password" : vm.password ,
        "name" : vm.name,
        "surname" : vm.surname ,
        "locationRegistered" : vm.locationRegistered ,
        "cellphone" : vm.cellphone ,
        "email" : vm.email,
        "userType" : vm.userType
      }

      AdminService.adminRegister(adminRegisterRequest)
        .then(function (result) {
          alert(JSON.stringify(result));
        }).catch(function (exception)
      {
        alert("Previous Exception");
      });
    };

    function adminParty()
    {
      var adminRegisterPartyRequest = {
        "partyId" : vm.pIdNum ,
        "password" : Sha256.hash(vm.pPassword) ,
        "partyName" : vm.pName ,
        "nationalVoteCount" : 0 ,
        "provincialVoteCount" : 0 ,
        "blockchainNodeAddress" : "" ,
        "ipAddress" : "" ,
        "partyDescription" : vm.pDescription ,
        "imgURL" : vm.pImgUrl
      }

      AdminService.adminParty(adminRegisterPartyRequest)
        .then(function (result) {
          alert(JSON.stringify(result));
        }).catch(function (exception)
      {
        alert("Previous Exception");
      });
    };

    function search()
    {
      var searchRequest = {
        "idNum" : vm.idNum
      }

      AdminService.search(searchRequest)
        .then(function (result)
        {
          $localStorage.dataN = result;

          $scope.message =  { "fullName" : $localStorage.dataN.name + " " + $localStorage.dataN.surname,
            "idNum" : "ID number : " + $localStorage.dataN.IDNum,
            "email" :  "Email : " + $localStorage.dataN.email,
            "cellphone" :  "Cellphone : " + $localStorage.dataN.cellphone,
            "locationRegistered" :  "Location registered : " + $localStorage.dataN.locationRegistered,
            "activationStatus" :  "Activation status : " + $localStorage.dataN.activated,
            "votesRemaining" :  "Votes remaining : " + $localStorage.dataN.votes};

          document.getElementById("btnDeactivate").hidden = false;

          console.log(result);
        });
    };

    function deactivate()
    {
      var deactivateRequest = {
        "idNum" : vm.idNum
      }

      AdminService.deactivate(deactivateRequest)
        .then(function (result)
        {
          $localStorage.dataN = result;

          $scope.message =  { "fullName" : $localStorage.dataN.name + " " + $localStorage.dataN.surname,
            "idNum" : "ID number : " + $localStorage.dataN.IDNum,
            "email" :  "Email : " + $localStorage.dataN.email,
            "cellphone" :  "Cellphone : " + $localStorage.dataN.cellphone,
            "locationRegistered" :  "Location registered : " + $localStorage.dataN.locationRegistered,
            "activationStatus" :  "Activation status : " + $localStorage.dataN.activated,
            "votesRemaining" :  "Votes remaining : " + $localStorage.dataN.votes};

          $localStorage.dataN = "";

          console.log(result);
        });
    };
  })
