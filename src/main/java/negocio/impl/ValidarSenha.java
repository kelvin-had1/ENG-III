package negocio.impl;

import org.mindrot.jbcrypt.BCrypt;

import dominio.Cliente;
import dominio.EntidadeDominio;
import negocio.IStrategy;

public class ValidarSenha implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;

		if(cliente.getSenha() == null) {
			return "Obrigatório inserir uma senha";
		}
		
		cliente.setSenha(BCrypt.hashpw(cliente.getSenha(), BCrypt.gensalt(12)));
		  
		return "";
	}

}
