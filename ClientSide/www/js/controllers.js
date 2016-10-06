angular.module('app.controllers', [])

.controller('eVotingCtrl', function($scope) {

})

.controller('electionInformationCtrl', function($scope) {

})

.controller('voteNationalCtrl', function($scope, VoteNationalService) {

  $scope.castVote = function(party) {

    alert("Attempting to cast your vote for : " + party)
    var castNationalRequest = {

      "partyName": party

    }
    VoteNationalService.castNational(castNationalRequest);
  }

})

  .controller('eVotingLoginCtrl', function($scope, LoginService) {

    var vm = this;

    vm.idNum;
    vm.password;

    vm.login = login;
    function  login()
    {
        var loginRequest = {
          "password" : vm.password ,
          "idNum" : vm.idNum
        }

     // alert(JSON.stringify(loginRequest));
      LoginService.login(loginRequest)
        .then(function (result)
        {
           $scope.name = result.name;
          console.log(result);

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
      "password" : vm.password ,
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

.controller('accountInformationCtrl', function($scope, LoginService) {

  $ionicPlatform.registerBackButtonAction(function (e) {
    e.preventDefault();
    function showConfirm() {
      var confirmPopup = $ionicPopup.show({
        title: 'Logout...',
        template: 'Are you sure you want to logout?',
        buttons: [{
          text: 'Cancel',
          type : '',
        }, {
          text: 'Ok',
          type : 'button-calm',
          onTap: function () {
// write your code for logout here..
          }
        }]
      });
    };
    if ($ionicHistory.backView()) {
      $ionicHistory.backView().go();
    } else {
      showConfirm();
    }
    return false;
  }, 101);


})

  .controller('MainCtrl', function($scope) {




  })
