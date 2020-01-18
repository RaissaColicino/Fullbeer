<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="beans.*" %>
<%@	page import="java.util.*" %>

<%
	Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		session.setAttribute("previousPage", "/Acquisto.jsp");
		
		response.sendRedirect(request.getContextPath() + "/Login.jsp");
	}else{
  		UtenteB user=(UtenteB) session.getAttribute("userLogged");
  		LinkedHashSet<IndirizzoB> indirizzi=(LinkedHashSet<IndirizzoB>) user.getIndirizzi();
  		%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Acquisto</title>
</head>
<body>
<header>
<%@ include file="Nav_bar.jsp" %>
</header>

<form name='acquisto' action="SottomissioneOrdine" method="post">
						<h5>Indirizzo di spedizione</h5>
                   		<div class="input-group">
  							<select class="custom-select" name="indirizzo" id="indirizzo">
							    <option selected>Scegli un indirizzo di spedizione</option>
							    <% 
							    	for(IndirizzoB indirizzo: indirizzi){
							    %>
							    <option value= "<%= indirizzo.getUsername() %> "> <%= indirizzo %> </option>
							    <% } %>
  							</select>
						</div>
						<br>
                  		<button type="submit" class="btn btn-black">Procedi</button>
                  		<button type="reset" class="btn btn-secondary">Annulla</button>
                  		<button class="btn btn-danger">
							<a class="text-light no-dec" href="Carrello.jsp" style="text-decoration: none">
								Indietro
							</a>
						</button>
               		</form>
						<br>
				
</body>
</html>
<%} %>