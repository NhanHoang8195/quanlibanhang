$(document).ready(function(c) {
    
});
function activeMenu(){
	var url = window.location; 
    var element = $('ul.side-nav a').filter(function() {
    	return this.href == url || (url.href.indexOf(this.href) == 0 && this.pathname != '/') ; 
    }).parent().addClass('active');
    //#4a433f
    var element = $('ul.side-nav li li a').filter(function() {
    	return this.href == url || (url.href.indexOf(this.href) == 0 && this.pathname != '/') ; 
    }).parent().css("background-color", "#4a433f");
    
    if (element.is('li')) { 
         element.addClass('active').parent().parent('li').addClass('active')
    }
}
function resetAllFiles() {
    var inputs = document.getElementsByTagName('input');
    var length = inputs.length;
    for (i = 0; i < length; i++) {
        if (inputs[i].type == "file") {
            inputs[i].value = "";
        }
    }
}
var app = angular.module('myApp', []);

angular.module('myApp').directive('customOnChange', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var onChangeHandler = scope.$eval(attrs.customOnChange);
            element.bind('change', onChangeHandler);
        }
    };
});
function defineBaseFunction(scope, http){
	scope.currentModel = {};
	
	scope.setCurrentModel = function(model){
		scope.currentModel = JSON.parse(JSON.stringify(model));
	};
	
	scope.emptyCurrentModel = function(){
		scope.currentModel = {};
		scope.password = "";
	};
	
	scope.addFile= function (event) {
        var file = event.target.files[0];

        var formData = new FormData();
        formData.append('file', file);
        http.post("/upload/", formData, {
            transformRequest : angular.identity,
            headers : {
                'Content-Type' : undefined
            }})
		.then(function(response) {
			scope.currentModel.image = response.data.name;

			resetAllFiles();
		})
    };
    
	crudFunction(scope, http);
	
	setInterval(function(){
		if($('.modal').hasClass('in') || $(".dropdown-menu").is(":visible")){ 
			return;
		}
		var flag = true;
		$(".move-branch").each(function(idx, li) {
		    var product = $(li);
		    if(product.is(":enabled"))
		    	flag = false;
		});
		if(scope.name == "orders" && flag==true){
			scope.get();
		}
	}, 5000);
	// Account
	var email = $("#authenticationEmail")[0];
    if(email != undefined){
	   scope.findByEmail($("#authenticationEmail")[0].innerText, function(model){
	    	console.log(model);
	    	scope.account = model;
	    });
    }
	scope.waitAccountLoad = function() {
		while(scope.account.branch == undefined){}
	}
	scope.sleep = function(delay) {
        var start = new Date().getTime();
        while (new Date().getTime() < start + delay);
     }
}
app.controller('foodController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "foods";
	scope.singularName = "food";
	
	defineBaseFunction(scope, http);
	
	scope.get();
	scope.get("categories", function(data){
		scope.categories = data;
	});	
});
app.controller('menuController', function($scope, $http) {
	var scope = $scope; var http = $http;
	crudFunction(scope, http);
	
	var email = $("#authenticationEmail")[0];
    if(email != undefined){
	   scope.findByEmail($("#authenticationEmail")[0].innerText, function(model){
	    	console.log(model);
	    	scope.account = model;
	    });
    }
    scope.hasRoleAdmin = function(){
    	if(scope.account == undefined)
    		return false;
    	var filteredRoles = scope.account.roles.filter(function(item) {
			return item.roleName == "ROLE_ADMIN";
		});activeMenu();
    	return filteredRoles.length > 0;
    }
    scope.hasRoleSwitchboard = function(){
    	if(scope.account == undefined)
    		return false;
    	var filteredRoles = scope.account.roles.filter(function(item) {
			return item.roleName == "ROLE_SWITCHBOARD";
		});activeMenu();
    	return filteredRoles.length > 0;
    }
    scope.hasRoleBranch = function(){
    	if(scope.account == undefined)
    		return false;
    	var filteredRoles = scope.account.roles.filter(function(item) {
			return item.roleName == "ROLE_BRANCH";
		});activeMenu();
    	return filteredRoles.length > 0;
    }
});
app.controller('branchController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "branches"
	scope.singularName = "branch";
	
	defineBaseFunction(scope, http);

	scope.get();
	scope.get("foods", function(data){
		scope.foods = data;
		scope.$watch('currentModel.foods', function() {
			if(scope.currentModel.foods == undefined){
				scope.currentModel.foods = [];
				return;
			}
			scope.filteredFoods = scope.foods.filter( function(item) {
				for(var i=0;i<scope.currentModel.foods.length;i++){
					if(scope.currentModel.foods[i].foodName == item.foodName){
						return false;
					}
		    	}
				return true;
			});
	    }, true);
	});	
});
app.controller('customerController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "accounts";
	scope.singularName = "account";
	
	defineBaseFunction(scope, http);
	
	scope.get();
	scope.afterGet = function(models){
		scope.models = models.filter(function(item) {return item.roles[0].roleName =="ROLE_MEMBER"});
	};
	scope.beforePost = function(model){
		model.roles = scope.roles.filter(function(item) {return item.roleName =="ROLE_MEMBER"});
	};
	
	scope.afterPut = function(){
		if(scope.password == undefined || scope.password == ""){
			return;
		}
		scope.updatePassword(scope.currentModel.email, scope.password);
	}
});
app.controller('staffController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "accounts";
	scope.singularName = "account";
	
	defineBaseFunction(scope, http);
	scope.getRoles = function(roles){
		var roleNameArray = [];
		for(var index in roles){
			roleNameArray.push(roles[index].roleName);
		}
		return roleNameArray.join(",");
	}
	scope.get("branches", function(data){
		scope.branches = data;
	});
	scope.get("roles", function(models){
		scope.roles = models;
		scope.$watch('currentModel.roles', function() {
			if(scope.currentModel.roles == undefined){
				scope.currentModel.roles = [];
				return;
			}
			scope.filteredRoles = scope.roles.filter( function(item) {
				for(var i=0;i<scope.currentModel.roles.length;i++){
					if(scope.currentModel.roles[i].roleName == item.roleName){
						return false;
					}
		    	}
				return true;
			});
	    }, true);
	});
	scope.get();
	scope.afterGet = function(models){
		scope.models = models.filter(function(item) {return item.roles[0].roleName !="ROLE_MEMBER"});
	};
	scope.afterPut = function(){
		if(scope.password == undefined || scope.password == ""){
			return;
		}
		scope.updatePassword(scope.currentModel.email, scope.password);
	}
});
app.controller('categoryController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "categories";
	scope.singularName = "category";
	
	defineBaseFunction(scope, http);
	
	scope.get();
});
app.controller('orderSwitchboardController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "orders";
	scope.singularName = "order";
	
	defineBaseFunction(scope, http);
	
	scope.get();
	scope.afterGet = function(models){
		scope.newStatusModels = scope.models.filter(function(item) {return item.orderStatus=="NEW"});
		scope.processingStatusModels = scope.models.filter(function(item) {return item.orderStatus=="PROCESSING"});
		baseOrder(scope);
	};
	scope.beforePost = function(model){
		model.orderStatus = 'NEW';
		model.dateOrder = new Date().toLocaleString();
		model.account = scope.account;
	};
	
	scope.get("branches", function(data){
		scope.branches = data;
	});	
	scope.get("foods", function(data){
		scope.foods = data;
	});	
});

