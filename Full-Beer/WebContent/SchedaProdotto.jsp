<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="beans.*" %>
<%@ page import="java.util.*" %>
<%ProdottoB p=(ProdottoB) session.getAttribute("ProdottoDaMostrare"); %>


<!------ Include the above in your HEAD tag ---------->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dettagli Prodotto</title>
</head>
<body>
<header>
<%@ include file="Nav_bar.jsp" %>
</header>
<br><br>



   <p>  <%=p.getNome()%></p>
   <p>  <%=p.getPrezzo()%> </p>
    <p> <%p.getDescrizione();%></p>



<br><br>
<footer>
		<%@ include file="Footer.jsp" %>
</footer>
</body>
</html>