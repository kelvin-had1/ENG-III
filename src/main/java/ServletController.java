import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import controle.IFacade;
import controle.impl.Facade;
import dominio.Bairro;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Genero;
import dominio.Telefone;
import dominio.TipoEndereco;
import dominio.TipoLogradouro;
import dominio.TipoResidencia;
import dominio.TipoTelefone;

public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IFacade fachada = new Facade();
      
    public ServletController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Cliente clienteInstance = new Cliente();
        clienteInstance.setId(0L);
		List<EntidadeDominio> clientes = this.fachada.consultar(clienteInstance);
	
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Nome</th>");
        out.println("<th>Email</th>");
        out.println("<th>Ações</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        for (EntidadeDominio entidade : clientes) {
            if (entidade instanceof Cliente) {
                Cliente cliente = (Cliente) entidade;
                out.println("<tr>");
                out.println("<td>" + cliente.getNome() + "</td>");
                out.println("<td>" + cliente.getEmail() + "</td>");
                out.println("<td>");
                out.println("<a href='editarusuario.jsp?id=" + cliente.getId() + "'>Editar</a>");
                out.println("<a href='apagarcliente.jsp?id=" + cliente.getId() + "'>Apagar</a>");
                out.println("</td>");
                out.println("</tr>");
            }
        }
        out.println("</tbody>");

        out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String genero = request.getParameter("genero");
        String dtNascimento = request.getParameter("dtNascimento");
        
        String bairroEntrega = request.getParameter("bairroEntrega");
        String cepEntrega = request.getParameter("cepEntrega");
        String logradouroEntrega = request.getParameter("logradouroEntrega");
        String tipoLogradouroEntrega = request.getParameter("tipoLogradouroEntrega");
        String numeroEntrega = request.getParameter("numeroEntrega");
        String tipoResidenciaEntrega = request.getParameter("tipoResidenciaEntrega");
        
        String bairroCobranca = request.getParameter("bairroCobranca");
        String cepCobranca = request.getParameter("cepCobranca");
        String logradouroCobranca = request.getParameter("logradouroCobranca");
        String tipoLogradouroCobranca = request.getParameter("tipoLogradouroCobranca");
        String numeroCobranca = request.getParameter("numeroCobranca");
        String tipoResidenciaCobranca = request.getParameter("tipoResidenciaCobranca");

        String bairroResidencial = request.getParameter("bairroResidencial");
        String cepResidencial = request.getParameter("cepResidencial");
        String logradouroResidencial = request.getParameter("logradouroResidencial");
        String tipoLogradouroResidencial = request.getParameter("tipoLogradouroResidencial");
        String numeroResidencial = request.getParameter("numeroResidencial");
        String tipoResidenciaResidencial = request.getParameter("tipoResidenciaResidencial");

        String ddd = request.getParameter("ddd");
        String numeroTelefone = request.getParameter("numeroTelefone");
        String tipoTelefone = request.getParameter("tipoTelefone");
        
		Bairro bairroC = new Bairro();
		Bairro bairroE = new Bairro();
		Bairro bairroR = new Bairro();
		bairroC.setId(Long.valueOf(bairroCobranca));
		bairroE.setId(Long.valueOf(bairroEntrega));
		bairroR.setId(Long.valueOf(bairroResidencial));
		
		Endereco enderecoC = new Endereco();		
		
		enderecoC.setBairro(bairroC);
		enderecoC.setCep(cepCobranca);
		enderecoC.setLogradouro(logradouroCobranca);
		enderecoC.setNumero(numeroCobranca);
		enderecoC.setTipoResidencia(TipoResidencia.mapearEnum(Long.valueOf(tipoResidenciaCobranca)));
		enderecoC.setTipoEndereco(TipoEndereco.Cobranca);
		enderecoC.setTipoLogradouro(TipoLogradouro.mapearEnum(Long.valueOf(tipoLogradouroCobranca)));
		
		List<Endereco> enderecosCobranca = new ArrayList<Endereco>();
		enderecosCobranca.add(enderecoC);
		
		Endereco enderecoE = new Endereco();
		
		enderecoE.setBairro(bairroE);
		enderecoE.setCep(cepEntrega);
		enderecoE.setLogradouro(logradouroEntrega);
		enderecoE.setNumero(numeroEntrega);
		enderecoE.setTipoResidencia(TipoResidencia.mapearEnum(Long.valueOf(tipoResidenciaEntrega)));
		enderecoE.setTipoEndereco(TipoEndereco.Entrega);
		enderecoE.setTipoLogradouro(TipoLogradouro.mapearEnum(Long.valueOf(tipoLogradouroEntrega)));
		
		List<Endereco> enderecosEntrega = new ArrayList<Endereco>();
		enderecosEntrega.add(enderecoE);
		
		Endereco enderecoR = new Endereco();
		
		enderecoR.setBairro(bairroR);
		enderecoR.setCep(cepResidencial);
		enderecoR.setLogradouro(logradouroResidencial);
		enderecoR.setNumero(numeroResidencial);
		enderecoR.setTipoResidencia(TipoResidencia.mapearEnum(Long.valueOf(tipoResidenciaResidencial)));
		enderecoR.setTipoEndereco(TipoEndereco.Residencial);
		enderecoR.setTipoLogradouro(TipoLogradouro.mapearEnum(Long.valueOf(tipoLogradouroResidencial)));
		
		List<Endereco> enderecosResidencial = new ArrayList<Endereco>();
		enderecosResidencial.add(enderecoR);
		
		Cliente cliente = new Cliente();
		
		cliente.setCpf(cpf);
		cliente.setDtNascimento(dtNascimento);
		cliente.setEmail(email);
		
		cliente.setEndCobranca(enderecosCobranca);
		cliente.setEndEntrega(enderecosEntrega);
		cliente.setEndResidencial(enderecoR);
		cliente.setGenero(Genero.mapearEnum(Long.valueOf(genero)));
		cliente.setNome(nome);
		cliente.setSenha(senha);
		
		Telefone telefone = new Telefone();
		telefone.setDdd(ddd);
		telefone.setNumero(numeroTelefone);
		telefone.setTipo(TipoTelefone.mapearEnum(Long.valueOf(tipoTelefone)));
		cliente.setTelefone(telefone);
		
		String resposta = this.fachada.salvar(cliente);
		
		request.setAttribute("message", resposta);
		
		String jspPage = "/pagina.jsp";
        getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}
	
	
}
