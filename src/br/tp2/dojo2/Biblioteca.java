package br.tp2.dojo2;

import java.util.ArrayList;

public class Biblioteca {
    private static final ArrayList<Aluguel> alugueis = new ArrayList<>();
    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static final ArrayList<Livro> livros = new ArrayList<>();

    public void adicionaLivro() {
        Livro livro = new Livro();
        livro = livro.novoRegistro();

        if (livro != null) {
            livros.add(livro);
            System.out.println("\nCadastro de livro realizado com sucesso.");
        }
    }

    public void removeLivro() {
        Livro livro = new Livro();
        livro = livro.exclusaoRegistro();

        if (livro != null) {
            livros.remove(livro);
            System.out.println("\nExclusão de livro realizada com sucesso.");
        }
    }

    public void adicionaCliente() {
        Cliente cliente = new Cliente();
        cliente = cliente.novoRegistro();

        if (cliente != null) {
            clientes.add(cliente);
            System.out.println("\nCadastro de cliente realizado com sucesso.");
        }
    }

    public void removeCliente() {
        Cliente cliente = new Cliente();
        cliente = cliente.exclusaoRegistro();

        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("\nExclusão de cliente realizada com sucesso.");
        }
    }

    public void adicionaAluguel() {
        Aluguel aluguel = new Aluguel();
        aluguel = aluguel.novoRegistro();

        if (aluguel != null) {
            Livro livro = new Livro();
            livro = livro.procuraLivro(aluguel.getTituloLivro(), aluguel.getAutorLivro());

            alugueis.add(aluguel);
            livros.get(livros.indexOf(livro)).setDisponivel();

            System.out.println("\nCadastro de aluguel realizado com sucesso.");
        }
    }

    public void finalizaAluguel() {
        Aluguel aluguel = new Aluguel();
        aluguel = aluguel.finalizaRegistro();

        if (aluguel != null) {
            Livro livro = new Livro();
            livro = livro.procuraLivro(aluguel.getTituloLivro(), aluguel.getAutorLivro());

            alugueis.get(alugueis.indexOf(aluguel)).setEmCurso();
            livros.get(livros.indexOf(livro)).setDisponivel();

            System.out.println("\nDevolução de livro realizada com sucesso.");
        }
    }

    public static ArrayList<Aluguel> getAlugueis() {
        return alugueis;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static ArrayList<Livro> getLivros() {
        return livros;
    }
}
