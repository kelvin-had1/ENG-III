package negocio.impl;

import dominio.Cliente;
import dominio.EntidadeDominio;
import negocio.IStrategy;

public class ValidarEnderecos implements IStrategy  {

	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getEndResidencial() == null) {
			return "Obrigatório inserir um endereço residencial";
		}
		
		if(cliente.getEndCobranca().size() == 0) {
			return "Obrigatório ter um endereço de cobrança";
		}
		
		if(cliente.getEndEntrega().size() == 0) {
			return "Obrigatório ter um endereço de entrega";
		}
		
		return "";
	}

}
