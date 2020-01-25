<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
        
<%@ page import="beans.*" %>
<%@ page import="java.util.*" %>


 <% 
	session.setAttribute("ruolo", RuoloB.UTENTE);
	Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		String ord="sottomissione desc";
		session.setAttribute("previousPage", "/Ordine?toDo=utente&order=" + ord);
		response.sendRedirect("./Login.jsp");
	}
	else{
		
		LinkedHashSet<OrdineB> ordini=(LinkedHashSet<OrdineB>) session.getAttribute("Ordini");

		
		
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/ordinegestore.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Ordine Utente</title>
</head>
<body>

<header>
			<%@ include file="Nav_bar.jsp" %>
</header>

<h1 align="center"><b>I tuoi ordini</b></h1>
<div class="w3-container">
  		
  		
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
        
	
						<%! String totale="totale desc"; %>
	        			<%! String sottomissione="sottomissione desc"; %>
	        			
	      				
	      				<%
	      					for(OrdineB o : ordini){
	      						
	      							
	      				%>
	      				
	      				
	      					<tr>
	      				    <td><b><%= o.getN_fattura() %></b></td>
	      				    <td><%= o.getStato() %></td>
	      				    <td><%= o.getDate() %></td>
	      				 	<td>Consegna prevista il:</td>
	      				    <td><%= o.getConsegna() %></td>
	      				    <td> <%= (float) o.getImporto() + "&euro;" %></td>
	      				  
	      				  
	      					<td><button class=btnn ><a  href="Fattura?numeroOrdine=<%= o.getN_fattura()%>"style="color:white;">Dettagli</a></button></td>
				            </tr>                   			
				                        		
	      				 <%
                        		}
	      					
	      			%>
</table>
	<br>
	<br>
	<footer>
		<%@ include file="Footer.jsp" %>
</footer>					
</body>
</html>
<%
   		}
	      				
	    	%>