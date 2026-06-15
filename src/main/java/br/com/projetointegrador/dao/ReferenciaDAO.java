package br.com.projetointegrador.dao;

import br.com.projetointegrador.database.ConexaoMySQL;
import br.com.projetointegrador.model.Escolaridade;
import br.com.projetointegrador.model.Regiao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReferenciaDAO {
    public List<Regiao> listarRegioes() throws SQLException {
        String sql = "SELECT id, nome FROM regiao ORDER BY id";
        List<Regiao> regioes = new ArrayList<>();

        try (Connection conexao = ConexaoMySQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                regioes.add(new Regiao(rs.getInt("id"), rs.getString("nome")));
            }
        }

        return regioes;
    }

    public List<Escolaridade> listarEscolaridades() throws SQLException {
        String sql = "SELECT id, descricao FROM escolaridade ORDER BY id";
        List<Escolaridade> escolaridades = new ArrayList<>();

        try (Connection conexao = ConexaoMySQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                escolaridades.add(new Escolaridade(rs.getInt("id"), rs.getString("descricao")));
            }
        }

        return escolaridades;
    }
}
