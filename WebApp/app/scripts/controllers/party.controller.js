/**
 * Created by gift on 2016/10/23.
 */


'use strict';

angular.module('eVotingWebApp')

  .controller('PartyCtrl', function($scope, PartyService ,  $localStorage, $location) {

    $scope.getPartyStats = function()
    {
      var getPartyStatsRequest = {
        "partyName" : $localStorage.partyName
      };
        PartyService.getPartyStats(getPartyStatsRequest)
        .then(function (result)
        {
          $localStorage.nationalPercentage = result.data.nationalPercentage;
          $localStorage.provincialPercentage = result.data.provincialPercentage;
          $localStorage.thePartyName = result.data.partyName;

          $scope.thePartyName = $localStorage.thePartyName;
          $scope.nationalPercentage =  $localStorage.nationalPercentage;
          $scope.provincialPercentage =  $localStorage.provincialPercentage;
        });
    }
  })
