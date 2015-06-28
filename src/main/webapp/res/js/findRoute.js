var findRoute = angular.module("findRoute", []);
findRoute.filter('unsafe', function($sce) { return $sce.trustAsHtml; });
findRoute.controller("routeController", function($scope, $http, $timeout) {
  $scope.car = true;
  $scope.taxi = true;
  $scope.train = true;
  $scope.plane = true;
  
  $scope.data = [];
  $scope.idx = 0;
  
  $scope.loading = false;
  $scope.cancel = function (form) {
    $scope.loading = false;
    
    form.$setPristine();
    form.$setUntouched();
  };
  $scope.request = function(form) {
    //invalid submit
    if(!form.$valid) {
      form.$setSubmitted();
      return;
    }
    
    $scope.loading = true;
    serverFindRoute($http, $timeout,
          "/" + $scope.origin
        + "/" + $scope.destination
        + "/" + $scope.balance,
      function(data, status, headers, config) {
        //cancelled
        if(!$scope.loading)
          return;
        $scope.loading = false;
        
        if(data.status != "ok") {
          $scope.errorMessage = "Invalid data.";
          $scope.errorData = data;
          $scope.hasErrorData = true;
          $("#errorModal").modal("show");
          return;
        }
        
        $scope.data = data.routes;
        $scope.collectPolylines();
        $scope.switchTo(0);
        $("#findRouteModal").modal("hide");
        
        $scope.origin = "";
        $scope.destination = "";
        
        form.$setPristine();
        form.$setUntouched();
      },
      function(data, status, headers, config) {
        $scope.loading = false;
        
        $scope.errorMessage = "Exception while trying to connect to the server. Maybe try again later.";
        $scope.hasErrorData = false;
        $("#errorModal").modal("show");
      });
  };
  $scope.showError = function() {
    var data = $scope.errorData;
    if(angular.isObject(data))
      data = angular.toJson(data, true);
    
    var date = new Date();
    data = "<head><title>Error: "
      + date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds()
      + "</title></head><body>" + data + "</body>";
    
    myWindow = window.open("data:text/html," + encodeURIComponent(data), "_blank");
    myWindow.focus();
  }
  $scope.collectPolylines = function() {
    $scope.data.forEach(function(element, index, array) {
      element.layer = L.layerGroup();
      if(element.route) {
        element.route.forEach(function(e, i, a) {
          if(e.data && e.data.routes[0].overview_polyline) {
            element.layer.addLayer(L.Polyline.fromEncoded(e.data.routes[0].overview_polyline.points));
          }
          if(e.data && e.data.routes[0].bounds) {
            if(!element.bounds) {
              element.bounds = L.latLngBounds(
                [
                  e.data.routes[0].bounds.northeast.lat,
                  e.data.routes[0].bounds.northeast.lng
                ], 
                [
                  e.data.routes[0].bounds.southwest.lat,
                  e.data.routes[0].bounds.southwest.lng
                ]
              );
            }
            element.bounds.extend([e.data.routes[0].bounds.northeast.lat, e.data.routes[0].bounds.northeast.lng]);
            element.bounds.extend([e.data.routes[0].bounds.southwest.lat, e.data.routes[0].bounds.southwest.lng]);
          }
        });
      }
    });
  }
  $scope.switchTo = function(i) {
    map.removeLayer($scope.data[$scope.idx].layer);
    $scope.idx = i;
    map.addLayer($scope.data[i].layer);
    map.fitBounds($scope.data[i].bounds);
  }
  $scope.setViewport = function(bounds) {
    map.fitBounds([[bounds.northeast.lat, bounds.northeast.lng], [bounds.southwest.lat, bounds.southwest.lng]]);
  }
});
findRoute.directive("combobox", function() {
  return {
    restrict: 'A',
    require: '?ngModel',
    
    link: function(scope, element, attrs, ngModel) {
      if (!ngModel) return;
      
      element.on('hide.bs.dropdown', function() {
        scope.$evalAsync(read);
      });
      read();
      
      function read() {
        ngModel.$setViewValue(
          element.children(".dropdown-menu").children(":has(.active)").index()
        );
      }
    }
  };
});
