$(function(){
	
	$(".open").click(function(){
		
		$(".cw-seak").show();
		$(".tanchu").show();
		
	});	
	$(".cw-seak,.yinc").click(function(){
		
		$(".tanchu").hide();
		$(".cw-seak").hide();
		
		
	});	
	
});
$(function(){
	$(".guanbiD").click(function(){
		$(".tanchu").hide();
		$(".cw-seak").hide();
		
		$(".cw-seak1").show();
		$(".tanchu1").show();
	});
	
	$(".cw-seak1,.guanbiT").click(function(){
		
		$(".tanchu1").hide();
		$(".cw-seak1").hide();
		
		
	});	
});



$(function(){
	
	$(".open1").click(function(){
		$(".tanchu").hide();
		$(".cw-seak").hide();
		
		$(".cw-seak1").show();
		$(".tanchu1").show();
		
	});	
	$(".cw-seak1").click(function(){
		
		$(".tanchu1").hide();
		$(".cw-seak1").hide();
		
		
	});	
	
});

$(function(){
	
	$(".open2").click(function(){
		$(".cw-seak").hide();
		$(".cw-seak2").show();
		$(".tanchu2").show();
		
	});	
	$(".cw-seak2").click(function(){
		
		$(".tanchu2").hide();
		$(".cw-seak2").hide();
		
		
	});	
	
});


$(function(){
	
	$(".ctan").click(function(){
		$(".contentqwe,.ctan,.timeZtit").hide();
		$(".tancuC12345").show();
		
		
	});	
	$(".headerTou,.tiaoM,.header").click(function(){
		
		$(".tancuC12345").hide();
		$(".contentqwe,.ctan,.timeZtit").show();
		
		
	});	
	
});
