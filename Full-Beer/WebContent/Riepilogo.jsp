<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="beans.UtenteB" %>

<%Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		response.sendRedirect("./Login.jsp");
	}
	else{
		String UTENTE="Utente";
		session.setAttribute("ruolo", UTENTE);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/Riepilogo.css">
<title>Riepilogo Ordine</title>
</head>
<body>


<header>
<%@ include file="Nav_bar.jsp" %>
</header>
 	<% UtenteB userForName=(UtenteB) session.getAttribute("userLogged"); %>
                            <h2><%= userForName.getNome() + " " + userForName.getCognome() %>, il tuo ordine è stato effettuato con successo!</h2>

		
		<div class="img_container">
		<img src="img/simpson.jpg">
		</div>
<footer>
		<%@ include file="Footer.jsp" %>
</footer>
</body>
</html>
<% } %>