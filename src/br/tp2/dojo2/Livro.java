package br.tp2.dojo2;

import java.util.Objects;
import java.util.Scanner;

public class Livro {
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;

    public Livro() {
        this.titulo = null;
        this.autor = null;
        this.editora = null;
        this.anoPublicacao = 1900;
    }

    public Livro(String titulo, String autor, String editora, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
    }

    public Livro procuraLivro(String titulo, String autor) {
        Biblioteca biblioteca = new Biblioteca();

        for (Livro livro : biblioteca.getLivros())
            if (Objects.equals(livro.getTitulo(), titulo) && Objects.equals(livro.getAutor(), autor))
                return livro;

        return null;
    }

    public String leTitulo() {
        String titulo;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Título:");
            titulo = scanner.nextLine();

            if (titulo.length() == 0)
                System.out.println("Título inválido");
            else
                break;
        } while (true);

        return titulo;
    }

    public String leAutor() {
        String autor;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Autor:");
            autor = scanner.nextLine();

            if (autor.length() == 0)
                System.out.println("Autor inválido");
            else
                break;
        } while (true);

        return autor;
    }

    private String leEditora() {
        String editora;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Editora:");
            editora = scanner.nextLine();

            if (editora.length() == 0)
                System.out.println("Editora inválido");
            else
                break;
        } while (true);

        return editora;
    }

    private int leAnoPublicacao() {
        int anoPublicacao;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Ano de publicação:");
            anoPublicacao = scanner.nextInt();

            if (anoPublicacao < 1900 || anoPublicacao > 2022)
                System.out.println("Ano de publicação inválido");
            else
                break;
        } while (true);

        return anoPublicacao;
    }

    public Livro novoRegistro() {
        String titulo, autor;

        System.out.println("""
                 * * * * * * * * * * * * * * * * * * * *
                NOVO LIVRO
                Preencha os dados abaixo
                """);

        titulo = leTitulo();
        autor = leAutor();

        if (procuraLivro(titulo, autor) != null) {
            System.out.println("\nErro: Livro já cadastrado\nO cadastro de novo livro foi interrompido.");
            return null;
        }

        return new Livro(titulo, autor, leEditora(), leAnoPublicacao());
    }

    public Livro removeRegistro() {
        Livro livro;

        System.out.println("""
                 * * * * * * * * * * * * * * * * * * * *
                REMOVER LIVRO
                Preencha os dados abaixo
                """);

        livro = procuraLivro(leTitulo(), leAutor());

        if (livro == null)
            System.out.println("\nErro: Livro não cadastrado\nA exclusão de livro foi interrompida.");

        return livro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
}
