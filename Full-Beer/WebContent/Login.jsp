<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/Login.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<header>
<%@ include file="Nav_bar.jsp" %>
</header>

<form action="Login" method="post" style="max-width:500px;margin:auto">
	
<h2 text-align="center"><strong>Accedi a Fullbeer</strong></h2>

	
	<div class="img_container">
		<img src="img/avatar.jpg">
	</div>
	
	
	<div class="input-c">
    <i class="fa fa-user-circle-o" style="font-size:32px""></i>
    <input class="input-f" type="text" placeholder="Username" name="username">
  	</div>
	
	
    <div class="input-c">
    <i class="fa fa-key"style="font-size:32px"></i>
    <input class="input-f" type="password" placeholder="Password" name="password">
  	</div>
	
	  <div align ="center">
	 <button type="submit" class=btnn>Login</button> 
	 <br><br>
	 <button type="button"  onclick="location.href='Registrazione.jsp'" class=btnn>Registrati</button>
		</div>

</form>
</body>
</html>