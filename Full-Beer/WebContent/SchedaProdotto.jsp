<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="beans.*" %>
<%@ page import="java.util.*" %>
<%@page import="model.*" %>
    <%@page import="controller.*" %>
<%ProdottoB p=(ProdottoB) session.getAttribute("ProdottoDaMostrare"); %>


<!------ Include the above in your HEAD tag ---------->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/Catalogo.css">
<link rel="stylesheet" href="css/ordine.css">
<title>Dettagli Prodotto</title>
</head>
<body>
<header>
<%@ include file="Nav_bar.jsp" %>
</header>
<br><br>


		<div align="center" >
   
    <img src="img/<%=p.getImmagine()%>" >
  <h1 align="center">  <strong><%=p.getNome()%></strong></h1>
  <h2 align="center">  <strong><%=p.getPrezzo()%>&euro;</strong></h2> 
  <h3    align="center"> <%=p.getDescrizione() %></h3>
<button  class=btnn><a href="AddProdottoCarrello?id=<%=p.getId()%>"style="color:white"> Aggiungi al carrello</a></button
</div>

<br><br>
<footer>
		<%@ include file="Footer.jsp" %>
</footer>
</body>
</html>