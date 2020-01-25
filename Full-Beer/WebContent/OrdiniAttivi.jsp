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
<link rel="stylesheet" href="css/ordinegestore.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Gestione Ordini attivi</title>
</head>
<body>

<header>
<%@ include file= "Nav_bar_gestoreOrdini.jsp" %>
</header>
 
 <div class="w3-container">
  		
  		<h3 align=center>Ordini Attivi</h3>
  		<br>
  		<br>
     	<table class="w3-table-all w3-hoverable" align=center >		
        
 		<tr class="w3-light-grey">	
        <th>Numero Ordine</th>
        <th>Stato</th>
        <th>Sottomesso da:</th>
        <th>Sottomesso il:</th>
        <th>Consegna prevista il:</th>
        <th>Totale ordine</th>
        <th></th>
        <th></th>
        </tr>
        
	      				
	      				<%
	      					for(OrdineB o: ordiniAttivi){
	      				%>
	      				
	      				<tr>
	      				 <td>  <%= o.getN_fattura() %></td>
	      				  <td> <%= o.getStato() %> </td>
                          <td>Sottomesso da <%= o.getUsername() %></td>
                          <td>Sottomesso il <%= o.getDate() %></td>
                          <td>Consegna prevista il <%= o.getConsegna() %></td>
                          <td>Totale <%= (float) o.getImporto() + "&euro;" %></td>


							<td><button type="submit" class=btnn >
							
							<a href="Fattura?numeroOrdine=<%= o.getN_fattura() %>" style="color:white">
				            Dettagli</a>
				           
				            </button>                 		
				            </td>  
				          
				          <td><button type="submit" class=btnn >
							<a href="AggiornaStato?what=write&numero=<%= o.getN_fattura() %>" style="color:white">
				      		Aggiorna stato</a>
				      		</button>
				      		</td>
				      		</tr>
<%} %>
</table>
<br>
<br>
<footer>
		<%@ include file="Footer.jsp" %>
</footer>
</body>
</html>
<%}
		}%>