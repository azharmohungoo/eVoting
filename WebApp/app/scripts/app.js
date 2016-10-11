'use strict';

/**
 * @ngdoc overview
 * @name eVotingWebApp
 * @description
 * # eVotingWebApp
 *
 * Main module of the application.
 */
angular
  .module('eVotingWebApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngStorage'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'LoginCtrl',
        controllerAs: 'vm'
      })
      /*.when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl',
        controllerAs: 'vm'
      })*/
      .when('/register', {
        templateUrl: 'views/register.html',
        controller: 'RegisterCtrl',
        controllerAs: 'vm'
      })
      .when('/admin', {
        templateUrl: 'views/admin.html',
        //controller: '',
        //controllerAs: 'vm'
      })
      .when('/activator', {
        templateUrl: 'views/activator.html',
        //controller: '',
        //controllerAs: ''
      })
      .when('/party', {
        templateUrl: 'views/party.html',
        //controller: '',
        //controllerAs: ''
      })
      .when('/voter', {
        templateUrl: 'views/voter.html',
        controller: 'VoterCtrl',
        controllerAs: 'vm'
      })
      .when('/election', {
        templateUrl: 'views/election.html',
        //controller: '',
        //controllerAs: ''
      })
      .when('/accountInfo', {
        templateUrl: 'views/accountInfo.html',
        controller: 'VoterCtrl',
        controllerAs: 'vm'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
