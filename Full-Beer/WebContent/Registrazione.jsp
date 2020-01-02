<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrazione</title>
<link rel="stylesheet" href="css/Registrazione.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

<header>
<%@ include file="Nav_bar.jsp" %>
</header>

<form action="" style="max-width:500px;margin:auto">
  <h2><strong>Registrazione Fullbeer</strong></h2>
  
  <div align="right" class="img_c">
		<img src="img/registrazione.jpg">
	</div>
 
  
    <div class="input-container">
    <i class="fa fa-address-book" style="font-size:36px" ></i>
    <input class="input-field" type="text" placeholder="Nome" name="nome">
  </div>
  
    <div class="input-container">
    <i class="fa fa-address-book-o" style="font-size:32px" ></i>
    <input class="input-field" type="text" placeholder="Cognome" name="cognome">
  </div>
  
    <div class="input-container">
    <i class="fa fa-address-card" style="font-size:32px"></i>
    <input class="input-field" type="text" placeholder="Città" name="città">
  </div>

	<div class="input-container">
    <i class="fa fa-map-signs"  style="font-size:32px"></i>
    <input class="input-field" type="text" placeholder="Via" name="via">
  </div>
  
    <div class="input-container">
    <i class="fa fa-home"  style="font-size:32px"></i>
    <input class="input-field" type="text" placeholder="Cap" name="cap">
  </div>
  
   <div class="input-container">
    <i class="fa fa-user-circle-o" style="font-size:32px"></i>
    <input class="input-field" type="text" placeholder="Username" name="username">
  </div>

  <div class="input-container">
    <i class="fa fa-envelope"style="font-size:32px"></i>
    <input class="input-field" type="text" placeholder="Email" name="mail">
  </div>
  
  <div class="input-container">
    <i class="fa fa-key" style="font-size:32px"></i>
    <input class="input-field" type="password" placeholder="Password" name="password">
  </div>
  
  <button type="submit" class="btn">Registrati</button>
</form>

</body>
</html>

