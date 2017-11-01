$(function(){
	/** Top Search**/
    $(document).on('click', '#top-search', function(e){
    	// console.log("aa");
        e.preventDefault();
        $('#header').addClass('search-toggled');
        $('#top-search-wrap input').focus();
        $('#top-search-wrap input').on('click', function(e){e.stopPropagation();});
        $('body').one('click', function(){
            $('#header').removeClass('search-toggled');
            e.preventDefault();
        });
    });
    $(document).on('click', '#top-search-close', function(e){
        e.preventDefault();
        $('#header').removeClass('search-toggled');
    });

    /** Profile Menu **/
    $('body').on('click', '.profile-menu > a', function(e){
        e.preventDefault();
        $(this).parent().toggleClass('toggled');
        $(this).next().slideToggle(200);
    });

    // top chat
    $(document).on('click', '#chat_btn', function(e){
        e = e||event;
        $('#chat').toggleClass('toggled');
        $('#chat').on('click', function(e){e.stopPropagation();});
        $('body').one('click', function(e){
            $('#chat').removeClass('toggled');
            e.preventDefault();
        });
    });

    // top-menu background
    $(document).on('click', '#top-menu .mdl-navigation__link', function(e){
        e = e||event;
        $(this).toggleClass('open');
        e.stopPropagation();
        $('.dropdown-menu-lg').on('click', function(e){e.stopPropagation();});
        $('body').one('click', function(e){
            $('#top-menu .mdl-navigation__link').removeClass('open');
            $('.wrapper .mdl-menu__container').removeClass('is-visible');
            e.stopPropagation();
        });
    });
});