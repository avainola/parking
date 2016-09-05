parkingApp.controller("ParkingsController", function ($scope, Parkings) {
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

    Parkings.query(function (response) {
        $scope.parkings = response ? response : [];
    });

    $scope.deleteParking = function (parking) {
        parking.$remove(function () {
            $scope.parkings.splice($scope.parkings.indexOf(parking), 1);
        });
    };
});