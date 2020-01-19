<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@page import= "beans.*" %> 
    <% String ruolo=(String) session.getAttribute("ruolo");
       String CATALOGO="Catalogo";
       String ORDINI="Ordini";
       String UTENTE="Utente";
    %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">
<link rel="stylesheet" href="css/Homepage.css">

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

<h2 align="center">Fullbeer</h2>
<div class="container">
  <img src="img/slider.jpg" alt="Nature" style="width:100%;" width="400" height="600">
  <div class="text-block">
    <h4>Fullbeer</h4>
    <p>L&apos;acqua puo&#769; diventare anche una buona bevanda,se mescolata con malto e luppolo!</p>
  </div>
</div>
<br>
<br>
<br>
<br>
<br>
<br>

<div >
<table align="center" class="imgbirre">
<tr>
<td><img  src="img/login1.jpg" width="300" height="300" class="ex"></td>
<td><strong>La nostra Azienda seleziona le migliori birre al mondo.<br>Per saperne di più visita il nostro Shop</strong><br><br><br>
<button class="btn" >
<a href="Prodotto.jsp">Vai allo Shop</a>
</button>
</td>

<td><img  src="img/birrae.jpg" width="300" height="300" class="ex1"></td>
<td><strong>Clicca qui per saperne di più.</strong><br><br><br>
<button class="btnn" >
<a href="ChiSiamo.jsp">Chi Siamo</a>
</button>
</td>
</tr>

</table>
</div>



<script>
 $(document).ready(function(){
	      $(".btn").mouseenter(function(){
	      $(".btn").css("background-color", "orange");
	      
	  });
	      $(".btn").mouseleave(function(){
          $(".btn").css("background-color", "#C0C0C0"); 
     });
	  });
 
	  </script>	
	  <script>
 $(document).ready(function(){
	      $(".btnn").mouseenter(function(){
	      $(".btnn").css("background-color", "orange");
	      
	  });
	      $(".btnn").mouseleave(function(){
          $(".btnn").css("background-color", "#C0C0C0"); 
     });
	  });
 
	  </script>	
	  <script>
$(document).ready(function(){
			 $(".ex").mouseenter(function(){ //ex query utilizzata per ingrandire le img al passaggio del mouse
	       
			 $(".ex").css("opacity","0.6");
			 });
			  $(".ex").mouseleave(function(){
			  
			  $(".ex").css("opacity","1.0");
			  });
			});
		
</script>
	  <script>
$(document).ready(function(){
			 $(".ex1").mouseenter(function(){ //ex query utilizzata per ingrandire le img al passaggio del mouse
	       
			 $(".ex1").css("opacity","0.6"); //# per prendere l'id nell css
			 });
			  $(".ex1").mouseleave(function(){
			  
			  $(".ex1").css("opacity","1.0");
		   
			  });
			});
		
</script>


</body>
</html>