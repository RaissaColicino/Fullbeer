<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
   <%@ page import="beans.UtenteB" %>
   <%@ page import="java.util.*" %>
   <%@ page import="beans.IndirizzoB" %>
    
    <% 
	String ruolo=(String) session.getAttribute("ruolo");
	String CATALOGO="Catalogo";
	String ORDINI="Ordini";
	String UTENTE="Utente";
	Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		session.setAttribute("previousPage", "/AreaUtente.jsp");
		response.sendRedirect("./Login.jsp");
	}else{
		UtenteB user=(UtenteB) session.getAttribute("userLogged");
		LinkedHashSet<IndirizzoB> indirizzi=(LinkedHashSet<IndirizzoB>) user.getIndirizzi();
		%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="css/ordine.css">
<title>AreaUtente</title>
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



	<h1 align="center">Benvenuto nella tua AraeUtente, <%=user.getNome() %> <%=user.getCognome() %>
	<h2>I tuoi dati</h2>
			
			<table width="600" align="left" border="1">

			<tr>
			<th>Username</th>
			<th><%= user.getUsername() %></th>
			</tr>
			
			<tr>
			<th>Nome</th>
			<th><%= user.getNome() %></th>
			</tr>
			
			<tr>
			<th>Cognome</th>
			<th><%=user.getCognome() %></th>
			</tr>
			
			<tr>
			<th>Mail</th>
			<th> <%= user.getMail() %> </th>
			</tr>
				<tr>
			<th>Password</th>
			<th> <%= user.getPassword() %> </th>
			</tr>
			
			<tr>
			<th> Indirizzo</th>
			<th>	<%for(IndirizzoB indirizzo : indirizzi){ %>
			<%=indirizzo.getCap()%><br>
			<%=indirizzo.getCitta()%>-<%=indirizzo.getVia()%><br>
			
			<% %>
			<%}  %></th>
			
			
			</tr>
			
			</table>
		
		
		<button type="submit" class=btnn><a href="OrdineUtente.jsp" style="color:white;" >I miei ordini</button>
		<button type="submit" class=btnn><a href="Logout"style="color:white;" text-decoration="none">LOGOUT</a></button>
		
</body>
</html>
<%}%>