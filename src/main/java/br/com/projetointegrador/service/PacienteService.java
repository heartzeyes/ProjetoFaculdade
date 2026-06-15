package br.com.projetointegrador.service;

import br.com.projetointegrador.dao.PacienteDAO;
import br.com.projetointegrador.model.Paciente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PacienteService {
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final List<Paciente> pacientesTemporarios = new ArrayList<>();
    private final Map<String, Paciente> pacientesPorTelefone = new HashMap<>();

    public Paciente cadastrar(Paciente paciente) throws SQLException {
        validar(paciente);

        pacientesTemporarios.add(paciente);
        pacientesPorTelefone.put(paciente.getTelefone(), paciente);

        int idGerado = pacienteDAO.inserir(paciente);
        paciente.setId(idGerado);
        return paciente;
    }

    public List<Paciente> listarTodos() throws SQLException {
        return pacienteDAO.listarTodos();
    }

    public Optional<Paciente> buscarPorId(int id) throws SQLException {
        return pacienteDAO.buscarPorId(id);
    }

    public List<Paciente> buscarPorNomeOuTelefone(String termo) throws SQLException {
        if (pacientesPorTelefone.containsKey(termo)) {
            return List.of(pacientesPorTelefone.get(termo));
        }
        return pacienteDAO.buscarPorNomeOuTelefone(termo);
    }

    public int totalPacientesTemporarios() {
        return pacientesTemporarios.size();
    }

    private void validar(Paciente paciente) {
        if (paciente.getNome() == null || paciente.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do paciente e obrigatorio.");
        }
        if (paciente.getIdade() <= 0) {
            throw new IllegalArgumentException("Idade deve ser maior que zero.");
        }
        if (paciente.getTelefone() == null || paciente.getTelefone().isBlank()) {
            throw new IllegalArgumentException("Telefone e obrigatorio.");
        }
        if (paciente.getRegiaoId() <= 0) {
            throw new IllegalArgumentException("Regiao invalida.");
        }
        if (paciente.getEscolaridadeId() <= 0) {
            throw new IllegalArgumentException("Escolaridade invalida.");
        }
    }
}
