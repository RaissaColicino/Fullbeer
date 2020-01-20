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
<link rel="stylesheet" href="css/OrdiniUtente.css">
<title>Ordine Utente</title>
</head>



<body>
<header>
			<%@ include file="Nav_bar.jsp" %>
</header>

<h1 align="center">Dettaglio Ordini</h1>
						<br> <br><br>
	
						<%! String totale="totale desc"; %>
	        			<%! String sottomissione="sottomissione desc"; %>
	        			
	      				
	      				<%
	      					for(OrdineB o : ordini){
	      						
	      							
	      				%>	<div align="center">
	      				    <b> Ordine <%= o.getN_fattura() %></b>
	      				 <h5> <%= o.getStato() %> </h5>
	      				 Sottomesso il <%= o.getDate() %><br>
	      				 Consegna prevista il <%= o.getConsegna() %><br>
	      				  <h4>Totale <%= (float) o.getImporto() + "&euro;" %></h4>
	      				  
	      				  <button class=btnn ><a  href="Fattura?numeroOrdine=<%= o.getN_fattura()%>"style="color:white;">Dettagli</a></button><br><br>
				                              			
				          </div>                    		
	      				 <%
                        		}
	      					
	      			%>
	
						
</body>
</html>
<%
   		}
	      				
	    	%>