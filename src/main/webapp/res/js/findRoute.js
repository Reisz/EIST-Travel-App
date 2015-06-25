var findRoute = angular.module("findRoute", []);
findRoute.controller("routeController", function($scope, $http, $timeout) {
  $scope.car = true;
  $scope.taxi = true;
  $scope.train = true;
  $scope.plane = true;
  
  $scope.data = [];
  
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
        
        $scope.data = data.data;
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
