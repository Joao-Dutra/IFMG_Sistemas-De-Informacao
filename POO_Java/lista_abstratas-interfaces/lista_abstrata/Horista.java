package Exercicios_Classes_Abstratas_Polimorfismo_Interfaces;

public class Horista extends Empregado {
    
    private double precoHora;
    private double horasTrabalhadas;
    
    public Horista(String nome, String sobrenome, String cpf, double precoHora, double horasTrabalhadas){
        super(nome,sobrenome,cpf);
        this.precoHora = precoHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }
    
    public double vencimento(){
        double v = this.precoHora * this.horasTrabalhadas;
        return v;
    }
}
