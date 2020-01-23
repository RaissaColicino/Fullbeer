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
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<title>Full-Beer Carrello</title>
<link rel="stylesheet" href="css/Carrello.css">

</head>
<body>

<header>
<%@ include file="Nav_bar.jsp" %>
</header>



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
					%>
				<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">E-COMMERCE CART</h1>
     </div>
</section>

<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"> </th>
                            <th scope="col">Prodotto</th>
                             <th scope="col" class="text-center">Quantità</th>
                            <th scope="col" class="text-right">Prezzo</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>
                 <%    for(CarrelloItem i: items){
						ProdottoB p=i.getProdotto();
						imgString="images/" + p.getImmagine() + ".jpg"; 
				%> 
				 <tr>
                            <td><img src="img/<%=p.getImmagine()%>" style="width:20%"> </td>
                            <td><%=p.getNome() %></td>
                            
                      	
                            
                     <td>
                     <% if(i.getQt()<i.getProdotto().getQt()){ %>
                    <span id="sp-qt"><%= i.getQt() %></span>  </th> 
                    <button class="w3-button w3-xsmall w3-black"><a href="ModificaQt?action=plus&prodotto=<%= p.getId()%>"style="color:white">+</a></button>
                     	
			
									<% }%><% if(i.getQt()!=1){ %>		
				<button class="w3-button w3-xsmall w3-black"><a href="ModificaQt?action=minus&prodotto=<%= p.getId()%>"style="color:white">-</a></button>	
				
									<%}%></td> 
				
				 <td class="text-right"><%=p.getPrezzo() %></td>
				 
				<td class="text-right"><button type="submit" class="btn btn-sm btn-danger"><a href="RemoveProdottoCarrello?prodotto=<%= p.getId()%>"style="color:white"><i class="fa fa-trash"></i></a></button></td>
                        </tr>
                    	<%}%>
                          		<%}%>
                          		<tr>
                          		
                          		  <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><strong>Totale</strong></td>
                            <td class="text-right"><%= ct %><strong></strong></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-6">
                    <button class="btn btn-block btn-light"><a href="Catalogo"style="color:white">Continua lo Shopping</a></button>
                </div>
                <div class="col-sm-12 col-md-6 text-right">
                    <button class="btn btn-lg btn-block btn-success text-uppercase"><a href="Acquisto.jsp" style="color:white">Procedi al pagamento</a></button>
                </div>
            </div>
        </div>
    </div>
</div> 
<a href="SvuotaCarrello" class="list-group-item bb">
              			<span class="a-nsp">Svuota il carrello</span>
 </a>
 <footer>
		<%@ include file="Footer.jsp" %>
</footer>   
</body>
</html>