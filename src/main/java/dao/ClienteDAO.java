package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import dominio.EntidadeDominio;
import dominio.Genero;
import dominio.Telefone;
import dominio.TipoTelefone;
import dominio.Cliente;
import dominio.Endereco;

public class ClienteDAO extends DAO {
	private EnderecoDAO enderecoDAO;
	private TelefoneDAO telefoneDAO;
	
	public ClienteDAO() {
		this.enderecoDAO = new EnderecoDAO();
		this.telefoneDAO = new TelefoneDAO();
	}
	
	public String salvar(EntidadeDominio entidade) {
		if(!(entidade instanceof Cliente)) {
			throw new IllegalArgumentException("Entidade inválida - esperado: Cliente");
		}
		
		Cliente cliente = (Cliente) entidade;
		
		String vstrSql = "insert into cliente(nome, generoId, dtNasc, cpf, email, senha)values(?,?,?,?,?,?) returning id;;";
		
		try {
			PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
			stmt.setString(1, cliente.getNome());
			stmt.setLong(2, cliente.getGenero().getId());
			stmt.setDate(3, (Date) cliente.getDtNascimento());
			stmt.setString(4, cliente.getCpf());
			stmt.setString(5, cliente.getEmail());
			stmt.setString(6, cliente.getSenha());
			
			boolean inseriu = stmt.execute();

		    if (!inseriu) {
		    	return "A inserção falhou, nenhum registro foi criado.";
		    }
		    try (ResultSet rs = stmt.getResultSet()) {
		        if (rs.next()) {
		            cliente.setId(rs.getLong("id"));
		        } else {
		        	return "Não foi possível inserir o cliente";
		        }
		    }
			
			cliente.getEndCobranca().stream().forEach(it -> {
				it.setClienteId(cliente.getId());
				enderecoDAO.salvar(it);
			});
			
			cliente.getEndEntrega().stream().forEach(it -> {
				it.setClienteId(cliente.getId());
				enderecoDAO.salvar(it);
			});

			Telefone telefone = cliente.getTelefone();
			telefone.setCliente(cliente);
			Endereco enderecoResidencial = cliente.getEndResidencial();
			enderecoResidencial.setClienteId(cliente.getId());
			
			enderecoDAO.salvar(enderecoResidencial);
			this.telefoneDAO.salvar(telefone);
			
			return "";
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "Não foi possível inserir o cliente";
		}
		
	}

	public String alterar(EntidadeDominio entidade) {
		if(!(entidade instanceof Cliente)) {
			throw new IllegalArgumentException("Entidade inválida - esperado: Cliente");
		}
		
		Cliente cliente = (Cliente) entidade;
		
		String vstrSql = "update cliente set nome = ?, generoId = ?, dtNasc = ?, cpf = ?, email = ?, senha = ? where id = ?;";
		
		try {
		    PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
		    stmt.setString(1, cliente.getNome());
			stmt.setLong(2, cliente.getGenero().getId());
			stmt.setDate(3, (Date) cliente.getDtNascimento());
			stmt.setString(4, cliente.getCpf());
			stmt.setString(5, cliente.getEmail());
			stmt.setString(6, cliente.getSenha());
		    stmt.setLong(7, cliente.getId());
		    
		    int linhasAfetadas = stmt.executeUpdate();
		    if (linhasAfetadas > 0) {
		    	
				this.telefoneDAO.alterar(cliente.getTelefone());
		        return "";
		    } else {
		        return "Não foi possível atualizar o cliente";
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    return "Não foi possível atualizar o cliente";
		}
	}
	public List<EntidadeDominio> consultar(Optional<EntidadeDominio> entidade) {
		String vstrSql = "select c.id, c.nome, c.generoId, c.dtNasc, c.dtCadastro, c.cpf, c.email, t.numero, t.ddd, t.tipoId from cliente c inner join telefone t"
				+ " on t.clienteId = c.id ";
		
		ArrayList<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
		try {
			PreparedStatement stmt = null;
			
			if(!entidade.isEmpty()) {
				Cliente cliente = (Cliente) entidade.get();
				vstrSql += "where c.cpf = ? order by c.id asc;";
				stmt = this.getConexao().prepareStatement(vstrSql);
				stmt.setString(1, cliente.getCpf());

			}else {
				vstrSql += "order by c.id asc;";
				stmt = this.getConexao().prepareStatement(vstrSql);
			}
			
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				Cliente cliente = new Cliente();
				Telefone telefone = new Telefone();
				
				
				cliente.setId(result.getLong(1));
				cliente.setNome(result.getString(2));
				cliente.setGenero(Genero.mapearEnum(result.getLong(3)));
				cliente.setDtNascimento(result.getDate(4).toString());
				cliente.setDtCadastro(result.getDate(5));
				cliente.setCpf(result.getString(6));
				cliente.setEmail(result.getString(7));

				telefone.setNumero(result.getString(8));
				telefone.setDdd(result.getString(9));
				telefone.setTipo(TipoTelefone.mapearEnum(result.getLong(10)));
				cliente.setTelefone(telefone);
				clientes.add(cliente);
			}
			
			return clientes;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return clientes;
		
		}
	}
	
	public String apagar(Long id) {
		String vstrSql = "delete from cliente where id = ?;";
		try {
			PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
			stmt.setLong(1, id);
			boolean deleted = stmt.execute();
			
			if(!deleted) {
				return "Não foi possível apagar o cliente";	
			}
			
			return null;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "Não foi possível apagar o cliente";
		}
	}

	public EntidadeDominio consultarPeloId(Long id) {
		String vstrSql = "select c.id, c.nome, c.generoId, c.dtNasc, c.dtCadastro, c.cpf, c.email, c.senha, t.numero, t.ddd, t.tipoId, t.id from cliente c inner join telefone t"
				+ " on t.clienteId = c.id where c.id = ?;";

		try {
			PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
			stmt.setLong(1, id);
			
			ResultSet result = stmt.executeQuery();

			Cliente clienteInstance = new Cliente();
			Telefone telefone = new Telefone();
			while(result.next()) {

				clienteInstance.setId(result.getLong(1));
				clienteInstance.setNome(result.getString(2));
				clienteInstance.setGenero(Genero.mapearEnum(result.getLong(3)));
				clienteInstance.setDtNascimento(result.getDate(4).toString());
				clienteInstance.setDtCadastro(result.getDate(5));
				clienteInstance.setCpf(result.getString(6));
				clienteInstance.setEmail(result.getString(7));
				clienteInstance.setSenha(result.getString(8));

				telefone.setNumero(result.getString(9));
				telefone.setDdd(result.getString(10));
				telefone.setTipo(TipoTelefone.mapearEnum(result.getLong(11)));
				telefone.setId(result.getLong(12));
				clienteInstance.setTelefone(telefone);
			}
			
			return clienteInstance;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		
		}
	}

	

}
