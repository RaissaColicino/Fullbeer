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
<title>Gestione Ordini </title>
</head>
<body>
	<header>
			<%@ include file="Nav_bar_gestoreOrdini.jsp" %>
		</header> <br><br><br>
		
		
		<h1 >Ordini Aperti</h1>
	      				<%
	      					for(OrdineB o: ordini){
	      						if(o.getStato().equals(OrdineB.ELABORAZIONE) || o.getStato().equals(OrdineB.SPEDIZIONE)){
	      							areAttivi=true;
	      				%>
	      				<div>
	      				<a href="#">
                                                     Ordine <%= o.getN_fattura() %>
                                                  </a>
                                                
                                                <h5> <%= o.getStato() %> </h5>
                                                <h6>Sottomesso da <%= o.getUsername() %></h6>
                                                <h6>Sottomesso il <%= o.getDate() %></h6>
                                                <h6>Consegna prevista il <%= o.getConsegna() %></h6>
                                                
                                                <h4>Totale <%= (float) o.getImporto() + "&euro;" %></h4>
                                               <button id="carrello-button" class="btn btn-secondary bg-dark text-white">
				                              		<a class="text-light a-btt" href="Fattura?numeroOrdine=<%= o.getN_fattura()%>">
				                              			Dettagli
				                              		</a>
				                          		</button>
				                          		
				                          		<button id="carrello-button" class="btn btn-secondary bg-dark text-white">
				                              		<a class="text-light a-btt" href="AggiornaStato?what=write&numero=<%= o.getN_fattura() %>">
				                              			Aggiorna stato
				                              		</a>
				                          		</button><br><br>
				                </div>
				                          		 <%
                        		}
	      					}
	      				
	      					if(!areAttivi){ 
	      				%>
								<h4>Nessun ordine attivo</h4>
	      				<% } %>
	      				
	      				
	      				
	      				<h1>Ordini chiusi</h1>
	      				
	      				<%
	      					for(OrdineB o: ordini){
	      						if(o.getStato().equals(OrdineB.CONSEGNATO) && ordini.size()!=0){
	      							areChiusi=true;
	      				%>
	      				
	      				              <div>
                                                <h4>
                                                  <a class="title-prod" href="#">
                                                     Ordine <%= o.getN_fattura() %>
                                                  </a>
                                                </h4>
                                                <h5> <%= o.getStato() %> </h5>
                                                <h6>Sottomesso il <%= o.getDate() %></h6>
                                                <h6>Sottomesso da <%= o.getUsername() %></h6>
                                                <h6>Consegnato il <%= o.getConsegna() %></h6>
                                                
                                                <h4>Totale <%= (float) o.getImporto() + "&euro;" %></h4>
                                                
                                                <button id="carrello-button" class="btn btn-secondary bg-dark text-white">
				                              		<a class="text-light a-btt" href="Fattura?numeroOrdine=<%= o.getN_fattura() %>">
				                              			Dettagli
				                              		</a>
				                          		</button>
                                     </div>
                        <%
                        		}
	      					}
	      				
	      					if(!areChiusi){ 
	      				%>
								<h4>Nessun ordine chiuso</h4>
	      				<% } %>
</body>
</html>
<% }}%>