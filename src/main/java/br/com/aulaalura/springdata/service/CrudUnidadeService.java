package br.com.aulaalura.springdata.service;

import br.com.aulaalura.springdata.orm.Unidade;
import br.com.aulaalura.springdata.repository.UnidadeRepository;
import br.com.aulaalura.springdata.util.ServiceUtil;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class CrudUnidadeService {
    private static final PrintStream out = System.out;
    
    private final UnidadeRepository unidadeRepository;
    
    public CrudUnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }
    
    public void inicial(Scanner scanner) {
        boolean rodando = true;
        
        while (rodando) {
            ServiceUtil.mostrarOpcoes("unidade");
            
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
        Unidade unidade = new Unidade();
        
        out.println("Descrição da unidade:");
        unidade.setDescricao(scanner.next() + scanner.nextLine());
        
        out.println("Endereço da unidade");
        unidade.setEnderenco(scanner.next() + scanner.nextLine());
        
        unidadeRepository.save(unidade);
        
        out.println("Salvo: " + unidade.toString());
    }
    
    private void atualizar(Scanner scanner) throws Exception {
        visualizar();
        
        out.println("Coloque o ID que deseja atualizar:");
        int id = scanner.nextInt();
        
        Unidade unidade = unidadeRepository.findById(id)
                                           .orElseThrow(() -> new Exception("Unidade não encontrada!"));
        
        out.print("Deseja atualizar a descrição ? (s/n): ");
        if (scanner.next().equalsIgnoreCase("s")) {
            out.println("Descrição da unidade:");
            unidade.setDescricao(scanner.next() + scanner.nextLine());
        }
        
        out.print("Deseja atualizar o endereço ? (s/n): ");
        if(scanner.next().equalsIgnoreCase("s")) {
            out.println("Endereço da unidade:");
            unidade.setEnderenco(scanner.next() + scanner.nextLine());
        }
        
        
        unidadeRepository.save(unidade);
        
        out.println("Atualizado: " + unidade.toString());
    }
    
    private void visualizar() {
        Iterable<Unidade> unidades = unidadeRepository.findAll();
        
        unidades.forEach(out::println);
    }
    
    private void deletar(Scanner scanner) throws Exception {
        visualizar();
        
        out.println("Coloque o ID que deseja deletar:");
        int id = scanner.nextInt();
        
        Unidade unidade = unidadeRepository.findById(id)
                                           .orElseThrow(() -> new Exception("Unidade não encontrada!"));
        
        if(!unidade.getFuncionarios().isEmpty()){
            throw new Exception("Têm funcionários nesta unidade. Remova estes funcionários primeiro!");
        }
        
        unidadeRepository.delete(unidade);
        
        out.println("Deletado: " + unidade.toString());
    }
}