/**
 * Implement saving account opening,inquiry,withdrawal,deposit functions
 */

(function(){
	$("#A_open").on("click",function(){
		closeFns(".openAccount","#A_open","opening");
	})
	$("#A_inquiry").on("click",function(){
		closeFns(".inquiryAccount","#A_inquiry","inquiry");
	})
	$("#A_withdrawal").on("click",function(){
		closeFns(".withdrawalAccount","#A_withdrawal","withdrawal");
	})
	$("#A_deposit").on("click",function(){
		closeFns(".depositAccount","#A_deposit","deposit");
	})
	function closeFns(target,Ele,name){
		$(target).fadeIn(300).siblings().hide();
		$(Ele).addClass("target").siblings().removeClass("target");
		$(".keyword").text(name);
	}
})();