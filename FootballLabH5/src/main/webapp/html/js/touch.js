$(document).ready(function(){
	$(".main_image").touchSlider({
		flexible : true,
		/*speed : 200,
		btn_prev : $("#btn_prev"),
		btn_next : $("#btn_next"),*/
		paging : $(".flicking_con span"),
		counter : function (e){
			$(".flicking_con span").removeClass("on").eq(e.current-1).addClass("on");
		}
	});
});