/**
 * 
 */


let	leaflitapp = angular.module('leaflitapp', ['ngRoute']);

	leaflitapp.config(function($routeProvider) {
		$routeProvider
		  .when("/stack", {
		    templateUrl : "stack.html"
		  })
		  .when("/resume", {
		    templateUrl : "JackSkeltonResume.html"
		  })
		  .when("/watering", {
		    templateUrl : "watering.html"
		  })
		  .when("/light", {
		    templateUrl : "light.html"
		  })
		  .when("/soil", {
		    templateUrl : "soil.html"
		  })
		  .when("/hardinessZones", {
		    templateUrl : "hardinessZones.html"
		  })
		  .when("/randomPlant", {
		    templateUrl : "randomPlant.html",
		    controller : "randomPlantController"
		  })
		  .when("/discover", {
		    templateUrl : "discover.html",
		    controller : "discoverController"
		  })
		  .when("/admin", {
		    templateUrl : "admin.html",
		    controller : "adminController"
		  })
		  .when("/search", {
		    templateUrl : "search.html",
		    controller : "searchController"
		  })
		  .when("/create", {
		    templateUrl : "create.html",
		    controller : "createController"
		  })
		  .when("/update/:plantId", {
		    templateUrl : "update.html",
			controller : "updateController"
		  })
		  .when("/main", {
		    templateUrl : "main.html",
			controller : "mainController"
		  })
		  .otherwise({
		    templateUrl : "main.html",
			controller : "mainController"
		  });
	});