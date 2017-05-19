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
	$("#A_changePIN").on("click",function(){
		closeFns(".changePIN","#A_changePIN","Change PIN");
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
												+"<p><label>Card number:</label><span>"+data.cardNum+"</span></p>";
											$(".info .detail").html(html);
											$(".openAccount").hide();
											$(".info").fadeIn(300);
											$(".c_name1").val("");	
											$(".c_id1").val("");
											$(".c_pwd1").val("");
											$(".c_pwd2").val("");
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
	
	//先用一个全局变量将customerID装起来
	var customerID="";
	//inquiry submit
	$("#inquirySubmit").on("click",function(){
		var c_id = $.trim($(".c_id2").val()),
			v_id = ifEmpty(c_id);
		
		if(v_id){
			if(validate_id(c_id)){
				$(".err_num2").text("");
				customerID = c_id;
				//ajax将数据传到后台
				$.ajax({
					url: "../InquiryServlet",
					data:{
						"CustomerID": c_id,
					},
					dataType: "json",
					type: "POST",
					success: function(data){
						if(data.success == 1){
							console.log(data);
							$(".info h3").text("You have inquiried the saving account successfully");
							var html = "",closeBtn="";
							html+="<p><label>Customer’s Name:</label><span>"+data.name+"</span></p>"
								+"<p><label>Saving account number:</label><span>"+data.accountNum+"</span></p>"
								+"<p><label>Card number:</label><span>"+data.cardNum+"</span></p>"
								+"<p><label>Online Banking Account Status :</label><span>"+data.status+"</span></p>";
//							closeBtn = "<div class='login'><input type='button' value='Close Account' id='closeAccount'></div>";
							$(".info .detail").html(html);
//							$(".info").append(closeBtn);
							$(".inquiryAccount").hide();
							$(".info").fadeIn(300);	
							$(".c_id2").val("");
						}else{
							$(".err_num2").text(data.msg);
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
		blurUserNum(".userNum1",".err_userNum1",error);
	})
	
	//deposit输入用户名的时候：当离开的时候,验证是否存在这个用户的user account number
	$(".userNum2").on("blur",function(){
		blurUserNum(".userNum2",".err_userNum2",error1);//将一个存在的数存进去
	})
	
	//将input user account number的blur封装成一个函数
	function blurUserNum(target,err_name,err){
		var userNum = $.trim($(target).val()),
			v_UNum = ifEmpty(userNum);
		if(v_UNum){
			if(validate_id(userNum)){
				$(err_name).text("");
				
				//ajax将数据传到后台
				$.ajax({
					url:"../getCountNum",
					data:{
							"AccountNumber": userNum,
					},
					dataType: "json",
					type: "POST",
					success: function(data){
						console.log(data);
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
		WDSubmit(error,".userNum1",".withAmount1","../WithdrawServlet2");
	})
	
	//deposit submit
	$("#depositSubmit").on("click",function(){
		WDSubmit(error1,".userNum2",".dsitAmount1","../DepositServlet2");
	})
	
	//withdrawal and deposit submit -->function
	function WDSubmit(err,userNum,Amount,url){
		if(err["userNum"] == 1){
			return false;
		}else if(err["withAmount"] == 1){
			return false;
		}else{
			var userNum1 = $.trim($(userNum).val()),
				amount = $.trim($(Amount).val()),
				selected,
				errMsg,
				hideForm,
				addDed;
			
			if(userNum == ".userNum2" ){
				selected = "deposit";
				errMsg = ".err_amount2";
				hideForm = ".depositAccount";
				addDed = "added";
			}else{
				selected = "withdrawal";
				errMsg = ".err_amount1";
				hideForm = ".withdrawalAccount";
				addDed = "deducted";
			}
			//ajax将数据传到后台
			$.ajax({
				url: url,
				data:{
					"AccountNumber": userNum1,
					"Amount": amount
				},
				dataType: "json",
				type: "POST",
				success: function(data){
					if(data.success == 1){
						console.log(data);
						$(".info h3").text("The "+selected+" action is completed  and "+amount+"RMB is "+addDed+" to the balance");
						var html = "";
						html+="<p><label>User Account Number:</label><span>"+userNum1+"</span></p>"
							+"<p><label>Original balances:</label><span>"+data.oriBalance+"</span></p>"
							+"<p><label>Existing balances:</label><span>"+data.exiBalance+"</span></p>";
						$(".info .detail").html(html);
						$(hideForm).hide();
						$(".info").fadeIn(300);	
						$(userNum).val("");
						$(Amount).val("");
					}else{
						
						$(errMsg).text(data.msg);
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
				url: "../authCode",
				data:{
					"authCode": code,
				},
				dataType: "json",
				type: "POST",
				success: function(data){
					console.log(data);
					if(data.success == 1){
						if(statusWD == "withdrawal"){
							error.withAmount = 0;
							$("#withdrawalSubmit").click();
						}else if(statusWD == "deposit"){
							error1.withAmount = 0;
							$("#depositSubmit").click();
						}else{
							deleteUserInfo();
						}
						$(".grey").hide();
						$(".auth").fadeOut(300);
						$(".auth_code").val("");
					}else{
						$(".err_auth").text("The authentication code is wrong");
						return false;
					}
				}
			})
		}else{
			$(".err_auth").text("Please input authentication code");
			return false;
		}
	})
	
	//changePIN submit
	$("#changePINSubmit").on("click",function(){
		$.ajax({
			url: "../changePIN",
			data:{
				"cardNum": cardNum,
				"ID": id,
				"oldPIN":oldPIN,
				"newPIN":newPIN
			},
			dataType: "json",
			type: "POST",
			success: function(data){
				console.log(data);
				if(data.success == 1){
//					var balance = data.balance;
//					if(balance>0){
//						console.log("The account has outstanding balance and this account can’t be closed"+ balance);
//					}else{
//						statusWD="closeAnt";
//						$(".grey").show();
//						$(".auth").fadeIn(300);
//					}
				}else{
					console.log(data.msg);
					return false;
				}
			}
		})
	})
	//click close Account
	$("#closeAccount").on("click",function(){
		console.log(2);
		//ajax将数据传到后台
		$.ajax({
			url: "../InquiryServlet",
			data:{
				"CustomerID": customerID,
			},
			dataType: "json",
			type: "POST",
			success: function(data){
				console.log(data);
				if(data.success == 1){
					var balance = data.balance;
					if(balance>0){
						console.log("The account has outstanding balance and this account can’t be closed"+ balance);
					}else{
						statusWD="closeAnt";
						$(".grey").show();
						$(".auth").fadeIn(300);
					}
				}else{
					console.log(data.msg);
					return false;
				}
			}
		})
	})
	
	//delete user info
	function deleteUserInfo(){
		//ajax将数据传到后台
		$.ajax({
			url: "../deleteUserInfo",
			data:{
				"CustomerID": customerID,
			},
			dataType: "json",
			type: "POST",
			success: function(data){
				console.log(data);
				if(data.success == 1){
					$(".info h3").text("You have closed the account successfully!");
				}else{
					console.log("You haven't closed the account successfully!");
					return false;
				}
			}
		})
	}
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
    
    
    //click log out
    
    $(".logOut").on("click",function(){
    	//ajax将数据传到后台
			$.ajax({
				url: "../LogOutServlet",
				dataType: "json",
				success: function(data){
					console.log(data);
					if(data.success == 1){						
						window.location.href="index.jsp";
					}else{
						//console.log(data.msg);//理论上来说应该是没有err的
						return false;
					}
				}
			})
    })
})();