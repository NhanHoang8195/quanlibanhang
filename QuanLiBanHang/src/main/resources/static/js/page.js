$(document).ready(function() {
    var url = window.location; 
    var element = $('ul.megamenu a').filter(function() {
    	return this.href == url; 
    }).parent().addClass('active');
    
    if (element.is('li')) { 
         element.addClass('active').parent().parent('li').addClass('active')
     }
});