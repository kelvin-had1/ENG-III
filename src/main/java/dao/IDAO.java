package dao;
import dominio.EntidadeDominio;
import java.util.List;
import java.util.Optional;

public interface IDAO {	
	public String salvar(EntidadeDominio entidade);
	public String alterar(EntidadeDominio entidade);
	public EntidadeDominio consultarPeloId(Long id);
	public List<EntidadeDominio> consultar(Optional<EntidadeDominio> entidade);
	public String apagar(Long id);
}
