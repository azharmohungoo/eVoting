angular.module('app.controllers', [])

.controller('eVotingCtrl', function($scope) {

})

.controller('electionInformationCtrl', function($scope) {

})

.controller('voteNationalCtrl', function($scope) {

  $scope.castVote = function() {

    alert("Congratulations. Your vote has been cast. ")
  }

})


.controller('eVotingLoginCtrl', function($scope, LoginService, $ionicPopup, $state) {
 
  var vm = this;
  
  var loginRequest = {"idNum" : vm.idNum , "password" : vm.password}
  
  vm.login = login;
  function login()
  {
    LoginService.loginUser(loginRequest)
  }
  
  
 /*
  $scope.data = {};

  $scope.login = function() {
    LoginService.loginUser($scope.data.id, $scope.data.password).success(function(data) {
      $state.go('tabsController.electionInformation');
    }).error(function(data) {
      var alertPopup = $ionicPopup.alert({
        title: 'Login failed!',
        template: 'Please check your credentials!'
      });
    });
    }
    */
})

.controller('registerCtrl' , function($scope , RegisterService) {

  var vm = this;

  vm.idNum = "893992";
  vm.password = "anotherPass";
  vm.name = "jane";
  vm.surname = "dee";
  vm.locationRegistered = "Gauteng";
  vm.cellphone = "09328873";
  vm.email = "goglo@oid.com";


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

    alert(JSON.stringify(registerRequest));

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

.controller('accountInformationCtrl', function($scope) {

})

  .controller('MainCtrl', function($scope) {




  })
