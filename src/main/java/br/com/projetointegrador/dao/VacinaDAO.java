package br.com.projetointegrador.dao;

import br.com.projetointegrador.database.ConexaoMySQL;
import br.com.projetointegrador.model.Vacina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VacinaDAO {
    public int inserir(Vacina vacina) throws SQLException {
        String sql = """
                INSERT INTO vacina (nome, fabricante, doenca_alvo, qtd_doses_recomendadas)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conexao = ConexaoMySQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getFabricante());
            stmt.setString(3, vacina.getDoencaAlvo());
            stmt.setInt(4, vacina.getQtdDosesRecomendadas());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        throw new SQLException("Nao foi possivel obter o ID gerado para a vacina.");
    }

    public List<Vacina> listarTodos() throws SQLException {
        String sql = "SELECT id, nome, fabricante, doenca_alvo, qtd_doses_recomendadas FROM vacina ORDER BY id";
        List<Vacina> vacinas = new ArrayList<>();

        try (Connection conexao = ConexaoMySQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                vacinas.add(mapear(rs));
            }
        }

        return vacinas;
    }

    public Optional<Vacina> buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nome, fabricante, doenca_alvo, qtd_doses_recomendadas FROM vacina WHERE id = ?";

        try (Connection conexao = ConexaoMySQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        }

        return Optional.empty();
    }

    private Vacina mapear(ResultSet rs) throws SQLException {
        Vacina vacina = new Vacina();
        vacina.setId(rs.getInt("id"));
        vacina.setNome(rs.getString("nome"));
        vacina.setFabricante(rs.getString("fabricante"));
        vacina.setDoencaAlvo(rs.getString("doenca_alvo"));
        vacina.setQtdDosesRecomendadas(rs.getInt("qtd_doses_recomendadas"));
        return vacina;
    }
}
