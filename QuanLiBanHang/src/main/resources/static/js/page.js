$(document).ready(function(c) {
    var url = window.location; 
    var element = $('ul.megamenu a').filter(function() {
    	return this.href == url || (url.href.indexOf(this.href) == 0 && this.pathname != '/') ; 
    }).parent().addClass('active');
	
    if (element.is('li')) { 
         element.addClass('active').parent().parent('li').addClass('active')
     }
    
    
    UpdateCartUI();
});
cart = {};

function addToCart(element)
{
	var parent = element.parentElement.parentElement.parentElement;
	var item = {};
	item.price = parseInt(parent.getElementsByClassName("item_price")[0].innerText.replace(' VND', ''));
	item.foodName = parent.getElementsByClassName("item_name")[0].innerText;
	item.image = parent.getElementsByClassName("item_image")[0].src;
	item.foodId = parent.getElementsByClassName("item_id")[0].innerText;
	item.quantity = 1;
	item.total = item.price;
	
	UpdateCartObject(item);
	UpdateCartUI();
}
function CalculateCartTotal()
{
	cart.total = 0;
	var count = cart.items.length;
	for(i=0;i<count;i++){
		cart.total = cart.total + cart.items[i].total;
	}
}
function UpdateCartObject(item)
{
	InitCart();
	
	var count = cart.items.length;
	var isExist = false;
	for(i=0;i<count;i++){
		var loopItem = cart.items[i];
		if(loopItem.foodName == item.foodName){
			isExist = true;
			loopItem.quantity = loopItem.quantity + 1;
			loopItem.total = loopItem.price*loopItem.quantity;
		} 
	}
	if(!isExist){
		cart.items.push(item);
	}
	
	localStorageAPI.setObject("cart", cart);
}
function UpdateCartUI()
{
	InitCart();
	CalculateCartTotal();
	document.getElementsByClassName("cart_total")[0].innerText = cart.total;
	document.getElementsByClassName("cart_quantity")[0].innerText = cart.items.length;
}
function RemoveCart()
{
	localStorageAPI.removeItem("cart");
	
	UpdateCartUI();
}
function InitCart()
{
	var tempCart = localStorageAPI.getObject("cart");
	if(tempCart != null){
		cart.items = tempCart.items;
		return;
	}
	cart.items = [];
}
function RemoveItem(index){
	cart.items.splice(index, 1);
	localStorageAPI.setObject("cart", cart);
	UpdateCartUI();
}
var localStorageAPI = {
		 
	    // This method may not be needed as we go along
	    // the support is becoming better and better day-by-day
	    // http://caniuse.com/#feat=namevalue-storage
	 
	    // better to be safe than sorry or get script errors :|
	    isSupported: function() {
	        return window.localStorage;
	    },
	 
	    setItem: function(key, value) {
	        return localStorage.setItem(key, value);
	    },
	 
	    getItem: function(key) {
	        return localStorage.getItem(key);
	    },
	 
	    // If do not want to build a wrapper like how I did here but implement 
	    // setObject() and getObject(), you can create prototype methods on  
	    // Storage
	 
	    // Storing Objects in HTML5 localStorage : http://stackoverflow.com/a/3146971/1015046 
	 
	    setObject: function(key, object) {
	        return localStorage.setItem(key, JSON.stringify(object));
	    },
	 
	    getObject: function(key) {
	        return JSON.parse(localStorage.getItem(key));
	    },
	 
	    removeItem: function(key) {
	        return localStorage.removeItem(key);
	    },
	 
	    clearAll: function() {
	        return localStorage.clear();
	    }
	 
};


InitCart();
