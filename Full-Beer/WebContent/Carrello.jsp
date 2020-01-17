<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*" %> 
<%@ page import="beans.*" %>
    <%! 
	CarrelloB carrello;
	HashSet<CarrelloItem> items;
	double costoTotale=0 ;
	float ct=0; %>
	
	<%
	String UTENTE="Utente";
	session.setAttribute("ruolo", UTENTE);
	carrello=(CarrelloB) session.getAttribute("Carrello");
	if(carrello!=null)
		items=(HashSet<CarrelloItem>) carrello.getCarrello();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Full-Beer Carrello</title>
</head>
<body>
<header>
<%@ include file="Nav_bar.jsp" %>
</header>
<h1>Riepilogo</h1>

<%				costoTotale=0;
        	     if(carrello!=null && !carrello.isEmpty()){	
        						for(CarrelloItem i: items){
        						ProdottoB tp=i.getProdotto();
        						double initprezzo=tp.getPrezzo();
        						double prezzo=0;
        						
        						prezzo=initprezzo*i.getQt();
        						costoTotale+=prezzo;
        						ct=(float)costoTotale;
        						}
        						} 	
        						session.setAttribute("costoTotale",ct);
        						%>
        						
      <% 
		        	if(carrello==null || carrello.isEmpty()){
		        %>
		        	
		        	<div class="not-cart">
		        		<h1>Carrello vuoto</h1>
		        	</div>
		        	
		        <% }else{ 
		         		        
					String imgString="";
					for(CarrelloItem i: items){
						ProdottoB p=i.getProdotto();
						imgString="images/" + p.getImmagine() + ".jpg"; 
				%> 
				
				
					<table border="0" width="500" align="center">
			
			
			<tr>
			
			<th rowspan="5"><img src= <%=imgString %>"width="140" height="140"></th>
			<th> Codice prodotto:</th>
			<th><input type="text" name="txtId" value="<%=p.getId() %>" readonly></th>
			
			</tr><tr>
			
			<th>Prodotto:</th>
			<th><input type="text" name="txtNome" value="<%=p.getNome() %>" readonly></th>
			
			</tr><tr>
			
			<th>Prezzo:</th>
			<th><input type="text" name="txtPrezzo" value="<%=p.getPrezzo() %>" readonly></th>
			
			</tr><tr>
			
			<p>
                          			<span id="sp-qt">Quantità acquisto <%= i.getQt() %></span>
                          			&nbsp;
                          			<% if(i.getQt()<i.getProdotto().getQt()){ %>
                          			
									<a href="ModificaQt?action=plus&prodotto=<%= p.getId()%>">Aggiungi<a>
									<% }
                          			 if(i.getQt()!=1){ %>
										<a href="ModificaQt?action=minus&prodotto=<%= p.getId()%>">Elimina</a>
										<%}%>
										<a class="text-light a-btt" href="RemoveProdottoCarrello?prodotto=<%= p.getId()%>">
                              			Rimuovi dal carrello
                              		</a>
                          		</p>
                          		
                              		
					<%}%>
                          		<%}%>
			</tr><tr></tr></table>
			<br><br><br>
			
        		<div id="opt-cart" class="list-group">
          			<a href="Acquisto.jsp" class="list-group-item bb">
            			<span class="a-sp">Procedi al pagamento</span>
          			</a>
          			<br>
          			<a href="Catalogo" class="list-group-item bb">
            			<span class="a-nsp">Compra altre Birre</span>
          			</a>
          			<br>
         	 		<a href="SvuotaCarrello" class="list-group-item bb">
              			<span class="a-nsp">Svuota il carrello</span>
          			</a>
        		</div>
			
</body>
</html>