package br.com.projetointegrador.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class LeitorEntrada {
    private final Scanner scanner;

    public LeitorEntrada(Scanner scanner) {
        this.scanner = scanner;
    }

    public String lerTextoObrigatorio(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = scanner.nextLine().trim();
            if (!valor.isBlank()) {
                return valor;
            }
            System.out.println("Valor obrigatorio. Tente novamente.");
        }
    }

    public String lerTextoOpcional(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    public int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = scanner.nextLine().trim();
            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                System.out.println("Informe um numero inteiro valido.");
            }
        }
    }

    public int lerInteiroPositivo(String mensagem) {
        while (true) {
            int valor = lerInteiro(mensagem);
            if (valor > 0) {
                return valor;
            }
            System.out.println("Informe um numero maior que zero.");
        }
    }

    public boolean lerSimNao(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = scanner.nextLine().trim();
            if (valor.equalsIgnoreCase("S") || valor.equalsIgnoreCase("SIM")) {
                return true;
            }
            if (valor.equalsIgnoreCase("N") || valor.equalsIgnoreCase("NAO")) {
                return false;
            }
            System.out.println("Digite S para sim ou N para nao.");
        }
    }

    public LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = scanner.nextLine().trim();
            try {
                return LocalDate.parse(valor);
            } catch (DateTimeParseException e) {
                System.out.println("Data invalida. Use o formato AAAA-MM-DD, por exemplo 2024-05-10.");
            }
        }
    }
}
