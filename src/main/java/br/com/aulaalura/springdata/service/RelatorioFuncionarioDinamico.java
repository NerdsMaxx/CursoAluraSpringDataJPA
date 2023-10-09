package br.com.aulaalura.springdata.service;

import br.com.aulaalura.springdata.orm.Funcionario;
import br.com.aulaalura.springdata.repository.FuncionarioRepository;
import br.com.aulaalura.springdata.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {
    
    private static final PrintStream out = System.out;
    private final FuncionarioRepository funcionarioRepository;
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }
    
    public void inicial(Scanner scanner) {
        out.println("Digite o nome");
        String nome = scanner.next() + scanner.nextLine();
        nome = (isNullStr(nome)) ? null : nome;
        
        out.println("Digite o CPF");
        String cpf = scanner.next();
        cpf = (isNullStr(cpf)) ? null : cpf;
        
        out.println("Digite o salário");
        Double salario = scanner.nextDouble();
        salario = (salario == 0) ? null : salario;
        
        out.println("Digite a data de contratação");
        String data = scanner.next();
        LocalDate dataContratacao;
        dataContratacao = (isNullStr(data)) ? null : LocalDate.parse(data, formatter);
        
        List<Funcionario> funcionarios = funcionarioRepository.findAll(
                Specification.where(SpecificationFuncionario.nome(nome))
                                    .or(SpecificationFuncionario.cpf(cpf)
                                    .or(SpecificationFuncionario.salario(salario)
                                    .or(SpecificationFuncionario.dataContratacao(dataContratacao)))));
        
        funcionarios.forEach(out::println);
    }
    
    private boolean isNullStr(String str) {return "null".equalsIgnoreCase(str);}
    
}