package br.com.projetointegrador.service;

import br.com.projetointegrador.dao.AplicacaoVacinaDAO;
import br.com.projetointegrador.dao.PacienteDAO;
import br.com.projetointegrador.dao.VacinaDAO;
import br.com.projetointegrador.model.AplicacaoVacina;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AplicacaoVacinaService {
    private final AplicacaoVacinaDAO aplicacaoVacinaDAO = new AplicacaoVacinaDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final VacinaDAO vacinaDAO = new VacinaDAO();
    private final List<AplicacaoVacina> aplicacoesTemporarias = new ArrayList<>();

    public AplicacaoVacina registrar(AplicacaoVacina aplicacao) throws SQLException {
        validar(aplicacao);

        if (pacienteDAO.buscarPorId(aplicacao.getPacienteId()).isEmpty()) {
            throw new IllegalArgumentException("Paciente nao encontrado.");
        }
        if (vacinaDAO.buscarPorId(aplicacao.getVacinaId()).isEmpty()) {
            throw new IllegalArgumentException("Vacina nao encontrada.");
        }

        aplicacoesTemporarias.add(aplicacao);

        int idGerado = aplicacaoVacinaDAO.inserir(aplicacao);
        aplicacao.setId(idGerado);
        return aplicacao;
    }

    public List<AplicacaoVacina> listarTodos() throws SQLException {
        return aplicacaoVacinaDAO.listarTodos();
    }

    public int totalAplicacoesTemporarias() {
        return aplicacoesTemporarias.size();
    }

    private void validar(AplicacaoVacina aplicacao) {
        if (aplicacao.getPacienteId() <= 0) {
            throw new IllegalArgumentException("Paciente invalido.");
        }
        if (aplicacao.getVacinaId() <= 0) {
            throw new IllegalArgumentException("Vacina invalida.");
        }
        if (aplicacao.getDataAplicacao() == null) {
            throw new IllegalArgumentException("Data de aplicacao e obrigatoria.");
        }
        if (aplicacao.getDataAplicacao().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de aplicacao nao pode estar no futuro.");
        }
        if (aplicacao.getNumeroDose() <= 0) {
            throw new IllegalArgumentException("Numero da dose deve ser maior que zero.");
        }
    }
}
