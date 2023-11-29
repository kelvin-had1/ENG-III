package negocio.impl;

import dominio.EntidadeDominio;

import java.util.Optional;
import java.util.List;

import dao.ClienteDAO;
import dao.IDAO;
import negocio.IStrategy;

public class ValidarExistencia implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		
		IDAO clienteDAO = new ClienteDAO();
		
		List<EntidadeDominio> clienteExistente = clienteDAO.consultar(Optional.of(entidade));
		
		if(clienteExistente.size() > 0) {
			return "Já existe um cliente cadastrado com esse cpf";
		}
		return "";
	}

}
