package br.tp2.dojo2;

import java.util.Scanner;

public class Biblioteca {
    public Biblioteca() {

    }

    private boolean administraMenuPrincipal(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("\nMenu Aluguéis\n");
                break;
            case 2:
                System.out.println("\nMenu Clientes\n");
                break;
            case 3:
                System.out.println("\nMenu Livros\n");
                break;
            case 4:
                System.out.println("\nMenu Relatórios\n");
                break;
            case 5:
                System.out.println("\nPrograma finalizado.");
                break;

            default:
                System.out.println("\nOpção inválida");
                return false;
        }

        return true;
    }

    public void menuPrincipal() {
        int opcao;
        Scanner scanner = new Scanner(System.in);
        boolean opcaoValida;

        do {
            System.out.println("""
                 * * * * * * * * * * * * * * * * * * * *
                MENU INICIAL
                Escolha uma das opções abaixo

                1. Administrar aluguéis
                2. Administrar clientes
                3. Administrar livros
                4. Emitir relatório
                5. Finalizar programa
                """);

            do {
                System.out.println("Digite a opção desejada:");
                opcao = scanner.nextInt();

                opcaoValida = administraMenuPrincipal(opcao);
            } while (!opcaoValida);
        } while (opcao != 5);
    }
}
