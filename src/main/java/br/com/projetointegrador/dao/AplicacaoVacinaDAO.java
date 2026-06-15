package br.com.projetointegrador.dao;

import br.com.projetointegrador.database.ConexaoMySQL;
import br.com.projetointegrador.model.AplicacaoVacina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AplicacaoVacinaDAO {
    public int inserir(AplicacaoVacina aplicacao) throws SQLException {
        String sql = """
                INSERT INTO aplicacao_vacina (paciente_id, vacina_id, data_aplicacao, numero_dose, observacao)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conexao = ConexaoMySQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, aplicacao.getPacienteId());
            stmt.setInt(2, aplicacao.getVacinaId());
            stmt.setDate(3, Date.valueOf(aplicacao.getDataAplicacao()));
            stmt.setInt(4, aplicacao.getNumeroDose());
            stmt.setString(5, aplicacao.getObservacao());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        throw new SQLException("Nao foi possivel obter o ID gerado para a aplicacao.");
    }

    public List<AplicacaoVacina> listarTodos() throws SQLException {
        String sql = """
                SELECT av.id, av.paciente_id, p.nome AS paciente_nome, av.vacina_id, v.nome AS vacina_nome,
                       av.data_aplicacao, av.numero_dose, av.observacao
                FROM aplicacao_vacina av
                JOIN paciente p ON p.id = av.paciente_id
                JOIN vacina v ON v.id = av.vacina_id
                ORDER BY av.data_aplicacao, av.id
                """;
        List<AplicacaoVacina> aplicacoes = new ArrayList<>();

        try (Connection conexao = ConexaoMySQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                aplicacoes.add(mapear(rs));
            }
        }

        return aplicacoes;
    }

    private AplicacaoVacina mapear(ResultSet rs) throws SQLException {
        AplicacaoVacina aplicacao = new AplicacaoVacina();
        aplicacao.setId(rs.getInt("id"));
        aplicacao.setPacienteId(rs.getInt("paciente_id"));
        aplicacao.setPacienteNome(rs.getString("paciente_nome"));
        aplicacao.setVacinaId(rs.getInt("vacina_id"));
        aplicacao.setVacinaNome(rs.getString("vacina_nome"));
        aplicacao.setDataAplicacao(rs.getDate("data_aplicacao").toLocalDate());
        aplicacao.setNumeroDose(rs.getInt("numero_dose"));
        aplicacao.setObservacao(rs.getString("observacao"));
        return aplicacao;
    }
}
