<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro</title>
</head>
<body>
	<a href="listagemclientes.jsp"><button>Voltar</button></a>
	<h1>Cadastro</h1>
    
    <form action="ServletController" method="post">
    <h2>Dados Pessoais</h2>
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome"><br><br>

    <label for="cpf">CPF:</label>
    <input type="text" id="cpf" name="cpf"><br><br>

    <label for="email">E-mail:</label>
    <input type="email" id="email" name="email"><br><br>
    
    <label for="senha">Senha:</label>
    <input type="password" id="senha" name="senha" required><br><br>

    <label for="genero">Gênero:</label>
    <select id="genero" name="genero">
      <option value="1">Masculino</option>
      <option value="2">Feminino</option>
      <option value="3">Outro</option>
    </select><br><br>

    <label for="dtNascimento">Data de Nascimento:</label>
    <input type="date" id="dtNascimento" name="dtNascimento"><br><br>
    
    <h2>Telefone</h2>
    <label for="ddd">DDD:</label>
    <input type="text" id="ddd" name="ddd"><br><br>

    <label for="numeroTelefone">Número:</label>
    <input type="text" id="numeroTelefone" name="numeroTelefone"><br><br>

    <label for="tipoTelefone">Tipo de Telefone:</label>
    <select id="tipoTelefone" name="tipoTelefone">
      <option value="2">Celular</option>
      <option value="1">Residencial</option>
    </select><br><br>
    
    <h2>Endereço de Entrega</h2>
    
    <label for="bairro">Bairro:</label>
    <select id="bairroEntrega" name="bairroEntrega">
      <option value="1">Jardins</option>
      <option value="2">Copacabana</option>
      <option value="3">Downtown</option>
      <option value="4">Hollywood</option>
    </select><br><br>
    
    <label for="cepEntrega">CEP:</label>
    <input type="text" id="cepEntrega" name="cepEntrega"><br><br>

    <label for="logradouroEntrega">Logradouro:</label>
    <input type="text" id="logradouroEntrega" name="logradouroEntrega"><br><br>
    
    <label for="tipoLogradouro">Tipo de Logradouro:</label>
    <select id="tipoLogradouroEntrega" name="tipoLogradouroEntrega">
      <option value="1">Rua</option>
      <option value="2">Avenida</option>
    </select><br><br>
    
    <label style="display:none;" for="tipoEndereco">Tipo de endereco:</label>
    <input style="display:none;" type="text" id="tipoEnderecoEntrega" name="tipoEnderecoEntrega" value="1">

    <label for="numeroEntrega">Número:</label>
    <input type="text" id="numeroEntrega" name="numeroEntrega"><br><br>

    <label for="tipoResidenciaEntrega">Tipo de Residência:</label>
    <select id="tipoResidenciaEntrega" name="tipoResidenciaEntrega">
      <option value="1">Casa</option>
      <option value="2">Apartamento</option>
    </select><br><br>
    
    <h2>Endereço de Cobrança</h2>
    
    <label for="bairro">Bairro:</label>
    <select id="bairroCobranca" name="bairroCobranca">
      <option value="1">Jardins</option>
      <option value="2">Copacabana</option>
      <option value="3">Downtown</option>
      <option value="4">Hollywood</option>
    </select><br><br>
    
    <label for="cepCobranca">CEP:</label>
    <input type="text" id="cepCobranca" name="cepCobranca"><br><br>

    <label for="logradouroCobranca">Logradouro:</label>
    <input type="text" id="logradouroCobranca" name="logradouroCobranca"><br><br>
    
    <label for="tipoLogradouro">Tipo de Logradouro:</label>
    <select id="tipoLogradouroCobranca" name="tipoLogradouroCobranca">
      <option value="1">Rua</option>
      <option value="2">Avenida</option>
    </select><br><br>
    
    <label style="display:none;" for="tipoEndereco">Tipo de endereco:</label>
    <input style="display:none;" type="text" id="tipoEnderecoCobranca" name="tipoEnderecoCobranca" value="2">

    <label for="numeroCobranca">Número:</label>
    <input type="text" id="numeroCobranca" name="numeroCobranca"><br><br>

    <label for="tipoResidenciaCobranca">Tipo de Residência:</label>
    <select id="tipoResidenciaCobranca" name="tipoResidenciaCobranca">
      <option value="1">Casa</option>
      <option value="2">Apartamento</option>
    </select><br><br>


	<h2>Endereço de Residencial</h2>
    
    <label for="bairro">Bairro:</label>
    <select id="bairroResidencial" name="bairroResidencial">
      <option value="1">Jardins</option>
      <option value="2">Copacabana</option>
      <option value="3">Downtown</option>
      <option value="4">Hollywood</option>
    </select><br><br>
    
    <label for="cepResidencial">CEP:</label>
    <input type="text" id="cepResidencial" name="cepResidencial"><br><br>

    <label for="logradouroResidencial">Logradouro:</label>
    <input type="text" id="logradouroResidencial" name="logradouroResidencial"><br><br>
    
    <label for="tipoLogradouro">Tipo de Logradouro:</label>
    <select id="tipoLogradouroResidencial" name="tipoLogradouroResidencial">
      <option value="1">Rua</option>
      <option value="2">Avenida</option>
    </select><br><br>
    
    <label style="display:none;" for="tipoEndereco">Tipo de endereco:</label>
    <input style="display:none;" type="text" id="tipoEnderecoResidencial" name="tipoEnderecoResidencial" value="3">

    <label for="numeroResidencial">Número:</label>
    <input type="text" id="numeroResidencial" name="numeroResidencial"><br><br>

    <label for="tipoResidenciaResidencial">Tipo de Residência:</label>
    <select id="tipoResidenciaResidencial" name="tipoResidenciaResidencial">
      <option value="1">Casa</option>
      <option value="2">Apartamento</option>
    </select><br><br>

    <input type="submit" value="Enviar">
  </form>
</body>
</html>