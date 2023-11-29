package negocio.impl;

import dominio.EntidadeDominio;
import dominio.Cliente;

import dao.IDAO;
import dao.ClienteDAO;
import negocio.IStrategy;

public class VerificarSeSenhaFoiAlterada implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		if(cliente.getSenha().length() > 0) {
			IStrategy validarSenha = new ValidarSenha();
			String resposta = validarSenha.processar(cliente);
			return resposta;
		}
		
		IDAO clienteDao = new ClienteDAO();
		Cliente clienteCadastrado = (Cliente) clienteDao.consultarPeloId(cliente.getId());
		cliente.setSenha(clienteCadastrado.getSenha());
		
		return "";
	}

}
