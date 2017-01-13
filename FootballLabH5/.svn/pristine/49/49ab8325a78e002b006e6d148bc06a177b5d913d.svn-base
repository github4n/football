function touchSlider() {
	$(".subNav-t").click(function() {
		var s = $(this).find("span");
		var index = $(this).index();
		if (index == 0) {
			owl.trigger('owl.prev');
		} else {
			owl.trigger('owl.next');
		}
		$(this).parent().find("span").removeClass("se");
		$(this).find("span").addClass("se");
	});
}
var owl = $("#owl-demo").owlCarousel({
	slideSpeed : 300,
	paginationSpeed : 400,
	singleItem : true,
	pagination : false,
	lazyLoad : true,
	afterMove : function() {
		$(".subNav-t span").removeClass("se");
		var s = this.playDirection;
		if (s == "next") {
			$("#btn-next span").addClass("se");
		} else {
			$("#btn-prev span").addClass("se");
		}
	}
});


