<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>E_Bank</title>
<link rel="stylesheet" href="../css/mainPage.css"/>
</head>
<body>
	<header>
		<div class="g_container">
			<div class="logo">
				<h1>E_Bank</h1>
			</div>
			<div class="perInfo">
				<div class="perPic">
					<span><img src="../img/userPic.png"/></span>
				</div>
				<span>Administor</span>
			</div>
		</div>
	</header>
	<div class="mainCon">
		<div class="g_container">
			<div class="left_con">
				<h3>Business Management</h3>
				<ul class="fns">
					<li class="target" id="A_open">
						Saving account opening
					</li>
					<li id="A_inquiry">
						Saving account inquiry
					</li>
					<li id="A_withdrawal">
						Saving account withdrawal
					</li>
					<li id="A_deposit">
						Saving account deposit
					</li>
				</ul>
			</div>
			<div class="right_con">
				<!-- title -->
				<h3>
					<span class="cblue">Business Management</span> 
					<span>></span><span>Saving account</span>
					<span class="keyword">opening</span>
				</h3>
				<div class="rmainCon">
					<!-- Saving account opening -->
					<div class="AccountFn openAccount">
						<form action="" class="openAccountForm">
							<div class="inputText">
								<label>Input Customer’s Name:</label>
								<input type="text" class="c_name1">
							</div>
							<!-- input is blank -->
							<div class="tip_error err_name1"></div>
							<div class="inputText">
								<label>Input Customer’s ID Number:</label>
								<input type="text" class="c_id1">
							</div>
							<!-- input is blank -->
							<div class="tip_error err_num1"></div>
							<div class="inputText">
								<label>Input Saving Account PIN:</label>
								<input type="password" class="c_pwd1">
							</div>
							<!-- input is blank -->
							<div class="tip_error err_pwd1"></div>
							<div class="inputText">
								<label>Re-Input Saving Account PIN:</label>
								<input type="password" class="c_pwd2">
							</div>
							<!-- input is blank -->
							<div class="tip_error err_pwd2"></div>
							<div class="login">
								<input type="button" value="Submit" id="openSubmit">
							</div>
						</form>
					</div>
					<!-- Saving account inquiry -->
					<div class="AccountFn inquiryAccount">
						<form action="" class="inquiryAccountForm">
							<div class="inputText">
								<label>Input Customer’s ID Number:</label>
								<input type="text" class="c_id2">
							</div>
							<div class="tip_error err_num2"></div>
							<div class="login">
								<input type="button" value="Submit" id="inquirySubmit">
							</div>
						</form>
					</div>
					<!-- Saving account withdrawal -->
					<div class="AccountFn withdrawalAccount">
						<form action="" class="withdrawalAccountForm">
							<div class="inputText">
								<label> Input User Account Number:</label>
								<input type="text" class="userNum1">
							</div>
							<div class="tip_error err_userNum1"></div>
							<div class="inputText">
								<label> Input Withdrawal Amount:</label>
								<input type="text" class="withAmount1">
							</div>
							<div class="tip_error err_amount1"></div>
							<div class="login">
								<input type="button" value="Confirm" id="withdrawalSubmit">
							</div>
						</form>
					</div>
					<!-- Saving account deposit -->
					<div class="AccountFn depositAccount">
						<form action="" class="depositAccountForm">
							<div class="inputText">
								<label> Input User Account Number:</label>
								<input type="text" class="userNum2">
							</div>
							<div class="tip_error err_userNum2"></div>
							<div class="inputText">
								<label> Input Deposit Amount:</label>
								<input type="text" class="dsitAmount1">
							</div>
							<div class="tip_error err_amount2"></div>
							<div class="login">
								<input type="button" value="Confirm" id="depositSubmit">
							</div>
						</form>
					</div>
					
					<!-- 后台返回的信息显示区域 -->
					<div class="info">
						<h3>You have opened the saving account successfully</h3>
						<p><label>Customer’s Name:</label><span>JessyLiSuzhen</span></p>
						<p><label>Saving account number:</label><span>12345678901254</span></p>
						<p><label>Card number:</label><span>1234567890125415</span></p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 覆盖一整个页面的灰屏 -->
    <div class="grey"></div>
    <!-- pop-up box about the authentication code when the amount>50000 -->
    <div class="auth">
       <div class="close">
        ×
       </div>
		<h3>Please input the authentication code</h3>
		<form action="" class="authForm">
			<div class="inputText">
				<input type="text" class="auth_code">
			</div>
			<div class="tip_error err_auth"></div>
			<div class="login">
				<input type="button" value="Submit" id="authSubmit">
			</div>
		</form>
	</div>
	<script src="../js/jquery-2.1.4.min.js" type="text/javascript"></script>
	<script src="../js/mainPage.js"></script>
</body>
</html>