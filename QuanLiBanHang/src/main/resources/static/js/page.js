$(document).ready(function() {
    var url = window.location; 
    var element = $('ul.megamenu a').filter(function() {
    	return this.href == url || (url.href.indexOf(this.href) == 0 && this.pathname != '/') ; 
    }).parent().addClass('active');
    
    if (element.is('li')) { 
         element.addClass('active').parent().parent('li').addClass('active')
     }
});