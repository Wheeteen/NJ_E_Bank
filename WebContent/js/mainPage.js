/**
 * Implement saving account opening,inquiry,withdrawal,deposit functions
 */

(function(){
	$("#A_open").on("click",function(){
		closeFns(".openAccount",'.inquiryAccount ,.withdrawalAccount ,.depositAccount');
	})
	$("#A_inquiry").on("click",function(){
		closeFns(".inquiryAccount",'.openAccount ,.withdrawalAccount ,.depositAccount');
	})
	$("#A_withdrawal").on("click",function(){
		closeFns(".withdrawalAccount",'.inquiryAccount ,.openAccount ,.depositAccount');
	})
	$("#A_deposit").on("click",function(){
		closeFns(".depositAccount",'.inquiryAccount ,.withdrawalAccount ,.openAccount');
	})
	function closeFns(target,otherEle){
		$(target).fadeIn(300);
		$(otherEle).hide();
	}
})();