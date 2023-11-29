package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dominio.Cidade;
import dominio.EntidadeDominio;

public class CidadeDAO extends DAO {

    public String salvar(EntidadeDominio entidade) {
        if (!(entidade instanceof Cidade)) {
            throw new IllegalArgumentException("Entidade inválida - esperado: Cidade");
        }

        Cidade cidade = (Cidade) entidade;

        String vstrSql = "INSERT INTO cidade(nome, estadoId) VALUES(?, ?);";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setString(1, cidade.getNome());
            stmt.setLong(2, cidade.getEstado().getId());

            stmt.execute();
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível inserir a cidade";
        } 
    }

    public String alterar(EntidadeDominio entidade) {
        if (!(entidade instanceof Cidade)) {
            throw new IllegalArgumentException("Entidade inválida - esperado: Cidade");
        }

        Cidade cidade = (Cidade) entidade;

        String vstrSql = "UPDATE cidade SET nome=?, estadoId=? WHERE id=?;";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setString(1, cidade.getNome());
            stmt.setLong(2, cidade.getEstado().getId());
            stmt.setLong(3, cidade.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                return null;
            } else {
                return "Não foi possível atualizar a cidade";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível atualizar a cidade";
        }
    }

    public List<EntidadeDominio> consultar(Optional<EntidadeDominio> entidade) {
        String vstrSql = "SELECT * FROM cidade;";
        ArrayList<EntidadeDominio> cidades = new ArrayList<>();

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(result.getLong("id"));
                cidade.setNome(result.getString("nome"));
                cidades.add(cidade);
            }

            return cidades;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return cidades;
        }
    }

    public String apagar(Long id) {
        String vstrSql = "DELETE FROM cidade WHERE id = ?;";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, id);
            boolean deleted = stmt.execute();

            if (!deleted) {
                return "Não foi possível apagar a cidade";
            }

            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível apagar a cidade";
        }
    }

    public EntidadeDominio consultarPeloId(Long id) {
        String vstrSql = "SELECT * FROM cidade WHERE id = ?;";
        Cidade cidade = null;

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, id);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                cidade = new Cidade();
                cidade.setId(result.getLong("id"));
                cidade.setNome(result.getString("nome"));
            }

            return cidade;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return cidade;
        }
    }
}
