(function($){
	$("#tableList .listCell li").click(function(){	 
		if($(this).hasClass('active')){
			$(this).removeClass('active');
		}else{
			$("#tableList .listCell li").removeClass('active');
			$(this).addClass('active');			
		}
		var size=$("#tableList .listCell li.active").size();

		toggleSelet(size);	
		if(toggleSelet(size)){
			$("#js-submit").addClass('bet-active').removeAttr('disabled');
			bindSelect();
		}else{
			$("#js-submit").removeClass('bet-active').attr('disabled',true);
		}
		compute();
	})
		
	getVal();
	
	
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


function toggleSelet(size){
	if(size){
		$("#toggleBox").css('display','block');
		return true;
	}else{
		$("#toggleBox").css('display','none');
		return false;
	}
	
}

function getVal(){
	$("#mySelect .selectVal i").html($('.defaultVal i').html())		
}

function compute(){
	var rate=parseFloat($("#tableList .listCell li.active").find('.scaleNum').html()).toFixed(2);
	var intergal=parseFloat($('#mySelect .selectVal i').html()).toFixed(2);
	$("#intergalNum").html(parseInt(rate*100)*intergal/100);		
}
