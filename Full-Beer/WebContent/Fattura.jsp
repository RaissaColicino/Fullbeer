<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="beans.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

	
<% 
	Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		String ord="sottomissione desc";
		session.setAttribute("previousPage", "/Ordine?toDo=utente&order=" + ord);
		
		response.sendRedirect("./Login.jsp");
	}
	else{
		String ruolo=(String) session.getAttribute("ruolo");
		String CATALOGO="Catalogo";
		String ORDINI="Ordini";
		String UTENTE="Utente";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>Dettagli Ordine</title>
</head>
<body>
	<header>
				<% 
				String pg="";
			
				if(ruolo==null || ruolo.equals(UTENTE))
					pg="Nav_bar.jsp"; 
				else if(ruolo.equals(CATALOGO))
					pg="NavbarCatalogo.jsp";
				else if(ruolo.equals(ORDINI))
					pg="Nav_bar_gestoreOrdini.jsp";
			
			%>
			
			<jsp:include page="<%= pg %>" />			
</header><br><br><br>

	
		
		<%
  			OrdineB ordine=(OrdineB) session.getAttribute("Ordine");
  			LinkedHashSet<ComposizioneB> comp=(LinkedHashSet<ComposizioneB>) ordine.getComposizione();
  		%>
  		
  		  <table border=1 align=center >
	            <tr class="top">
	                <td colspan="4">
	                    <table>
	                        <tr>
	                           
	                            
	                            <td>
	                                <b>FullBeer<br>
	                                <b>TEL.3200642373<br>
								<b>MAIL:Full_Beer@libero.it
	                               
	                            </td>
	                        </tr>
	                    </table>
	                </td>
	            </tr>
	            
	            <tr>
	                <td>
	                    <table>
	                        <tr>
	                            <td>
	                                <b>Fattuna n° <%= ordine.getN_fattura() %></b><br>
	                                Data sottomissione <%= ordine.getDate() %><br>

	                                Data consegna <%= ordine.getConsegna() %>
	                            </td>
	                            
	                            <td>
	                            	<%
	                            		UtenteB user=(UtenteB) session.getAttribute("UtenteFattura");
	                            	                            		
	                            	%>
	                                <b>Cliente</b><br>
	                                <%= user.getNome() + " " + user.getCognome() %> <br>
	                                <%= user.getMail() %><br>
	                            </td>
	                        </tr>
	                    </table>
	                </td>
	            </tr>
	            
	            <tr class="heading">
	                <td><strong>Prodotto</strong></td>
	                <td><strong>Quantità</strong></td>
	                <td><strong>Prezzo</strong></td>
	               
	            </tr>
	            <%
	        
	            	boolean done=false;
	            	for(ComposizioneB c: comp){
	            		
	            %>
	            <tr class="item">
	                <td><%= c.getNome_prodotto() %></td>
	                <td><%= c.getQuantità() %></td>
	                <td><%= c.getPrezzo() %></td>
	                
	            </tr>
	            <%} %>
	            
	            <tr class="total">
	                <td></td>
	                
	                <td colspan="4">
	                    <br>
	               <b>Totale fattura: <%= (float) ordine.getImporto() + "&euro;" %></b>
	                </td>
	            </tr>
	        </table>
	    </div>
  		<!-- End Page Content -->
  		</div>
</body>
</html>
<%} %>