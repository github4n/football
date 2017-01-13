jQuery(document).ready(function($){
    //打开窗口
    $('.btn_see').on('click', function(event){
        event.preventDefault();
        $('.cd-popup').addClass('is-visible');
        //$(".dialog-addquxiao").hide()
    });
    //关闭窗口
    $('.cd-popup').on('click', function(event){
        if( $(event.target).is('.cd-popup') ) {
            event.preventDefault();
            $(this).removeClass('is-visible');
        }
    });
});