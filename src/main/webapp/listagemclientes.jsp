<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Listagem</title>
    <script>
        function carregarClientes() {
            fetch('ServletController') // Ajuste o nome da servlet conforme necessário
            .then(response => response.text())
            .then(data => {
                document.getElementById("tabelaClientes").innerHTML = data;
            })
            .catch(error => console.error('Erro:', error));
        }

        window.onload = function() {
            carregarClientes();
        }
    </script>
</head>
<body>
    <h1>Lista de Usuários</h1>
    <a href="cadastrar.jsp"><button>Cadastrar</button></a>
    <br>
    <br>
    <table border="1" id="tabelaClientes">
    </table>
</body>
</html>