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
function defineBaseFunction(scope, http)
{
	scope.currentModel = {};
	var url = "/"+ scope.name + "/"; 
	scope.setCurrentModel = function(index){
		scope.currentModel = scope.models[index];
	};
	scope.emptyCurrentModel = function(){
		scope.currentModel = {};
	};
	scope.get = function()
	{
		http.get(url)
	    .then(function(response) {
	        scope.models = response.data._embedded[scope.name];
	    });
	}
	scope.post = function()
	{
		http.post(url, scope.currentModel)
		.then(function(response) {
			scope.currentModel = {};
			scope.get();
		})
	}
	scope.put = function(){
		if(scope.currentModel._links == undefined){
			scope.post();
			return;
		}
		var putUrl = scope.currentModel._links.self.href;
		http.put(putUrl, scope.currentModel)
		.then(function(response) {
			scope.currentModel = {};
			scope.get();
		})
	}
	scope.delete = function(index){
		http.delete(scope.currentModel._links.self.href, scope.currentModel)
		.then(function(response) {
			scope.currentModel = {};
			scope.get();
		})
	}
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
}

app.controller('foodController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "foods"
	defineBaseFunction(scope, http);
	
	scope.get();
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

