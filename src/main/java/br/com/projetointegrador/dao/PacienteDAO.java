package br.com.projetointegrador.dao;

import br.com.projetointegrador.database.ConexaoMySQL;
import br.com.projetointegrador.model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteDAO {
    public int inserir(Paciente paciente) throws SQLException {
        String sql = """
                INSERT INTO paciente (nome, idade, endereco, telefone, regiao_id, escolaridade_id, ficou_doente)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conexao = ConexaoMySQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getIdade());
            stmt.setString(3, paciente.getEndereco());
            stmt.setString(4, paciente.getTelefone());
            stmt.setInt(5, paciente.getRegiaoId());
            stmt.setInt(6, paciente.getEscolaridadeId());
            stmt.setBoolean(7, paciente.isFicouDoente());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        throw new SQLException("Nao foi possivel obter o ID gerado para o paciente.");
    }

    public List<Paciente> listarTodos() throws SQLException {
        String sql = """
                SELECT p.id, p.nome, p.idade, p.endereco, p.telefone, p.regiao_id, r.nome AS regiao_nome,
                       p.escolaridade_id, e.descricao AS escolaridade_descricao, p.ficou_doente
                FROM paciente p
                JOIN regiao r ON r.id = p.regiao_id
                JOIN escolaridade e ON e.id = p.escolaridade_id
                ORDER BY p.id
                """;
        List<Paciente> pacientes = new ArrayList<>();

        try (Connection conexao = ConexaoMySQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                pacientes.add(mapear(rs));
            }
        }

        return pacientes;
    }

    public Optional<Paciente> buscarPorId(int id) throws SQLException {
        String sql = """
                SELECT p.id, p.nome, p.idade, p.endereco, p.telefone, p.regiao_id, r.nome AS regiao_nome,
                       p.escolaridade_id, e.descricao AS escolaridade_descricao, p.ficou_doente
                FROM paciente p
                JOIN regiao r ON r.id = p.regiao_id
                JOIN escolaridade e ON e.id = p.escolaridade_id
                WHERE p.id = ?
                """;

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

    public List<Paciente> buscarPorNomeOuTelefone(String termo) throws SQLException {
        String sql = """
                SELECT p.id, p.nome, p.idade, p.endereco, p.telefone, p.regiao_id, r.nome AS regiao_nome,
                       p.escolaridade_id, e.descricao AS escolaridade_descricao, p.ficou_doente
                FROM paciente p
                JOIN regiao r ON r.id = p.regiao_id
                JOIN escolaridade e ON e.id = p.escolaridade_id
                WHERE LOWER(p.nome) LIKE LOWER(?) OR p.telefone LIKE ?
                ORDER BY p.nome
                """;
        List<Paciente> pacientes = new ArrayList<>();
        String busca = "%" + termo + "%";

        try (Connection conexao = ConexaoMySQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, busca);
            stmt.setString(2, busca);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pacientes.add(mapear(rs));
                }
            }
        }

        return pacientes;
    }

    private Paciente mapear(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setId(rs.getInt("id"));
        paciente.setNome(rs.getString("nome"));
        paciente.setIdade(rs.getInt("idade"));
        paciente.setEndereco(rs.getString("endereco"));
        paciente.setTelefone(rs.getString("telefone"));
        paciente.setRegiaoId(rs.getInt("regiao_id"));
        paciente.setRegiaoNome(rs.getString("regiao_nome"));
        paciente.setEscolaridadeId(rs.getInt("escolaridade_id"));
        paciente.setEscolaridadeDescricao(rs.getString("escolaridade_descricao"));
        paciente.setFicouDoente(rs.getBoolean("ficou_doente"));
        return paciente;
    }
}
