package br.tp2.dojo2;

import java.util.ArrayList;
import java.util.Arrays;

public class InterfaceUsuario {
    private static final ArrayList<String> opcoesMenuInicial = new ArrayList<>(Arrays.asList("MENU INICIAL",
            "Administrar aluguéis", "Administrar clientes", "Administrar livros", "Emitir relatório",
            "Finalizar programa"));
    private static final ArrayList<String> opcoesMenuAluguel = new ArrayList<>(Arrays.asList("ALUGUÉIS",
            "Novo aluguel", "Devolução de livro", "Voltar ao menu inicial"));
    private static final ArrayList<String> opcoesMenuCliente = new ArrayList<>(Arrays.asList("CLIENTES",
            "Novo cliente", "Remover cliente", "Voltar ao menu inicial"));
    private static final ArrayList<String> opcoesMenuLivro = new ArrayList<>(Arrays.asList("LIVROS",
            "Novo livro", "Remover livro", "Voltar ao menu inicial"));
    private static final ArrayList<String> opcoesMenuRelatorio = new ArrayList<>(Arrays.asList("RELATÓRIOS",
            "Filtrar por cliente", "Filtrar por livro", "Voltar ao menu inicial"));

    private void exibeOpcoes(ArrayList<String> listaOpcoes) {
        System.out.println(" * * * * * * * * * * * * * * * * * * * *\n" + listaOpcoes.get(0) +
                "\nEscolha uma das opções abaixo\n");

        for (int index = 1; index < listaOpcoes.size(); index++) {
            System.out.println(index + ". " + listaOpcoes.get(index));
        }
    }

    public void exibeCabecalhoFormulario(String titulo) {
        System.out.println(" * * * * * * * * * * * * * * * * * * * *\n" + titulo + "\nPreencha os dados abaixo\n");
    }

    private boolean administraMenuRelatorio(int opcao) {
        Relatorio relatorio = new Relatorio();

        switch (opcao) {
            case 1 -> relatorio.emiteRelatorio(1);
            case 2 -> relatorio.emiteRelatorio(2);
            case 3 -> {}
            default -> {
                System.out.println("Opção inválida");
                return false;
            }
        }

        return true;
    }

    private void menuRelatorio() {
        int opcao = 0;
        Utilitario utilitario = new Utilitario();
        boolean opcaoValida;

        exibeOpcoes(opcoesMenuRelatorio);

        do {
            try {
                opcao = Integer.parseInt(utilitario.leTexto("\nDigite a opção desejada"));
            } catch (NumberFormatException ignored) {}

            opcaoValida = administraMenuRelatorio(opcao);
        } while (!opcaoValida);
    }

    private boolean administraMenuLivro(int opcao) {
        Biblioteca biblioteca = new Biblioteca();

        switch (opcao) {
            case 1 -> biblioteca.adicionaLivro();
            case 2 -> biblioteca.removeLivro();
            case 3 -> {}
            default -> {
                System.out.println("Opção inválida");
                return false;
            }
        }

        return true;
    }

    private void menuLivro() {
        int opcao = 0;
        Utilitario utilitario = new Utilitario();
        boolean opcaoValida;

        exibeOpcoes(opcoesMenuLivro);

        do {
            try {
                opcao = Integer.parseInt(utilitario.leTexto("\nDigite a opção desejada"));
            } catch (NumberFormatException ignored) {}

            opcaoValida = administraMenuLivro(opcao);
        } while (!opcaoValida);
    }

    private boolean administraMenuCliente(int opcao) {
        Biblioteca biblioteca = new Biblioteca();

        switch (opcao) {
            case 1 -> biblioteca.adicionaCliente();
            case 2 -> biblioteca.removeCliente();
            case 3 -> {}
            default -> {
                System.out.println("Opção inválida");
                return false;
            }
        }

        return true;
    }

    private void menuCliente() {
        int opcao = 0;
        Utilitario utilitario = new Utilitario();
        boolean opcaoValida;

        exibeOpcoes(opcoesMenuCliente);

        do {
            try {
                opcao = Integer.parseInt(utilitario.leTexto("\nDigite a opção desejada"));
            } catch (NumberFormatException ignored) {}

            opcaoValida = administraMenuCliente(opcao);
        } while (!opcaoValida);
    }

    private boolean administraMenuAluguel(int opcao) {
        Biblioteca biblioteca = new Biblioteca();

        switch (opcao) {
            case 1 -> biblioteca.adicionaAluguel();
            case 2 -> biblioteca.finalizaAluguel();
            case 3 -> {}
            default -> {
                System.out.println("Opção inválida");
                return false;
            }
        }

        return true;
    }

    private void menuAluguel() {
        int opcao = 0;
        Utilitario utilitario = new Utilitario();
        boolean opcaoValida;

        exibeOpcoes(opcoesMenuAluguel);

        do {
            try {
                opcao = Integer.parseInt(utilitario.leTexto("\nDigite a opção desejada"));
            } catch (NumberFormatException ignored) {}

            opcaoValida = administraMenuAluguel(opcao);
        } while (!opcaoValida);
    }

    private boolean administraMenuInicial(int opcao) {
        switch (opcao) {
            case 1 -> menuAluguel();
            case 2 -> menuCliente();
            case 3 -> menuLivro();
            case 4 -> menuRelatorio();
            case 5 -> System.out.println("\nPrograma finalizado.");
            default -> {
                System.out.println("Opção inválida");
                return false;
            }
        }

        return true;
    }

    public void menuInicial() {
        int opcao = 0;
        Utilitario utilitario = new Utilitario();
        boolean opcaoValida;

        do {
            exibeOpcoes(opcoesMenuInicial);

            do {
                try {
                    opcao = Integer.parseInt(utilitario.leTexto("\nDigite a opção desejada"));
                } catch (NumberFormatException ignored) {}

                opcaoValida = administraMenuInicial(opcao);
            } while (!opcaoValida);
        } while (opcao != 5);
    }
}
