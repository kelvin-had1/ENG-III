package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dominio.BandeiraSeguranca;
import dominio.CartaoCredito;
import dominio.EntidadeDominio;

public class CartaoDeCreditoDAO extends DAO {

    public String salvar(EntidadeDominio entidade) {
        CartaoCredito cartao = (CartaoCredito) entidade;
        String query = "INSERT INTO cartao_credito (numero, nomeImpresso, codSeguranca, clienteId, bandeiraId) VALUES (?, ?, ?, ?, ?)";

        try (
             PreparedStatement stmt = this.getConexao().prepareStatement(query)) {

            stmt.setString(1, cartao.getNumero());
            stmt.setString(2, cartao.getNomeImpresso());
            stmt.setString(3, cartao.getCodSeguranca());
            stmt.setLong(4, cartao.getClienteId());
            stmt.setLong(5, cartao.getBandeira().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao salvar o cartão de crédito: " + e.getMessage();
        }
        return "";
    }

    public String alterar(EntidadeDominio entidade) {
        CartaoCredito cartao = (CartaoCredito) entidade;
        String query = "UPDATE cartao_credito SET numero=?, nomeImpresso=?, codSeguranca=?, clienteId=?, bandeiraId=? WHERE id=?";

        try (
             PreparedStatement stmt = this.getConexao().prepareStatement(query)) {

            stmt.setString(1, cartao.getNumero());
            stmt.setString(2, cartao.getNomeImpresso());
            stmt.setString(3, cartao.getCodSeguranca());
            stmt.setLong(4, cartao.getClienteId());
            stmt.setLong(5, cartao.getBandeira().getId());
            stmt.setLong(6, cartao.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao alterar o cartão de crédito: " + e.getMessage();
        }
        return "";
    }

    public EntidadeDominio consultarPeloId(Long id) {
        String query = "SELECT * FROM cartao_credito WHERE id=?";
        CartaoCredito cartao = new CartaoCredito();

        try (
             PreparedStatement stmt = this.getConexao().prepareStatement(query)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cartao.setId(rs.getLong("id"));
                cartao.setNumero(rs.getString("numero"));
                cartao.setNomeImpresso(rs.getString("nomeImpresso"));
                cartao.setCodSeguranca(rs.getString("codSeguranca"));
                cartao.setClienteId(rs.getLong("clienteId"));
                Long bandeiraId = rs.getLong("bandeiraId");
                BandeiraSeguranca bandeira = BandeiraSeguranca.mapearEnum(bandeiraId);
                cartao.setBandeira(bandeira);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartao;
    }

    public List<EntidadeDominio> consultar(Optional<EntidadeDominio> entidade) {
        List<EntidadeDominio> cartoes = new ArrayList<>();
        String query = "SELECT * FROM cartao_credito";

        try (
             PreparedStatement stmt = this.getConexao().prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	CartaoCredito cartao = new CartaoCredito();
                cartao.setId(rs.getLong("id"));
                cartao.setNumero(rs.getString("numero"));
                cartao.setNomeImpresso(rs.getString("nomeImpresso"));
                cartao.setCodSeguranca(rs.getString("codSeguranca"));
                cartao.setClienteId(rs.getLong("clienteId"));
                Long bandeiraId = rs.getLong("bandeiraId");
                BandeiraSeguranca bandeira = BandeiraSeguranca.mapearEnum(bandeiraId);
                cartao.setBandeira(bandeira);
                cartoes.add(cartao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartoes;
    }

    public String apagar(Long id) {
        String query = "DELETE FROM cartao_credito WHERE id=?";

        try (
             PreparedStatement stmt = this.getConexao().prepareStatement(query)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao apagar o cartão de crédito: " + e.getMessage();
        }
        return "Cartão de crédito apagado com sucesso!";
    }

}