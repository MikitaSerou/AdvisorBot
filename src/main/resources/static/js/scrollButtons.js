
$(function() {
    var $elem = $('#content');

    $('#nav_up').fadeIn('slow');

    $(window).bind('scrollstart', function(){
        $('#nav_up,#nav_down').stop().animate({'opacity':'0.2'});
    });
    $(window).bind('scrollstop', function(){
        $('#nav_up,#nav_down').stop().animate({'opacity':'1'});
    });

    $('#nav_up').click(
        function (e) {
            $('html, body').animate({scrollTop: '0px'}, 800);
        }
    );
});