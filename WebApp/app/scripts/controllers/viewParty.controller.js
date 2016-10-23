'use strict';

angular.module('eVotingWebApp')

.controller('ViewPartyCtrl', function($scope, $localStorage){
  $scope.thePartyName = $localStorage.thePartyName;
})
