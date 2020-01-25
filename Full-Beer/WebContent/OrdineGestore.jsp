<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        
<%@ page import="beans.*" %>
<%@ page import="java.util.*" %>

<% 
	Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		String ord="sottomissione desc";
		session.setAttribute("previousPage", "/Ordine?toDo=gestore&order=" + ord);
		response.sendRedirect("./Login.jsp");
	}
	else{
		UtenteB userForRoleControl=(UtenteB) session.getAttribute("userLogged");
		if(!userForRoleControl.getRuolo().containsKey(RuoloB.ORDINI)){
			response.sendRedirect("./OnlyAdminPage.html");
		}
		else{
			session.setAttribute("ruolo", RuoloB.ORDINI);
			boolean areAttivi=false;
			boolean areChiusi=false;
			LinkedHashSet<OrdineB> ordini=(LinkedHashSet<OrdineB>) session.getAttribute("Ordini");
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/ordinegestore.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Gestione Ordini </title>
</head>
<body>
	
<header>
			<%@ include file="Nav_bar_gestoreOrdini.jsp" %>
</header> 
	
		<div class="w3-container">
  		<h2 align=center>Gestione Ordini</h2>
  		<h3>Ordini da gestire</h3>
     	<table class="w3-table-all w3-hoverable" >		
        
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
     			for(OrdineB o: ordini){	
	      						if(o.getStato().equals(OrdineB.ELABORAZIONE) || o.getStato().equals(OrdineB.SPEDIZIONE)){
	      							areAttivi=true;
	      				%>
	      				<tr>
	      				<td><b><%= o.getN_fattura() %></b></td>
                                         		
                                         		<td> <%= o.getStato() %> </td>
                                                <td><%= o.getUsername() %></td>
                                                <td><%= o.getDate() %></td>
                                                <td><%= o.getConsegna() %></td>
                                                
                                                
	      									
                                                 
                                                  
                                                <td>Totale <%= (float) o.getImporto() + "&euro;" %></td>
                                             
                                             <td><button id="carrello-button" class="btnn" >
				                              		<a class="text-light a-btt" style="color:white" href="Fattura?numeroOrdine=<%= o.getN_fattura()%>">Dettagli</a>
				                          		</button>
				                          		</td>
				                          	
				                          		<td><button id="aggiornastato" class="btnn">
				                              <a class="text-light a-btt" style="color:white" href="AggiornaStato?what=write&numero=<%= o.getN_fattura() %>">Aggiorna stato</a>
				                              </button>
				                				</td>
				                </tr>
				                
				                          		 <%
                        		}
	      					}%></table>
	      				
	      			<% 		if(!areAttivi){ 
	      				%>
								<h4>Nessun ordine attivo</h4>
	      				<% } %>
	      				
	     <div class="w3-container">
  		
  		<h3>Ordini completati</h3>
     	<table class="w3-table-all w3-hoverable" >		
        
        <tr class="w3-light-grey">	
        <th>Numero Ordine</th>
        <th>Stato</th>
        <th>Sottomesso da:</th>
        <th>Sottomesso il:</th>
        <th>Consegna prevista il:</th>
        <th>Totale ordine</th>
        <th></th>
        </tr>
	      				
	      				<%
	      					for(OrdineB o: ordini){
	      						if(o.getStato().equals(OrdineB.CONSEGNATO) && ordini.size()!=0){
	      							areChiusi=true;
	      				%>
	      				
	      				            <tr>
                                    <td><%= o.getN_fattura() %></td>
                                    <td> <%= o.getStato() %> </td>
                                    <td>Sottomesso il <%= o.getDate() %></td>
                                    <td>Sottomesso da <%= o.getUsername() %></td>
                                    <td>Consegnato il <%= o.getConsegna() %></td>
                                    <td>Totale <%= (float) o.getImporto() + "&euro;" %></td>
                                                
           							<td><button id="carrello-button" class="btnn">
           							<a class="text-light a-btt"  style="color:white" href="Fattura?numeroOrdine=<%= o.getN_fattura() %>">	
									Dettagli </a>	
				                    </button>
                                    </td>
                                    </tr>
                                  
                        <%
                        		}
	      					}
	      				%>
	      				
	      				</table>
	      				<br>
	      				<br>	
	      					<%if(!areChiusi){ 
	      				%>
								<h4>Nessun ordine chiuso</h4>
	      				<% } %>
	      			
<footer>
		<%@ include file="Footer.jsp" %>
</footer>
</body>
</html>
<% }}%>