/**
 * Created by Azhar on 2016/10/22.
 */

angular.module('eVotingWebApp')

.service('ipProvider', function(){

  return {
    getIP : function ()
    {
      return "localhost";
    }

  }
})
