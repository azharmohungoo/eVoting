/**
 * Created by Azhar on 2016/10/22.
 */

'use strict';

angular.module('eVotingWebApp')

.controller('ElectionCtrl', function($scope, ElectionService ,  $localStorage, $location) {
  $scope.getParty = function(partyName)
  {
    var loadPartyRequest = {
      "partyName" : partyName
    };

    alert("sending to service " + partyName );
    ElectionService.getParty(loadPartyRequest)
      .then(function (result)
      {
        $localStorage.thePartyName = result.data.partyName;
        alert("my guy, the party name " + $localStorage.thePartyName);
        $location.path("/viewParty");
      });
  };

  $scope.castVote = function(party, voteType) {

    alert("Attempting to cast your vote for : " + party)
    var voterID =$localStorage.data.IDNum;
    alert(voterID);
    var castNationalRequest = {

      "partyName": party,
      "voterID": voterID,
      "voterPassword": $localStorage.data.password,
      "voteType":voteType

    };
    ElectionService.castNational(castNationalRequest);
  }

})
