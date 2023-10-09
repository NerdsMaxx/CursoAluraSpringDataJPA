package br.com.aulaalura.springdata;

import br.com.aulaalura.springdata.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
    
    private static final PrintStream out = System.out;
    
    private final CrudCargoService cargoService;
    
    private final CrudUnidadeService unidadeService;
    
    private final CrudFuncionarioService funcionarioService;
    
    private final RelatorioService relatorioService;
    
    private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;
    
    public SpringDataApplication(CrudCargoService cargoService, CrudUnidadeService unidadeService, CrudFuncionarioService funcionarioService, RelatorioService relatorioService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
        this.cargoService = cargoService;
        this.unidadeService = unidadeService;
        this.funcionarioService = funcionarioService;
        this.relatorioService = relatorioService;
        this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }
    
    
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ENGLISH);
        
        boolean rodando = true;
        
        while (rodando) {
            out.println("Qual entidade você quer executar o CRUD ?");
            out.println("0 - Sair");
            out.println("1 - Cargo");
            out.println("2 - Unidade");
            out.println("3 - Funcionário");
            out.println("4 - Relatórios");
            out.println("5 - Relatórios dinâmicos");
            
            int opcao = scanner.nextInt();
            switch (opcao){
                case 1:
                    cargoService.inicial(scanner);
                    break;
                case 2:
                    unidadeService.inicial(scanner);
                    break;
                case 3:
                    funcionarioService.inicial(scanner);
                    break;
                case 4:
                    relatorioService.inicial(scanner);
                    break;
                case 5:
                    relatorioFuncionarioDinamico.inicial(scanner);
                    break;
                default:
                    rodando = false;
            }
            
        }
        
    }
}