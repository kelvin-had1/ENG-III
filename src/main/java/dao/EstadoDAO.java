package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dominio.EntidadeDominio;
import dominio.Estado;

public class EstadoDAO extends DAO {

    public String salvar(EntidadeDominio entidade) {
        if (!(entidade instanceof Estado)) {
            throw new IllegalArgumentException("Entidade inválida - esperado: Estado");
        }

        Estado estado = (Estado) entidade;

        String vstrSql = "INSERT INTO estado(nome, paisId) VALUES(?, ?);";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setString(1, estado.getNome());
            stmt.setLong(2, estado.getPais().getId());

            stmt.execute();
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível inserir o estado";
        }
    }

    public String alterar(EntidadeDominio entidade) {
        if (!(entidade instanceof Estado)) {
            throw new IllegalArgumentException("Entidade inválida - esperado: Estado");
        }

        Estado estado = (Estado) entidade;

        String vstrSql = "UPDATE estado SET nome=?, paisId=? WHERE id=?;";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setString(1, estado.getNome());
            stmt.setLong(2, estado.getPais().getId());
            stmt.setLong(3, estado.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                return null;
            } else {
                return "Não foi possível atualizar o estado";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível atualizar o estado";
        }
    }

    public List<EntidadeDominio> consultar(Optional<EntidadeDominio> entidade) {
        String vstrSql = "SELECT * FROM estado;";
        ArrayList<EntidadeDominio> estados = new ArrayList<>();

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Estado estado = new Estado();
                estado.setId(result.getLong("id"));
                estado.setNome(result.getString("nome"));

                estados.add(estado);
            }

            return estados;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return estados;
        }
    }

    public String apagar(Long id) {
        String vstrSql = "DELETE FROM estado WHERE id = ?;";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, id);
            boolean deleted = stmt.execute();

            if (!deleted) {
                return "Não foi possível apagar o estado";
            }

            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível apagar o estado";
        }
    }

    public EntidadeDominio consultarPeloId(Long id) {
        String vstrSql = "SELECT * FROM estado WHERE id = ?;";
        Estado estado = null;

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, id);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                estado = new Estado();
                estado.setId(result.getLong("id"));
                estado.setNome(result.getString("nome"));
            }

            return estado;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return estado;
        }
    }
}
