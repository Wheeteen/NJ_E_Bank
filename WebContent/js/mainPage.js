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
		$(".auth_code").val("");
	})
	
	//opening submit
	$("#openSubmit").on("click",function(){
		var c_name = $.trim($(".c_name1").val()),
			c_id = $.trim($(".c_id1").val()),
			c_pwd1 = $.trim($(".c_pwd1").val()),
			c_pwd2 = $.trim($(".c_pwd2").val()),
			v_name = ifEmpty(c_name),
			v_id = ifEmpty(c_id),
			v_pwd1 = ifEmpty(c_pwd1),
			v_pwd2 = ifEmpty(c_pwd2);
		
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
									url: "../OpenServlet",
									data:{
										"username": c_name,
										"userid": c_id,
										"PIN2": c_pwd1
									},
									dataType: "json",
									type: "POST",
									success: function(data){
										console.log(data);
										if(data.success == 1){
											$(".info h3").text("You have opened the saving account successfully");
											var html = "";
											html+="<p><label>Customer’s Name:</label><span>"+c_name+"</span></p>"
												+"<p><label>Saving account number:</label><span>"+data.accountNum+"</span></p>"
												+"<p><label>Card number:</label><span>+"+data.cardNum+"</span></p>";
											$(".info .detail").append(html);
											$(".openAccount").hide();
											$(".info").fadeIn(300);
												
										}else{
											$(".err_num1").text(data.msg);
										}
									}
								})
								
							}else{
								$(".err_pwd2").text("Two passwords entered differently!");
								return false;
							}
							
						}else{
							$(".err_pwd2").text("Please Re-input Saving Account PIN");
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
			$(".err_name1").text("Please input Customer's name");
			return false;
		}
		
		
	})
	
	//inquiry submit
	$("#inquirySubmit").on("click",function(){
		var c_id = $.trim($(".c_id2").val()),
			v_id = ifEmpty(c_id);
		
		if(v_id){
			if(validate_id(c_id)){
				$(".err_num2").text("");
				
				//ajax将数据传到后台
				$.ajax({
					url: "",
					data:{
//						"username": username,
//						"password": password
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
				$(".err_num2").text("Require number only!");
				return false;
			}
		}else{
			$(".err_num2").text("Please input Customer’s ID Number");
			return false;
		}
	})
	
	//withdrawal: arr 用来存储是否能提交数据了
	
	var error = {
		"userNum": 1,
		"withAmount": 1
	}
	//deposit： errorArr
	var error1 = {
		"userNum": 1,
		"withAmount": 1
	}

	//use this variable to judge which one is chosen:withdrawal/deposit
	var statusWD;
	//withdrawal输入用户名的时候：当离开的时候,验证是否存在这个用户的user account number
	$(".userNum1").on("blur",function(){
		blurUserNum(".userNum1",".err_userNum1","",error);
	})
	
	//deposit输入用户名的时候：当离开的时候,验证是否存在这个用户的user account number
	$(".userNum2").on("blur",function(){
		blurUserNum(".userNum2",".err_userNum2","",error1);//将一个存在的数存进去
	})
	
	//将input user account number的blur封装成一个函数
	function blurUserNum(target,err_name,url,err){
		var userNum = $.trim($(target).val()),
			v_UNum = ifEmpty(userNum);
		if(v_UNum){
			if(validate_id(userNum)){
				$(err_name).text("");
				
				//ajax将数据传到后台
				$.ajax({
					url:url,
					data:{
//							"username": userNum,
					},
					dataType: "json",
					type: "POST",
					success: function(data){
						if(data.success == 1){
							err["userNum"] = 0;
						}else{
							$(err_name).text(data.msg);
							err["userNum"]= 1;
							return false;
						}
					}
				})
			}else{
				$(err_name).text("Require number only!");
				err["userNum"] = 1;
				return false;
			}
		}else{
			$(err_name).text("Please input User Account Number");
			err["userNum"] = 1;
			return false;
		}
	}
	
	
	//withdrawal输入金额的时候：当离开的时候,验证这个数字是否>50000
	$(".withAmount1").on("blur",function(){
		blurAmount(".withAmount1",".err_amount1",error,"Withdrawal");
	})
	
	//deposit输入金额的时候：当离开的时候,验证这个数字是否>50000
	$(".dsitAmount1").on("blur",function(){
		blurAmount(".dsitAmount1",".err_amount2",error1,"Deposit");
	})
	
	//将input withdrawal amount封装成一个函数
	function blurAmount(target,error_name,err,keyword){
		var amount = $.trim($(target).val()),
			v_amount = ifEmpty(amount);
		
		if(v_amount){
			if(validate_id(amount)){
				$(error_name).text("");
				if(amount<50000){
					err["withAmount"] = 0;
					return true;
				}else{
					err = 1;
					$(".grey").show();
					$(".auth").fadeIn(300);
					if(target == ".withAmount1"){
						statusWD = "withdrawal";
					}else{
						statusWD = "deposit";
					}
					return false;
				}
			}else{
				$(error_name).text("Require number only!");
				err["withAmount"] = 1;
				return false;
			}
		}else{
			$(error_name).text("Please input "+ keyword+" Amount");
			err["withAmount"] = 1;
			return false;
		}
	
	}
	//withdrawal  submit
	$("#withdrawalSubmit").on("click",function(){
		WDSubmit(error,".userNum1",".withAmount1","");
	})
	
	//deposit submit
	$("#depositSubmit").on("click",function(){
		WDSubmit(error1,".userNum2",".dsitAmount1","");
	})
	
	//withdrawal and deposit submit -->function
	function WDSubmit(err,userNum,Amount,url){
		if(err["userNum"] == 1){
			return false;
		}else if(err["withAmount"] == 1){
			return false;
		}else{
			var userNum = $.trim($(userNum).val()),
				amount = $.trim($(Amount).val());
			
			//ajax将数据传到后台
			$.ajax({
				url: url,
				data:{
					"userNum": userNum,
					"amount": amount
				},
				dataType: "json",
				type: "POST",
				success: function(data){
					if(data.success == 1){
						
					}else{
						if(userNum == ".userNum1" ){
							$(".err_amount1").text(data.msg);
						}else{
							$(".err_amount2").text(data.msg);
						}
						return false;
					}
				}
			})
		}
	}
	//authentication code submit
	$("#authSubmit").on("click",function(){
		var code = $.trim($(".auth_code").val()),
			v_code = ifEmpty(code);
		
		if(v_code){
			$(".err_auth").text("");
			
			//ajax将数据传到后台
			$.ajax({
				url: "",
				data:{
//					"username": username,
				},
				dataType: "json",
				type: "POST",
				success: function(data){
					if(data.success == 1){
						if(statusWD == "withdrawal"){
							error.withAmount = 0;
							$("#withdrawalSubmit").click();
						}else{
							error1.withAmount = 0;
							$("#depositSubmit").click();
						}
						$(".grey").hide();
						$(".auth").fadeOut(300);
						$(".auth_code").val("");
					}else{
//						$(".err_auth").text(data.msg);
						return false;
					}
				}
			})
		}else{
			$(".err_auth").text("Please input authentication code");
			return false;
		}
	})
	// 判断输入框是否为空的函数
    function ifEmpty(clsName) {
     if(clsName==""||clsName==null) {
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
    
    //opening  form enter
    getEnter(".openAccountForm","#openSubmit");
    //inquiry  form enter
    getEnter(".inquiryAccountForm","#inquirySubmit");
    
    //auth form enter
    getEnter(".authForm","#authSubmit");
})();