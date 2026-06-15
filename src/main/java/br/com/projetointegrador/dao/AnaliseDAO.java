package br.com.projetointegrador.dao;

import br.com.projetointegrador.database.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnaliseDAO {
    public List<String> vacinadosPorRegiao() throws SQLException {
        String sql = """
                SELECT r.nome AS regiao, COUNT(DISTINCT av.paciente_id) AS total_vacinados
                FROM regiao r
                JOIN paciente p ON p.regiao_id = r.id
                JOIN aplicacao_vacina av ON av.paciente_id = p.id
                GROUP BY r.nome
                ORDER BY r.nome
                """;
        return executarConsultaFormatada(sql);
    }

    public List<String> vacinadoDoente() throws SQLException {
        String sql = """
                SELECT
                    CASE WHEN av.paciente_id IS NULL THEN 'Nao vacinado' ELSE 'Vacinado' END AS status_vacinacao,
                    CASE WHEN p.ficou_doente THEN 'Ficou doente' ELSE 'Nao ficou doente' END AS situacao_saude,
                    COUNT(*) AS quantidade
                FROM paciente p
                LEFT JOIN (SELECT DISTINCT paciente_id FROM aplicacao_vacina) av ON av.paciente_id = p.id
                GROUP BY status_vacinacao, situacao_saude
                ORDER BY status_vacinacao, situacao_saude
                """;
        return executarConsultaFormatada(sql);
    }

    public List<String> vacinacaoPorEscolaridade() throws SQLException {
        String sql = """
                SELECT e.descricao AS escolaridade, COUNT(DISTINCT av.paciente_id) AS total_vacinados
                FROM escolaridade e
                JOIN paciente p ON p.escolaridade_id = e.id
                JOIN aplicacao_vacina av ON av.paciente_id = p.id
                GROUP BY e.descricao
                ORDER BY e.descricao
                """;
        return executarConsultaFormatada(sql);
    }

    public List<String> percentualVacinacaoPorRegiao() throws SQLException {
        String sql = """
                SELECT r.nome AS regiao,
                       COUNT(DISTINCT p.id) AS total_pacientes,
                       COUNT(DISTINCT av.paciente_id) AS total_vacinados,
                       ROUND((COUNT(DISTINCT av.paciente_id) / COUNT(DISTINCT p.id)) * 100, 2) AS percentual_vacinados
                FROM regiao r
                JOIN paciente p ON p.regiao_id = r.id
                LEFT JOIN aplicacao_vacina av ON av.paciente_id = p.id
                GROUP BY r.nome
                ORDER BY r.nome
                """;
        return executarConsultaFormatada(sql);
    }

    public List<String> pacientesComDosesIncompletas() throws SQLException {
        String sql = """
                SELECT p.nome AS paciente,
                       v.nome AS vacina,
                       COUNT(av.id) AS doses_recebidas,
                       v.qtd_doses_recomendadas AS doses_recomendadas
                FROM paciente p
                JOIN aplicacao_vacina av ON av.paciente_id = p.id
                JOIN vacina v ON v.id = av.vacina_id
                GROUP BY p.id, p.nome, v.id, v.nome, v.qtd_doses_recomendadas
                HAVING COUNT(av.id) < v.qtd_doses_recomendadas
                ORDER BY p.nome, v.nome
                """;
        return executarConsultaFormatada(sql);
    }

    public List<String> vacinasAplicadasPorPaciente() throws SQLException {
        String sql = """
                SELECT p.nome AS paciente,
                       v.nome AS vacina,
                       av.numero_dose,
                       av.data_aplicacao,
                       COALESCE(av.observacao, '-') AS observacao
                FROM aplicacao_vacina av
                JOIN paciente p ON p.id = av.paciente_id
                JOIN vacina v ON v.id = av.vacina_id
                ORDER BY p.nome, v.nome, av.numero_dose
                """;
        return executarConsultaFormatada(sql);
    }

    public List<String> vacinasAplicadasPorRegiao() throws SQLException {
        String sql = """
                SELECT r.nome AS regiao, COUNT(av.id) AS total_aplicacoes
                FROM regiao r
                JOIN paciente p ON p.regiao_id = r.id
                JOIN aplicacao_vacina av ON av.paciente_id = p.id
                GROUP BY r.nome
                ORDER BY r.nome
                """;
        return executarConsultaFormatada(sql);
    }

    private List<String> executarConsultaFormatada(String sql) throws SQLException {
        List<String> linhas = new ArrayList<>();

        try (Connection conexao = ConexaoMySQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ResultSetMetaData metaData = rs.getMetaData();
            int colunas = metaData.getColumnCount();

            while (rs.next()) {
                StringBuilder linha = new StringBuilder();
                for (int i = 1; i <= colunas; i++) {
                    if (i > 1) {
                        linha.append(" | ");
                    }
                    linha.append(metaData.getColumnLabel(i)).append(": ").append(rs.getString(i));
                }
                linhas.add(linha.toString());
            }
        }

        return linhas;
    }
}
