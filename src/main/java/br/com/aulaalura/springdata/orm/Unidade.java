package br.com.aulaalura.springdata.orm;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "unidades")
public class Unidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String descricao;
    
    private String enderenco;
    
    @ManyToMany(mappedBy = "unidades")
    private Set<Funcionario> funcionarios = new HashSet<>();
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getEnderenco() {
        return enderenco;
    }
    
    public void setEnderenco(String enderenco) {
        this.enderenco = enderenco;
    }
    
    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    
    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    @Override
    public String toString() {
        return "Unidade{" +
               "id=" + id +
               ", descricao='" + descricao + '\'' +
               ", enderenco='" + enderenco + '\'' +
               '}';
    }
}