<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %> 
<%@ page import="beans.*" %>
<%! CatalogoB ctlg; 
	HashSet<ProdottoB> catalogo;
%>
<%
	ctlg=(CatalogoB) session.getAttribute("CatalogoDaGestire");
	catalogo=(HashSet<ProdottoB>) ctlg.getCatalogo();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestione Catalogo</title>
</head>
<body>


<% for(ProdottoB p: catalogo){%>
<p><%=p.getNome() %></p>
<button>
	<a href="ModificaProdottoAdmin.jsp?id=<%=p.getId()%>"> Modifica</a><br>
</button><button>
	<a href="RimuoviProdottoAdmin?id=<%=p.getId()%>"> Rimuovi </a>
</button>
	
<%}%>


</body>
</html>