parkingApp.controller("InvoicesController", function ($scope, $uibModal, Invoices) {
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

    Invoices.query(function (response) {
        $scope.invoices = response ? response : [];
    });

    $scope.showInvoice = function (invoice) {
        var modalInstance = $uibModal.open({
            animation: true,
            size: 'lg',
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