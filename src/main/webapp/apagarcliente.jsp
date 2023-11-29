<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dominio.Cliente, controle.IFacade, controle.impl.Facade"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Apagar</title>

</head>
<body>
<%
	String id = request.getParameter("id");
	Cliente cliente = new Cliente();
	cliente.setId(Long.valueOf(id));
	
	IFacade fachada = new Facade();
	String resposta = fachada.apagar(cliente);
	
	if(resposta == null || resposta.isEmpty()) {
        out.println("<h1>APAGADO COM SUCESSO!</h1>");
    }else{
    	%> <h1> ${message} </h1> <%
    }
%>

<a href="listagemclientes.jsp"><button>Continuar</button></a>
</body>
</html>