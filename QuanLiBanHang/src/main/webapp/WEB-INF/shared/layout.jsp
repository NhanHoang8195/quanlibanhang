<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en">
  <head>
		<title>Buy_shop an E-Commerce online Shopping Category Flat Bootstarp responsive Website Template| Home :: w3layouts</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="Buy_shop Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
		Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<link href="/css/bootstrap.css" rel='stylesheet' type='text/css' />
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<!-- Custom Theme files -->
		<link href="/css/style.css" rel='stylesheet' type='text/css' />
		<script src="/js/simpleCart.min.js"> </script>
		<!-- Custom Theme files -->
		<!--webfont-->
		<link href='http://fonts.googleapis.com/css?family=Lato:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
		<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
		<!-- start menu -->
		<script src="/js/jquery.easydropdown.js"></script>
		<link href="/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
		<script type="text/javascript" src="/js/megamenu.js"></script>
		<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
		<link rel="stylesheet" href="/css/etalage.css">
		<script src="/js/jquery.etalage.min.js"></script>
		<script>
					jQuery(document).ready(function($){
		
						$('#etalage').etalage({
							thumb_image_width: 300,
							thumb_image_height: 400,
							source_image_width: 900,
							source_image_height: 1200,
							show_hint: true,
							click_callback: function(image_anchor, instance_id){
								alert('Callback example:\nYou clicked on an image with the anchor: "'+image_anchor+'"\n(in Etalage instance: "'+instance_id+'")');
							}
						});
		
					});
				</script>
		<!--initiate accordion-->
		<script type="text/javascript">
	$(function() {
	
	    var menu_ul = $('.menu_drop > li > ul'),
	           menu_a  = $('.menu_drop > li > a');
	    
	    menu_ul.hide();
	
	    menu_a.click(function(e) {
	        e.preventDefault();
	        if(!$(this).hasClass('active')) {
	            menu_a.removeClass('active');
	            menu_ul.filter(':visible').slideUp('normal');
	            $(this).addClass('active').next().stop(true,true).slideDown('normal');
	        } else {
	            $(this).removeClass('active');
	            $(this).next().stop(true,true).slideUp('normal');
	        }
	    });
	
	});
</script>
	</head>
  <body>
	<tiles:insertAttribute name="header" />
	
	<tiles:insertAttribute name="content" />
	
    <tiles:insertAttribute name="footer" />
   
  </body>
</html>