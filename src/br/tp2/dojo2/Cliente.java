package br.tp2.dojo2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Cliente {
    private final String cpf;
    private String nome;
    private final ArrayList<Aluguel> alugueis = new ArrayList<>();

    public Cliente() {
        this.cpf = null;
        this.nome = null;
    }

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public Cliente procuraCliente(String cpf) {
        Biblioteca biblioteca = new Biblioteca();

        for (Cliente cliente : biblioteca.getClientes())
            if (Objects.equals(cliente.getCpf(), cpf))
                return cliente;

        return null;
    }

    public String leCpf() {
        String cpf;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("CPF:");
            cpf = scanner.next();

            if (cpf.length() != 11)
                System.out.println("CPF inválido");
            else
                break;
        } while (true);

        return cpf;
    }

    private String leNome() {
        String nome;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Nome:");
            nome = scanner.nextLine();

            if (nome.length() == 0)
                System.out.println("Nome inválido");
            else
                break;
        } while (true);

        return nome;
    }

    public Cliente novoRegistro() {
        String cpf;

        System.out.println("""
                 * * * * * * * * * * * * * * * * * * * *
                NOVO CLIENTE
                Preencha os dados abaixo
                """);

        cpf = leCpf();

        if (procuraCliente(cpf) != null) {
            System.out.println("\nErro: CPF já cadastrado\nO cadastro de novo cliente foi interrompido.");
            return null;
        }

        return new Cliente(cpf, leNome());
    }

    public Cliente removeRegistro() {
        Cliente cliente;

        System.out.println("""
                 * * * * * * * * * * * * * * * * * * * *
                REMOVER CLIENTE
                Preencha os dados abaixo
                """);

        cliente = procuraCliente(leCpf());

        if (cliente == null)
            System.out.println("\nErro: CPF não cadastrado\nA exclusão de cliente foi interrompida.");

        return cliente;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
