(function($){
	var payIntgal=parseInt($('#payIntgal').html());
	var curIntgal=parseInt($('#curIntgal').html());
	$('#diffIntgal').html(payIntgal-curIntgal);	
	var diffIntgal=$('#diffIntgal').html();
	compute(diffIntgal);
})($)

function compute(num){
	if(num){	
		$("#payMoney").html(num/100)
	}
}	