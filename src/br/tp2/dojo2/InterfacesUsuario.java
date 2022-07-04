package br.tp2.dojo2;

import java.lang.runtime.ObjectMethods;

public class InterfacesUsuario {
    private void cabecalhoMenuPrincipal() {
        System.out.println("""
                 * * * * * * * * * * * * * * * * * * * *
                MENU INICIAL
                Escolha uma das opções abaixo

                1. Administrar aluguéis
                2. Administrar clientes
                3. Administrar livros
                4. Emitir relatório
                5. Finalizar programa
                """);
    }

    public void cabecalhoMenusClienteLivro() {
        System.out.printf("""
             * * * * * * * * * * * * * * * * * * * *
            %s
            Escolha uma das opções abaixo

            1. Novo livro
            2. Remover livro
            3. Voltar ao menu inicial
            """, ObjectMethods.class.getName());
    }
}
