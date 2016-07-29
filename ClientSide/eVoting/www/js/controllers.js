angular.module('app.controllers', [])

.controller('eVotingCtrl', function($scope) {

})

.controller('electionInformationCtrl', function($scope) {

})

.controller('voteNationalCtrl', function($scope) {

})


.controller('eVotingLoginCtrl', function($scope, LoginService, $ionicPopup, $state, userService) {
  $scope.data = {};

  $scope.login = function() {
    LoginService.loginUser($scope.data.username, $scope.data.password).success(function(data) {
      $state.go('tabsController.electionInformation');
    }).error(function(data) {
      var alertPopup = $ionicPopup.alert({
        title: 'Login failed!',
        template: 'Please check your credentials!'
      });
    });


    }

  $scope.GetUsers = function(){
    alert('something');
    userService.GetUsers().then(function(result){
      //$scope.users = users;
      console.log(result);

    });
  }

})




.controller('registerCtrl', function($scope) {

})

.controller('voteProvincialCtrl', function($scope) {

})

.controller('accountInformationCtrl', function($scope) {

})

  .controller('MainCtrl', function($scope, userService) {




  })
