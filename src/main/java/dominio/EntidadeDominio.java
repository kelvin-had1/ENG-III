package dominio;
import java.sql.Date;

public abstract class EntidadeDominio {
	private Long id;
	private Date dtCadastro;
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	

}
