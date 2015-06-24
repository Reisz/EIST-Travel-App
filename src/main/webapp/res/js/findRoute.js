var findRoute = angular.module("findRoute", []);
findRoute.controller("routeController", function($scope, $http) {
  $scope.car = true;
  $scope.taxi = true;
  $scope.train = true;
  $scope.plane = true;
  $scope.request = function() {
    //TODO loadAnimation
    $http.get("findRoute"
      + "/" + $scope.origin
      + "/" + $scope.destination
      + "/" + $scope.balance)
    .success(function(response) {
      console.log(response);
      //TODO clear load animation
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