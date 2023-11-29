package dominio;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Cliente extends EntidadeDominio {
	private String nome;
	private Genero genero;
	private Date dtNascimento;
	private String cpf;	
	private String email;
	private String senha;
	
	private Telefone telefone;
	private Endereco endResidencial;
	private List<Endereco> endCobranca;
	private List<Endereco> endEntrega;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Date getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(String dtNascimento) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
            java.util.Date utilDate = formato.parse(dtNascimento);
            this.dtNascimento = new Date(utilDate.getTime());
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Endereco getEndResidencial() {
		return endResidencial;
	}
	public void setEndResidencial(Endereco endResidencial) {
		this.endResidencial = endResidencial;
	}
	public List<Endereco> getEndCobranca() {
		return endCobranca;
	}
	public void setEndCobranca(List<Endereco> endCobranca) {
		this.endCobranca = endCobranca;
	}
	public List<Endereco> getEndEntrega() {
		return endEntrega;
	}
	public void setEndEntrega(List<Endereco> endEntrega) {
		this.endEntrega = endEntrega;
	}
	
	
}
