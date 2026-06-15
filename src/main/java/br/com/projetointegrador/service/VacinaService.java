package br.com.projetointegrador.service;

import br.com.projetointegrador.dao.VacinaDAO;
import br.com.projetointegrador.model.Vacina;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VacinaService {
    private final VacinaDAO vacinaDAO = new VacinaDAO();
    private final List<Vacina> vacinasTemporarias = new ArrayList<>();

    public Vacina cadastrar(Vacina vacina) throws SQLException {
        validar(vacina);

        vacinasTemporarias.add(vacina);

        int idGerado = vacinaDAO.inserir(vacina);
        vacina.setId(idGerado);
        return vacina;
    }

    public List<Vacina> listarTodos() throws SQLException {
        return vacinaDAO.listarTodos();
    }

    public Optional<Vacina> buscarPorId(int id) throws SQLException {
        return vacinaDAO.buscarPorId(id);
    }

    public int totalVacinasTemporarias() {
        return vacinasTemporarias.size();
    }

    private void validar(Vacina vacina) {
        if (vacina.getNome() == null || vacina.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome da vacina e obrigatorio.");
        }
        if (vacina.getDoencaAlvo() == null || vacina.getDoencaAlvo().isBlank()) {
            throw new IllegalArgumentException("Doenca alvo e obrigatoria.");
        }
        if (vacina.getQtdDosesRecomendadas() <= 0) {
            throw new IllegalArgumentException("Quantidade de doses recomendadas deve ser maior que zero.");
        }
    }
}
