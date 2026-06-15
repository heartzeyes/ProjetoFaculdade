package br.com.projetointegrador.model;

public class Vacina {
    private int id;
    private String nome;
    private String fabricante;
    private String doencaAlvo;
    private int qtdDosesRecomendadas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getDoencaAlvo() {
        return doencaAlvo;
    }

    public void setDoencaAlvo(String doencaAlvo) {
        this.doencaAlvo = doencaAlvo;
    }

    public int getQtdDosesRecomendadas() {
        return qtdDosesRecomendadas;
    }

    public void setQtdDosesRecomendadas(int qtdDosesRecomendadas) {
        this.qtdDosesRecomendadas = qtdDosesRecomendadas;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Nome: " + nome
                + " | Fabricante: " + fabricante
                + " | Doenca alvo: " + doencaAlvo
                + " | Doses recomendadas: " + qtdDosesRecomendadas;
    }
}
