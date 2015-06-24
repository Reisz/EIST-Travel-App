var findRoute = angular.module("findRoute", []);
findRoute.controller("routeController", function($scope, $http, $timeout) {
  $scope.car = true;
  $scope.taxi = true;
  $scope.train = true;
  $scope.plane = true;
  $scope.loading = false;
  $scope.cancel = function () {
    $scope.loading = false;
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
      function(response) {
        if(!$scope.loading) //cancelled
          return;
        $scope.loading = false;
        
        //do stuff
        console.log(response);
      },
      function() {
        $scope.loading = false;
        
        //error stuff
      });
  };
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