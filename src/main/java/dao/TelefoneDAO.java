package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dominio.EntidadeDominio;
import dominio.Telefone;

public class TelefoneDAO extends DAO {

    public String salvar(EntidadeDominio entidade) {
        if (!(entidade instanceof Telefone)) {
            throw new IllegalArgumentException("Entidade inválida - esperado: Telefone");
        }

        Telefone telefone = (Telefone) entidade;

        String vstrSql = "INSERT INTO telefone(ddd, numero, tipoId, clienteId) VALUES(?, ?, ?, ?);";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setString(1, telefone.getDdd());
            stmt.setString(2, telefone.getNumero());
            stmt.setLong(3, telefone.getTipo().getId());
            stmt.setLong(4, telefone.getCliente().getId());

            stmt.execute();
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível inserir o telefone";
        }
    }

    public String alterar(EntidadeDominio entidade) {
        if (!(entidade instanceof Telefone)) {
            throw new IllegalArgumentException("Entidade inválida - esperado: Telefone");
        }

        Telefone telefone = (Telefone) entidade;

        String vstrSql = "UPDATE telefone SET ddd=?, numero=?, tipoId=?, clienteId=? WHERE id=?;";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setString(1, telefone.getDdd());
            stmt.setString(2, telefone.getNumero());
            stmt.setLong(3, telefone.getTipo().getId());
            stmt.setLong(4, telefone.getCliente().getId());
            stmt.setLong(5, telefone.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                return null;
            } else {
                return "Não foi possível atualizar o telefone";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível atualizar o telefone";
        }
    }

    public List<EntidadeDominio> consultar(Optional<EntidadeDominio> entidade) {
        String vstrSql = "SELECT * FROM telefone;";
        ArrayList<EntidadeDominio> telefones = new ArrayList<>();

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(result.getLong("id"));
                telefone.setDdd(result.getString("ddd"));
                telefone.setNumero(result.getString("numero"));
                
                telefones.add(telefone);
            }

            return telefones;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return telefones;
        }
    }

    public String apagar(Long id) {
        String vstrSql = "DELETE FROM telefone WHERE id = ?;";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, id);
            boolean deleted = stmt.execute();

            if (!deleted) {
                return "Não foi possível apagar o telefone";
            }

            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Não foi possível apagar o telefone";
        }
    }

    public EntidadeDominio consultarPeloId(Long id) {
        String vstrSql = "SELECT * FROM telefone WHERE id = ?;";
        Telefone telefone = null;

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(vstrSql);
            stmt.setLong(1, id);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                telefone = new Telefone();
                telefone.setId(result.getLong("id"));
                telefone.setDdd(result.getString("ddd"));
                telefone.setNumero(result.getString("numero"));
            }

            return telefone;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return telefone;
        }
    }
}
