parkingApp.controller("InvoiceModalController", function($scope, $uibModalInstance, invoice) {
    $scope.invoice = invoice;
    $scope.subtotal = 0;
    invoice.parkings.forEach(function(parking) {
        $scope.subtotal += parking.fee;
    });
    $scope.close = function () {
        $uibModalInstance.dismiss("Close");
    };
});