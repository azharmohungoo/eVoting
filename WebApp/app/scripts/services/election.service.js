
angular.module('eVotingWebApp')

  .service('ElectionService',  function($http, ipProvider) {
      return {

        castNational: function (castNationalRequest) {

          alert("Casting vote..");
          var myUrl = "http://" + ipProvider.getIP() + ":8080/castVote";
          return $http({ url : myUrl , data : castNationalRequest , method : "POST" })
            .then(function (result){
              alert(result.data.reason);
            })
        },

        getParty: function(viewPartyRequest)
        {
          var myUrl = "http://" + ipProvider.getIP() + ":8080/getParty";
          return $http({url : myUrl , data : viewPartyRequest , method : "POST"})
        }
      }
    }
  )
