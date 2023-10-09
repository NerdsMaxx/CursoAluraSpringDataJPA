package br.com.aulaalura.springdata.util;

import java.io.PrintStream;

public final class ServiceUtil {
    
    private static final PrintStream out = System.out;
    
    private ServiceUtil() {}
    
    public static void mostrarOpcoes(String entidade) {
        out.println("Quais opções você deseja executar para " + entidade + " ?");
        out.println("0 - Sair");
        out.println("1 - Inserir");
        out.println("2 - Atualizar");
        out.println("3 - Visualizar");
        out.println("4 - Deletar");
    }
}