/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package funcionario;

/**
 *
 * @author suelen
 */
public abstract class Funcionario {

     protected String nome;
    private String cpf;
    protected double salario;
    
    
    public Funcionario(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.salario = 0.0;
        
    }
    
    public abstract double calculaBonificacao();

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
    
    
    
}
