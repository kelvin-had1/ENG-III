package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dominio.Bairro;
import dominio.Cidade;
import dominio.EntidadeDominio;
import dominio.Estado;
import dominio.Pais;

public class BairroDAO extends DAO {

    public String salvar(EntidadeDominio entidade) {
        if (!(entidade instanceof Bairro)) {
            throw new IllegalArgumentException("Entidade inválida - esperado: Bairro");
        }

        Bairro bairro = (Bairro) entidade;

        String vstrSql = "INSERT INTO bairro(nome, cidadeId) VALUES(?, ?);";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setString(1, bairro.getNome());
            stmt.setLong(2, bairro.getCidade().getId());

            stmt.execute();
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível inserir o bairro";
        }
    }

    public String alterar(EntidadeDominio entidade) {
        if (!(entidade instanceof Bairro)) {
            throw new IllegalArgumentException("Entidade inválida - esperado: Bairro");
        }

        Bairro bairro = (Bairro) entidade;

        String vstrSql = "UPDATE bairro SET nome=?, cidadeId=? WHERE id=?;";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setString(1, bairro.getNome());
            stmt.setLong(2, bairro.getCidade().getId());
            stmt.setLong(3, bairro.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                return null;
            } else {
                return "Não foi possível atualizar o bairro";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível atualizar o bairro";
        }
    }

    public List<EntidadeDominio> consultar(Optional<EntidadeDominio> entidade) {
        String vstrSql = "SELECT \r\n"
        		+ "    bairro.id AS bairro_id,\r\n"
        		+ "    bairro.nome AS bairro,\r\n"
        		+ "    cidade.id AS cidade_id,\r\n"
        		+ "    cidade.nome AS cidade,\r\n"
        		+ "    estado.id AS estado_id,\r\n"
        		+ "    estado.nome AS estado,\r\n"
        		+ "    pais.id AS pais_id,\r\n"
        		+ "    pais.nome AS pais\r\n"
        		+ "FROM bairro\r\n"
        		+ "JOIN cidade ON bairro.cidadeId = cidade.id\r\n"
        		+ "JOIN estado ON cidade.estadoId = estado.id\r\n"
        		+ "JOIN pais ON estado.paisId = pais.id;\r\n"
        		+ "";
        ArrayList<EntidadeDominio> bairros = new ArrayList<>();

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Bairro bairro = new Bairro();
                Cidade cidade = new Cidade();
                Estado estado = new Estado();
                Pais pais = new Pais();
                
                bairro.setId(result.getLong("bairro_id"));
                bairro.setNome(result.getString("bairro"));
                cidade.setId(result.getLong("cidade_id"));
                cidade.setNome(result.getString("cidade"));
                estado.setId(result.getLong("estado_id"));
                estado.setNome(result.getString("estado"));
                pais.setId(result.getLong("pais_id"));
                pais.setNome(result.getString("pais"));
                
                estado.setPais(pais);
                cidade.setEstado(estado);
                bairro.setCidade(cidade);
                
                bairros.add(bairro);
            }

            return bairros;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return bairros;
        }
    }

    public String apagar(Long id) {
        String vstrSql = "DELETE FROM bairro WHERE id = ?;";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, id);
            boolean deleted = stmt.execute();

            if (!deleted) {
                return "Não foi possível apagar o bairro";
            }

            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível apagar o bairro";
        }
    }

    public EntidadeDominio consultarPeloId(Long id) {
        String vstrSql = "SELECT \\r\\n\"\r\n"
        		+ "        		+ \"    bairro.id AS bairro_id,\\r\\n\"\r\n"
        		+ "        		+ \"    bairro.nome AS bairro,\\r\\n\"\r\n"
        		+ "        		+ \"    cidade.id AS cidade_id,\\r\\n\"\r\n"
        		+ "        		+ \"    cidade.nome AS cidade,\\r\\n\"\r\n"
        		+ "        		+ \"    estado.id AS estado_id,\\r\\n\"\r\n"
        		+ "        		+ \"    estado.nome AS estado,\\r\\n\"\r\n"
        		+ "        		+ \"    pais.id AS pais_id,\\r\\n\"\r\n"
        		+ "        		+ \"    pais.nome AS pais\\r\\n\"\r\n"
        		+ "        		+ \"FROM bairro\\r\\n\"\r\n"
        		+ "        		+ \"JOIN cidade ON bairro.cidadeId = cidade.id\\r\\n\"\r\n"
        		+ "        		+ \"JOIN estado ON cidade.estadoId = estado.id\\r\\n\"\r\n"
        		+ "        		+ \"JOIN pais ON estado.paisId = pais.id;\\r\\n\"\r\n"
        		+ "        		+ \" WHERE bairro.id = ?;";
        Bairro bairro = null;

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, id);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                bairro = new Bairro();
                Cidade cidade = new Cidade();
                Estado estado = new Estado();
                Pais pais = new Pais();
                
                bairro.setId(result.getLong("bairro_id"));
                bairro.setNome(result.getString("bairro"));
                cidade.setId(result.getLong("cidade_id"));
                cidade.setNome(result.getString("cidade"));
                estado.setId(result.getLong("estado_id"));
                estado.setNome(result.getString("estado"));
                pais.setId(result.getLong("pais_id"));
                pais.setNome(result.getString("pais"));
                
                estado.setPais(pais);
                cidade.setEstado(estado);
                bairro.setCidade(cidade);
                bairro.setId(result.getLong("id"));
                bairro.setNome(result.getString("nome"));
            }

            return bairro;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return bairro;
        }
    }
}
