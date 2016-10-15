/**
 * Created by Azhar on 2016/10/15.
 */

'use strict';

angular.module('eVotingWebApp')

  .controller('NavCtrl',function($location, $scope, $localStorage){
    var vm = this;

    vm.logOut = logOut;
    vm.isLoggedIn = isLoggedIn;

    function logOut()
    {
      //alert("clear");
      $localStorage.$reset();
      $location.path('/');
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
