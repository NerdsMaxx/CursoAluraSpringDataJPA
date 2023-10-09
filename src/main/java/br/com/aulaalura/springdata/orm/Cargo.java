package br.com.aulaalura.springdata.orm;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cargos")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String descricao;
    
    @OneToMany(mappedBy = "cargo")
    private Set<Funcionario> funcionarios = new HashSet<>();
    
    public Integer getId() {
        return id;
    }
    
    public void setId( Integer id ) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao( String descricao ) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return "Cargo{" +
               "id=" + id +
               ", descricao='" + descricao + '\'' +
               '}';
    }
}