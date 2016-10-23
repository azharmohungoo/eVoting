angular.module('app.routes', [])

.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider

      .state('eVoting', {
    url: '/page2',
    templateUrl: 'templates/eVoting.html',
    controller: 'eVotingCtrl'
  })

  .state('tabsController.electionInformation', {
    url: '/page3',
    views: {
      'tab1': {
        templateUrl: 'templates/electionInformation.html',
        controller: 'electionInformationCtrl'
      }
    }
  })

    .state('tabsController.viewParty', {
      url: '/viewParty',
      views: {
        'tab2': {
          templateUrl: 'templates/viewParty.html',
          controller: 'viewPartyCtrl'
        }
      }
    })

  .state('tabsController.voteNational', {
    url: '/page4',
    views: {
      'tab2': {
        templateUrl: 'templates/voteNational.html',
        controller: 'voteNationalCtrl'
      }
    }
  })

  .state('tabsController', {
    url: '/page1',
    templateUrl: 'templates/tabsController.html',
    abstract:true
  })

  .state('eVotingLogin', {
    url: '/page5',
    templateUrl: 'templates/eVotingLogin.html',
    controller: 'eVotingLoginCtrl',
    controllerAs: "vm"
  })

  .state('register', {
    url: '/page6',
    templateUrl: 'templates/register.html',
    controller: 'registerCtrl',
    controllerAs: "vm"
  })

  .state('tabsController.voteProvincial', {
    url: '/page7',
    views: {
      'tab3': {
        templateUrl: 'templates/voteProvincial.html',
        controller: 'voteNationalCtrl'
      }
    }
  })

  .state('accountInformation', {
    url: '/page8',
    templateUrl: 'templates/accountInformation.html',
    controller: 'accountInformationCtrl'
  })

$urlRouterProvider.otherwise('/page5')



});
