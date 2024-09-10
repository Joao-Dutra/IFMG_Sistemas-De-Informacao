package Exercicios_Classes_Abstratas_Polimorfismo_Interfaces;

public abstract class Empregado {
    
    private String nome;
    private String sobrenome;
    private String cpf;
    
    public Empregado(String nome, String sobrenome, String cpf){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }
    
    public abstract double vencimento();
}
