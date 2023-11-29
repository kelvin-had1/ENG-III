package dominio;

public class CartaoCredito extends EntidadeDominio {
	private String numero;
	private String nomeImpresso;
	private String codSeguranca;
	private Long clienteId;	
	private BandeiraSeguranca bandeira;
	
	
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNomeImpresso() {
		return nomeImpresso;
	}
	public void setNomeImpresso(String nomeImpresso) {
		this.nomeImpresso = nomeImpresso;
	}
	public String getCodSeguranca() {
		return codSeguranca;
	}
	public void setCodSeguranca(String codSeguranca) {
		this.codSeguranca = codSeguranca;
	}
	public BandeiraSeguranca getBandeira() {
		return bandeira;
	}
	public void setBandeira(BandeiraSeguranca bandeira) {
		this.bandeira = bandeira;
	}
	
	
}
