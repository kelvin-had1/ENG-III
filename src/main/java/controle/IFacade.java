package controle;

import java.util.List;

import dominio.EntidadeDominio;

public interface IFacade {

	public String salvar(EntidadeDominio entidade);
	
	public String alterar(EntidadeDominio entidade);
	
	public String apagar(EntidadeDominio entidade);
	
	public List<EntidadeDominio> consultar(EntidadeDominio entidade);
}
