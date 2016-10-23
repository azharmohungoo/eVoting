/**
 * Created by Azhar on 2016/10/10.
 */

'use strict';

angular.module('eVotingWebApp')

  .controller('RegisterCtrl' , function($scope , RegisterService, $location) {
    var vm = this;

    vm.idNum;
    vm.password;
    vm.name;
    vm.surname;
    vm.locationRegistered;
    vm.cellphone;
    vm.email;

    vm.register = register;

    function register() {

      var registerRequest = {
        "idNum" : vm.idNum,
        "password" : Sha256.hash(vm.password) ,
        //"password" : vm.password ,
        "name" : vm.name,
        "surname" : vm.surname ,
        "locationRegistered" : vm.locationRegistered ,
        "cellphone" : vm.cellphone ,
        "email" : vm.email
      }

      RegisterService.register(registerRequest)
        .then(function (result) {
          alert("You have successfully been registered!");
          $location.path("/");
        }).catch(function (exception)
      {
        alert("Previous Exception");
      });
    }
})
