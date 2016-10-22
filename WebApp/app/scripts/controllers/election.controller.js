/**
 * Created by Azhar on 2016/10/22.
 */

'use strict';

angular.module('eVotingWebApp')

.controller('ElectionCtrl', function($scope, ElectionService ,  $localStorage, $location) {
  var vm = this;

  vm.getParty = getParty;
  vm.castVote = castVote;

  function getParty(partyName)
  {
    var loadPartyRequest = {
      "partyName" : partyName
    };

    alert("sending to service " + partyName );
    ElectionService.getParty(loadPartyRequest)
      .then(function (result)
      {
        $localStorage.thePartyName = result.data.partyName;
        $location.path('/viewParty');
      });
  };

  function castVote(party, voteType) {

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
