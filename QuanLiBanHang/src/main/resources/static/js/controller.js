var app = angular.module('myApp', []);
app.controller('checkoutController', function($scope, $http) {
	$scope.name = "orders";
	$scope.singularName = "order";
	
	setTimeout(function(){  
		$scope.cart = cart;
		console.log($scope.cart);
		$scope.$apply();
	},1000)
    crudFunction($scope, $http);
	$scope.currentModel = {};
    $scope.removeCart = function(){
    	RemoveCart();
    	$scope.cart = cart;
    };
    $scope.removeItem = function(index){
    	RemoveItem(index);
    };
    $scope.setCurrentModel = function(){
    	$scope.currentModel = convertToOrder(cart);
    	if($scope.account != undefined){
    		$scope.currentModel.phone = $scope.account.phone;
    		$scope.currentModel.address = $scope.account.address;
    	}
    }
    $scope.afterGet = function(){
    	$scope.removeCart();
    }
    
    var email = $("#authenticationEmail")[0];
    if(email != undefined){
	   $scope.findByEmail($("#authenticationEmail")[0].innerText, function(model){
	    	console.log(model);
	    	$scope.account = model;
	    });
    }
});
function convertToOrder(cart){
	var order = {};
	order.foods = [];
	for(var index in cart.items){
		var item = cart.items[index];
		for(var i = 0;i<item.quantity;i++){
			item._links = {}; item._links.self={};
			item._links.self.href= "/foods/" + item.foodId;
			order.foods.push(item);
		}
	}
	order.orderStatus = "NEW";
	order.totalMoney = cart.total;
	return order;
}