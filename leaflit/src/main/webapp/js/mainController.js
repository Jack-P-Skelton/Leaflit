/**
 * 
 */

var myLeaflitApp1 = angular.module('leaflitapp');

myLeaflitApp1.controller('mainController', function($scope, $http,
		$routeParams, $location) {

	$scope.goToDiscoverView = function() {
		console.log('go to discover view');
		$location.path('/discover');
	}
});