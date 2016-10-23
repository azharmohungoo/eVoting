/**
 * Created by gift on 2016/10/23.
 */


angular.module('eVotingWebApp')

  .service('PartyService',  function($http, ipProvider) {
      return {

        getPartyStats: function(getPartyStatsRequest)
        {
          var myUrl = "http://" + ipProvider.getIP() + ":8080/getPartyStats";
          return $http({url : myUrl , data : getPartyStatsRequest , method : "POST"})
        }
      }
    }
  )
