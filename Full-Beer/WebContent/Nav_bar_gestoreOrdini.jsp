<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
   <%@ page import="beans.*"%>

<%
	Boolean userAuth = (Boolean) session.getAttribute("userAuth");
	String ruoloToChange = (String) session.getAttribute("ruolo");
	
	UtenteB user = (UtenteB) session.getAttribute("userLogged");
%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/Nav_bar.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>



  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>



<title>Gestore Ordini</title>
</head>
<body>
<header class="header clearfix">
<a href="" class="header_icon">
<ul class="header_menu">	

		<li class="header_menu_item"><a href="Homepage.jsp"style="color:white">Home</a></li>
		<li class="header_menu_item"><a href="Ordine?toDo=gestore"style="color:white">Visualizza Ordini</a></li>
	    <li class="header_menu_item"><a href="OrdiniAttivi"style="color:white">Gestisci Ordini</a></li>
		<li class="header_menu_item"><a href="AreaUtente.jsp"style="color:white">Area Utente</a></li>
		<li class="header_menu_item"> <a href="Logout"style="color:white">LOGOUT</a></li>

		<br>
		<br>	
			<%
				if (user.getRuolo().containsKey(RuoloB.CATALOGO) || user.getRuolo().containsKey(RuoloB.ORDINI)) {
			%>
		<li class="dropdown" align="left" >
		<button class="btn btn primary dropdown toggle" tybe="button" data-toggle="dropdown" href="#">Permessi
		<span class="caret"></span></button>
		<ul class="dropdown-menu">
		</li>
		
		<%
						for (RuoloB r : user.getRuolo().values()) {
					%>
							<li>
						<% if(r.getRuolo().equals(RuoloB.CATALOGO) || r.getRuolo().equals(RuoloB.ORDINI)) { %>
							<a href="Ruolo?permesso=<%=r.getRuolo()%>">Gestore <%=r.getRuolo()%></a></li>
						<%}
						   else{
						%>
							<li><a href="Ruolo?permesso=<%=r.getRuolo()%>"><%=r.getRuolo()%></a></li>
						<% }
						   }
						}
					%>
	</ul>
	</div>
		<li class="header_menu_item">
		<div class= "search-box">
		<input class="search-txt" type="text" name="cerca" placeholder="Ricerca">
		<a class="search-btn" href="" ><span class="icon-search"></span></a>
		</div></li>	
</ul>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$(".header_icon").click(function(e){ /* aggiungo l'evento alla funzione per non far ricaricare la agina al click*/
		$(".header_menu").toggleClass('is-open');
	    e.preventDefault(); 
	});
			
});
</script>
</body>
</html>