<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dao.ClienteDAO, dao.EnderecoDAO, dominio.Endereco, dominio.EntidadeDominio, dominio.Cliente, java.util.List, java.util.Optional"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Cliente</title>
</head>
<body>
<%
        String clienteID = request.getParameter("id");
        ClienteDAO clientedao = new ClienteDAO();
        
        Cliente cliente = (Cliente) clientedao.consultarPeloId(Long.valueOf(clienteID));   
        EnderecoDAO enderecoDao = new EnderecoDAO();
        
        List<EntidadeDominio> enderecos = enderecoDao.consultar(Optional.of(cliente));
        
    %>
	<div style="display:flex;">
		<a href="listagemclientes.jsp"><button>Voltar</button></a>   
	    <a href="adicionarenderecocobranca.jsp?clienteid=<%= cliente.getId() %>"><button>Adicionar endereco de cobrança</button></a>   
	    <a href="adicionarenderecoentrega.jsp?clienteid=<%= cliente.getId() %>"><button>Adicionar endereco de entrega</button></a>
	</div>
        
    <h1>Editar Cliente</h1>
    
    <form id="form" action="AlterarClienteServletController" method="post">
        <h2>Dados Pessoais</h2>
        <input type="text" id="id" name="id" style="display:none;" value="<%= cliente.getId() %>">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="<%= cliente.getNome() %>"><br><br>

        <label for="cpf">CPF:</label>
        <input type="text" id="cpf" name="cpf" value="<%= cliente.getCpf() %>"><br><br>

        <label for="email">E-mail:</label>
        <input type="email" id="email" name="email" value="<%= cliente.getEmail() %>"><br><br>
        
        <label for="senha">Senha:</label>
        <input type="password" id="senha" name="senha" value=""><br><br>

        <label for="genero">Genero:</label>
        <select id="genero" name="genero">
            <% String genero = cliente.getGenero().getId().toString(); %>
            <option value="1" <%= (genero != null && genero.equals("1")) ? "selected" : "" %>>Masculino</option>
            <option value="2" <%= (genero != null && genero.equals("2")) ? "selected" : "" %>>Feminino</option>
            <option value="3" <%= (genero != null && genero.equals("3")) ? "selected" : "" %>>Outro</option>
        </select><br><br>

        <label for="dtNascimento">Data de Nascimento:</label>
        <input type="date" id="dtNascimento" name="dtNascimento" value="<%= cliente.getDtNascimento() %>"><br><br>
        
        <h2>Telefone</h2>
        <input type="text" id="telefoneId" name="telefoneId" style="display:none;" value="<%= cliente.getTelefone().getId() %>">
    	<label for="ddd">DDD:</label>
    	<input type="text" id="ddd" value="<%= cliente.getTelefone().getDdd() %>" name="ddd"><br><br>

    	<label for="numeroTelefone">Númeromero:</label>
    	<input type="text" id="numeroTelefone" value="<%= cliente.getTelefone().getNumero() %>" name="numeroTelefone"><br><br>

	    <label for="tipoTelefone">Tipo de Telefone:</label>
	    <select id="tipoTelefone" name="tipoTelefone">
	      	<option <%= (cliente != null && cliente.getTelefone() != null && cliente.getTelefone().getTipo().getId() == 2) ? "selected" : "" %> value="2">Celular</option>	      	
      		<option <%= (cliente != null && cliente.getTelefone() != null && cliente.getTelefone().getTipo().getId() == 1) ? "selected" : "" %> value="1">Residencial</option>
    	</select><br><br>
    	
        <input type="submit" value="alterar">
    	
    	<h2>Endereços</h2>

    <% 
    int cont = 0;
        for (EntidadeDominio enderecoEntidade : enderecos) {
            if (enderecoEntidade instanceof Endereco) {
                Endereco endereco = (Endereco) enderecoEntidade;
                cont++;
    	%>
    	<h4>Endereco <%= cont %></h4>
    	<label for="tipoEndereco">Tipo de endereco:</label>
    <input disabled  type="text" id="tipoEnderecoEntrega" name="tipoEnderecoEntrega" value="<%= endereco.getTipoEndereco() %>">
    	
                <label for="bairro">Bairro:</label>
    <select disabled>
    	<option <%= (endereco != null && endereco.getBairro() != null && endereco.getBairro().getId() == 1) ? "selected" : "" %>>Jardins</option>
    	<option <%= (endereco != null && endereco.getBairro() != null && endereco.getBairro().getId() == 2) ? "selected" : "" %>>Copacabana</option>
    	<option <%= (endereco != null && endereco.getBairro() != null && endereco.getBairro().getId() == 3) ? "selected" : "" %>>Downtown</option>
    	<option <%= (endereco != null && endereco.getBairro() != null && endereco.getBairro().getId() == 4) ? "selected" : "" %>>Hollywood</option>
      
    </select><br><br>
    
    <label for="cepEntrega">CEP:</label>
    <input disabled type="text" id="cepEntrega" name="cepEntrega" value="<%= endereco.getCep() %>"><br><br>

    <label for="logradouroEntrega">Logradouro:</label>
    <input disabled type="text" id="logradouroEntrega" name="logradouroEntrega" value="<%= endereco.getLogradouro() %>"><br><br>
    
    <label for="tipoLogradouro">Tipo de Logradouro:</label>
    <select disabled id="tipoLogradouroEntrega" name="tipoLogradouroEntrega">
    <option <%= (endereco != null && endereco.getBairro() != null && endereco.getTipoLogradouro().getId() == 1) ? "selected" : "" %>>Rua</option>
    <option <%= (endereco != null && endereco.getBairro() != null && endereco.getTipoLogradouro().getId() == 2) ? "selected" : "" %>>Avenida</option>
    </select><br><br>
    
    <label for="numeroEntrega">Número:</label>
    <input disabled type="text" id="numeroEntrega" name="numeroEntrega" value="<%= endereco.getNumero() %>"><br><br>

    <label for="tipoResidenciaEntrega">Tipo de Residência:</label>
    <select disabled id="tipoResidenciaEntrega" name="tipoResidenciaEntrega">
    	<option value="<%= endereco.getTipoResidencia().getId() %>"><%= endereco.getTipoResidencia() %></option>
      <option value="1">Casa</option>
      <option value="2">Apartamento</option>
    </select><br><br>
    	<% 
    	        }
	        }
    	%>
        
    </form>
    
</body>
</html>
