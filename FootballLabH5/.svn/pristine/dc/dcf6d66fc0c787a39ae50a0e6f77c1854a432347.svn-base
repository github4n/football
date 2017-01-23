(function($){
	bindSelect();
	getVal();
	compute();
//	$("#js-submit").click(function(){
//			confirm_error1("跟投成功！",function(){
//				location.href="followOrderHall.html?experts_code="+getQueryString("experts_code");
//			},function(){
//				location.href="myFollowOrder.html?experts_code="+getQueryString("experts_code");
//			},"查看详情","继续跟投")
//	})
})($)

function bindSelect(){
	$("#mySelect").unbind('click').click(function(e){
		e.stopPropagation();
		$(this).toggleClass('downIcon').children('.selectList').toggle();
	})

	$("#mySelect .selectList li").unbind('click').click(function(){
		$(this).addClass('defaultVal active').siblings('li').removeClass('defaultVal active')
		getVal();
		compute();
	})			
}

function getVal(){
	$("#mySelect .selectVal i").html($('.defaultVal i').html())		
}

function compute(){
	var rate=parseInt($("#interestRate").html())/100;
	var intergal=parseFloat($('#mySelect .selectVal i').html()).toFixed(2);
	$("#intergalNum").html((parseInt(rate*100)*intergal/100) + parseInt(intergal));
	
}
