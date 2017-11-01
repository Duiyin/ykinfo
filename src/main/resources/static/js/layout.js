$(function(){
	/** Top Search**/
	$('body').on('click', '#top-search', function(e){
        e.preventDefault();
        $('#header').addClass('search-toggled');
        $('#top-search-wrap input').focus();
    });
    $('body').on('click', '#top-search-close', function(e){
        e.preventDefault();
        $('#header').removeClass('search-toggled');
    });
    $("#notifications_right").on('click', function(e){
    	e = e||event;
    	$(this).toggleClass("open");
    	$(".dropdown-menu-lg").on("click", function(e){e.stopPropagation();})
    	e.stopPropagation();
    	$(document).one('click', function(e){
	    	$("#notifications_right").removeClass("open");
	    	e.stopPropagation();
	    });
    });
});

