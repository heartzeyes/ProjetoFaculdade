package br.com.projetointegrador.service;

import br.com.projetointegrador.dao.AnaliseDAO;

import java.sql.SQLException;
import java.util.List;

public class AnaliseService {
    private final AnaliseDAO analiseDAO = new AnaliseDAO();

    public List<String> vacinadosPorRegiao() throws SQLException {
        return analiseDAO.vacinadosPorRegiao();
    }

    public List<String> vacinadoDoente() throws SQLException {
        return analiseDAO.vacinadoDoente();
    }

    public List<String> vacinacaoPorEscolaridade() throws SQLException {
        return analiseDAO.vacinacaoPorEscolaridade();
    }

    public List<String> percentualVacinacaoPorRegiao() throws SQLException {
        return analiseDAO.percentualVacinacaoPorRegiao();
    }

    public List<String> pacientesComDosesIncompletas() throws SQLException {
        return analiseDAO.pacientesComDosesIncompletas();
    }

    public List<String> vacinasAplicadasPorPaciente() throws SQLException {
        return analiseDAO.vacinasAplicadasPorPaciente();
    }

    public List<String> vacinasAplicadasPorRegiao() throws SQLException {
        return analiseDAO.vacinasAplicadasPorRegiao();
    }
}
