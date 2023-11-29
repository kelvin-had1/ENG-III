package dominio;

import java.util.List;

public class Cidade extends EntidadeDominio{
	private String nome;
	private Estado estado;
	private List<Bairro> bairros;
	
	
	public Cidade() {
		super();
	}
	
	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
