package br.tp2.dojo2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Utilitario {
    public String leTexto(String tipo) {
        String texto;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(tipo + ":");
            texto = scanner.nextLine();

            if (texto.isBlank())
                System.out.println(tipo + " inválido\n");
            else
                break;
        } while (true);

        return texto;
    }

    public Date converteTextoEmData(String data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return formato.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date leData(String mensagem) {
        String texto;
        Scanner scanner = new Scanner(System.in);
        Date data;

        do {
            System.out.println(mensagem);
            texto = scanner.next();

            data = converteTextoEmData(texto);

            if (data == null)
                System.out.println("Data inválida\n");
            else
                break;
        } while (true);

        return data;
    }

    public String leCpf() {
        String cpf;
        boolean valido = true;

        do {
            cpf = leTexto("CPF");

            try {
                Long.parseLong(cpf);
            } catch (NumberFormatException e) {
                valido = false;
            }

            if (cpf.length() == 11 && valido)
                break;

            System.out.println("CPF inválido\n");
            valido = true;
        } while (true);

        return cpf;
    }

    public int leAnoPublicacao() {
        int anoPublicacao = 0;
        boolean valido;

        do {
            valido = true;

            try {
                anoPublicacao = Integer.parseInt(leTexto("Ano de publicação"));
            } catch (NumberFormatException e) {
                valido = false;
            }

            if (!valido) {
                System.out.println("Ano de publicação inválido\n");
                continue;
            }

            if (anoPublicacao < 0) {
                System.out.println("Ano de publicação inválido\n");
                valido = false;
            }
        } while (!valido);

        return anoPublicacao;
    }
}
