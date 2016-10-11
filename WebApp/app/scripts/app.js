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
      .otherwise({
        redirectTo: '/'
      });
  });
