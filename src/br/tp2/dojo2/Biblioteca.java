package br.tp2.dojo2;

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static final ArrayList<Livro> livros = new ArrayList<>();
    private static final ArrayList<Aluguel> alugueis = new ArrayList<>();

    public Biblioteca() {
    }

    private boolean administraMenuRelatorios(int opcao) {
        switch (opcao) {
            case 1:
                novoAluguel();
                break;
            case 2:
                devolveLivro();
                break;
            case 3:
                break;

            default:
                System.out.println("\nOpção inválida");
                return false;
        }

        return true;
    }

    private void menuRelatorios() {
        int opcao;
        Scanner scanner = new Scanner(System.in);
        boolean opcaoValida;

        System.out.println("""
             * * * * * * * * * * * * * * * * * * * *
            RELATÓRIOS
            Escolha uma das opções abaixo

            1. Filtrar aluguéis por cliente
            2. Filtrar aluguéis por livro
            3. Voltar ao menu inicial
            """);

        do {
            System.out.println("Digite a opção desejada:");
            opcao = scanner.nextInt();

            opcaoValida = administraMenuAlugueis(opcao);
        } while (!opcaoValida);
    }

    private void novoAluguel() {
        Aluguel aluguel = new Aluguel();
        aluguel = aluguel.novoRegistro();

        if (aluguel != null) {
            Livro livro = new Livro();
            livro = livro.procuraLivro(aluguel.getTituloLivro(), aluguel.getAutorLivro());

            adicionaAluguel(aluguel);
            livros.get(livros.indexOf(livro)).setDisponivel();

            System.out.println("\nAluguel de livro realizado com sucesso.");
        }
    }

    private void devolveLivro() {
        Aluguel aluguel = new Aluguel();
        aluguel = aluguel.finalizaAluguel();

        if (aluguel != null) {
            Livro livro = new Livro();
            livro = livro.procuraLivro(aluguel.getTituloLivro(), aluguel.getAutorLivro());

            devolveLivro(aluguel);
            livros.get(livros.indexOf(livro)).setDisponivel();

            System.out.println("\nDevolução de livro realizada com sucesso.");
        }
    }

    private boolean administraMenuAlugueis(int opcao) {
        switch (opcao) {
            case 1:
                novoAluguel();
                break;
            case 2:
                devolveLivro();
                break;
            case 3:
                break;

            default:
                System.out.println("\nOpção inválida");
                return false;
        }

        return true;
    }

    private void menuAlugueis() {
        int opcao;
        Scanner scanner = new Scanner(System.in);
        boolean opcaoValida;

        System.out.println("""
             * * * * * * * * * * * * * * * * * * * *
            ALUGUÉIS
            Escolha uma das opções abaixo

            1. Novo aluguel
            2. Devolução de livro
            3. Voltar ao menu inicial
            """);

        do {
            System.out.println("Digite a opção desejada:");
            opcao = scanner.nextInt();

            opcaoValida = administraMenuAlugueis(opcao);
        } while (!opcaoValida);
    }

    private void novoLivro() {
        Livro livro = new Livro();
        livro = livro.novoRegistro();

        if (livro != null) {
            adicionaLivro(livro);
            System.out.println("\nCadastro de livro realizado com sucesso.");
        }
    }

    private void removeLivro() {
        Livro livro = new Livro();
        livro = livro.removeRegistro();

        if (livro != null) {
            removeLivro(livro);
            System.out.println("\nExclusão de livro realizada com sucesso.");
        }
    }

    private boolean administraMenuLivros(int opcao) {
        switch (opcao) {
            case 1:
                novoLivro();
                break;
            case 2:
                removeLivro();
                break;
            case 3:
                break;

            default:
                System.out.println("\nOpção inválida");
                return false;
        }

        return true;
    }

    private void menuLivros() {
        int opcao;
        Scanner scanner = new Scanner(System.in);
        boolean opcaoValida;

        System.out.println("""
             * * * * * * * * * * * * * * * * * * * *
            LIVROS
            Escolha uma das opções abaixo

            1. Novo livro
            2. Remover livro
            3. Voltar ao menu inicial
            """);

        do {
            System.out.println("Digite a opção desejada:");
            opcao = scanner.nextInt();

            opcaoValida = administraMenuLivros(opcao);
        } while (!opcaoValida);
    }

    private void novoCliente() {
        Cliente cliente = new Cliente();
        cliente = cliente.novoRegistro();

        if (cliente != null) {
            adicionaCliente(cliente);
            System.out.println("\nCadastro de cliente realizado com sucesso.");
        }
    }

    private void removeCliente() {
        Cliente cliente = new Cliente();
        cliente = cliente.removeRegistro();

        if (cliente != null) {
            removeCliente(cliente);
            System.out.println("\nExclusão de cliente realizada com sucesso.");
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
                menuAlugueis();
                break;
            case 2:
                menuClientes();
                break;
            case 3:
                menuLivros();
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

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    private void adicionaLivro(Livro livro) {
        livros.add(livro);
    }

    private void removeLivro(Livro livro) {
        livros.remove(livro);
    }

    public ArrayList<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void adicionaAluguel(Aluguel aluguel) {
        alugueis.add(aluguel);
    }

    public void devolveLivro(Aluguel aluguel) {
        for (Aluguel registro : alugueis)
            if (registro == aluguel)
                alugueis.get(alugueis.indexOf(registro)).setEmCurso();
    }
}
