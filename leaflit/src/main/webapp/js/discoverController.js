/**
 * Access the previously created module 'plantapp'
 */
(function() {
	var myLeaflitApp = angular.module('leaflitapp');

	myLeaflitApp
			.controller(
					'discoverController',
					function($scope, $http, $routeParams, $location) {
						
						$scope.lightRequired = ['Full Sun','Morning Sun','Afternoon Sun','Partial Shade','Full Shade'];
						$scope.gardeningTime = ['< 5 minutes','5-10 minutes','15-30 minutes','> 30 minutes'];
						$scope.showCard = false;

						$scope.getUserHardinessZone = function(userInput) {
							var config = {
								params : $scope.discover
							}
							const response = $http(
									{
										method : 'GET',
										url : 'https://plant-hardiness-zone.p.rapidapi.com/zipcodes/'
												+ userInput,
										headers : {
											'X-RapidAPI-Key' : 'YOURRAPIDAPIKEY',
											'X-RapidAPI-Host' : 'plant-hardiness-zone.p.rapidapi.com'
										}
									})
									.then(
											function successCallback(response) {
												var convertedZipCode = response.data.hardiness_zone;
												$scope.response = response;
												console.log(convertedZipCode);
												$http
														.get(
																'/leaflit/webapi/plants/hardinessZone/'
																		+ convertedZipCode)
														.then(
																function(
																		response) {
																	$scope.showCard = true;
																	$scope.plants = response.data;
																	var plantsByZone = $scope.plants;
																	console
																			.log('number of results: '
																					+ $scope.plants.length);
																		
																},
																function error(
																		response) {
																	console
																			.log('error http GET plants: '
																					+ response.status);
																})

											},
											function errorCallback(response) {
												console
														.log('Unable to preform get request');
											});
						};
						
						$scope.getDiscover = function(lightRequired,plantsByZone) {
							var config = {
								params : $scope.discover
							}							
							$http
									.get(
											'/leaflit/webapi/plants/discoverResults', config)
									.then(
											function(
													response) {
												
												$scope.plants = response.data;
												console
														.log('number of results: '
																+ $scope.plants.length);
																	
											},
											function error(
													response) {
												console
														.log('error http GET plants: '
																+ response.status);
											

											
											});
						};

						$scope.clearDiscover = function() {
							$scope.discover.fullSun = false;
							$scope.discover.morningSun = false;
							$scope.discover.afternoonSun = false;
							$scope.discover.partialShade = false;
							$scope.discover.fullShade = false;

							$scope.userInput = null;

							$scope.discover.gardeningTime = null;

							$scope.searchResults = '';
						}
					});
})()
