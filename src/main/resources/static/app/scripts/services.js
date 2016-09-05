parkingApp.factory("Parkings", function ($resource) {
    return $resource('/parkings/:id', {
        id: '@id'
    });
});

parkingApp.factory("ParkingHouses", function ($resource) {
    return $resource('/parkingHouses/:id', {
        id: '@id'
    });
});

parkingApp.factory("PriceSchemas", function ($resource) {
    return $resource('/priceSchemas/:id', {
        id: '@id'
    });
});

parkingApp.factory("ClientTypes", function ($resource) {
    return $resource('/clientTypes/:id', {
        id: '@id'
    });
});

parkingApp.factory("Invoices", function ($resource) {
    return $resource('/invoices/:id', {
        id: '@id'
    });
});

parkingApp.factory("Clients", function ($resource) {
    return $resource('/clients/:id', {
        id: '@id'
    });
});

parkingApp.factory("ClientParkings", function ($resource) {
    return $resource('/clients/:id/parkings', {
        id: '@id'
    });
});

parkingApp.factory("ClientInvoices", function ($resource) {
    return $resource('/clients/:id/invoices', {
        id: '@id'
    });
});
