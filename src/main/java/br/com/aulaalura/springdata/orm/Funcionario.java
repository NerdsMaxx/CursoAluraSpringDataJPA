package br.com.aulaalura.springdata.orm;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nome;
    
    private String cpf;
    
    private double salario;
    
    @Column(name = "data_contratacao")
    private LocalDate dataContratacao;
    
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
    
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "funcionario_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "unidade_id", referencedColumnName = "id"))
    private Set<Unidade> unidades = new HashSet<>();
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public double getSalario() {
        return salario;
    }
    
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public LocalDate getDataContratacao() {
        return dataContratacao;
    }
    
    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }
    
    public Cargo getCargo() {
        return cargo;
    }
    
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    public Set<Unidade> getUnidades() {
        return unidades;
    }
    
    public void setUnidades(Set<Unidade> unidades) {
        this.unidades = unidades;
    }
    
    @Override
    public String toString() {
        return "Funcionario{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", cpf='" + cpf + '\'' +
               ", salario=" + salario +
               ", dataContratacao=" + dataContratacao +
               ", cargo=" + cargo +
               ", unidades=" + unidades +
               '}';
    }
}