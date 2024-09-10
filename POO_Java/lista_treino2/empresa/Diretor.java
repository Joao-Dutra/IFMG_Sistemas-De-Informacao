package empresa;

public final class Diretor extends Gerente {
    
    private int numDepto;
    
    public Diretor(String nome, String cpf, int senha){
        super(senha, nome, cpf);
        this.numDepto = 4;
        
    }
    
    @Override
    public double calculaBonificacao(){
        
        return (super.calculaBonificacao() + 5000);
        
    }
    
}
