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
	      						
	      							
	      				%>
	      				<table border="1" width="50%" align="center">
	      				
	      					<tr>
	      				    <td><b> Ordine <%= o.getN_fattura() %></b></td><td><%= o.getStato() %></td>
	      				    </tr>
	      				  
	      				   <tr>
	      				 
	      				 	<td>Sottomesso il:</td> <td><%= o.getDate() %></td>
	      				 
	      				 </tr>
	      				
	      				<tr>
	      				 <td>Consegna prevista il:</td> <td><%= o.getConsegna() %></td>
	      				  
	      				 </tr>
	      				 <tr> 
	      				  <td>Totale</td><td> <%= (float) o.getImporto() + "&euro;" %></td>
	      				  </tr>
	      				  
	      				
	      				  </table>
	      				    
	      				    <div align="center">
	      				  <button class=btnn ><a  href="Fattura?numeroOrdine=<%= o.getN_fattura()%>"style="color:white;">Dettagli</a></button><br><br>
				           </div>                    			
				                         		
	      				 <%
                        		}
	      					
	      			%>
	
	<footer>
		<%@ include file="Footer.jsp" %>
</footer>					
</body>
</html>
<%
   		}
	      				
	    	%>