app.controller('orderBranchController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "orders";
	scope.singularName = "order";
	
	defineBaseFunction(scope, http);
	
	scope.get();
	scope.afterGet = function(models){
		scope.processingStatusModels = scope.models.filter(function(item) {
			if(item.branch == undefined){
				return false;
			}
			
			return item.orderStatus=="PROCESSING" && item.branch.name == scope.account.branch.name;
		});
		scope.cookingStatusModels = scope.models.filter(function(item) {
			if(item.branch == undefined){
				return false;
			}
			
			return item.orderStatus=="COOKING" && item.branch.name == scope.account.branch.name;
		});
		baseOrder(scope);
	};
	scope.beforePost = function(model){
		model.orderStatus = 'PROCESSING';
		model.dateOrder = new Date().toLocaleString();
		model.branch = scope.account.branch;
	};
	
	scope.get("branches", function(data){
		scope.branches = data;
	});	
	scope.get("foods", function(data){
		scope.foods = data;
	});
});

function baseOrder(scope){
	scope.$watch('foods', function() {
		scope.filteredFoods = scope.foods;
		scope.suggestion = "";
    }, true);
	scope.$watch('suggestion', function() {
		if(scope.foods == undefined){
			scope.suggestion = scope.suggestion?scope.suggestion:"";
			return;
		}
		$("#suggestion").parent().parent().addClass('open');
		scope.suggestion = scope.suggestion?scope.suggestion:"";
		scope.filteredFoods = scope.foods.filter(function(item){
			return item.foodName.toLowerCase().startsWith(scope.suggestion.toLowerCase());
		})
    }, true);
	scope.emptySuggestion = function(){
		scope.suggestion = ""; $("#suggestion").focus();
	}
	scope.focusSuggestion = function(){
		$("#suggestion").focus();$("#suggestion").parent().parent().addClass('open');
	}
	scope.blurSuggestion = function(){
		setTimeout(function(){
			$("#suggestion").parent().parent().removeClass('open');
		}, 200)
	}
	scope.$watch('currentModel.foods', function() {
		if(scope.currentModel.foods == undefined){
			scope.currentModel.foods = []; scope.currentModel.totalMoney = 0;
			return;
		}
		var money = 0;
		for(var index in scope.currentModel.foods)
		{
			money = money + scope.currentModel.foods[index].price;
		}
		scope.currentModel.totalMoney = money;
    }, true);
}

app.controller('reportController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "reports";
	scope.singularName = "report";
	
	defineBaseFunction(scope, http);
	
	scope.revenueBranchModel = {};
	scope.orderBranchModel = {};
	scope.foodBranchModel = {};
	scope.orderCustomerModel = {};
	
	scope.get("branches", function(data){
		scope.branches = data;
		scope.revenueBranchModel.branch = scope.branches[0];
		scope.orderBranchModel.branch = scope.branches[0];
		scope.foodBranchModel.branch = scope.branches[0];
	});	
	scope.get("accounts", function(data){
		scope.accounts = data.filter(function(item) {return item.roles[0].roleName =="ROLE_MEMBER"});
		scope.orderCustomerModel.account = scope.accounts[0];
	});	
});


