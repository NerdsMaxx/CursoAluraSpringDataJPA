package br.com.aulaalura.springdata.service;

import br.com.aulaalura.springdata.orm.Funcionario;
import br.com.aulaalura.springdata.orm.FuncionarioProjecao;
import br.com.aulaalura.springdata.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService {
    
    private static final PrintStream out = System.out;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private final FuncionarioRepository funcionarioRepository;
    
    public RelatorioService(FuncionarioRepository funcionarioRepository) {this.funcionarioRepository = funcionarioRepository;}
    
    public void inicial(Scanner scanner) {
        boolean rodando = true;
        
        while (rodando) {
            out.println("Quais opções você deseja executar para relatório ?");
            out.println("0 - Sair");
            out.println("1 - Buscar por nome");
            out.println("2 - Buscar por nome, data de contratação e salário maior");
            out.println("3 - Buscar por data de contratação maior");
            out.println("4 - Buscar funcionários com salários");
            
            int opcao = scanner.nextInt();
            
            try {
                switch (opcao) {
                    case 1:
                        buscaFuncionarioNome(scanner);
                        break;
                    case 2:
                        buscaFuncionarioNomeSalarioMaiorDataContratacao(scanner);
                        break;
                    case 3:
                        buscaFuncionarioDataContratacao(scanner);
                        break;
                    case 4:
                        buscaFuncionarioSalario();
                        break;
                    default:
                        rodando = false;
                }
            } catch (Exception erro) {
                out.println(erro.getMessage());
            }
        }
    }
    
    private void buscaFuncionarioNome(Scanner scanner) {
        out.println("Qual nome deseja pesquisar");
        String nome = scanner.next() + scanner.nextLine();
    
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
        funcionarios.forEach(out::println);
    }
    
    private void buscaFuncionarioNomeSalarioMaiorDataContratacao(Scanner scanner) {
        out.println("Qual nome deseja pesquisar ?");
        String nome = scanner.next() + scanner.nextLine();
        
        out.println("Qual data de contratação deseja pesquisar ?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);
        
        out.println("Qual salario deseja pesquisar ?");
        Double salario = scanner.nextDouble();
        
        List<Funcionario> funcionarios = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        
        funcionarios.forEach(out::println);
    }
    
    private void buscaFuncionarioDataContratacao(Scanner scanner) {
        out.println("Qual data de contratação deseja pesquisar ?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);
        
        List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoMaior(localDate);
        
        funcionarios.forEach(out::println);
    }
    
    private void buscaFuncionarioSalario() {
        List <FuncionarioProjecao> funcionarioProjecaos = funcionarioRepository.findFuncionarioSalario();
        funcionarioProjecaos.forEach(f -> out.println("Funcionário id: " + f.getId() + '\n' +
                                                      "Nome: " + f.getNome() + '\n' +
                                                      "Salário: " + f.getSalario() + '\n'));
    }
}