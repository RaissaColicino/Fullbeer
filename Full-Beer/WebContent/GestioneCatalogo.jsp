<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %> 
<%@ page import="beans.*" %>

<% 
	Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		session.setAttribute("previousPage", "/GestioneCatalogo?tipo=Divisa&order=nome");
		response.sendRedirect("./Login.jsp");
	}
	else{
		UtenteB userForRoleControl=(UtenteB) session.getAttribute("userLogged");
		if(!userForRoleControl.getRuolo().containsKey(RuoloB.CATALOGO)){
			response.sendRedirect("./ErrorPage.html");
		}
		else{
			session.setAttribute("ruolo", RuoloB.CATALOGO);

%>
<%  
String ruolo=(String) session.getAttribute("ruolo");
String CATALOGO="Catalogo";
String ORDINI="Ordini";
String UTENTE="Utente";
%>
<%!
CatalogoB ctlg; 
HashSet<ProdottoB> catalogo;
%>
<%
	ctlg=(CatalogoB) session.getAttribute("CatalogoDaGestire");
	catalogo=(HashSet<ProdottoB>) ctlg.getCatalogo();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/Catalogo.css">
<title>Gestione Catalogo</title>
</head>
<body>
<header>
<%@ include file="NavBarCatalogo.jsp" %>		
</header>


<table class="table" align="center" width="60%" >
	<%	int salto=0;
		int i=0; %>
	
	<% for(ProdottoB p: catalogo){%>
			<% i++;
					%>
		 <!-- ContenitoreFOto-->
		<td width="200" heigth="200"> 
		<span  class="card">
     <img src="img/<%=p.getImmagine()%>" style="width:100%">
  			   <h1><%=p.getNome()%></h1>
  			   <p class="prezzo"><%=p.getPrezzo()%></p>
  			   <p><%=p.getDescrizione()%></p>
  			   
  	<button><a href="ModificaProdottoAdmin.jsp?id=<%=p.getId()%>"style="color:white"> Modifica </a></button>
  	<br>
  	<br>
  	<button><a href="RimuoviProdottoAdmin?id=<%=p.getId()%>"style="color:white"> Elimina </a></button>
	</span>
	</td>
	
	<%
	salto++;
	if(salto==3){
	%>
	<tr>
	<%
	salto=0;
	}}%>
	
	</table>
	
<footer>
		<%@ include file="Footer.jsp" %>
</footer>
	
</body>
</html>
<% }}%>