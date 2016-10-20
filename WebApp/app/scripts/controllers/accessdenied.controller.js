/**
 * Created by Azhar on 2016/10/15.
 */


'use strict';

angular.module('eVotingWebApp')

  .controller('AccessDeniedCtrl',function($location, $scope, $localStorage){
    var vm = this;

    vm.goBack = goBack;

    function goBack()
    {
      alert("Redirecting...");
      window.history.go(-2);
    }
  });
