/**
 * Access the previously created module 'plantapp'
 */

let myLeaflitApp = angular.module('leaflitapp');

myLeaflitApp.controller('searchController', function($scope, $http, $location) {

	$scope.goToUpdateView = function(plantId) {
		console.log('go to update view');
		console.log('plantId : ' + plantId);
		$location.path('/update/' + plantId);
	}
	
	$scope.getAllPlants = function() {
		$http.get("/leaflit/webapi/plants")
		.then(function(response) {
			$scope.plants = response.data;
			console.log('number of plants: ' + $scope.plants.length);
		}, function(response) {
			console.log('error http GET plants: ' + response.status);
		});
	}
		
	$scope.getAllPlants();
	
});

