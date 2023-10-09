package br.com.aulaalura.springdata.service;

import br.com.aulaalura.springdata.orm.Cargo;
import br.com.aulaalura.springdata.repository.CargoRepository;
import br.com.aulaalura.springdata.util.ServiceUtil;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class CrudCargoService {
    
    private static final PrintStream out = System.out;
    
    private final CargoRepository cargoRepository;
    
    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }
    
    public void inicial(Scanner scanner) throws Exception {
        boolean rodando = true;
        
        while (rodando) {
            ServiceUtil.mostrarOpcoes("cargo");
            
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
                        visualizar();
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
    
    private void salvar(Scanner scanner) {
        Cargo cargo = new Cargo();
        
        out.println("Descrição do cargo:");
        cargo.setDescricao(scanner.next() + scanner.nextLine());
        
        cargoRepository.save(cargo);
        
        out.println("Salvo: " + cargo.toString());
    }
    
    private void atualizar(Scanner scanner) throws Exception {
        visualizar();
        
        out.println("Coloque o ID que deseja atualizar:");
        int id = scanner.nextInt();
        
        Cargo cargo = cargoRepository.findById(id)
                                     .orElseThrow(() -> new Exception("Cargo não encontrado!"));
        
        out.println("Atualizar a descrição do cargo:");
        cargo.setDescricao(scanner.next() + scanner.nextLine());
        
        cargoRepository.save(cargo);
        
        out.println("Atualizado: " + cargo.toString());
    }
    
    private void visualizar() {
        Iterable<Cargo> cargos = cargoRepository.findAll();
        
        cargos.forEach(out::println);
    }
    
    private void deletar(Scanner scanner) throws Exception {
        visualizar();
        
        out.println("Coloque o ID que deseja deletar:");
        int id = scanner.nextInt();
        
        Cargo cargo = cargoRepository.findById(id)
                                     .orElseThrow(() -> new Exception("Cargo não encontrado!"));
        
        cargoRepository.delete(cargo);
        
        out.println("Deletado: " + cargo.toString());
    }
}