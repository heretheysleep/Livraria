package br.tp2.dojo2;

import java.util.ArrayList;
import java.util.Objects;

public class Livro {
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;
    private boolean disponivel;

    public Livro() {
        this.titulo = null;
        this.autor = null;
        this.editora = null;
        this.anoPublicacao = 1900;
        this.disponivel = true;
    }

    public Livro(String titulo, String autor, String editora, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true;
    }

    public Livro procuraLivro(String titulo, String autor) {
        for (Livro livro : Biblioteca.getLivros())
            if (Objects.equals(livro.getTitulo(), titulo) && Objects.equals(livro.getAutor(), autor))
                return livro;

        return null;
    }

    public Livro novoRegistro() {
        String titulo, autor;
        Utilitario utilitario = new Utilitario();
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        interfaceUsuario.exibeCabecalhoFormulario("NOVO LIVRO");
        titulo = utilitario.leTexto("Título");
        autor = utilitario.leTexto("Autor");

        if (procuraLivro(titulo, autor) != null) {
            System.out.println("\nErro: Livro já cadastrado\nO cadastro de novo livro foi interrompido.");
            return null;
        }

        return new Livro(titulo, autor, utilitario.leTexto("Editora"), utilitario.leAnoPublicacao());
    }

    public Livro exclusaoRegistro() {
        String titulo, autor;
        Livro livro;
        Utilitario utilitario = new Utilitario();
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        Aluguel aluguel = new Aluguel();
        ArrayList<Aluguel> alugueis;

        interfaceUsuario.exibeCabecalhoFormulario("EXCLUSÃO DE CLIENTE");

        titulo = utilitario.leTexto("Título");
        autor = utilitario.leTexto("Autor");
        livro = procuraLivro(titulo, autor);

        if (livro == null)
            System.out.println("\nErro: Livro não cadastrado\nA exclusão de livro foi interrompida.");

        alugueis = aluguel.refinaListaAlugueisLivro(titulo, autor);

        if (aluguel.aluguelEmCurso(alugueis)) {
            System.out.println("\nErro: Livro alugado\nA exclusão de cliente foi interrompida.");
            return null;
        }

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

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel() {
        this.disponivel = !this.disponivel;
    }
}
