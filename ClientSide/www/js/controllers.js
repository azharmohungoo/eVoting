angular.module('app.controllers', [])

.controller('eVotingCtrl', function($scope) {
})

.controller('electionInformationCtrl', function($scope, $localStorage) {
})


.controller('viewPartyCtrl', function($scope, $localStorage){
  $scope.thePartyName = $localStorage.thePartyName;

})

.controller('voteNationalCtrl', function($scope, VoteNationalService ,  $localStorage, $state) {

  $scope.getParty = function(partyName)
  {
    var loadPartyRequest = {
      "partyName" : partyName
    };

    VoteNationalService.getParty(loadPartyRequest)
      .then(function (result)
      {
        $localStorage.thePartyName = result.data.partyName;
        $state.go('tabsController.viewParty');
      });
  };

  $scope.castVote = function(party, voteType) {

    var voterID =$localStorage.data.IDNum;
    var castNationalRequest = {

      "partyName": party,
      "voterID": voterID,
      "voterPassword": $localStorage.data.password,
      "voteType":voteType

    };
    VoteNationalService.castNational(castNationalRequest);
  };

  $scope.notAvailable = function () {
    alert("Sorry, technically available.");
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
          $localStorage.data = result;
        });
    }
  })


.controller('registerCtrl' , function($scope , RegisterService, $state) {

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
        if(result == true)
        {
          alert("Success! You can now log in.");
          $state.go('eVotingLogin');
        }
        else
        {
            alert("Ooops.. something went wrong. Please try again.")
        }
      })

  }
})

.controller('voteProvincialCtrl', function($scope) {

})

.controller('accountInformationCtrl', function($scope, $localStorage, $state) {

    $scope.name = $localStorage.data.name;
    $scope.surname = $localStorage.data.surname;
    $scope.IDNum = $localStorage.data.IDNum;
    $scope.votes =  $localStorage.data.votes;
    $scope.votedNational = $localStorage.data.votedNational;
    $scope.votedProvincial = $localStorage.data.votedProvincial;
    $scope.email = $localStorage.data.email;
    $scope.activated = $localStorage.data.activated;
    $scope.locationRegistered = $localStorage.data.locationRegistered;

   $scope.logout = function () {
     alert("Logging Out. Thank You.");
     $localStorage.$reset();
     $state.go('eVotingLogin');
   }

})

  .controller('MainCtrl', function($scope) {


  })
