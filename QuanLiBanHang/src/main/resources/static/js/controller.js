var app = angular.module('myApp', []);
app.controller('checkoutController', function($scope) {
    $scope.cart = cart;
    console.log($scope.cart);
    
    $scope.removeCart = function(){
    	RemoveCart();
    	$scope.cart = cart;
    };
    $scope.removeItem = function(index){
    	//$scope.cart.items.splice(index, 1);
    	RemoveItem(index);
    };
});