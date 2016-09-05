parkingApp.controller("ClientController", function ($scope, $location, $routeParams, $uibModal,
Clients, Parkings, ClientParkings, ClientInvoices, ParkingHouses) {
    $scope.invoicesOrder = {
        column: 'created',
        reverse: false,
        sort: function (column) {
            if (this.column === column) {
                this.reverse = !this.reverse;
            }
            else {
                this.column = column;
                this.reverse = false;
            }
        }
    };

    $scope.parkingsOrder = {
        column: 'startTime',
        reverse: false,
        sort: function (column) {
            if (this.column === column) {
                this.reverse = !this.reverse;
            }
            else {
                this.column = column;
                this.reverse = false;
            }
        }
    };

    $scope.back = function() {
        $location.path("/clients");
    };

    Clients.get({ id: $routeParams.id }, function (response) {
        $scope.client = response ? response : [];
    });

    ClientParkings.query({ id: $routeParams.id }, function (response) {
        $scope.parkings = response ? response : [];
    });

    ClientInvoices.query({ id: $routeParams.id }, function (response) {
        $scope.invoices = response ? response : [];
    });

    ParkingHouses.query(function (response) {
        $scope.parkingHouses = response ? response : [];
    });

    $scope.addParking = function (parkingHouse, start, end) {
        var modalInstance = $uibModal.open({
            animation: true,
            size: 'lg',
            backdrop: 'static',
            templateUrl: 'app/templates/addParkingModal.html',
            controller: function($scope, $uibModalInstance, parkingHouses) {
                $scope.parkingHouses = parkingHouses;
                $scope.newParking = {};
                $scope.cancel = function () {
                    $uibModalInstance.dismiss("Cancel");
                };
                $scope.ok = function () {
                    $uibModalInstance.close($scope.newParking);
                };
            },
            resolve: {
                parkingHouses: function() {
                    return $scope.parkingHouses;
                }
            }
        }).result.then(function(newParking) {
            new ClientParkings({
                client: $scope.client,
                parkingHouse: newParking.parkingHouse,
                startTime: newParking.start,
                endTime: newParking.end
            }).$save({ id: $routeParams.id }, function (parking) {
                $scope.parkings.push(parking);
            }, function(error) {
                console.error(error);
            });
        }, function() {
            // add cancelled
        });
    };

    $scope.createInvoice = function () {
        ClientInvoices.save({ id: $routeParams.id }, function (response) {
            if (response) {
                $scope.invoices.push(response);
                ClientParkings.query({ id: $routeParams.id }, function (response) {
                    $scope.parkings = response ? response : [];
                });
            }
        });
    };

    $scope.showInvoice = function (invoice) {
        var modalInstance = $uibModal.open({
            animation: true,
            backdrop: 'static',
            templateUrl: 'app/templates/invoiceModal.html',
            controller: 'InvoiceModalController',
            resolve: {
                invoice: function() {
                    return invoice;
                }
            }
        });
    };
});