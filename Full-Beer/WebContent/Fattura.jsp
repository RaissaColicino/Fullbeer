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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dettagli Ordine</title>
</head>
<body>
		<header>
			<% 
				String pg="";
			
				if(ruolo==null || ruolo.equals(RuoloB.UTENTE) || ruolo.equals(RuoloB.CATALOGO))
					pg="Nav_bar.jsp";
				else if(ruolo.equals(RuoloB.ORDINI))
					pg="Nav_bar_gestoreOrdini.jsp";			
			%>
			
			<jsp:include page="<%= pg %>" />		
		</header>
		
		<%
  			OrdineB ordine=(OrdineB) session.getAttribute("Ordine");
  			LinkedHashSet<ComposizioneB> comp=(LinkedHashSet<ComposizioneB>) ordine.getComposizione();
  		%>
  		  <table cellpadding="0" cellspacing="0">
	            <tr class="top">
	                <td colspan="4">
	                    <table>
	                        <tr>
	                            <td class="title">
	                                <img src="images/LogoSfondoBianco.png" style="width:60%; max-width:180px;">
	                            </td>
	                            
	                            <td>
	                                <b>eSport<br>
	                                Università degli studi di Salerno</b>
	                            </td>
	                        </tr>
	                    </table>
	                </td>
	            </tr>
	            
	            <tr class="information">
	                <td colspan="4">
	                    <table>
	                        <tr>
	                            <td>
	                                <b>Fattuna n° <%= ordine.getNumero() %></b><br>
	                                Data sottomissione <%= ordine.getSottomissione() %><br>

	                                Data consegna <%= ordine.getConsegna() %>
	                            </td>
	                            
	                            <td>
	                            	<%
	                            		UtenteB user=(UtenteB) session.getAttribute("UtenteFattura");
	                            		IndirizzoB indirizzo=user.getIndirizzi();
	                            		
	                            	%>
	                                <b>Cliente</b><br>
	                                <%= user.getNome() + " " + user.getCognome() %> <br>
	                                <%= indirizzo %><br>
	                                <%= user.getMail() %><br>
	                            </td>
	                        </tr>
	                    </table>
	                </td>
	            </tr>
	            
	            <tr class="heading">
	                <td>Prodotto</td>
	                <td>Quantità</td>
	                <td>Prezzo</td>
	               
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