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

<link rel="stylesheet" href="css/Catalogo.css">
<link rel="stylesheet" href="css/ordine.css">
<title>Catalogo</title>
</head>
<body>

<header>
<%@ include file="Nav_bar.jsp" %>
</header>

<h1 align="center">Le nostre birre<h1>
<table class="table" align="center" width="60%" >
	<%	int salto=0;
		int i=0; %>
	
	<% for(ProdottoB p: catalogoa){%>
			<% i++;
					%>
					
		 <!-- ContenitoreFOto-->
		<td width="200" heigth="200"> 
		<div  class="card">
	
     <img src="img/<%=p.getImmagine()%>" style="width:100%">
  			   <h3><%=p.getNome()%></h3>
  			   <p class="prezzo"><%=p.getPrezzo()%>&euro;</p>
  			<!--    <p><%=p.getDescrizione()%></p> -->
  			   
  			   
  	<button  class=btnn><a href="SchedaProdotto?id=<%=p.getId()%>"style="color:white"> Dettagli </a></button>
  	<br>
  	<br>
  	<button  class=btnn><a href="AddProdottoCarrello?id=<%=p.getId()%>"style="color:white"> Aggiungi </a></button>
	</div>
	</td>
	
	<%
	salto++;
	if(salto==3){
	%>
	<tr>
	<%
	salto=0;
	}}%>
	
	</table>
	
	<footer>
		<%@ include file="Footer.jsp" %>
</footer>
</body>
</html>