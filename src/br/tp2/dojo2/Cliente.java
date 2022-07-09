package br.tp2.dojo2;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente {
    private final String cpf;
    private String nome;

    public Cliente() {
        this.cpf = null;
        this.nome = null;
    }

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public Cliente procuraCliente(String cpf) {
        for (Cliente cliente : Biblioteca.getClientes())
            if (Objects.equals(cliente.getCpf(), cpf))
                return cliente;

        return null;
    }

    public Cliente novoRegistro() {
        String cpf;
        Utilitario utilitario = new Utilitario();
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        interfaceUsuario.exibeCabecalhoFormulario("NOVO CLIENTE");
        cpf = utilitario.leCpf();

        if (procuraCliente(cpf) != null) {
            System.out.println("\nErro: CPF já cadastrado\nO cadastro de novo cliente foi interrompido.");
            return null;
        }

        return new Cliente(cpf, utilitario.leTexto("Nome"));
    }

    public Cliente exclusaoRegistro() {
        String cpf;
        Cliente cliente;
        Utilitario utilitario = new Utilitario();
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        Aluguel aluguel = new Aluguel();
        ArrayList<Aluguel> alugueis;

        interfaceUsuario.exibeCabecalhoFormulario("EXCLUSÃO DE CLIENTE");

        cpf = utilitario.leCpf();
        cliente = procuraCliente(cpf);

        if (cliente == null)
            System.out.println("\nErro: CPF não cadastrado\nA exclusão de cliente foi interrompida.");

        alugueis = aluguel.refinaListaAlugueisCliente(cpf);

        if (aluguel.aluguelEmCurso(alugueis)) {
            System.out.println("\nErro: Cliente possui aluguel em andamento\nA exclusão de cliente foi interrompida.");
            return null;
        }

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
}
