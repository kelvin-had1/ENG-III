package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dominio.EntidadeDominio;
import dominio.Pais;

public class PaisDAO extends DAO {

    public String salvar(EntidadeDominio entidade) {
        if (!(entidade instanceof Pais)) {
            throw new IllegalArgumentException("Entidade inválida - esperado: Pais");
        }

        Pais pais = (Pais) entidade;

        String vstrSql = "INSERT INTO pais(nome) VALUES(?);";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setString(1, pais.getNome());

            stmt.execute();
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível inserir o país";
        }
    }

    public String alterar(EntidadeDominio entidade) {
        if (!(entidade instanceof Pais)) {
            throw new IllegalArgumentException("Entidade inválida - esperado: Pais");
        }

        Pais pais = (Pais) entidade;

        String vstrSql = "UPDATE pais SET nome=? WHERE id=?;";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setString(1, pais.getNome());
            stmt.setLong(2, pais.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                return null;
            } else {
                return "Não foi possível atualizar o país";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível atualizar o país";
        }
    }

    public List<EntidadeDominio> consultar(Optional<EntidadeDominio> entidade) {
        String vstrSql = "SELECT * FROM pais;";
        ArrayList<EntidadeDominio> paises = new ArrayList<>();

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Pais pais = new Pais();
                pais.setId(result.getLong("id"));
                pais.setNome(result.getString("nome"));

                paises.add(pais);
            }

            return paises;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return paises;
        }
    }

    public String apagar(Long id) {
        String vstrSql = "DELETE FROM pais WHERE id = ?;";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, id);
            boolean deleted = stmt.execute();

            if (!deleted) {
                return "Não foi possível apagar o país";
            }

            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível apagar o país";
        }
    }

    public EntidadeDominio consultarPeloId(Long id) {
        String vstrSql = "SELECT * FROM pais WHERE id = ?;";
        Pais pais = null;

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, id);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                pais = new Pais();
                pais.setId(result.getLong("id"));
                pais.setNome(result.getString("nome"));
            }

            return pais;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return pais;
        }
    }
}
