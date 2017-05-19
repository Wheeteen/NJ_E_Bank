(function(){
	var vm = new Vue({
		el: 'body',
		data: {
			form1:{
				'name':'',
				'pwd':''
			},
			form2:{
				'name':'',
				'id':'',
				'email':'',
				'pwd1':'',
				'pwd2':''
			},
			form3: {
				'name':'',
				'id':'',
				'email':''
			},
			form4:{
				'pwd1':'',
				'pwd2':''
			},
			page:{
				isLogin: true,
				isActivate: false,
				isModifyPwd: false,
				isResetPwd: false
			},
			status: {
				isLogin: true,
				isRegister: false,
				LoginForm: true,
				RegisterForm: false,
				isGrey: false,
				isError: false,
				errMsg: '',
				isSuccess: false,//register successful tip
			},
			Tip: {
				title: '',
				info: '',
			},
			userAccount: '',
			emailMsg: '',
			err1:{
				'name':'',
				'pwd':''
			},
			err2: {
				'name':'',
				'id':'',
				'email':'',
				'pwd1':'',
				'pwd2':'',
				'code':''
			},
			err3: {
				'name':'',
				'id':'',
				'email':'',
			},
			err4:{
				'pwd1':'',
				'pwd2':''
			}
		},
		ready: function(){
			this.getParam();
			console.log(this.userAccount);
		},
		methods: {
			getParam: function(){
				var str = location.search;
				if(str.indexOf("=") == -1){
					str = str.substr(1,str.length).split("?");
					if(str == "login"){
						this.onLogin();
					}else{
						this.onRegister();
					}
					
				}else{
					str = str.substr(1,str.length).split("=");
					if(str[0] == "activate"){
						this.userAccount = str[1];
						this.onActivate();
					}else if(str[0] == "resetPwd"){
						this.userAccount = str[1];
						this.onCodePwd();
					}
				}
			},
			onCodePwd: function(){
				this.$http.post('../verifyCodePwd', {
					'code': this.userAccount,							
				}, {
					emulateJSON:true,
					headers:{
						'Content-Type':'application/json'	
					}
				}).then(function(res) {
					console.log(res.json());
					var json = res.json().success;
					if(json == 1){
						this.page.isLogin = false;
						this.page.isResetPwd = true;
					}else{
						this.emailMsg="You have reset your password !";
						this.page.isActivate =true;
						this.page.isLogin = false;
					}
					
				});
			},
			onSubmitLogin:function(){
				if(this.form1.name!==''){
					this.err1.name = '';
					if(this.form1.pwd!==''){
						this.err1.pwd = '';
						this.$http.post('../UserLoginServlet', {
							'Uaccount': this.form1.name,
							'Upwd': this.form1.pwd,							
						}, {
							emulateJSON:true,
							headers:{
								'Content-Type':'application/json'	
							}
						}).then(function(res) {
							console.log(res.json());
							if (res.json().success == 1) {
								location.href = 'userIndex.html';
							} else {
								this.status.errMsg = res.json().msg;
								this.status.isGrey = true;
								this.status.isError = true;
							}
						});
					}else{
						this.err1.pwd = "The user account is required."
					}
				}else{
					this.err1.name = "The password is required.";
				}
			},
			onSubmitRegister: function(){
				var form = this.form2,
					err = this.err2,
					tip = this.Tip;
				if(form.name!==''){
					err.name = '';
					if(form.id!==''){
						if(this.verifyID(form.id)){
							err.id = "";
							if(form.email!==''){
								if(this.verifyEmail(form.email)){
									err.email = '';
									if(form.pwd1!==''){
										if(this.verifyPwd(form.pwd1)){
											err.pwd1="";
											if(form.pwd2!==''){
												if(form.pwd1==form.pwd2){
													err.pwd2="";

													this.$http.post('../RegisterServlet', {
														'Uaccount': form.name,
														'UserID': form.id,
														'email': form.email,
														'password':form.pwd1							
													}, {
														emulateJSON:true,
														headers:{
															'Content-Type':'application/json'	
														}
													}).then(function(res) {
														console.log(res.json());
														if (res.json().success == 1) {
															tip.title = "Register Successful";
															tip.info = "Congratualations ! You have registered the online banking account successfully! We have sent a confirmation link to your mailbox! Please check it and  activate your account ASAP !";
															this.status.isGrey =true;
															this.status.isSuccess = true;
														} else {
															this.status.errMsg = res.json().msg;
															this.status.isGrey = true;
															this.status.isError = true;
														}
													});
												}else{
													err.pwd2 = "The confirm password is inconsistent with the verification password.";
												}
											}else{
												err.pwd2 = "The comfirm password is required.";
											}
										}else{
											err.pwd1="Require at least 6 characters with a mixture of lower and upper case letter and digit.";
										}
									}else{
										err.pwd1="The login password is required.";
									}
								}else{
									err.email = "Invalid email!";
								}
							}else{
								err.email = "The email is required.";
							}
						}else{
							err.id = "Invalid identity ID number.";
						}
					}else{
						err.id="The identity ID number is required.";
					}
				}else{
					err.name = "The user account is required.";
				}
			},
			onSubmitModifyPwd: function(){
				var form = this.form3,
					err = this.err3,
					tip = this.Tip;
				if(form.name!==''){
					err.name = '';
					if(form.id!==''){
						if(this.verifyID(form.id)){
							err.id = "";
							if(form.email!==''){
								if(this.verifyEmail(form.email)){
									err.email = '';
									this.$http.post('../PasswordRetrieveServlet', {
										'Uaccount': form.name,
										'Cid': form.id,
										'email': form.email,						
									}, {
										emulateJSON:true,
										headers:{
											'Content-Type':'application/json'	
										}
									}).then(function(res) {
										console.log(res.json());
										if (res.json().success == 1) {
											tip.title = "Password-Reset Notification";
											tip.info = "We have sent a verification link to your mailbox! Please check it and retrieve your password !";
											form.name = "";
											form.id = "";
											form.email = "";
											this.status.isGrey =true;
											this.status.isSuccess = true;
										} else {
											this.status.errMsg = res.json().msg;
											this.status.isGrey = true;
											this.status.isError = true;
										}
									});
								}else{
									err.email = "Invalid email!";
								}
							}else{
								err.email = "The email is required.";
							}
						}else{
							err.id = "Invalid identity ID number.";
						}
					}else{
						err.id="The identity ID number is required.";
					}
				}else{
					err.name = "The user account is required.";
				}
			},
			onForgetPwd: function(){
				this.page.isLogin = false;
				this.page.isModifyPwd = true;
			},
			onSubmitResetPwd: function(){
				var form = this.form4,
					err = this.err4,
					tip = this.Tip;
				if(form.pwd1!==''){
					if(this.verifyPwd(form.pwd1)){
						err.pwd1="";
						if(form.pwd2!==''){
							if(form.pwd1==form.pwd2){
								err.pwd2="";

								this.$http.post('../NewPwdConfirmServlet', {
									'Uaccount': this.userAccount,
									'newPwd': form.pwd1,							
								}, {
									emulateJSON:true,
									headers:{
										'Content-Type':'application/json'	
									}
								}).then(function(res) {
									console.log(res.json());
									if (res.json().success == 1) {
										tip.title = "Password-Reset Successful";
										tip.info = "Congratualations ! You have reset your password. Please use the new password next time! ";
										this.status.isGrey =true;
										this.status.isSuccess = true;
									} else {
										this.status.errMsg = res.json().msg;
										this.status.isGrey = true;
										this.status.isError = true;
									}
								});
							}else{
								err.pwd2 = "The confirm password is inconsistent with the verification password.";
							}
						}else{
							err.pwd2 = "The comfirm password is required.";
						}
					}else{
						err.pwd1="Require at least 6 characters with a mixture of lower and upper case letter and digit.";
					}
				}else{
					err.pwd1="The password is required.";
				}
			},
			verifyPwd: function(name){
				//at least 6 characters and contains a mixture of lower and upper case letter and digit
				var pattern = new RegExp(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{6,}$/);
				return pattern.test(name);
			},
			//register or activate successful
			onReturn: function(user){
				window.location.href="userIndex.html";
			},
			verifyEmail: function(email){			
 				var pattern = new RegExp(/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/);
				return pattern.test(email);
			},
			verifyID: function(id){
				// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X 
				var pattern = new RegExp(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/);
				return pattern.test(id);
			},
			onLogin: function(){
				this.status.isLogin = true;
				this.status.isRegister = false;
				this.status.LoginForm = true;
				this.status.RegisterForm = false;
			},
			onRegister: function(){
				this.status.isLogin = false;
				this.status.isRegister = true;
				this.status.LoginForm = false;
				this.status.RegisterForm = true;
			},
			onClose: function(){
				this.status.errMsg = '';
				this.status.isGrey = false;
				this.status.isError = false;
			},
			//提交用户同意activate  account
			onActivate: function(){
				this.$http.post('../AccountActivatedServlet', {
					'Uaccount': this.userAccount,							
				}, {
					emulateJSON:true,
					headers:{
						'Content-Type':'application/json'	
					}
				}).then(function(res) {
					console.log(res.json());
					var json = res.json().success;
					if(json == 0){
						//已经激活
						this.emailMsg="Your account has been activated ! Please don't activate it again !";
					}else if(json == 1){
						this.emailMsg="Sorry ! Your account hasn't been activated ! Please activate it again !";
					}else{
						this.emailMsg="Congratulations ! Your account has been activated !";
					}
					this.page.isActivate =true;
					this.page.isLogin = false;
				});
			},
			ReturnLogin: function(){
				this.status.isGrey = false;
				this.status.isSuccess = false;
				this.onLogin();
				this.page.isModifyPwd = false;
				this.page.isLogin = true;
			},
			onOk: function(){
				var title = this.Tip.title;
				if(title == "Register Successful"){
					this.onReturn(this.form2.name);
				}else if(title == "Successful Activation"){
					this.onReturn(this.userAccount);
				}else if(title == "Password-Reset Notification"){
					this.ReturnLogin();
				}else{
					window.location.href="userIndex.html";
				}
			}
		}
	});
})();