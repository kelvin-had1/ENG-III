<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dominio.Cliente" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adicionar endereço de entrega</title>
</head>
<body>
<% 
	String clienteId = request.getParameter("clienteid");

	
%>
<form action="AdicionarEnderecoEntregaServletController" method="post">
	<h2>Endereço</h2>
    <input value="<%= clienteId %>" id="clienteid" name="clienteid" style="display:none;">
    <label for="bairro">Bairro:</label>
    <select id="bairro" name="bairro" required>
      <option value="1">Jardins</option>
      <option value="2">Copacabana</option>
      <option value="3">Downtown</option>
      <option value="4">Hollywood</option>
    </select><br><br>
    
    <label for="cep">CEP:</label>
    <input type="text" id="cep" name="cep" required><br><br>

    <label for="logradouro">Logradouro:</label>
    <input type="text" id="logradouro" name="logradouro" required><br><br>
    
    <label for="tipoLogradouro">Tipo de Logradouro:</label>
    <select id="tipoLogradouro" name="tipoLogradouro" required>
      <option value="1">Rua</option>
      <option value="2">Avenida</option>
    </select><br><br>
    
    <label for="numero">Número:</label>
    <input type="text" id="numero" name="numero"  required><br><br>

    <label for="tipoResidencia">Tipo de Residência:</label>
    <select id="tipoResidencia" name="tipoResidencia" required>
      <option value="1">Casa</option>
      <option value="2">Apartamento</option>
    </select><br><br>
    
    <input type="submit" value="Salvar">
</form>
</body>
</html>