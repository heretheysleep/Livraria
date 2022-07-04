package br.tp2.dojo2;

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    private static final ArrayList<Cliente> clientes = new ArrayList<>();

    public Biblioteca() {
    }

    private void novoCliente() {
        Cliente cliente = new Cliente();
        cliente = cliente.novoRegistro();

        if (cliente != null) {
            adicionaCliente(cliente);
            System.out.println("Cadastro de cliente realizado com sucesso.");
        }
    }

    private void removeCliente() {
        Cliente cliente = new Cliente();
        cliente = cliente.removeRegistro();

        if (cliente != null) {
            removeCliente(cliente);
            System.out.println("Exclusão de cliente realizada com sucesso.");
        }
    }

    private boolean administraMenuClientes(int opcao) {
        switch (opcao) {
            case 1:
                novoCliente();
                break;
            case 2:
                removeCliente();
                break;
            case 3:
                break;

            default:
                System.out.println("\nOpção inválida");
                return false;
        }

        return true;
    }

    private void menuClientes() {
        int opcao;
        Scanner scanner = new Scanner(System.in);
        boolean opcaoValida;

        System.out.println("""
             * * * * * * * * * * * * * * * * * * * *
            CLIENTES
            Escolha uma das opções abaixo

            1. Novo cliente
            2. Remover cliente
            3. Voltar ao menu inicial
            """);

        do {
            System.out.println("Digite a opção desejada:");
            opcao = scanner.nextInt();

            opcaoValida = administraMenuClientes(opcao);
        } while (!opcaoValida);
    }

    private boolean administraMenuPrincipal(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("\nMenu Aluguéis\n");
                break;
            case 2:
                menuClientes();
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

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    private void adicionaCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    private void removeCliente(Cliente cliente) {
        clientes.remove(cliente);
    }
}
