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

<div class="container">
    <div class="row">
        <div class="col-xs-12">
    		<div class="invoice-title">
    			<h2>Fattura</h2><h3 class="pull-right">Ordine:<%= ordine.getN_fattura() %></h3>
    		</div>
    		<hr>
    		<div class="row">
    			<div class="col-xs-6">
    				<address>
    				<strong>Fatturata da:</strong><br>
    					Fullbeer<br>
    					mail. fullbeer@libero.it<br>
    					Tel. 3200642373<br>
    					Montemarano,CAP 83040
    				</address>
    			</div>
    			<%
	                            		UtenteB user=(UtenteB) session.getAttribute("UtenteFattura");
	                            	                            		
	                            	%>
    			<div class="col-xs-6 text-right">
    				<address>
        			<strong>Destinatario:</strong><br>
    					 <%= user.getNome() + " " + user.getCognome() %><br>
    					   <%= user.getMail() %><br>
    					MacchiadelMonte 14 (AV)<br>
    					
    				</address>
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-xs-6">
    				<address>
    					<strong>Metodo di Pagamento:</strong><br>
    					Pagamento alla Consegna<br>
    					
    				</address>
    			</div>
    			<div class="col-xs-6 text-right">
    				<address>
    					<strong>Data Sottomissione</strong><br>
    					<%= ordine.getDate() %><br><br>
    				</address>
    			</div>
    		</div>
    	</div>
    </div>
    
    <div class="row">
    	<div class="col-md-12">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Riepilogo Ordine</strong></h3>
    			</div>
    			<div class="panel-body">
    				<div class="table-responsive">
    					<table class="table table-condensed">
    						<thead>
    					
                                <tr>
        							<td><strong>Prodotto</strong></td>
        							<td class="text-center"><strong>Prezzo</strong></td>
        							<td class="text-center"><strong>Quantità</strong></td>
        							<td class="text-right"><strong>Totale</strong></td>
                                </tr>
    						</thead>
    						
    						<tbody>
    							   <%
	        
	            	boolean done=false;
    							   
    							   
	            	for(ComposizioneB c: comp){
	            		double ct= 0;
	            		ct= c.getPrezzo()*c.getQuantità();
	            					%>
    							<!-- foreach ($order->lineItems as $line) or some such thing here -->
    							<tr>
    								<td><%=c.getNome_prodotto()%></td>
    								<td class="text-center"><%= c.getPrezzo() %></td>
    								<td class="text-center"><%= c.getQuantità() %></td>
    								<td class="text-right"><%=ct %></td>
    							</tr>
    							<%} %>
                                <tr>
    								<td class="no-line"></td>
    								<td class="no-line"></td>
    								<td class="no-line text-center"><strong>Total</strong></td>
    								<td class="no-line text-right"><%= (float) ordine.getImporto() + "&euro;" %></td>
    							</tr>
    						</tbody>
    					</table>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
</div>

</body>
</html>
<%} %>