<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
   <%@ page import="beans.UtenteB" %>
   
    <% 
	String ruolo=(String) session.getAttribute("ruolo");
	String CATALOGO="Catalogo";
	String ORDINI="Ordini";
	String UTENTE="Utente";
	Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		session.setAttribute("previousPage", "/Profilo.jsp");
		response.sendRedirect("./Login.jsp");
	}
	else{
		UtenteB user=(UtenteB) session.getAttribute("userLogged");
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/Nav_bar.css">
<title>Insert title here</title>
</head>
<body>
<header>
<header class="header clearfix">
<a href="" class="header_icon">
	<div ></div>
  	<div ></div>
  	<div ></div>  
 </a> <!-- link che contiene l'icona del menu -->

	<ul class="header_menu">	
		<li class="header_menu_item"><a href="Home.jsp">HomePage</a></li>
		<li class="header_menu_item"><a href="Catalogo.jsp">Birre</a></li>
		<li class="header_menu_item"><a href="OrdineUtente.jsp">Visualizza Ordini</a></li>
		<li class="header_menu_item"><a href="Carrello.jsp">Carrello</a></li>
		<li class="header_menu_item">
		<div class= "search-box">
		<input class="search-txt" type="text" name="cerca" placeholder="Ricerca">
		<a class="search-btn" href="" ><span class="icon-search"></span></a>
		</div></li>	
	</ul>
</header>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$(".header_icon").click(function(e){ /* aggiungo l'evento alla funzione per non far ricaricare la agina al click*/
		$(".header_menu").toggleClass('is-open');
	    e.preventDefault(); 
	});
			
});
</script>
</header>
<h1><%=user.getNome() %><br>
	<%=user.getCognome() %>

</body>
</html>
<%}%>