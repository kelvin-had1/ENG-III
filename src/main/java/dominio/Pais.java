package dominio;

import java.util.List;

public class Pais extends EntidadeDominio {
	private String nome;
	private List<Estado> estados;
	
	public Pais() {
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}


}
