package br.tp2.dojo2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Aluguel {
    private final String cpf;
    private final String tituloLivro;
    private final String autorLivro;
    private boolean emCurso;
    private final Date inicioAluguel;
    private Date terminoAluguel;

    public Aluguel() {
        this.cpf = null;
        this.tituloLivro = null;
        this.autorLivro = null;
        this.emCurso = false;
        this.inicioAluguel = converteDataString("01/01/1900");
        this.terminoAluguel = null;
    }

    public Aluguel(String cpf, String tituloLivro, String autorLivro, Date inicioAluguel) {
        this.cpf = cpf;
        this.tituloLivro = tituloLivro;
        this.autorLivro = autorLivro;
        this.emCurso = true;
        this.inicioAluguel = inicioAluguel;
        this.terminoAluguel = null;
    }

    private Date converteDataString(String data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return formato.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }

    private Date leData() {
        String input;
        Scanner scanner = new Scanner(System.in);
        Date data;

        do {
            System.out.println("Data de início de aluguel:");
            input = scanner.next();

            data = converteDataString(input);

            if (data == null)
                System.out.println("Data inválida");
            else
                break;
        } while (true);

        return data;
    }

    public Aluguel novoRegistro() {
        Cliente cliente = new Cliente();
        Livro livro = new Livro();
        String cpf, titulo, autor;
        Date data;

        System.out.println("""
                 * * * * * * * * * * * * * * * * * * * *
                NOVO ALUGUEL
                Preencha os dados abaixo
                """);

        cpf = cliente.leCpf();

        cliente = cliente.procuraCliente(cpf);

        if (cliente == null) {
            System.out.println("\nErro: Cliente não cadastrado\nO cadastro de novo aluguel foi interrompido.");
            return null;
        }

        titulo = livro.leTitulo();
        autor = livro.leAutor();

        livro = livro.procuraLivro(titulo, autor);

        if (livro == null) {
            System.out.println("\nErro: Livro não cadastrado\nO cadastro de novo aluguel foi interrompido.");
            return null;
        }

        data = leData();

        return new Aluguel(cpf, titulo, autor, data);
    }

    public String getCpf() {
        return cpf;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public String getAutorLivro() {
        return autorLivro;
    }

    public boolean isEmCurso() {
        return emCurso;
    }

    public void setEmCurso() {
        this.emCurso = false;
    }

    public Date getInicioAluguel() {
        return inicioAluguel;
    }

    public Date getTerminoAluguel() {
        return terminoAluguel;
    }

    public void setTerminoAluguel(Date terminoAluguel) {
        this.terminoAluguel = terminoAluguel;
    }
}
