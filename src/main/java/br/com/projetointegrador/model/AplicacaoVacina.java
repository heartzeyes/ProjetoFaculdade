package br.com.projetointegrador.model;

import java.time.LocalDate;

public class AplicacaoVacina {
    private int id;
    private int pacienteId;
    private String pacienteNome;
    private int vacinaId;
    private String vacinaNome;
    private LocalDate dataAplicacao;
    private int numeroDose;
    private String observacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getPacienteNome() {
        return pacienteNome;
    }

    public void setPacienteNome(String pacienteNome) {
        this.pacienteNome = pacienteNome;
    }

    public int getVacinaId() {
        return vacinaId;
    }

    public void setVacinaId(int vacinaId) {
        this.vacinaId = vacinaId;
    }

    public String getVacinaNome() {
        return vacinaNome;
    }

    public void setVacinaNome(String vacinaNome) {
        this.vacinaNome = vacinaNome;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public int getNumeroDose() {
        return numeroDose;
    }

    public void setNumeroDose(int numeroDose) {
        this.numeroDose = numeroDose;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Paciente: " + valorOuId(pacienteNome, pacienteId)
                + " | Vacina: " + valorOuId(vacinaNome, vacinaId)
                + " | Data: " + dataAplicacao
                + " | Dose: " + numeroDose
                + " | Observacao: " + (observacao == null || observacao.isBlank() ? "-" : observacao);
    }

    private String valorOuId(String valor, int id) {
        if (valor == null || valor.isBlank()) {
            return String.valueOf(id);
        }
        return valor;
    }
}
