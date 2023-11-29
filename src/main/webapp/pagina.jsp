<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	<%

    String message = (String) request.getAttribute("message");
    if(message == null || message.isEmpty()) {
        out.println("<h1>SALVO COM SUCESSO!</h1>");
    }else{
    	%> <h1> ${message} </h1> <%
    }
%>
<a href="listagemclientes.jsp"><button>Continuar</button></a>
</body>
</html>