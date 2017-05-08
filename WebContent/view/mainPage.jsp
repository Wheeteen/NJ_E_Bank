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
					<span>></span>
					<span class="keyword">Saving account opening</span>
				</h3>
				<div class="rmainCon">
					<!-- Saving account opening 提交到OpenServlet处理-->
					<div class="AccountFn openAccount">
						<form action="../OpenServlet" class="openAccountForm" method="post">
							<div class="username">
								<label>Input Customer’s Name:</label>
								<input type="text" name="username">
							</div>
							<div class="pwd">
								<label>Input Customer’s ID Number:</label>
								<input type="text" name="userid">
							</div>
							<div class="pwd">
								<label>Input Saving Account PIN:</label>
								<input type="password" name="PIN1">
							</div>
							<div class="pwd">
								<label>Re-Input Saving Account PIN:</label>
								<input type="password" name="PIN2" >
							</div>
							<div class="login">
								<input type="button" value="Submit" onclick="this.form.submit()">
							</div>
						</form>
					</div>
					<!-- Saving account inquiry -->
					<div class="AccountFn inquiryAccount">
						<form action="" class="inquiryAccountForm">
							<div class="username">
								<label>Input Customer’s ID Number:</label>
								<input type="text">
							</div>
							<div class="login">
								<input type="button" value="Submit">
							</div>
						</form>
					</div>
					<!-- Saving account withdrawal -->
					<div class="AccountFn withdrawalAccount">
						<form action="" class="withdrawalAccountForm">
							<div class="username">
								<label> Input User Account Number:</label>
								<input type="text">
							</div>
							<div class="username">
								<label> Input Withdrawal Amount:</label>
								<input type="text">
							</div>
							<div class="login">
								<input type="button" value="Confirm">
							</div>
						</form>
					</div>
					<!-- Saving account deposit -->
					<div class="AccountFn depositAccount">
						<form action="" class="depositAccountForm">
							<div class="username">
								<label> Input User Account Number:</label>
								<input type="text">
							</div>
							<div class="username">
								<label> Input Deposit Amount:</label>
								<input type="text">
							</div>
							<div class="login">
								<input type="button" value="Confirm">
							</div>
						</form>
					</div>
					<!-- pop-up box about the authentication code when the amount>50000 -->
					<div class="auth">
						<h3>Please input the authentication code:</h3>
						<form action="" class="authForm">
							<div class="username">
								<input type="text">
							</div>
							<div class="login">
								<input type="button" value="Submit">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="../js/jquery-2.1.4.min.js" type="text/javascript"></script>
	<script src="../js/mainPage.js"></script>
</body>
</html>