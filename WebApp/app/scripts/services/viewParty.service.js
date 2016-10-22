
angular.module('eVotingWebApp')
.service('ViewPartyService', function($http, ipProvider){
  return {

    getParty: function(viewPartyRequest)
    {
      var myUrl = "http://" + ipProvider.getIP() + ":8080/getParty";
      return $http({url : myUrl , data : viewPartyRequest , method : "POST"})
        .then(function(result)
        {
          alert(result.data);
        })

    }

  }

})
