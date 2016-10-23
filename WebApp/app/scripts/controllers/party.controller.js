/**
 * Created by gift on 2016/10/23.
 */


'use strict';

angular.module('eVotingWebApp')

  .controller('PartyCtrl', function($scope, PartyService ,  $localStorage, $location) {


      alert("inside the controller");

    $scope.getPartyStats = function()
    {
      alert("inside get party stats");
      var getPartyStatsRequest = {
        "partyName" : $localStorage.partyName
      };

      alert("sending to service to get stats for " + $localStorage.partyName );
      PartyService.getPartyStats(getPartyStatsRequest)
        .then(function (result)
        {
          alert(JSON.stringify(result));
          $localStorage.nationalPercentage = result.data.nationalPercentage;
          $localStorage.provincialPercentage = result.data.provincialPercentage;
          $localStorage.thePartyName = result.data.partyName;

          $scope.thePartyName = $localStorage.thePartyName;
          $scope.nationalPercentage =  $localStorage.nationalPercentage;
          $scope.provincialPercentage =  $localStorage.provincialPercentage;

          alert("National : " + $localStorage.nationalPercentage);
          alert("Provincial : " + $localStorage.provincialPercentage);
         // $location.path("/viewParty");
          //$state.go('tabsController.viewParty');
        });
    }


  })
