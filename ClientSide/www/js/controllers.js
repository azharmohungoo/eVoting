angular.module('app.controllers', [])

.controller('eVotingCtrl', function($scope) {



})

.controller('electionInformationCtrl', function($scope) {

})


.controller('viewPartyCtrl', function($scope, $localStorage){


  $scope.thePartyName = "";
alert( "local " + $localStorage.thePartyName);

     $scope.thePartyName = $localStorage.thePartyName;


})

.controller('voteNationalCtrl', function($scope, VoteNationalService ,  $localStorage) {

  $scope.getParty = function(partyName)
  {
    var loadPartyRequest = {
      "partyName" : partyName
    };

    alert("sending to service " + partyName );
    VoteNationalService.getParty(loadPartyRequest)
      .then(function (result)
      {
        alert("Back inside the controller");
        alert("from the server " + result.data.partyName);

        $localStorage.thePartyName = result.data.partyName;


      });
  };

  $scope.castVote = function(party) {

    alert("Attempting to cast your vote for : " + party)
    var castNationalRequest = {

      "partyName": party

    };
    VoteNationalService.castNational(castNationalRequest);
  }

})





  .controller('eVotingLoginCtrl', function($scope, LoginService, $localStorage) {


    var vm = this;

    vm.idNum;
    vm.password;

    vm.login = login;
    function  login()
    {
        var loginRequest = {
          "password" : Sha256.hash(vm.password) ,
          "idNum" : vm.idNum
        }

      LoginService.login(loginRequest)
        .then(function (result)
        {
           $scope.name = result.name;
          $localStorage.data = result;

         // console.log(result);

        });

    }
  })


.controller('registerCtrl' , function($scope , RegisterService) {

  var vm = this;

  vm.idNum;
  vm.password;
  vm.name;
  vm.surname;
  vm.locationRegistered;
  vm.cellphone;
  vm.email;


  vm.register = register;
    function  register() {


    var registerRequest = {
      "name" : vm.name,
      "password" : Sha256.hash(vm.password) ,
      "idNum" : vm.idNum,
      "surname" : vm.surname ,
      "email" : vm.email ,
      "cellphone" : vm.cellphone ,
      "locationRegistered" : vm.locationRegistered
    }


    RegisterService.register(registerRequest)
      .then(function (result) {
        alert(JSON.stringify(result));
      }).catch(function (exception)
    {
      alert("Previous Exception");
    });

  }
})

.controller('voteProvincialCtrl', function($scope) {

})

.controller('accountInformationCtrl', function($scope, $localStorage) {

    $scope.name = $localStorage.data.name;
    $scope.surname = $localStorage.data.surname;
    $scope.IDNum = $localStorage.data.IDNum;
    $scope.votes =  $localStorage.data.votes;
    $scope.votedNational = $localStorage.data.votedNational;
    $scope.votedProvincial = $localStorage.data.votedProvincial;
    $scope.email = $localStorage.data.email;
    $scope.activated = $localStorage.data.activated;
    $scope.locationRegistered = $localStorage.data.locationRegistered;

})

  .controller('MainCtrl', function($scope) {




  })
