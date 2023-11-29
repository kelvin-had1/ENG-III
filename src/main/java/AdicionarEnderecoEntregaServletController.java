

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import controle.IFacade;
import controle.impl.Facade;
import dominio.Bairro;
import dominio.Endereco;
import dominio.TipoEndereco;
import dominio.TipoLogradouro;
import dominio.TipoResidencia;

/**
 * Servlet implementation class AdicionarEnderecoCobrancaServletController
 */
public class AdicionarEnderecoEntregaServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IFacade fachada = new Facade();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdicionarEnderecoEntregaServletController() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clienteId = request.getParameter("clienteid");
		String cep = request.getParameter("cep");
        String logradouro = request.getParameter("logradouro");
        String bairro = request.getParameter("bairro");
        String tipoLogradouro = request.getParameter("tipoLogradouro");
        String numero = request.getParameter("numero");
        String tipoResidencia = request.getParameter("tipoResidencia");

		Bairro bairroInstancia = new Bairro();
		bairroInstancia.setId(Long.valueOf(bairro));
		Endereco endereco = new Endereco();		
		endereco.setClienteId(Long.valueOf(clienteId));
		
		endereco.setBairro(bairroInstancia);
		endereco.setCep(cep);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setTipoResidencia(TipoResidencia.mapearEnum(Long.valueOf(tipoResidencia)));
		endereco.setTipoEndereco(TipoEndereco.Entrega);
		endereco.setTipoLogradouro(TipoLogradouro.mapearEnum(Long.valueOf(tipoLogradouro)));
		String resposta = this.fachada.salvar(endereco);
		
		request.setAttribute("message", resposta);
		
		String jspPage = "/pagina.jsp";
        getServletContext().getRequestDispatcher(jspPage).forward(request, response);
	}

}
