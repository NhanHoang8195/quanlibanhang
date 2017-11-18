$(document).ready(function(c) {
    var url = window.location; 
    var element = $('ul.side-nav a').filter(function() {
    	return this.href == url || (url.href.indexOf(this.href) == 0 && this.pathname != '/') ; 
    }).parent().addClass('active');
	
    if (element.is('li')) { 
         element.addClass('active').parent().parent('li').addClass('active')
     }
});

var app = angular.module('myApp', []);

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
}

app.controller('foodController', function($scope, $http) {
	var scope = $scope; var http = $http;
	scope.name = "foods"
	defineBaseFunction(scope, http);
	
	scope.get();
});

