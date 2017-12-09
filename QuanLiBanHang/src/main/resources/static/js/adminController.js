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
	var url = "/"+ scope.name + "/"; 
	scope.setCurrentModel = function(index){
		scope.currentModel = JSON.parse(JSON.stringify(scope.models[index]));
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
	defineBaseFunction(scope, http);
	
	scope.get();
	scope.get("categories", function(data){
		scope.categories = data;
	});	
});
app.controller('branchController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "branches"
	defineBaseFunction(scope, http);
	
	scope.get();
});
app.controller('customerController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "accounts"
	defineBaseFunction(scope, http);
	
	scope.get();
});
app.controller('categoryController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "categories"
	defineBaseFunction(scope, http);
	
	scope.get();
});

