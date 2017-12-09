function crudFunction(scope, http)
{
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
	    		scope.getExtendedProperties(models[i]);
	    	}
	    	
	        scope.models = models;
	    });
	}
	
	scope.post = function()
	{
		http.post(url, removeExtendedProperties(scope.currentModel))
		.then(function(response) {
			var model = getModelFromReponse(response);
			scope.putExtendedProperties(model);
			
			setTimeout(function(){
				scope.get();
			}, 1000);
		})
	}
	scope.getExtendedProperties = function(model){
		var ExtendedProperties = model._links;
		for(var propertyName in ExtendedProperties) {
			if(propertyName == "self" || ExtendedProperties[propertyName].href == ExtendedProperties["self"].href){
				continue;
			}
			callAjax(propertyName);
		}
		function callAjax(propertyName){
			var tempPropertyName = propertyName;
			var getPropertyUrl = ExtendedProperties[tempPropertyName].href;
			http.get(getPropertyUrl)
			.then(function(response) {
				model[tempPropertyName] = getModelFromReponse(response);
			}, function(response){})
		}
	}
	scope.putExtendedProperties = function(model){
		var ExtendedProperties = model._links;
		for(var propertyName in ExtendedProperties) {
			if(scope.currentModel[propertyName] == undefined){
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
				
				http.put(putPropertyUrl, associationLinks, configForAssociation)
				.then(function(response) {})
				continue;
			}
			
			http.put(putPropertyUrl, scope.currentModel[propertyName]._links.self.href, configForAssociation)
			.then(function(response) {})
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
			scope.putExtendedProperties(scope.currentModel);
			setTimeout(function(){
				scope.get();
			}, 1000);
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
	for(var propertyName in model._links) {
		newModel[propertyName] = undefined;
	}
	return newModel;
}

