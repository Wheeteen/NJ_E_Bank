/**
 * Administrator  login
 */

(function(){
	$("#submitLogin").on("click",function(){
		var uname = ifEmpty(".uname"),
			upwd = ifEmpty(".upwd"),
			username = $(".uname").val(),
			password = $(".upwd").val();
		
		if(uname){
			$(".err_name").text("");
			if(upwd){
				$(".err_pwd").text("");
				
				//ajax将数据传到后台
				$.ajax({
					url: "../LogInServlet",
					data:{
						"username": username,
						"password": password
					},
					dataType: "json",
					type: "POST",
					success: function(data){
						if(data.success == 1){
							location.href = "mainPage.jsp";
						}else{
							$(".err_pwd").text(data.msg);
						}
					}
				})
				
			}else{
				$(".err_pwd").text("Please input password");
			}
		}else{
			$(".err_name").text("Please input username");
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
    
    getEnter(".LoginForm","#submitLogin");
})()