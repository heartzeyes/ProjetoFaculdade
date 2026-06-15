package br.com.projetointegrador.model;

public class Paciente {
    private int id;
    private String nome;
    private int idade;
    private String endereco;
    private String telefone;
    private int regiaoId;
    private String regiaoNome;
    private int escolaridadeId;
    private String escolaridadeDescricao;
    private boolean ficouDoente;

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getRegiaoId() {
        return regiaoId;
    }

    public void setRegiaoId(int regiaoId) {
        this.regiaoId = regiaoId;
    }

    public String getRegiaoNome() {
        return regiaoNome;
    }

    public void setRegiaoNome(String regiaoNome) {
        this.regiaoNome = regiaoNome;
    }

    public int getEscolaridadeId() {
        return escolaridadeId;
    }

    public void setEscolaridadeId(int escolaridadeId) {
        this.escolaridadeId = escolaridadeId;
    }

    public String getEscolaridadeDescricao() {
        return escolaridadeDescricao;
    }

    public void setEscolaridadeDescricao(String escolaridadeDescricao) {
        this.escolaridadeDescricao = escolaridadeDescricao;
    }

    public boolean isFicouDoente() {
        return ficouDoente;
    }

    public void setFicouDoente(boolean ficouDoente) {
        this.ficouDoente = ficouDoente;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Nome: " + nome
                + " | Idade: " + idade
                + " | Telefone: " + telefone
                + " | Regiao: " + valorOuId(regiaoNome, regiaoId)
                + " | Escolaridade: " + valorOuId(escolaridadeDescricao, escolaridadeId)
                + " | Ficou doente: " + (ficouDoente ? "Sim" : "Nao");
    }

    private String valorOuId(String valor, int id) {
        if (valor == null || valor.isBlank()) {
            return String.valueOf(id);
        }
        return valor;
    }
}
