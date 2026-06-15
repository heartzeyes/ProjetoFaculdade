package br.com.projetointegrador.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    private static final String URL_PADRAO = "jdbc:mysql://localhost:3306/sistema_vacinacao?useSSL=false&serverTimezone=America/Sao_Paulo&characterEncoding=UTF-8";
    private static final String USUARIO_PADRAO = "root";
    private static final String SENHA_PADRAO = "biruta";

    private ConexaoMySQL() {
    }

    public static Connection obterConexao() throws SQLException {
        String url = valorConfigurado("DB_URL", URL_PADRAO);
        String usuario = valorConfigurado("DB_USER", USUARIO_PADRAO);
        String senha = valorConfigurado("DB_PASSWORD", SENHA_PADRAO);
        return DriverManager.getConnection(url, usuario, senha);
    }

    private static String valorConfigurado(String chave, String valorPadrao) {
        String propriedade = System.getProperty(chave);
        if (propriedade != null && !propriedade.isBlank()) {
            return propriedade;
        }
        String ambiente = System.getenv(chave);
        if (ambiente != null && !ambiente.isBlank()) {
            return ambiente;
        }
        return valorPadrao;
    }
}
