/**
 * 
 */
(function() {	
	
	var myLeaflitApp = angular.module('leaflitapp');
	
	myLeaflitApp.controller('updateController', function($scope, $http, $routeParams, $location) {
		
		$scope.plantType = ['Flowering','NonFlowering'];
	
		$scope.getPlantsById = function() {
			$http.get("/leaflit/webapi/plants/" + $routeParams.plantId)
			.then(function(response) {
				var plants = response.data;
				if (plants.length == 1) {
					$scope.plant = plants[0];
				} else {
					//TODO error message
				}				
			}, function(response) {
				console.log('error http GET plants by id: ' + response.status);
			});
		}

		$scope.getPlantsById();
		
		$scope.updatePlant = function() {
			$http.put("/leaflit/webapi/plants", $scope.plant)
			.then(function(response) {				
				$scope.updateStatus = 'update successful';			
			}, function(response) {
				$scope.updateStatus = 'error trying to update plant';	
				console.log('error http PUT movies: ' + response.status);
			});
		}
		
		$scope.deletePlant = function() {
			$http.delete("/leaflit/webapi/plants/" + $scope.plant.id)
			.then(function(response) {				
				$scope.updateStatus = 'delete successful';	
				$scope.disableUpdate = true;
			}, function(response) {
				$scope.updateStatus = 'error trying to delete movie';	
				console.log('error http DELETE movies: ' + response.status);
			});
		}
		
		$scope.goToAdminView = function() {
			console.log('go to search view');
			$location.path('/admin');
		}		
	});
})()