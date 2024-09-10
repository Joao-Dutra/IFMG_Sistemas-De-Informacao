package Exercicios_Classes_Abstratas_Polimorfismo_Interfaces;

public class Comissionado extends Empregado {
    
    private double totalVenda;
    private double taxaComissao;
    
    public Comissionado(String nome, String sobrenome, String cpf, double totalVenda, double taxaComissao){
        super(nome,sobrenome,cpf);
        
        this.totalVenda = totalVenda;
        this.taxaComissao = taxaComissao;
    }
    
    public double vencimento(){
        double v = this.totalVenda * this.taxaComissao;
        return v;
    }
}
