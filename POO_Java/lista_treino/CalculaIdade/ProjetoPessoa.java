package poo;

public class ProjetoPessoa {
    public static void main(String[] args) {
        
        Pessoa p1 = new Pessoa();
        Pessoa p2 = new Pessoa();
        
        p1.nome = "Albert Einstein";
        p1.dia = 14;
        p1.mes = 3;
        p1.ano = 1879;
        
        p2.nome = "Isaac Newton";
        p2.dia = 4;
        p2.mes = 1;
        p2.ano = 1643;
        
        
        int a = p1.ano;
        
        p1.informaNome();
        p1.ajustaDataDeNascimento();        
        p1.calculaIdade(a);
        p1.informaIdade();
        
        
        int a2 = p2.ano;

        
        p2.informaNome();
        p2.ajustaDataDeNascimento();
        p2.calculaIdade(a2);
        p2.informaIdade();
               
    }
}
