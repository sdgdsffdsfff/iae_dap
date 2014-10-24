$("#tab_container li").click(function(){
	var num = $(this).index();
	$(this).addClass("cur").siblings("li").removeClass("cur");
	$("#frame_container div.iframe_div").eq(num).addClass("focus").siblings().removeClass("focus");
});