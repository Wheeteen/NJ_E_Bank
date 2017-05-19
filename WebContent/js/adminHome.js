(function(){
	var vm = new Vue({
		el: 'body',
		data: {
			status: {
				isOpen: true,
				isInquiry: false,
				isDeposit: false,
				isWithdraw: false,
				isPin: false,
				isGrey: false,
				isError: false,
				errMsg: '',
				infoTitle: '',
				closeAccount: false,
				isAuth: false,
				username: 'Administrator',
			},
			auth: {
				"code":'',
				'err':'',
			},
			statusDWC:'',//记住弹窗写auth_code的时候选的是哪个
			keyword: 'Saving account opening',
			selectForm: {
				isOpen: true,
				isInquiry: false,
				isDeposit: false,
				isWithdraw: false,
				isPin: false,
				isInfo: false
			},
			info :[],
			inquiryId: '',
			form1:{
				'name':'',
				'id':'',
				'pin1':'',
				'pin2':''
			},
			form2:{
				'id':'',
			},
			form3: {
				'userNum':'',
				'amount':''
			},
			form4: {
				'userNum':'',
				'amount':''
			},
			form5: {
				cardNum: '',
				id: '',
				oldPin: '',
				newPin1:'',
				newPin2: ''
			},
			errDeposit:{
				'userNum':0,
				'amount':0
			},
			errWithdraw: {
				'userNum':0,
				'amount':0
			},
			err1: {
				'name':'',
				'id':'',
				'pin1':'',
				'pin2':''
			},
			err2:{
				'id':''
			},
			err3: {
				'userNum':'',
				'amount':''
			},
			err4: {
				'userNum':'',
				'amount':''
			},
			err5: {
				cardNum: '',
				id: '',
				oldPin: '',
				newPin1:'',
				newPin2: ''
			},
			changePin: {
				cardNum: 0,
				id: 0,
				oldPin: 0,
				newPin1: 0,
				newPin2: 0
			}
		},
		ready: function(){
			this.getUser();
		},
		methods: {
			getUser: function(){
				this.$http.post('../sessionAdmin', {							
				}, {
					emulateJSON:true,
					headers:{
						'Content-Type':'application/json'	
					}
				}).then(function(res) {
					console.log(res.json());
					this.status.username = res.json().msg;
	
				});
			},
			onOpen: function(){
				this.status.isOpen = true;
				this.status.isInquiry = false;
				this.status.isDeposit = false;
				this.status.isWithdraw = false;
				this.status.isPin = false;
				this.selectForm.isOpen = true;
				this.selectForm.isInquiry = false;
				this.selectForm.isDeposit = false;
				this.selectForm.isWithdraw = false;
				this.selectForm.isPin = false;
				this.selectForm.isInfo = false;
				this.status.closeAccount = false;
				this.keyword = "Saving account openging";
			},
			onInquiry: function(){
				this.status.isOpen = false;
				this.status.isInquiry = true;
				this.status.isDeposit = false;
				this.status.isWithdraw = false;
				this.status.isPin = false;
				this.selectForm.isOpen = false;
				this.selectForm.isInquiry = true;
				this.selectForm.isDeposit = false;
				this.selectForm.isWithdraw = false;
				this.selectForm.isPin = false;
				this.selectForm.isInfo = false;
				this.status.closeAccount = false;
				this.keyword = "Saving account inquiry";
			},
			onDeposit: function(){
				this.status.isOpen = false;
				this.status.isInquiry = false;
				this.status.isDeposit = true;
				this.status.isWithdraw = false;
				this.status.isPin = false;
				this.selectForm.isOpen = false;
				this.selectForm.isInquiry = false;
				this.selectForm.isDeposit = true;
				this.selectForm.isWithdraw = false;
				this.selectForm.isPin = false;
				this.selectForm.isInfo = false;
				this.status.closeAccount = false;
				this.keyword = "Saving account deposit";
			},
			onWithdraw: function(){
				this.status.isOpen = false;
				this.status.isInquiry = false;
				this.status.isDeposit = false;
				this.status.isWithdraw = true;
				this.status.isPin = false;
				this.selectForm.isOpen = false;
				this.selectForm.isInquiry = false;
				this.selectForm.isDeposit = false;
				this.selectForm.isWithdraw = true;
				this.selectForm.isPin = false;
				this.selectForm.isInfo = false;
				this.status.closeAccount = false;
				this.keyword = "Saving account withdraw";
			},
			onChangePin: function(){
				this.status.isOpen = false;
				this.status.isInquiry = false;
				this.status.isDeposit = false;
				this.status.isWithdraw = false;
				this.status.isPin = true;
				this.selectForm.isOpen = false;
				this.selectForm.isInquiry = false;
				this.selectForm.isDeposit = false;
				this.selectForm.isWithdraw = false;
				this.selectForm.isPin = true;
				this.selectForm.isInfo = false;
				this.status.closeAccount = false;
				this.keyword = "Change PIN";
			},
			onSubmitOpen: function(){
				var form = this.form1,
					err = this.err1;
				if(form.name!==''){
					err.name = '';
					if(form.id!==''){
						if(this.verifyID(form.id)){
							err.id = "";
							if(form.pin1!==''){
								if(this.verifySixDigit(form.pin1)){
									err.pin1 = '';
									if(form.pin2!==''){
										if(form.pin1==form.pin2){
											err.pin2 = '';
											this.$http.post('../OpenServlet', {
												"username": form.name,
												"userid": form.id,
												"PIN2": form.pin1						
											}, {
												emulateJSON:true,
												headers:{
													'Content-Type':'application/json'	
												}
											}).then(function(res) {
												console.log(res.json());
												if (res.json().success == 1) {
													var json = res.json();
													this.status.infoTitle = "You have opened the saving account successfully";
													this.info = [{
														'title':'Customer’s Name:',
														'text':form.name
													},{
														'title':'Card number:',
														'text':json.cardNum
													},{
														'title':'Saving account number:',
														'text':json.accountNum
													}];
													this.selectForm.isOpen =false;
													this.selectForm.isInfo = true;
													form.name='';
													form.id = '';
													form.pin1='';
													form.pin2='';
												} else {
													this.status.errMsg = res.json().msg;
													this.status.isGrey = true;
													this.status.isError = true;
												}
											});
										}else{
											err.pin2 = "The confirm password is inconsistent with the verification password.";
										}
									}else{
										err.pin2="The confirm saving account PIN is required."
									}
								}else{
									err.pin1 = "Require only 6 digits.";
								}
							}else{
								err.pin1 = "The saving account PIN is required.";
							}
						}else{
							err.id = "Invalid identity ID number.";
						}
					}else{
						err.id="The customer's ID number is required.";
					}
				}else{
					err.name = "The customer's name is required.";
				}
			},
			onSubmitInquiry: function(){
				var form = this.form2,
					err = this.err2;
				if(form.id!==''){
					if(this.verifyID(form.id)){
						err.id = '';
						this.$http.post('../InquiryServlet', {
							"CustomerID": form.id,					
						}, {
							emulateJSON:true,
							headers:{
								'Content-Type':'application/json'	
							}
						}).then(function(res) {
							console.log(res.json());
							if (res.json().success == 1) {
								var json = res.json();
								this.status.infoTitle = "You have inquiried the saving account successfully";
								this.info = [{
									'title':'Customer’s Name:',
									'text':json.name
								},{
									'title':'Card number:',
									'text':json.cardNum
								},{
									'title':'Saving account number:',
									'text':json.accountNum
								},{
									'title':'Online Banking Account Status:',
									'text':json.status
								}];
								this.selectForm.isInquiry =false;
								this.selectForm.isInfo = true;
								this.inquiryId = form.id;
								this.status.closeAccount = true;
								form.id='';
							} else {
								this.status.errMsg = res.json().msg;
								this.status.isGrey = true;
								this.status.isError = true;
							}
						});

					}else{
						err.id = "Invalid identity ID number.";
					}
				}else{
					err.id = "The customer's ID number is required.";
				}
			},
			onCloseAccount: function(){
				this.$http.post('../InquiryServlet', {
					"CustomerID": this.inquiryId,					
				}, {
					 emulateJSON:true,
					headers:{
						'Content-Type':'application/json'	
					}
				}).then(function(res) {
					console.log(res.json());
					if (res.json().success == 1) {
						var json = res.json();
						if(json.balance>0){
							this.status.infoTitle = "The account has outstanding balance, so this account can’t be closed";
							this.info = [{
								'title':"The account's balance:",
								'text':json.balance
							}];
							this.status.closeAccount = false;
							this.selectForm.isInfo = true;
						}else{
							this.statusDWC = "closeAccount";
							this.status.isGrey = true;
							this.status.isAuth = true;
						}
					} else {
						console.log("数据库查询有错");
					}
				});
			},
			onSubmitDeposit: function(url,key){
				var arr,form,err,Ckey;
				if(key == 'deposit'){
					arr = this.errDeposit;
					form = this.form3;
					err = this.err3;
					Ckey = "Deposit";
				}else{
					arr = this.errWithdraw;
					form = this.form4;
					err = this.err4;
					Ckey = "Withdraw";
				}
				if(arr.userNum == 1){
					if(arr.amount == 1){
						this.$http.post(url, {
							"AccountNumber": form.userNum,
							"Amount": form.amount				
						}, {
							 emulateJSON:true,
							headers:{
								'Content-Type':'application/json'	
							}
						}).then(function(res) {
							console.log(res.json());
							if (res.json().success == 1) {
								var json = res.json(),
									balance = this.formatNum(form.amount);
									exiBalance = this.formatNum(json.exiBalance);
								this.status.infoTitle = "The "+key+" action is completed";
								this.info = [{
									'title':'User Account Number:',
									'text':form.userNum
								},{
									'title':Ckey+' amount:',
									'text': balance
								},{
									'title':'Existing balances:',
									'text':exiBalance
								}];
								this.selectForm.isDeposit =false;
								this.selectForm.isWithdraw = false;
								this.selectForm.isInfo = true;
								form.userNum='';
								form.amount = "";
							} else {
								this.status.errMsg = res.json().msg;
								this.status.isGrey = true;
								this.status.isError = true;
							}
						});
					}else{
						this.onAmount(form.amount,err.amount,arr);
					}
				}else{
					this.onUserNum(form.userNum,err.userNum,arr);
				}
			},
			//blur: input user account number
			onUserNum: function(target,err,status){
				if(target!==''){
					if(this.verifyFteenDigit(target)){
						err["userNum"]='';
						this.$http.post('../getCountNum', {
							"AccountNumber": target,					
						}, {
							 emulateJSON:true,
							headers:{
								'Content-Type':'application/json'	
							}
						}).then(function(res) {
							console.log(res.json());
							if (res.json().success == 1) {
								var json = res.json();
								status["userNum"]=1;
							} else {
								status["userNum"]=0;
								err["userNum"]="Invalid user account number.";
							}
						});
					}else{
						status["userNum"]=0;
						err["userNum"]="Require only 14 digits.";
					}
				}else{
					status["userNum"]=0;
					err["userNum"] = "The user account number is required.";
				}
			},
			//blur: input amount
			onAmount: function(target,err,status,key){
				var form,errText;
				if(key == 'deposit'){
					form = this.form3.userNum;
					errText = this.err3;
				}else{
					form = this.form4.userNum;
					errText = this.err4;
				}

				if(status["userNum"]==1){
					if(target!==''){
						err["amount"] = "";
						if(target<50000){
							status["amount"]=1;
							return true;
						}else{
							status["amount"]=0;
							this.statusDWC = key;
							this.status.isGrey = true;
							this.status.isAuth = true;
						}
					}else{
						status["amount"]=0;
						err["amount"] = "The value is required.";
					}
				}else{
					if(key == "deposit"){
						this.onUserNum(form,errText,this.errDeposit);
					}else{
						this.onUserNum(form,errText,this.errWithdraw);
					}
					
				}
			},
			//delete user account
			deleteUserInfo: function(){
				this.$http.post('../deleteUserInfo', {
					"CustomerID": this.inquiryId,					
				}, {
					 emulateJSON:true,
					headers:{
						'Content-Type':'application/json'	
					}
				}).then(function(res) {
					console.log(res.json());
					if (res.json().success == 1) {
						var json = res.json();
						this.status.infoTitle = "You have closed the account successfully!";
						this.info = [];
						this.status.closeAccount = false;
						this.selectForm.isInfo = true;
						
					} else {
						this.status.infoTitle = "Sorry,incorrect identity id number.";
						this.info = [];
						this.status.closeAccount = false;
					}
				});
			},
			
			verifySixDigit: function(num){	
				var pattern = new RegExp(/^[0-9]{6}$/);
				return pattern.test(num);
			},
			//14位数字，account number 
			 verifyFteenDigit: function(num){				
			 	var pattern = new RegExp(/^[0-9]{14}$/);
			 	return pattern.test(num);
			 },
			//16位数字，account number 
			 verifySteenDigit: function(num){				
			 	var pattern = new RegExp(/^[0-9]{16}$/);
			 	return pattern.test(num);
			 },
			verifyID: function(id){
				// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X 
				var pattern = new RegExp(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/);
				return pattern.test(id);
			},
			//格式化数字，每三位加逗号,事实上就是操作字符串
			formatNum: function(num){
				var num = (num || 0).toString(), result = '';  
			    while (num.length > 3) {  
			        result = ',' + num.slice(-3) + result;  
			        num = num.slice(0, num.length - 3);  
			    }  
			    if (num) { result = num + result; }  
			    return result; 
			},
			onClose: function(){
				this.auth.code='';
				this.auth.err="";
				this.status.isGrey = false;
				this.status.isError = false;
				this.status.isAuth = false;
			},
			authSubmit: function(){
				var auth = this.auth,
					selected = this.statusDWC;
				if(auth.code!=''){
					auth.err = "";

					this.$http.post('../authCode', {
						"authCode": auth.code,					
					}, {
						 emulateJSON:true,
						headers:{
							'Content-Type':'application/json'	
						}
					}).then(function(res) {
						console.log(res.json());
						if (res.json().success == 1) {
							if(selected == "deposit"){
								this.errDeposit.amount = 1;
								this.onSubmitDeposit('../DepositServlet2','deposit');
							}else if(selected == "withdraw"){
								this.errWithdraw.amount = 1;
								this.onSubmitDeposit('../WithdrawServlet2','withdraw');
							}else if(selected == "closeAccount"){
								this.deleteUserInfo();
							}else{
								this.onSubmitPin();
							}
							this.status.isGrey = false;
							this.status.isAuth = false;
							auth.code = "";
						} else {
							auth.err="Invalid authentication code."
						}
					});
				}else{
					auth.err="The value is required.";
				}
			},
			//change pin : enter card num
			onCardNum: function(){
				
				var num = this.form5,
					err = this.err5,
					status = this.changePin;
				if(num.cardNum!==''){
					if(this.verifySteenDigit(num.cardNum)){
						err.cardNum ="";
						status.cardNum = 1;
					}else{
						err.cardNum = "Require only 16 digits.";
						status.cardNum = 0;
					}
				}else{
					err.cardNum = "The user card number is required.";
					status.cardNum = 0;
				}
			},
			onId: function(){
				var num = this.form5,
					err = this.err5,
					status = this.changePin;
				if(this.changePin.cardNum == 1){
					if(num.id!==''){
						if(this.verifyID(num.id)){
							err.id="";
							status.id = 1;
						}else{
							err.id = "Invalid identity id number.";
							status.id = 0;
						}
					}else{
						err.id = "The identity id number is required.";
						status.id = 0;
					}
				}else{
					this.onCardNum();
				}
			},
			onPin: function(target){
				var num=this.form5,err=this.err5,status=this.changePin,previous,key;
				if(target == 'oldPin'){
					key="oldPin";
					previous = this.changePin.id;
				}else{
					key = "newPin1";
					previous = this.changePin.oldPin;
				}
				
				if(previous == 1){
					if(num[key]!==''){
						if(this.verifySixDigit(num[key])){
							status[key] = 1;
							err[key] ="";
						}else{
							err[key] = "Require only 6 digits.";
							status[key] = 0;
						}
					}else{
						err[key] = "The value is required.";
						status[key] = 0;
					}
				}else{
					if(target == 'oldPin'){
						this.onId();
					}else{
						this.onPin('oldPin');
					}
					
				}
			},
			onConfirmPin: function(){
				var num=this.form5,err=this.err5,status=this.changePin;
				if(this.changePin.newPin1 == 1){
					if(num.newPin2!==''){
						if(num.newPin2 == this.form5.newPin1){
							err.newPin2="";
							status.newPin2 = 1;
							this.statusDWC = "changePin";
							this.status.isGrey = true;
							this.status.isAuth = true;
						}else{
							err.newPin2 = "The confirm password is inconsistent with the verification password.";
							status.newPin2 = 0;
						}
					}else{
						err.newPin2 = "The value is required.";
						status.newPin2 = 0;
					}
				}else{
					this.onPin('newPin1');
				}
			},
			onSubmitPin: function(){
				var status = this.changePin,form = this.form5;
				if(status.cardNum==1){
					if(status.id == 1){
						if(status.oldPin == 1){
							if(status.newPin1 ==1){
								if(status.newPin2 == 1){
									this.$http.post('../changePIN', {
										"cardNum": form.cardNum,
										"ID": form.id,
										"oldPIN":form.oldPin,
										"newPIN":form.newPin1					
									}, {
										emulateJSON:true,
										headers:{
											'Content-Type':'application/json'	
										}
									}).then(function(res) {
										console.log(res.json());
										if (res.json().success == 1) {
											var json = res.json();
											this.status.infoTitle = "Congratulations ! You have changed the PIN successfully!";
											this.info = [];
											this.selectForm.isPin = false;
											this.selectForm.isInfo = true;
											form.cardNum = "";
											form.id = "";
											form.oldPin = "";
											form.newPin1 = "";
											form.newPin2 = "";
										} else {
											this.status.errMsg = res.json().msg;
											this.status.isGrey = true;
											this.status.isError = true;
										}
									});
								}else{
									this.onConfirmPin();
								}
							}else{
								this.onPin('newPin1');
							}
						}else{
							this.onPin('oldPin');
						}
					}else{
						this.onId();
					}
				}else{
					this.onCardNum();
				}
			},
			logOut: function(){
				this.$http.post('../LogOutServlet', {				
				}, {
					emulateJSON:true,
					headers:{
						'Content-Type':'application/json'	
					}
				}).then(function(res) {
					console.log(res.json());
					if (res.json().success == 1) {
						window.location.href="index.html";
					} else {
						console.log("wrong log out!");
					}
				});
			}
		}
	});
})();