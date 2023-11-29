package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dominio.Bairro;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.TipoEndereco;
import dominio.TipoLogradouro;
import dominio.TipoResidencia;

public class EnderecoDAO extends DAO {

    public String alterar(EntidadeDominio entidade) {
        if (!(entidade instanceof Endereco)) {
            throw new IllegalArgumentException("Entidade inválida - esperado: Endereco");
        }

        Endereco endereco = (Endereco) entidade;

        String vstrSql = "UPDATE endereco SET tipoResidencialId=?, tipoLogradouroId=?, logradouro=?, numero=?, cep=?, observacoes=?, bairroId=?, tipoEnderecoId=? WHERE id=?;";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, endereco.getTipoResidencia().getId());
            stmt.setLong(2, endereco.getTipoLogradouro().getId());
            stmt.setString(3, endereco.getLogradouro());
            stmt.setString(4, endereco.getNumero());
            stmt.setString(5, endereco.getCep());
            stmt.setString(6, endereco.getObservacoes());
            stmt.setLong(7, endereco.getBairro().getId());
            stmt.setLong(8, endereco.getTipoEndereco().getId());
            stmt.setLong(9, endereco.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                return null;
            } else {
                return "Não foi possível atualizar o endereço";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível atualizar o endereço";
        }
    }

    public List<EntidadeDominio> consultar(Optional<EntidadeDominio> entidade) {
        String vstrSql = "SELECT * FROM endereco;";
        ArrayList<EntidadeDominio> enderecos = new ArrayList<>();
        PreparedStatement stmt = null; 
        try {
        	
        	if(entidade.isPresent()) {
        		vstrSql = vstrSql.substring(0, vstrSql.length() - 1) + " e where e.clienteId=?;";
        		stmt = this.getConexao().prepareStatement(vstrSql);
        		
        		stmt.setLong(1, entidade.get().getId());
        	}else {
        		stmt = this.getConexao().prepareStatement(vstrSql);
        	}
             
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(result.getLong("id"));
                endereco.setTipoResidencia(TipoResidencia.mapearEnum(result.getLong("tipoResidencialId")));
                endereco.setTipoLogradouro(TipoLogradouro.mapearEnum(result.getLong("tipoLogradouroId")));
                endereco.setTipoEndereco(TipoEndereco.mapearEnum(result.getLong("tipoEnderecoId")));
                endereco.setLogradouro(result.getString("logradouro"));
                endereco.setNumero(result.getString("numero"));
                endereco.setCep(result.getString("cep"));
                endereco.setObservacoes(result.getString("observacoes"));

                Bairro bairro = new Bairro();
                bairro.setId(result.getLong("bairroId"));
                endereco.setBairro(bairro);

                enderecos.add(endereco);
            }

            return enderecos;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return enderecos;
        }
    }

    public String apagar(Long id) {
        String vstrSql = "DELETE FROM endereco WHERE id = ?;";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, id);
            boolean deleted = stmt.execute();

            if (!deleted) {
                return "Não foi possível apagar o endereço";
            }

            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível apagar o endereço";
        }
    }

    public EntidadeDominio consultarPeloId(Long id) {
        String vstrSql = "SELECT * FROM endereco WHERE id = ?;";
        Endereco endereco = null;

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, id);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                endereco = new Endereco();
                endereco.setId(result.getLong("id"));
                endereco.setTipoResidencia(TipoResidencia.mapearEnum(result.getLong("tipoResidencialId")));
                endereco.setTipoLogradouro(TipoLogradouro.mapearEnum(result.getLong("tipoLogradouroId")));
                endereco.setLogradouro(result.getString("logradouro"));
                endereco.setNumero(result.getString("numero"));
                endereco.setTipoEndereco(TipoEndereco.mapearEnum(result.getLong("tipoEnderecoId")));
                endereco.setCep(result.getString("cep"));
                endereco.setObservacoes(result.getString("observacoes"));

                Bairro bairro = new Bairro();
                bairro.setId(result.getLong("bairroId"));
                endereco.setBairro(bairro);
            }

            return endereco;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return endereco;
        }
    }

	public String salvar(EntidadeDominio entidade) {
		if (!(entidade instanceof Endereco)) {
            throw new IllegalArgumentException("Entidade inválida - esperado: Endereco");
        }

        Endereco endereco = (Endereco) entidade;

        String vstrSql = "INSERT INTO endereco(tipoResidencialId, tipoLogradouroId, logradouro, numero, cep, observacoes, bairroId, tipoEnderecoId, clienteId) VALUES(?,?,?,?,?,?,?,?,?);";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, endereco.getTipoResidencia().getId());
            stmt.setLong(2, endereco.getTipoLogradouro().getId());
            stmt.setString(3, endereco.getLogradouro());
            stmt.setString(4, endereco.getNumero());
            stmt.setString(5, endereco.getCep());
            stmt.setString(6, endereco.getObservacoes());
            stmt.setLong(7, endereco.getBairro().getId());
            stmt.setLong(8, endereco.getTipoEndereco().getId());
            stmt.setLong(9, endereco.getClienteId());

            stmt.execute();
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível inserir o endereço";
        }
	}
}
