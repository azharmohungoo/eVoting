/**
 * Created by Azhar on 2016/10/11.
 */

'use strict';

angular.module('eVotingWebApp')

  .controller('VoterCtrl', function($scope, $localStorage) {
    $scope.name = $localStorage.data.name;
    $scope.surname = $localStorage.data.surname;
    $scope.cellphone = $localStorage.data.cellphone;
    $scope.email = $localStorage.data.email;
    $scope.IDNum = $localStorage.data.IDNum;
    $scope.locationRegistered = $localStorage.data.locationRegistered;
    $scope.votedNational = $localStorage.data.votedNational;
    $scope.votedProvincial = $localStorage.data.votedProvincial;
    $scope.votes =  $localStorage.data.votes;
    $scope.activated = $localStorage.data.activated;
    $scope.userType = $localStorage.data.userType;
  })
