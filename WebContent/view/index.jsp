<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E_Bank</title>
<link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
	<div class="main_con">
		<h1>E_Bank</h1>
		<h3>Administrator Login</h3>
		<!-- action="../LogInServlet" method="post" -->
		<form class="LoginForm">
			<div class="username">
				<input type="text" placeholder="Username" name="username" class="uname">
			</div>
			<!-- input is blank -->
			<div class="tip_error err_name"></div>
			<div class="username">
				<input type="password" placeholder="Password" name="password" class="upwd">
			</div>
			<!-- input is blank -->
			<div class="tip_error err_pwd"></div>
			<div class="login">
				<input type="button" value="Submit" id="submitLogin">
			</div>
		</form>
	</div>
	
	<script src="../js/jquery-2.1.4.min.js" type="text/javascript"></script>
	<script src="../js/admin.js"></script>
</body>
</html>