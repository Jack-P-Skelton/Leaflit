/**
 * 
 */
(function() {
	var leaflitapp = angular.module('leaflitapp');

	leaflitapp.controller('createController', function($scope, $http) {			
		
		alert('createController');
		
	});
	
	(function() {
	var leaflitapp = angular.module('leaflitapp');

	leaflitapp.controller('createController', function($scope, $http) {			
		
		$scope.plantType = ['Flowering','NonFlowering'];
		
		$scope.createPlant = function() {
			$http.post("/leaflit/webapi/plants", $scope.plant)
			.then(function(response) {				
				$scope.createStatus = 'create successful';
				$scope.disableCreate = true;
			}, function(response) {
				$scope.createStatus = 'error trying to create movie';	
				console.log('error http POST movies: ' + response.status);
			});
		}
		
		$scope.clear = function() {
			$scope.plant.name = '';
			$scope.plant.hardinessZones = '';
			$scope.plant.growingSeason = '';
			$scope.plant.waterRequired = '';
			$scope.plant.lightRequired = '';
			$scope.plant.plantType = '';
			$scope.plant.img = '';
			$scope.disableCreate = false;
			$scope.createForm.$setUntouched();
			$scope.createForm.$setPristine();
		}
		
	});
	
})()
	
})()