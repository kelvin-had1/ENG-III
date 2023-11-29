package negocio.impl;

import dominio.EntidadeDominio;
import dominio.Cliente;

import negocio.IStrategy;

public class ValidarDadosObrigatorios implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente == null) {
			return "Houve um problema ao salvar o cliente";
		}
		
		if(cliente.getGenero() == null) {
			return "Obrigatório ter um gênero";
		}
		
		if(cliente.getNome() == null) {
			return "Obrigatório ter um nome";
		}
		
		if(cliente.getDtNascimento() == null) {
			return "Obrigatório ter uma data de nascimento";
		}
		
		if(cliente.getCpf() == null) {
			return "Obrigatório digitar um CPF";
		}
		
		if(cliente.getTelefone() == null) {
			return "Obrigatório digitar um telefone";
		}
		
		if(cliente.getEmail() == null) {
			return "Obrigatório inserir um email";
		}
		
		
		return "";
	}

}
