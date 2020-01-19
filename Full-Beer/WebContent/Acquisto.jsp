<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="beans.*" %>
<%@	page import="java.util.*" %>
  <%@ page import="beans.IndirizzoB" %>
    
    <% 
	String ruolo=(String) session.getAttribute("ruolo");
	String CATALOGO="Catalogo";
	String ORDINI="Ordini";
	String UTENTE="Utente"; %>
<%
	Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		session.setAttribute("previousPage", "/Acquisto.jsp");
		
		response.sendRedirect(request.getContextPath() + "/Login.jsp");
	}else{
  		UtenteB user=(UtenteB) session.getAttribute("userLogged");
  		LinkedHashSet<IndirizzoB> indirizzi=(LinkedHashSet<IndirizzoB>) user.getIndirizzi();
  		float ct=0;
  		CarrelloB carrello;
  		HashSet<CarrelloItem> items;
  		carrello=(CarrelloB) session.getAttribute("Carrello");
  		ct=(float)session.getAttribute("costoTotale");
  		if(carrello!=null)
  			
  		%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Acquisto</title>
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
</header>

<form name='acquisto' action="SottomissioneOrdine" method="post">
						<h5>Indirizzo di spedizione</h5>
                   		<div class="input-group">
  							<select class="custom-select" name="indirizzo" id="indirizzo">
							    <option selected>Scegli un indirizzo di spedizione</option>
							    <% 
							    	for(IndirizzoB indirizzo: indirizzi){
							    %>
							    <option value= "<%= indirizzo.getUsername() %> "> <%= indirizzo %> </option>
							    <% } %>
  							</select>
						</div>
						<br><button type="submit" class="btn btn-black">Procedi</button>
                  		<button type="reset" class="btn btn-secondary">Annulla</button>
                  		<button class="btn btn-danger">
							<a class="text-light no-dec" href="Carrello.jsp" style="text-decoration: none">
								Indietro
							</a>
						</button>
               		</form>
               								<h1>Riepilogo Ordine</h1>
						<table border=1>
						<% String imgString="";
						items =(HashSet<CarrelloItem>) carrello.getCarrello();
						for (CarrelloItem i: items){
							ProdottoB p=i.getProdotto();
								imgString="images/" + p.getImmagine() + ".jpg"; 
								
						%>
						<tr>
						<th>
						<%=imgString %>
						</th><th>
						<%=p.getNome() %><br>
						Prezzo:<%=p.getPrezzo()%><br>
						Quantità:<%=i.getQt() %>
						</th></tr>
						<% } %>
						<tr><th>
						Totale: <%= ct %>
						<th></tr>
						</table>
						<br>
				
</body>
</html>
<%} %>