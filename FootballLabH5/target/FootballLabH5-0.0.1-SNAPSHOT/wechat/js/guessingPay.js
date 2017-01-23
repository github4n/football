(function($){
	bindSelect();
	getVal();
	compute();	
	/*$("#js-submit").click(function(){
		confirm_error1("跟投成功！",function(){},function(){},"查看详情","继续跟投");
	})*/
	$("#checkOption input").change(function(){
		checkOption(this);
		compute();	
	})
})($)

function checkOption(obj){
	$("#checkOption label").removeClass('active');
	if($(obj).is(':checked')){
		$(obj).parent('label').addClass('active');
	}
}

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
	var rate=parseFloat($("#checkOption input:checked").attr('data-val')).toFixed(2);
	var intergal=parseFloat($('#mySelect .selectVal i').html()).toFixed(2);
	$("#intergalNum").html(parseInt(rate*100)*intergal/100);
	
}
