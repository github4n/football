(function($){
	var val=$("#ul_grid").find('.checkBox.active').children('i').html();
	$("#ul_grid").children('li').click(function(){
		if($(this).find('.checkBox').hasClass('active')){
			$(this).find('.checkBox').removeClass('active');
		}else{
			$("#ul_grid").find('.checkBox').removeClass('active');
			$(this).find('.checkBox').addClass('active');			
		}	
		var size=$("#ul_grid").find('.checkBox.active').size();
		var curVal=$("#ul_grid").find('.checkBox.active').children('i').html();

		toggleType(curVal);
	})
	toggleType(val)
})($)
function toggleType(val){
	if(val){
		$("#otherAmount").val(val);

	}else{
		$("#otherAmount").val('');
	}
	
}