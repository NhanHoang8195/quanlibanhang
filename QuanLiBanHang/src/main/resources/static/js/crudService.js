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
	    		scope.getExtendProperties(models[i]);
	    	}
	    	
	        scope.models = models;
	    });
	}
	
	scope.post = function()
	{
		http.post(url, scope.currentModel)
		.then(function(response) {
			var model = getModelFromReponse(response);
			scope.putExtendProperties(model);
			
			scope.get();
		})
	}
	scope.getExtendProperties = function(model){
		var extendProperties = model._links;
		for(var propertyName in extendProperties) {
			if(propertyName == "self" || extendProperties[propertyName].href == extendProperties["self"].href){
				continue;
			}
			callAjax(propertyName);
		}
		function callAjax(propertyName){
			var tempPropertyName = propertyName;
			var getPropertyUrl = extendProperties[tempPropertyName].href;
			http.get(getPropertyUrl)
			.then(function(response) {
				model[tempPropertyName] = getModelFromReponse(response);
			}, function(response){})
		}
	}
	scope.putExtendProperties = function(model){
		var extendProperties = model._links;
		for(var propertyName in extendProperties) {
			if(scope.currentModel[propertyName] == undefined 
				|| scope.currentModel[propertyName]._links == undefined){
				continue;
			}
			
			var putPropertyUrl = extendProperties[propertyName].href;
			http.put(putPropertyUrl, scope.currentModel[propertyName]._links.self.href, configForAssociation)
			.then(function(response) {
				
			})
		}
	}
	
	scope.put = function(){
		if(scope.currentModel._links == undefined){
			scope.post();
			return;
		}
		
		var putUrl = scope.currentModel._links.self.href;
		
		
		http.put(putUrl, scope.currentModel)
		.then(function(response) {
			scope.putExtendProperties(scope.currentModel);
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

function getModelFromReponse(response){
	var embedded = response.data._embedded;
	if(embedded != undefined){
		for(var propertyName in embedded) {
			return embedded[propertyName];
		}
	}
	return response.data;
}


