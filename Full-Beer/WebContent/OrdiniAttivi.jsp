<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="beans.*" %>
<%@ page import="java.util.*" %>

<% 
	Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		response.sendRedirect("./Login.jsp");
	}
	else{
		UtenteB userForRoleControl=(UtenteB) session.getAttribute("userLogged");
		if(!userForRoleControl.getRuolo().containsKey(RuoloB.ORDINI)){
			response.sendRedirect("./ErrorPage.html");
		}
		else{
			session.setAttribute("ruolo", RuoloB.ORDINI);
			LinkedHashSet<OrdineB> ordiniAttivi=(LinkedHashSet<OrdineB>) session.getAttribute("OrdiniAttivi");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/ordine.css">
<title>Gestione Ordini attivi</title>
</head>
<body>

<header>
<%@ include file= "Nav_bar_gestoreOrdini.jsp" %>
</header>
 
 		<h1 class="my-4">Ordini attivi</h1>
	      				
	      				<%
	      					for(OrdineB o: ordiniAttivi){
	      				%>
	      				<div align="center">
	      				 <strong> Ordine <%= o.getN_fattura() %></strong>
	      				   <h5> <%= o.getStato() %> </h5>
                                                <h6>Sottomesso da <%= o.getUsername() %></h6>
                                                <h6>Sottomesso il <%= o.getDate() %></h6>
                                                <h6>Consegna prevista il <%= o.getConsegna() %></h6>
                                                
                                                <h4>Totale <%= (float) o.getImporto() + "&euro;" %></h4>


<button type="submit" class=btnn >
<a href="Fattura?numeroOrdine=<%= o.getN_fattura() %>" style="color:white">
				                              		 Dettagli
				                              		</a></button>  <button type="submit" class=btnn >
<a href="AggiornaStato?what=write&numero=<%= o.getN_fattura() %>" style="color:white">
				                              			Aggiorna stato
				                              		</a></button></div><br><br><br>
<%} %>
<footer>
		<%@ include file="Footer.jsp" %>
</footer>
</body>
</html>
<%}
		}%>