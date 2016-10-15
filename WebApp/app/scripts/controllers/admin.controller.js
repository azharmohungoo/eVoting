/**
 * Created by Azhar on 2016/10/14.
 */

'use strict';

angular.module('eVotingWebApp')

  .controller('AdminCtrl' , function($scope , AdminService) {
    var vm = this;

    vm.idNum;
    vm.password;
    vm.name;
    vm.surname;
    vm.locationRegistered;
    vm.cellphone;
    vm.email;
    vm.userType;

    vm.adminRegister = adminRegister;

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
    }
  })
