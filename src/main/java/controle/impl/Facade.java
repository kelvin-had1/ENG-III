package controle.impl;

import java.util.HashMap;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import controle.IFacade;
import dao.*;
import dominio.Bairro;
import dominio.Cidade;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Estado;
import dominio.Pais;
import dominio.Telefone;
import negocio.IStrategy;
import negocio.impl.GerarLog;
import negocio.impl.ValidarDadosObrigatorios;
import negocio.impl.ValidarEnderecos;
import negocio.impl.ValidarExistencia;
import negocio.impl.ValidarSenha;
import negocio.impl.VerificarSeSenhaFoiAlterada;

public class Facade implements IFacade {
	private HashMap<String, List<IStrategy>> rnsSalvar;
	private HashMap<String, List<IStrategy>> rnsAlterar;
	private HashMap<String, List<IStrategy>> rnsApagar;
	private HashMap<String, IDAO> DAOs;
	
	public Facade() {
		this.rnsSalvar = new HashMap<String, List<IStrategy>>();
		this.rnsAlterar = new HashMap<String, List<IStrategy>>();
		this.rnsApagar = new HashMap<String, List<IStrategy>>();
		this.DAOs = new HashMap<String, IDAO>();
		
		List<IStrategy> rnsSalvarCliente = new ArrayList<IStrategy>();
		ValidarDadosObrigatorios vDadosObrigatorios = new ValidarDadosObrigatorios();
		ValidarExistencia vExistencia = new ValidarExistencia();
		ValidarEnderecos vEnderecos = new ValidarEnderecos();
		ValidarSenha vSenha = new ValidarSenha();
		GerarLog gerarLog = new GerarLog();
		

		List<IStrategy> rnsSalvarEndereco = new ArrayList<IStrategy>();
		
		
		List<IStrategy> rnsAlterarCliente = new ArrayList<IStrategy>();
		VerificarSeSenhaFoiAlterada vSenhaAlterada = new VerificarSeSenhaFoiAlterada(); 
		rnsAlterarCliente.add(vSenhaAlterada);
		
		IDAO clienteDAO = new ClienteDAO();
		IDAO enderecoDAO = new EnderecoDAO();
		IDAO bairroDAO = new BairroDAO();
		IDAO cidadeDAO = new CidadeDAO();
		IDAO estadoDAO = new EstadoDAO();
		IDAO paisDAO = new PaisDAO();
		IDAO telefoneDAO = new TelefoneDAO();
		
		this.DAOs.put(Cliente.class.getName(), clienteDAO);
		this.DAOs.put(Endereco.class.getName(), enderecoDAO);
		this.DAOs.put(Bairro.class.getName(), bairroDAO);
		this.DAOs.put(Cidade.class.getName(), cidadeDAO);
		this.DAOs.put(Estado.class.getName(), estadoDAO);
		this.DAOs.put(Pais.class.getName(), paisDAO);
		this.DAOs.put(Telefone.class.getName(), telefoneDAO);
		
		rnsSalvarCliente.add(vDadosObrigatorios);
		rnsSalvarCliente.add(vExistencia);
		rnsSalvarCliente.add(vEnderecos);
		rnsSalvarCliente.add(vSenha);
		rnsSalvarCliente.add(gerarLog);
		
		this.rnsSalvar.put(Cliente.class.getName(), rnsSalvarCliente);		
		this.rnsAlterar.put(Cliente.class.getName(), rnsAlterarCliente);
		
		this.rnsSalvar.put(Endereco.class.getName(), rnsSalvarEndereco);
	}

	public String salvar(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
		StringBuilder str = new StringBuilder();
		
		List<IStrategy> rnsSalvar = this.rnsSalvar.get(nomeClasse);
		
		if(rnsSalvar != null) {
			rnsSalvar.stream()
			.forEach(it -> {
				str.append(it.processar(entidade));
			});	
		}
		
		if(str.length() > 0) {
			return str.toString();	
		}
		
		IDAO dao = this.DAOs.get(nomeClasse);
		
		try {
			dao.salvar(entidade);
		}catch(Exception ex) {
			ex.printStackTrace();
			return "Não foi possível salvar " + entidade.getClass().getSimpleName().toLowerCase();
		}
		return "";
	}

	public String alterar(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
		StringBuilder str = new StringBuilder();
		
		List<IStrategy> rnsAlterar = this.rnsAlterar.get(nomeClasse);
		
		if(rnsAlterar != null) {
			rnsAlterar.stream()
			.forEach(it -> {
				str.append(it.processar(entidade));
			});	
		}
		
		if(str.length() > 0) {
			return str.toString();	
		}
		
		IDAO dao = this.DAOs.get(nomeClasse);
		
		try {
			dao.alterar(entidade);
			return "";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "Não foi possível alterar " + entidade.getClass().getSimpleName().toLowerCase();
		}
	}
	
	public String apagar(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
		StringBuilder str = new StringBuilder();
		
		List<IStrategy> rnsApagar = this.rnsApagar.get(nomeClasse);
		
		if(rnsApagar != null) {
			rnsApagar.stream()
			.forEach(it -> {
				str.append(it.processar(entidade));
			});	
		}
		
		if(str.length() > 0) {
			return str.toString();	
		}
		
		IDAO dao = this.DAOs.get(nomeClasse);
		
		try {
			dao.apagar(entidade.getId());
			return "";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "Não foi possível apagar " + entidade.getClass().getSimpleName().toLowerCase();
		}
	}
	
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
		
		IDAO dao = this.DAOs.get(nomeClasse);
		
		if(entidade.getId() != 0) {
			return dao.consultar(Optional.of(entidade));
		}
		
		return dao.consultar(Optional.empty());		
	}

}
