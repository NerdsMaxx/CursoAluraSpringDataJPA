package br.com.aulaalura.springdata.service;

import br.com.aulaalura.springdata.orm.Cargo;
import br.com.aulaalura.springdata.orm.Funcionario;
import br.com.aulaalura.springdata.orm.Unidade;
import br.com.aulaalura.springdata.repository.CargoRepository;
import br.com.aulaalura.springdata.repository.FuncionarioRepository;
import br.com.aulaalura.springdata.repository.UnidadeRepository;
import br.com.aulaalura.springdata.util.ServiceUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Service
public class CrudFuncionarioService {
    
    private static final PrintStream out = System.out;
    private final FuncionarioRepository funcionarioRepository;
    
    private final CargoRepository cargoRepository;
    
    private final UnidadeRepository unidadeRepository;
    
    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, UnidadeRepository unidadeRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeRepository = unidadeRepository;
    }
    
    public void inicial(Scanner scanner) {
        boolean rodando = true;
        
        while (rodando) {
            ServiceUtil.mostrarOpcoes("funcionario");
            int opcao = scanner.nextInt();
            
            try {
                switch (opcao) {
                    case 1:
                        salvar(scanner);
                        break;
                    case 2:
                        atualizar(scanner);
                        break;
                    case 3:
                        visualizar(scanner);
                        break;
                    case 4:
                        deletar(scanner);
                        break;
                    default:
                        rodando = false;
                }
            } catch (Exception erro) {
                out.println(erro.getMessage());
            }
            
        }
    }
    
    private void salvar(Scanner scanner) throws Exception {
        Funcionario funcionario = new Funcionario();
        out.println("Nome do funcionário:");
        funcionario.setNome(scanner.next() + scanner.nextLine());
        
        out.println("CPF do funcionário:");
        funcionario.setCpf(scanner.next());
        
        out.println("Salário do funcionário:");
        funcionario.setSalario(scanner.nextDouble());
        
        funcionario.setDataContratacao(LocalDate.now());
        
        out.println("ID de cargo para funcionário:");
        int cargoId = scanner.nextInt();
        
        Cargo cargo = cargoRepository.findById(cargoId)
                                     .orElseThrow(() -> new Exception("Cargo não encontrado!"));
        
        funcionario.setCargo(cargo);
        
        out.println(
                "ID das unidades onde funcionário trabalha: (Digite -1 para terminar as unidades)");
        funcionario.setUnidades(lendoUnidades(scanner));
        
        funcionarioRepository.save(funcionario);
        
        out.println("Salvo: " + funcionario);
    }
    
    private void atualizar(Scanner scanner) throws Exception {
        out.println("Coloque o ID que deseja atualizar:");
        int id = scanner.nextInt();
        
        Funcionario funcionario = funcionarioRepository.findById(id)
                                                       .orElseThrow(() -> new Exception("Funcionário não encontrado."));
        
        out.print("Deseja atualizar o nome ? (s/n): ");
        if (scanner.next().equalsIgnoreCase("s")) {
            out.println("Nome do funcionário:");
            funcionario.setNome(scanner.next() + scanner.nextLine());
        }
        
        out.print("Deseja atualizar o salário ? (s/n): ");
        if (scanner.next().equalsIgnoreCase("s")) {
            out.println("Salário do funcionário:");
            funcionario.setSalario(scanner.nextDouble());
        }
        
        out.print("Deseja atualizar o cargo ? (s/n): ");
        if (scanner.next().equalsIgnoreCase("s")) {
            out.println("ID do cargo do funcionário:");
            int cargoId = scanner.nextInt();
            
            Cargo cargo = cargoRepository.findById(cargoId)
                                         .orElseThrow(() -> new Exception("Cargo não encontrado!"));
            
            funcionario.setCargo(cargo);
        }
        
        out.print("Deseja atualizar as unidades ? (s/n): ");
        if (scanner.next().equalsIgnoreCase("s")) {
            out.println("ID das unidades onde funcionário trabalha: (Digite -1 para terminar as unidades)");
            funcionario.setUnidades(lendoUnidades(scanner));
        }
        
        funcionarioRepository.save(funcionario);
        
        out.println("Atualizado: " + funcionario);
    }
    
    private Set<Unidade> lendoUnidades(Scanner scanner) throws Exception {
        Set<Integer> unidadesId = new HashSet<>();
        boolean escrevendo = true;
        
        while (escrevendo) {
            int unidadeId = scanner.nextInt();
            
            if (unidadeId == - 1) {
                escrevendo = false;
                continue;
            }
            
            unidadesId.add(unidadeId);
        }
        
        Set<Unidade> unidades = new HashSet<>();
        for (Integer unidadeId : unidadesId) {
            Unidade unidade = unidadeRepository.findById(unidadeId)
                                               .orElseThrow(() -> new Exception("Unidade não encontrado!"));
            
            unidades.add(unidade);
        }
        
        return unidades;
    }
    
    private void visualizar(Scanner scanner) {
        out.println("Qual página você deseja visualizar ?");
        Integer page = scanner.nextInt();
        Pageable pageable = PageRequest.of(page, 2, Sort.by(Sort.Direction.ASC, "nome"));
        
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
        
        out.println(funcionarios);
        out.println("Página atual = " + funcionarios.getNumber());
        out.println("Total elemento = " + funcionarios.getTotalElements());
        funcionarios.forEach(out::println);
    }
    
    private void deletar(Scanner scanner) throws Exception {
        out.println("Coloque o ID que deseja deletar:");
        int id = scanner.nextInt();
        
        Funcionario funcionario = funcionarioRepository.findById(id)
                                                       .orElseThrow(() -> new Exception("Funcionário não encontrado!"));
        
        funcionarioRepository.delete(funcionario);
        
        out.println("Deletado: " + funcionario);
    }
}