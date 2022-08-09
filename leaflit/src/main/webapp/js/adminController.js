/**
 * 
 */
(function() {	
	
	var myLeaflitApp = angular.module('leaflitapp');
	
	myLeaflitApp.controller('adminController', function($scope, $http, $routeParams, $location) {
				
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
		
		$scope.goToCreateView = function() {
			console.log('go to create view');
			$location.path('/create');
		}
		
	});
})()