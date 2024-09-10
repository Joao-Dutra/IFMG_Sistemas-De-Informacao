package Exercicios_Classes_Abstratas_Polimorfismo_Interfaces;

public class Assalariado extends Empregado {

    private double salario;
    
    public Assalariado(String nome, String sobrenome, String cpf, double salario){
        super(nome,sobrenome,cpf);
        this.salario = salario;
    }
    
    public double vencimento() {
        return this.salario;
    }
    
}
