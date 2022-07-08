package br.tp2.dojo2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public ArrayList<Aluguel> refinaListaAlugueisCliente(String cpf) {
        Biblioteca biblioteca = new Biblioteca();
        ArrayList<Aluguel> resultados = new ArrayList<>();

        for (Aluguel aluguel : biblioteca.getAlugueis())
            if (Objects.equals(aluguel.getCpf(), cpf))
                resultados.add(aluguel);

        return resultados;
    }

    public boolean novoAluguelPermitido(ArrayList<Aluguel> alugueis) {
        int contador = 0;

        for (Aluguel aluguel : alugueis) {
            if (aluguel.isEmCurso())
                contador++;

            if (contador == 2)
                return false;
        }

        return true;
    }

    public boolean livroAlugadoRecentemente(Livro livro, ArrayList<Aluguel> alugueis) {
        int qtdAlugueis = alugueis.size();

        if (qtdAlugueis != 0)
            for (int index = qtdAlugueis - 1; (index >= qtdAlugueis -3) && (index >= 0); index--) {
                Livro registro = new Livro();
                String titulo, autor;

                titulo = alugueis.get(index).getTituloLivro();
                autor = alugueis.get(index).getAutorLivro();

                registro = registro.procuraLivro(titulo, autor);

                if (registro == livro)
                    return true;
            }

        return false;
    }

    private Date converteDataString(String data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return formato.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }

    private Date leData(String momento, Date dataInicio, int anoPublicacao) {
        String input;
        Scanner scanner = new Scanner(System.in);
        Date data;

        do {
            System.out.println("Data de " + momento + " de aluguel:");
            input = scanner.next();

            data = converteDataString(input);

            if (data == null ||
                    (dataInicio != null && data.before(dataInicio)) ||
                    data.getYear() < anoPublicacao)
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
        ArrayList<Aluguel> alugueisCliente;
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

        alugueisCliente = refinaListaAlugueisCliente(cpf);

        if (!novoAluguelPermitido(alugueisCliente)) {
            System.out.println("\nErro: Cliente possui 2 aluguéis em curso\nO cadastro de novo aluguel foi interrompido.");
            return null;
        }

        titulo = livro.leTitulo();
        autor = livro.leAutor();

        livro = livro.procuraLivro(titulo, autor);

        if (livro == null) {
            System.out.println("\nErro: Livro não cadastrado\nO cadastro de novo aluguel foi interrompido.");
            return null;
        }

        if (!livro.isDisponivel()) {
            System.out.println("\nErro: Livro não disponível\nO cadastro de novo aluguel foi interrompido.");
            return null;
        }

        if (livroAlugadoRecentemente(livro, alugueisCliente)) {
            System.out.println("\nErro: Livro alugado recentemente\nO cadastro de novo aluguel foi interrompido.");
            return null;
        }

        data = leData("início", null, livro.getAnoPublicacao());

        return new Aluguel(cpf, titulo, autor, data);
    }

    public Aluguel procuraAluguel(String cpf, String titulo, String autor) {
        Biblioteca biblioteca = new Biblioteca();

        for (Aluguel aluguel : biblioteca.getAlugueis())
            if (Objects.equals(aluguel.getCpf(), cpf) &&
                    Objects.equals(aluguel.getTituloLivro(), titulo) &&
                    Objects.equals(aluguel.getAutorLivro(), autor) &&
                    aluguel.isEmCurso())
                return aluguel;

        return null;
    }

    public Aluguel finalizaAluguel() {
        Cliente cliente = new Cliente();
        Livro livro = new Livro();
        String cpf, titulo, autor;
        Aluguel aluguel = new Aluguel();
        Date data;

        System.out.println("""
                 * * * * * * * * * * * * * * * * * * * *
                DEVOLUÇÃO DE LIVRO
                Preencha os dados abaixo
                """);

        cpf = cliente.leCpf();
        cliente = cliente.procuraCliente(cpf);

        if (cliente == null) {
            System.out.println("\nErro: Cliente não cadastrado\nA devolução de livro foi interrompida.");
            return null;
        }

        titulo = livro.leTitulo();
        autor = livro.leAutor();

        livro = livro.procuraLivro(titulo, autor);

        if (livro == null) {
            System.out.println("\nErro: Livro não cadastrado\nA devolução de livro foi interrompida.");
            return null;
        }

        aluguel = aluguel.procuraAluguel(cpf, titulo, autor);

        if (aluguel == null) {
            System.out.println("\nErro: Aluguel em andamento não existe\nA devolução de livro foi interrompida.");
            return null;
        }

        data = leData("término", aluguel.getInicioAluguel(), livro.getAnoPublicacao());
        aluguel.setTerminoAluguel(data);

        return aluguel;
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
