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
	
	//close 
	$('.close').on("click",function(){
		$(".auth").hide();
		$(".grey").hide();
	})
	
	//opening submit
	$("#openSubmit").on("click",function(){
		var c_name = $.trim($(".u_name").val()),
			c_id = $.trim($("c_id").val()),
			c_pwd1 = $.trim($("c_pwd1").val()),
			c_pwd2 = $.trim($("c_pwd2").val()),
			v_name = ifEmpty(".c_name"),
			v_id = ifEmpty(".c_id"),
			v_pwd1 = ifEmpty(".c_pwd1"),
			v_pwd2 = ifEmpty(".c_pwd2");
		
		
		if(v_name){
			$(".err_name1").text("");
			if(v_id){
				if(validate_id(c_id)){
					$(".err_num1").text("");
					if(v_pwd1){
						$(".err_pwd1").text("");
						if(v_pwd2){
							if(c_pwd1 == c_pwd2){
								$(".err_pwd2").text("");
								
								//ajax将数据传到后台
								$.ajax({
									url: "",
									data:{
										"username": username,
										"password": password
									},
									dataType: "json",
									type: "POST",
									success: function(data){
										if(data.success == 1){
											
										}else{
											$(".err_pwd").text(data.msg);
										}
									}
								})
								
							}else{
								$(".err_pwd2").text("Two passwords entered differently!");
								return false;
							}
							
						}else{
							$(".err_pwd1").text("Please Re-input Saving Account PIN");
							return false;
						}
					}else{
						$(".err_pwd1").text("Please input Saving Account PIN");
						return false;
					}
				}else{
					$(".err_num1").text("Require number only!");
					return false;
				}
			}else{
				$(".err_num1").text("Please input Customer’s ID Number");
				return false;
			}
		}else{
			$(".err_name").text("Please input Customer's name");
			return false;
		}
		
		
	})
	
	// 判断输入框是否为空的函数
    function ifEmpty(clsName) {
     var username = $.trim($(clsName).val());
     if(username==""||username==null) {
      return false;
     }
      return true;
    }
	
	//验证数字
	function validate_id(id){
		var pattern = new RegExp(/^[0-9]*$/);
		return pattern.test(id);
	}
	//按下enter键的时候，完成相关事件
    function getEnter(frame,btn){
      $(frame).on('keydown',function(event){
        var e=event||window.event|| arguments.callee.caller.arguments[0];
        var keycode=e.which||e.keyCode; 
        if(keycode==13){
          $(btn).click();
          e.preventDefault(); 
        }
      })
    }
    
})();