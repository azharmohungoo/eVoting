'use strict';

angular.module('eVotingWebApp')

.controller('ViewPartyCtrl', function($scope, $localStorage){

  alert($localStorage.thePartyName);

    $scope.thePartyName = $localStorage.thePartyName;

})
