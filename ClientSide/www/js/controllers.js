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

      alert(JSON.stringify(loginRequest));
      LoginService.login(loginRequest)
        .then(function (result)
        {
          alert(JSON.stringify(result));
        }).catch(function (exception)
      {
        alert("Previous Exception");
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
