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
					pg="NavbarUtente.jsp";
				else if(ruolo.equals(RuoloB.ORDINI))
					pg="NavbarOrdini.jsp";			
			%>
			
			<jsp:include page="<%= pg %>" />		
		</header>
		
		<%
  			OrdineB ordine=(OrdineB) session.getAttribute("Ordine");
  			LinkedHashSet<ComposizioneB> comp=(LinkedHashSet<ComposizioneB>) ordine.getComposizione();
  		%>
</body>
</html>
<%} %>