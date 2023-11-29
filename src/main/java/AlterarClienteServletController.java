

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dominio.Cliente;
import dominio.Genero;
import dominio.Telefone;
import dominio.TipoTelefone;
import controle.IFacade;
import controle.impl.Facade;

/**
 * Servlet implementation class AlterarClienteServletController
 */
public class AlterarClienteServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IFacade fachada = new Facade();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarClienteServletController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
	    String cpf = request.getParameter("cpf");
	    String email = request.getParameter("email");
	    String senha = request.getParameter("senha");
	    String genero = request.getParameter("genero");
	    String dtNascimento = request.getParameter("dtNascimento");
	    
	    String telefoneId = request.getParameter("telefoneId");
	    String ddd = request.getParameter("ddd");
	    String numeroTelefone = request.getParameter("numeroTelefone");
	    String tipoTelefone = request.getParameter("tipoTelefone");
	    
		Cliente cliente = new Cliente();
		cliente.setId(Long.valueOf(id));
		cliente.setCpf(cpf);
		cliente.setDtNascimento(dtNascimento);
		cliente.setEmail(email);
		cliente.setGenero(Genero.mapearEnum(Long.valueOf(genero)));
		cliente.setNome(nome);
		cliente.setSenha(senha);
		
		Telefone telefone = new Telefone();

		telefone.setId(Long.valueOf(telefoneId));
		telefone.setDdd(ddd);
		telefone.setNumero(numeroTelefone);
		telefone.setTipo(TipoTelefone.mapearEnum(Long.valueOf(tipoTelefone)));
		telefone.setCliente(cliente);
		cliente.setTelefone(telefone);

		String resposta = this.fachada.alterar(cliente);
		
		request.setAttribute("message", resposta);

		String jspPage = "/pagina.jsp";
        getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}

}
