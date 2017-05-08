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
		<form action="../LogInServlet" method="post" >
			<div class="username">
				<input type="text" placeholder="Username" name="username">
			</div>
			<div class="pwd">
				<input type="password" placeholder="Password" name="password">
			</div>
			<div class="login">
				<input type="button" value="Submit" onclick="this.form.submit()">
			</div>
		</form>
	</div>
</body>
</html>