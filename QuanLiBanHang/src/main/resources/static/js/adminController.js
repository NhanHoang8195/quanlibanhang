$(document).ready(function(c) {
    var url = window.location; 
    var element = $('ul.side-nav a').filter(function() {
    	return this.href == url || (url.href.indexOf(this.href) == 0 && this.pathname != '/') ; 
    }).parent().addClass('active');
	
    if (element.is('li')) { 
         element.addClass('active').parent().parent('li').addClass('active')
     }
});
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
});
app.controller('categoryController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "categories";
	scope.singularName = "category";
	
	defineBaseFunction(scope, http);
	
	scope.get();
});

