/**
 * 
 */
(function() {
	var myLeaflitApp = angular.module('leaflitapp');

	myLeaflitApp.controller('randomPlantController', function($scope, $http,
			$location) {

		$scope.showCard = false;

		$scope.getRandomPlant = function() {
			$http.get("/leaflit/webapi/plants").then(
					function(response) {
						$scope.plants = response.data;
						console
								.log('number of plants: '
										+ $scope.plants.length);
						$scope.showCard = true;

						$scope.randomPlantIndex = Math.floor(Math.random()
								* ($scope.plants.length - 1)) + 1;
						console.log('random plant index: '
								+ $scope.randomPlantIndex);

						$http.get(
								"/leaflit/webapi/plants/"
										+ $scope.randomPlantIndex).then(
								function(response) {

									$scope.randomPlant = response.data;
									console.log('random plant chosen: '
											+ $scope.randomPlant);
								},
								function(response) {
									console.log('error http GET plants: '
											+ response.status);
								});

					},
					function(response) {
						console
								.log('error http GET plants: '
										+ response.status);
					});
		}
	})
})()