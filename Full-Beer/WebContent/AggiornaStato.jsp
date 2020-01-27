<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="beans.*" %>
<%@ page import="java.util.*" %>

<% 
	Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		String ord="sottomissione desc";
		session.setAttribute("previousPage", "/OrdiniAttivi?order=" + ord);
		
		response.sendRedirect("./Login.jsp");
	}
	else{
		UtenteB userForRoleControl=(UtenteB) session.getAttribute("userLogged");
		if(!userForRoleControl.getRuolo().containsKey(RuoloB.ORDINI)){
			response.sendRedirect("./ErrorPage.html");
		}
		else{
			session.setAttribute("ruolo", RuoloB.ORDINI);
			
			OrdineB ordineDaModificare=(OrdineB) session.getAttribute("OrdineDaModificare");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica stat ordini</title>
</head>
<body>
   	<div class="main">
			<div class="col-md-6 col-sm-12">
            	<div class="login-form">

	<form name='modifica-stato' action="AggiornaStato" method="get">
               		    <input type="hidden" name="what" value="save" />
               		
               			<h3>Ordine n° <%= ordineDaModificare.getN_fattura() %></h3>
               			<h5>Sottomesso  da <%=ordineDaModificare.getUsername() %></h5>
               			<h5>Sottomesso il <%= ordineDaModificare.getDate() %></h5>
               			<h5>Consegna prevista il <%= ordineDaModificare.getConsegna() %></h5>
               			
               			<h5>Stato attuale dell'ordine</h5>
               			<select class="custom-select" name="scelta-stato" id="inputGroupSelect04">
  								<option selected><%= ordineDaModificare.getStato() %></option>
  								<% 
  									String statoAttuale=ordineDaModificare.getStato(); 
  									if(statoAttuale.equals(OrdineB.ELABORAZIONE)){
  								%>
							    <option value="<%= OrdineB.SPEDIZIONE %>"><%= OrdineB.SPEDIZIONE %></option>
							    <option value="<%= OrdineB.CONSEGNATO %>"><%= OrdineB.CONSEGNATO %></option>
							    <%
  									}
  									else if(statoAttuale.equals(OrdineB.SPEDIZIONE)){
							    %>
							    <option value="<%= OrdineB.CONSEGNATO %>"><%= OrdineB.CONSEGNATO %></option>
							    <%
  									}
							    %>
  							</select>
  							
  							<button  type="submit" class="btnn">Modifica</button>
                  		<button type="reset" >Annulla</button>
                  		<button>
                  			<%! String sttmDsc="sottomissione desc"; %>
				     	<a  href="OrdiniAttivi?order=<%= sttmDsc %>" style="text-decoration: none">	Indietro</a>
						</button>
  
              			</form>
              			</div>
              			</div>
              			</div>
<footer>
		<%@ include file="Footer.jsp" %>
</footer>
</body>
</html>
<%}}%>