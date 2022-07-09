package br.tp2.dojo2;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

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

        Utilitario utilitario = new Utilitario();
        this.inicioAluguel = utilitario.converteTextoEmData("01/01/1900");

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

    public Aluguel procuraAluguel(String cpf, String titulo, String autor) {
        for (Aluguel aluguel : Biblioteca.getAlugueis())
            if (Objects.equals(aluguel.getCpf(), cpf) &&
                    Objects.equals(aluguel.getTituloLivro(), titulo) &&
                    Objects.equals(aluguel.getAutorLivro(), autor) &&
                    aluguel.isEmCurso())
                return aluguel;

        return null;
    }

    private boolean livroAlugadoRecentemente(Livro livro, ArrayList<Aluguel> alugueis) {
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

    public boolean aluguelEmCurso(ArrayList<Aluguel> alugueis) {
        for (Aluguel aluguel : alugueis)
            if (aluguel.isEmCurso())
                return true;

        return false;
    }

    private boolean novoAluguelPermitido(ArrayList<Aluguel> alugueis) {
        int contador = 0;

        for (Aluguel aluguel : alugueis) {
            if (aluguel.isEmCurso())
                contador++;

            if (contador == 2)
                return false;
        }

        return true;
    }

    public ArrayList<Aluguel> refinaListaAlugueisCliente(String cpf) {
        ArrayList<Aluguel> resultados = new ArrayList<>();

        for (Aluguel aluguel : Biblioteca.getAlugueis())
            if (Objects.equals(aluguel.getCpf(), cpf))
                resultados.add(aluguel);

        return resultados;
    }

    public ArrayList<Aluguel> refinaListaAlugueisLivro(String tituloLivro, String autor) {
        ArrayList<Aluguel> resultados = new ArrayList<>();

        for (Aluguel aluguel : Biblioteca.getAlugueis())
            if (Objects.equals(aluguel.getTituloLivro(), tituloLivro) &&
                    Objects.equals(aluguel.getAutorLivro(), autor))
                resultados.add(aluguel);

        return resultados;
    }

    public Aluguel novoRegistro() {
        Cliente cliente = new Cliente();
        Livro livro = new Livro();
        String cpf, titulo, autor;
        ArrayList<Aluguel> alugueisCliente;
        Date data;
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        Utilitario utilitario = new Utilitario();

        interfaceUsuario.exibeCabecalhoFormulario("NOVO ALUGUEL");

        cpf = utilitario.leCpf();
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

        titulo = utilitario.leTexto("Título");
        autor = utilitario.leTexto("Autor");
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

        do {
            data = utilitario.leData("Data de início:");

            if ((data.getYear() + 1900) < livro.getAnoPublicacao()) {
                System.out.println("Data inválida\n");
                continue;
            }

            break;
        } while (true);

        return new Aluguel(cpf, titulo, autor, data);
    }

    public Aluguel finalizaRegistro() {
        Cliente cliente = new Cliente();
        Livro livro = new Livro();
        String cpf, titulo, autor;
        Aluguel aluguel = new Aluguel();
        Date data;
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        Utilitario utilitario = new Utilitario();

        interfaceUsuario.exibeCabecalhoFormulario("DEVOLUÇÃO DE LIVRO");

        cpf = utilitario.leCpf();
        cliente = cliente.procuraCliente(cpf);

        if (cliente == null) {
            System.out.println("\nErro: Cliente não cadastrado\nA devolução de livro foi interrompida.");
            return null;
        }

        titulo = utilitario.leTexto("Título");
        autor = utilitario.leTexto("Autor");

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

        do {
            data = utilitario.leData("Data de término:");

            if (data.before(aluguel.getInicioAluguel())) {
                System.out.println("Data inválida\n");
                continue;
            }

            break;
        } while (true);

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
