parkingApp.controller("ClientsController", function ($scope, $location, $uibModal,
Clients, ClientTypes) {
    $scope.clientsOrder = {
        column: 'name',
        reverse: false,
        sort: function (column) {
            if ($scope.clientsOrder.column === column) {
                $scope.clientsOrder.reverse = !$scope.clientsOrder.reverse;
            }
            else {
                $scope.clientsOrder.column = column;
                $scope.clientsOrder.reverse = false;
            }
        }
    };

    Clients.query(function (response) {
        $scope.clients = response ? response : [];
    });

    ClientTypes.query(function (response) {
        $scope.clientTypes = response ? response : [];
    });

    $scope.addClient = function (clientType, name) {
        var modalInstance = $uibModal.open({
            animation: true,
            backdrop: 'static',
            templateUrl: 'app/templates/addClientModal.html',
            controller: function($scope, $uibModalInstance, clientTypes) {
                $scope.clientTypes = clientTypes;
                $scope.newClient = {};
                $scope.cancel = function () {
                    $uibModalInstance.dismiss("Cancel");
                };
                $scope.ok = function () {
                    $uibModalInstance.close($scope.newClient);
                };
            },
            resolve: {
                clientTypes: function() {
                    return $scope.clientTypes;
                }
            }
        }).result.then(function(newClient) {
            new Clients({
                clientType: newClient.clientType,
                name: newClient.name
            }).$save(function (client) {
                $scope.clients.push(client);
            }, function(error) {
                console.error(error);
            });
        }, function() {
            // add cancelled
        });
    };

    $scope.deleteClient = function (client) {
        client.$remove(function () {
            $scope.clients.splice($scope.clients.indexOf(client), 1);
        });
    };

    $scope.openClient = function (id) {
        $location.path('/clients/' + id);
    };
});