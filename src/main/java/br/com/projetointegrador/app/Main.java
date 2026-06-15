package br.com.projetointegrador.app;

import br.com.projetointegrador.dao.ReferenciaDAO;
import br.com.projetointegrador.model.AplicacaoVacina;
import br.com.projetointegrador.model.Escolaridade;
import br.com.projetointegrador.model.Paciente;
import br.com.projetointegrador.model.Regiao;
import br.com.projetointegrador.model.Vacina;
import br.com.projetointegrador.service.AnaliseService;
import br.com.projetointegrador.service.AplicacaoVacinaService;
import br.com.projetointegrador.service.PacienteService;
import br.com.projetointegrador.service.VacinaService;
import br.com.projetointegrador.util.LeitorEntrada;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final LeitorEntrada leitor = new LeitorEntrada(scanner);
    private final PacienteService pacienteService = new PacienteService();
    private final VacinaService vacinaService = new VacinaService();
    private final AplicacaoVacinaService aplicacaoVacinaService = new AplicacaoVacinaService();
    private final AnaliseService analiseService = new AnaliseService();
    private final ReferenciaDAO referenciaDAO = new ReferenciaDAO();

    public static void main(String[] args) {
        new Main().executar();
    }

    private void executar() {
        System.out.println("Sistema de Vacinacao iniciado.");
        System.out.println("Configure DB_URL, DB_USER e DB_PASSWORD se o MySQL nao usar os valores padrao.");

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = leitor.lerInteiro("Escolha uma opcao: ");
            tratarOpcaoPrincipal(opcao);
        } while (opcao != 0);

        scanner.close();
        System.out.println("Sistema finalizado.");
    }

    private void exibirMenuPrincipal() {
        System.out.println();
        System.out.println("===== SISTEMA DE VACINACAO =====");
        System.out.println("1 - Cadastrar paciente");
        System.out.println("2 - Cadastrar vacina");
        System.out.println("3 - Registrar aplicacao de vacina");
        System.out.println("4 - Listar pacientes");
        System.out.println("5 - Listar vacinas");
        System.out.println("6 - Consultar paciente");
        System.out.println("7 - Exibir aplicacoes de vacina");
        System.out.println("8 - Exibir analises");
        System.out.println("9 - Exibir uso de estruturas de dados");
        System.out.println("0 - Sair");
        System.out.println();
    }

    private void tratarOpcaoPrincipal(int opcao) {
        try {
            switch (opcao) {
                case 1 -> cadastrarPaciente();
                case 2 -> cadastrarVacina();
                case 3 -> registrarAplicacao();
                case 4 -> listarPacientes();
                case 5 -> listarVacinas();
                case 6 -> consultarPaciente();
                case 7 -> listarAplicacoes();
                case 8 -> exibirAnalises();
                case 9 -> exibirEstruturasDeDados();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opcao invalida.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validacao: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            System.out.println("Verifique se o MySQL esta ligado, se o banco foi criado e se usuario/senha estao corretos.");
        }
    }

    private void cadastrarPaciente() throws SQLException {
        System.out.println("\n===== CADASTRO DE PACIENTE =====");

        Paciente paciente = new Paciente();
        paciente.setNome(leitor.lerTextoObrigatorio("Nome: "));
        paciente.setIdade(leitor.lerInteiroPositivo("Idade: "));
        paciente.setEndereco(leitor.lerTextoObrigatorio("Endereco: "));
        paciente.setTelefone(leitor.lerTextoObrigatorio("Telefone: "));

        listarRegioesParaEscolha();
        paciente.setRegiaoId(leitor.lerInteiroPositivo("ID da regiao: "));

        listarEscolaridadesParaEscolha();
        paciente.setEscolaridadeId(leitor.lerInteiroPositivo("ID da escolaridade: "));

        paciente.setFicouDoente(leitor.lerSimNao("Ja ficou doente? (S/N): "));

        Paciente salvo = pacienteService.cadastrar(paciente);
        System.out.println("Paciente cadastrado com sucesso. ID gerado: " + salvo.getId());
    }

    private void cadastrarVacina() throws SQLException {
        System.out.println("\n===== CADASTRO DE VACINA =====");

        Vacina vacina = new Vacina();
        vacina.setNome(leitor.lerTextoObrigatorio("Nome ou tipo da vacina: "));
        vacina.setFabricante(leitor.lerTextoOpcional("Fabricante: "));
        vacina.setDoencaAlvo(leitor.lerTextoObrigatorio("Doenca alvo: "));
        vacina.setQtdDosesRecomendadas(leitor.lerInteiroPositivo("Quantidade de doses recomendadas: "));

        Vacina salva = vacinaService.cadastrar(vacina);
        System.out.println("Vacina cadastrada com sucesso. ID gerado: " + salva.getId());
    }

    private void registrarAplicacao() throws SQLException {
        System.out.println("\n===== REGISTRO DE APLICACAO =====");
        System.out.println("Pacientes disponiveis:");
        imprimirLista(pacienteService.listarTodos());

        System.out.println("\nVacinas disponiveis:");
        imprimirLista(vacinaService.listarTodos());

        AplicacaoVacina aplicacao = new AplicacaoVacina();
        aplicacao.setPacienteId(leitor.lerInteiroPositivo("ID do paciente: "));
        aplicacao.setVacinaId(leitor.lerInteiroPositivo("ID da vacina: "));
        aplicacao.setDataAplicacao(leitor.lerData("Data da aplicacao (AAAA-MM-DD): "));
        aplicacao.setNumeroDose(leitor.lerInteiroPositivo("Numero da dose: "));
        aplicacao.setObservacao(leitor.lerTextoOpcional("Observacao: "));

        AplicacaoVacina salva = aplicacaoVacinaService.registrar(aplicacao);
        System.out.println("Aplicacao registrada com sucesso. ID gerado: " + salva.getId());
    }

    private void listarPacientes() throws SQLException {
        System.out.println("\n===== PACIENTES CADASTRADOS =====");
        imprimirLista(pacienteService.listarTodos());
    }

    private void listarVacinas() throws SQLException {
        System.out.println("\n===== VACINAS CADASTRADAS =====");
        imprimirLista(vacinaService.listarTodos());
    }

    private void consultarPaciente() throws SQLException {
        System.out.println("\n===== CONSULTA DE PACIENTE =====");
        System.out.println("1 - Consultar por ID");
        System.out.println("2 - Consultar por nome ou telefone");
        int opcao = leitor.lerInteiro("Escolha uma opcao: ");

        if (opcao == 1) {
            int id = leitor.lerInteiroPositivo("ID do paciente: ");
            pacienteService.buscarPorId(id).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("Paciente nao encontrado.")
            );
        } else if (opcao == 2) {
            String termo = leitor.lerTextoObrigatorio("Nome ou telefone: ");
            imprimirLista(pacienteService.buscarPorNomeOuTelefone(termo));
        } else {
            System.out.println("Opcao invalida.");
        }
    }

    private void listarAplicacoes() throws SQLException {
        System.out.println("\n===== APLICACOES DE VACINA =====");
        imprimirLista(aplicacaoVacinaService.listarTodos());
    }

    private void exibirAnalises() throws SQLException {
        int opcao;
        do {
            exibirMenuAnalises();
            opcao = leitor.lerInteiro("Escolha uma opcao: ");
            tratarOpcaoAnalise(opcao);
        } while (opcao != 0);
    }

    private void exibirMenuAnalises() {
        System.out.println();
        System.out.println("===== ANALISES =====");
        System.out.println("1 - Quantidade de vacinados por regiao");
        System.out.println("2 - Vacinado x doente");
        System.out.println("3 - Vacinado x escolaridade");
        System.out.println("4 - Percentual de vacinacao por regiao");
        System.out.println("5 - Pacientes com doses incompletas");
        System.out.println("6 - Lista de vacinas aplicadas por paciente");
        System.out.println("7 - Quantidade de vacinas aplicadas por regiao");
        System.out.println("0 - Voltar");
        System.out.println();
    }

    private void tratarOpcaoAnalise(int opcao) throws SQLException {
        switch (opcao) {
            case 1 -> imprimirResultadoAnalise("Vacinados por regiao", analiseService.vacinadosPorRegiao());
            case 2 -> imprimirResultadoAnalise("Vacinado x doente", analiseService.vacinadoDoente());
            case 3 -> imprimirResultadoAnalise("Vacinacao por escolaridade", analiseService.vacinacaoPorEscolaridade());
            case 4 -> imprimirResultadoAnalise("Percentual de vacinacao por regiao", analiseService.percentualVacinacaoPorRegiao());
            case 5 -> imprimirResultadoAnalise("Pacientes com doses incompletas", analiseService.pacientesComDosesIncompletas());
            case 6 -> imprimirResultadoAnalise("Vacinas aplicadas por paciente", analiseService.vacinasAplicadasPorPaciente());
            case 7 -> imprimirResultadoAnalise("Vacinas aplicadas por regiao", analiseService.vacinasAplicadasPorRegiao());
            case 0 -> System.out.println("Voltando ao menu principal...");
            default -> System.out.println("Opcao invalida.");
        }
    }

    private void listarRegioesParaEscolha() throws SQLException {
        System.out.println("\nRegioes cadastradas:");
        List<Regiao> regioes = referenciaDAO.listarRegioes();
        imprimirLista(regioes);
    }

    private void listarEscolaridadesParaEscolha() throws SQLException {
        System.out.println("\nEscolaridades cadastradas:");
        List<Escolaridade> escolaridades = referenciaDAO.listarEscolaridades();
        imprimirLista(escolaridades);
    }

    private void imprimirResultadoAnalise(String titulo, List<String> linhas) {
        System.out.println("\n===== " + titulo.toUpperCase() + " =====");
        imprimirLista(linhas);
    }

    private void imprimirLista(List<?> itens) {
        if (itens.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
            return;
        }
        for (Object item : itens) {
            System.out.println(item);
        }
    }

    private void exibirEstruturasDeDados() {
        System.out.println("\n===== ESTRUTURAS DE DADOS EM JAVA =====");
        System.out.println("ArrayList<Paciente> pacientesTemporarios: " + pacienteService.totalPacientesTemporarios() + " item(ns) nesta execucao.");
        System.out.println("HashMap<String, Paciente> pacientesPorTelefone: usado para busca rapida por telefone nesta execucao.");
        System.out.println("ArrayList<Vacina> vacinasTemporarias: " + vacinaService.totalVacinasTemporarias() + " item(ns) nesta execucao.");
        System.out.println("ArrayList<AplicacaoVacina> aplicacoesTemporarias: " + aplicacaoVacinaService.totalAplicacoesTemporarias() + " item(ns) nesta execucao.");
        System.out.println("Essas estruturas recebem os objetos antes da persistencia no MySQL.");
    }
}
