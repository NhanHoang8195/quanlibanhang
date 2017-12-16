function crudFunction(scope, http)
{
	http.defaults.headers.post["X-CSRF-TOKEN"] = $("#csrf")[0].value;
    http.defaults.headers.put["X-CSRF-TOKEN"] = $("#csrf")[0].value;
	http.defaults.headers.common["X-CSRF-TOKEN"] = $("#csrf")[0].value;
	
	$injector = angular.injector(['ng']);
	q = $injector.get('$q');
	deferred = q.defer();
	
	var url = "/"+ scope.name + "/"; 
	var configForAssociation = {
		headers:{
			'Content-Type': 'text/uri-list'
	    }
	};
	
	scope.get = function(name, callback)
	{
		scope.currentModel = {};
		var tempName = name == undefined ? scope.name:name;
		var tempUrl = "/"+ tempName + "/"; 
		http.get(tempUrl)
	    .then(function(response) {
	    	var models = getModelFromReponse(response);
	    	
	    	if(callback != undefined) {
	    		callback(models);
	    		return;
	    	}
	    	for(var i=0;i<models.length;i++){
	    		scope.getExtendedProperties(models[i], function(){});
	    	}
	    	
	        scope.models = models;
	    });
	}
	
	scope.post = function()
	{
		http.post(url, removeExtendedProperties(scope.currentModel))
		.then(function(response) {
			var model = getModelFromReponse(response);
			scope.putExtendedProperties(model, function(){
				scope.get();
			});
		})
	}
	scope.getExtendedProperties = function(model, callback){
		var ExtendedProperties = model._links;
		var self = this; self.count = 0; self.callback = callback; self.tempCount = 0;
		
		for(var propertyName in ExtendedProperties) {
			if(propertyName == "self"){
				model[propertyName] = ExtendedProperties[propertyName].href.split("/").pop();
				continue;
			}
			if(ExtendedProperties[propertyName].href == ExtendedProperties["self"].href){
				continue;
			}
			callAjax(propertyName);
		}
		function callAjax(propertyName){
			var tempPropertyName = propertyName;
			var getPropertyUrl = ExtendedProperties[tempPropertyName].href;
			
			self.count++;
			http.get(getPropertyUrl)
			.then(function(response) {
				model[tempPropertyName] = getModelFromReponse(response);
				tempCallback(self);
			}, function(response){tempCallback(self);})
		}
	}
	scope.putExtendedProperties = function(model, callback){
		var ExtendedProperties = model._links;
		var self = this; self.count = 0; self.callback = callback; self.tempCount = 0;
		
		for(var propertyName in ExtendedProperties) {
			if(scope.currentModel[propertyName] == undefined || propertyName == "self"){
				continue;
			}
			
			var putPropertyUrl = ExtendedProperties[propertyName].href;
			
			if(scope.currentModel[propertyName]._links == undefined){
				if(scope.currentModel[propertyName].length == undefined){
					continue;
				}
				
				var arrayModel = scope.currentModel[propertyName];
				var associationLinks = "";
				for(var i=0;i<arrayModel.length;i++){
					associationLinks = associationLinks + arrayModel[i]._links.self.href + "\n";
		    	}
				
				self.count++;
				http.put(putPropertyUrl, associationLinks, configForAssociation)
				.then(function(response) {tempCallback(self);}, function(response) {tempCallback(self);});
				continue;
			}
			
			self.count++;
			http.put(putPropertyUrl, scope.currentModel[propertyName]._links.self.href, configForAssociation)
			.then(function(response) {tempCallback(self);}, function(response) {tempCallback(self);})
		}
	}
	
	scope.put = function(){
		if(scope.currentModel._links == undefined){
			scope.post();
			return;
		}
		
		var putUrl = scope.currentModel._links.self.href;
		
		http.put(putUrl, removeExtendedProperties(scope.currentModel))
		.then(function(response) {
			scope.putExtendedProperties(scope.currentModel, function(){
				scope.get();
			});
		})
	}
	scope.deleteExtendedProperties = function(model, callback){
		var ExtendedProperties = model._links;
		var self = this; self.count = 0; self.callback = callback; self.tempCount = 0;
		
		for(var propertyName in ExtendedProperties) {
			if(propertyName == "self" || ExtendedProperties[propertyName].href == ExtendedProperties["self"].href){
				continue;
			}
			
			callAjax(propertyName);
		}
		function callAjax(propertyName){
			self.count++;
			http.delete(ExtendedProperties[propertyName].href)
			.then(function(response) {
				tempCallback(self);
			}, function(rejection) {
				if(scope.currentModel[propertyName]._links == undefined){
					if(scope.currentModel[propertyName].length == undefined){
						return;
					}
					
					var arrayModel = scope.currentModel[propertyName];
					for(var i=0;i<arrayModel.length;i++){
						if(scope.name != undefined){
							self.count++;
							var tempName = arrayModel[i]._links[scope.name]?scope.name:scope.singularName;
							http.delete(arrayModel[i]._links[tempName].href + "/" + model.self)
							.then(function(response) {tempCallback(self);}, function(response) {tempCallback(self);});
						}
			    	}
				}
				
				tempCallback(self);
			});
		}
	}
	scope.delete = function(){
		scope.deleteExtendedProperties(scope.currentModel, function(){
			http.delete(scope.currentModel._links.self.href)
			.then(function(response) {
				scope.get();
			})
		})
	}
}

function getModelFromReponse(response){
	var embedded = response.data._embedded;
	if(embedded != undefined){
		for(var propertyName in embedded) {
			return embedded[propertyName];
		}
	}
	return response.data;
}
function removeExtendedProperties(model){
	var newModel = JSON.parse(JSON.stringify(model));
	for(var propertyName in model) {
		if(newModel[propertyName] instanceof Array || typeof newModel[propertyName] === 'object')
			newModel[propertyName] = undefined;
	}
	for(var propertyName in model._links) {
		newModel[propertyName] = undefined;
	}
	return newModel;
}
function tempCallback(self){
	self.tempCount++;
	if(self.tempCount == self.count){
		self.callback();
	}
}
