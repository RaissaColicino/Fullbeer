<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.*" %>
    <%@page import="controller.*" %>
    <%@page import="beans.*" %>
    <%@page import="java.util.*" %>
    
<%! CatalogoB catalogo; 
	HashSet<ProdottoB> catalogoa;
%>
<%
	String UTENTE="Utente";
	session.setAttribute("ruolo", UTENTE);
	catalogo=(CatalogoB) session.getAttribute("Catalogo");
	catalogoa=(HashSet<ProdottoB>) catalogo.getCatalogo();
%>
		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<title>Catalogo</title>
</head>
<body>
<header>
<%@ include file="Nav_bar.jsp" %>
</header>

	<% for(ProdottoB p: catalogoa){%>
		<div class="container_photo"></div>
 <!-- ContenitoreFOto-->
  <div class="w3-row-padding w3-padding-16 w3-center" id="drink">
    <div class="w3-quarter">
     <img src="img/<%=p.getImmagine()%>" style="width:50%">
  			   <h3><%=p.getNome()%></h3>
  			   <h3><%=p.getPrezzo()%></h3>
  			   
  	<a href="SchedaProdotto?id=<%=p.getId()%>"> Dettagli </a><br>
	<a href="AddProdottoCarrello?id=<%=p.getId()%>"> Aggiungi </a>
	<% }%>
        				
   
</body>
</html>