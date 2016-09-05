var parkingApp = angular.module("parkingApp", ["ngResource", "ngRoute", "ngAnimate", "ngTouch", "ui.bootstrap"]);

parkingApp.config(function($routeProvider, $locationProvider) {
    $routeProvider
    .when("/clients", {
        templateUrl : "app/templates/clients.html",
        controller: "ClientsController"
    })
    .when("/clients/:id", {
        templateUrl: "app/templates/client.html",
        controller: "ClientController"
    })
    .when("/parkings", {
        templateUrl : "app/templates/parkings.html",
        controller: "ParkingsController"
    })
    .when("/invoices", {
        templateUrl : "app/templates/invoices.html",
        controller: "InvoicesController"
    })
    .otherwise({
        redirectTo: '/clients'
    });
});

parkingApp.run(function($rootScope, $location) {
    $rootScope.$location = $location;
});