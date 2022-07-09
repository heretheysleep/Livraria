package br.tp2.dojo2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Relatorio {
    private final Date inicioPeriodo;
    private final Date terminoPeriodo;
    private final ArrayList<Aluguel> alugueis = new ArrayList<>();

    public Relatorio() {
        this.inicioPeriodo = null;
        this.terminoPeriodo = null;
    }

    public Relatorio(Date inicioPeriodo, Date terminoPeriodo, ArrayList<Aluguel> alugueis) {
        this.inicioPeriodo = inicioPeriodo;
        this.terminoPeriodo = terminoPeriodo;
        this.alugueis.addAll(alugueis);
    }

    private Relatorio filtraAlugueisPorCliente() {
        Utilitario utilitario = new Utilitario();
        String cpf;
        Cliente cliente = new Cliente();
        ArrayList<Aluguel> alugueis;
        Aluguel aluguel = new Aluguel();

        cpf = utilitario.leCpf();
        cliente = cliente.procuraCliente(cpf);

        if (cliente == null) {
            System.out.println("\nErro: CPF não cadastrado\nA emissão de relatório foi interrompida.");
            return null;
        }

        alugueis = aluguel.refinaListaAlugueisCliente(cpf);

        return new Relatorio(inicioPeriodo, terminoPeriodo, alugueis);
    }

    private Relatorio filtraAlugueisPorLivro() {
        Utilitario utilitario = new Utilitario();
        String titulo, autor;
        Livro livro = new Livro();
        ArrayList<Aluguel> alugueis;
        Aluguel aluguel = new Aluguel();

        titulo = utilitario.leTexto("Título");
        autor = utilitario.leTexto("Autor");
        livro = livro.procuraLivro(titulo, autor);

        if (livro == null) {
            System.out.println("\nErro: Livro não cadastrado\nA emissão de relatório foi interrompida.");
            return null;
        }

        alugueis = aluguel.refinaListaAlugueisLivro(titulo, autor);

        return new Relatorio(inicioPeriodo, terminoPeriodo, alugueis);
    }

    private ArrayList<Aluguel> filtraAlugueisPorPeriodo(ArrayList<Aluguel> alugueis,
                                                        Date inicioPeriodo,
                                                        Date terminoPeriodo) {
        ArrayList<Aluguel> resultados = new ArrayList<>();

        for (Aluguel aluguel : alugueis)
            if ((aluguel.getInicioAluguel().after(inicioPeriodo) || aluguel.getInicioAluguel().equals(inicioPeriodo)) &&
                    (aluguel.getInicioAluguel().before(terminoPeriodo) || aluguel.getTerminoAluguel().equals(terminoPeriodo)))
                resultados.add(aluguel);

        return resultados;
    }

    public void emiteRelatorio(int opcao) {
        Date inicioPeriodo, terminoPeriodo;
        Utilitario utilitario = new Utilitario();
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        Relatorio relatorio;
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String inicioAluguel, terminoAluguel;
        ArrayList<Aluguel> alugueis;

        interfaceUsuario.exibeCabecalhoFormulario("EMISSÃO DE RELATÓRIO");

        inicioPeriodo = utilitario.leData("Data inicial de análise:");
        terminoPeriodo = utilitario.leData("Data final de análise:");

        if (opcao == 1)
            relatorio = filtraAlugueisPorCliente();
        else
            relatorio = filtraAlugueisPorLivro();

        if (relatorio != null) {
            alugueis = filtraAlugueisPorPeriodo(relatorio.getAlugueis(), inicioPeriodo, terminoPeriodo);

            if (alugueis.size() == 0)
                System.out.println("\nNenhum resultado encontrado.");
            else {
                System.out.println("\n\nRESULTADOS:\n");

                for (int index = 0; index < relatorio.getAlugueis().size(); index++) {
                    System.out.println(index + 1 + "º REGISTRO");

                    System.out.println("   CPF do cliente: " + relatorio.getAlugueis().get(index).getCpf());
                    System.out.println("   Título do livro: " + relatorio.getAlugueis().get(index).getTituloLivro());
                    System.out.println("   Autor do livro: " + relatorio.getAlugueis().get(index).getAutorLivro());

                    inicioAluguel = formatoData.format(relatorio.getAlugueis().get(index).getInicioAluguel());

                    if (relatorio.getAlugueis().get(index).getTerminoAluguel() != null)
                        terminoAluguel = formatoData.format(relatorio.getAlugueis().get(index).getTerminoAluguel());
                    else
                        terminoAluguel = "em andamento";

                    System.out.println("   Data de início do aluguel: " + inicioAluguel);
                    System.out.println("   Data de início do aluguel: " + terminoAluguel);
                }
            }
        }
    }

    public ArrayList<Aluguel> getAlugueis() {
        return alugueis;
    }
}
