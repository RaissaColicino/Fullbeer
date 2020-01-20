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
<link rel="stylesheet" href="css/Carrello.css">

</head>
<body>

<header>
<%@ include file="Nav_bar.jsp" %>
</header>

<h1 align="center" color"">Il tuo Carrello</h1>
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
				
				
					<table width="600" align="center" border="0">
			
			
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
			
			<% if(i.getQt()<i.getProdotto().getQt()){ %>
                          	
			<th>Quantità:</th>
			<th><input type="text" name="txtPrezzo" value="<%=i.getQt() %>" readonly></th>  
			    
			<th><button  class=btn> <a href="ModificaQt?action=plus&prodotto=<%= p.getId()%>"style="color:white">Aggiungi</a></button>
			
									<% }%><% if(i.getQt()!=1){ %>			
			<button  class=btn>	<a href="ModificaQt?action=minus&prodotto=<%= p.getId()%>"style="color:white">Elimina</a></button>
									<%}%></th> 
									
			</tr><tr><th></th><th>
			<button type="submit" class=btn><a href="RemoveProdottoCarrello?prodotto=<%= p.getId()%>"style="color:white">Rimuovi dal carrello</a></button></th>
	          		
					<%}%>
                          		<%}%>
			</tr><tr></tr></table>
			<br><br><br>
			
        		<div  align=center>
          			<button class=btn style="width:200px;"><a href="Acquisto.jsp"style="color:white">
            			<span >Procedi al pagamento</span>
          			</a></button>
          			
          			<button class=btn style="width:200px;"><a href="Catalogo"style="color:white">
            			<span>Compra altre Birre</span>
          			</a></button>
          			
         	 		<button class=btn  style="width:200px;"><a href="SvuotaCarrello"style="color:white" >
              			<span>Svuota il carrello</span>
          			</a></button>
        		</div>
			
</body>
</html>