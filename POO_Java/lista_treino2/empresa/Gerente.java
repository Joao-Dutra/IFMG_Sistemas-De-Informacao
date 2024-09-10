package empresa;


public class Gerente extends Funcionario {

    private int senha;
    private int numFuncGerenciados;

    public Gerente(int senha, String nome, String cpf) {

        super(nome, cpf);
        
        this.senha = senha;

    }

    public boolean validaSenha(int senha) {

        System.out.println("Sou o gerente " + this.nome
                + " e estou validando minha senha!");

        if (senha == this.senha) {

            return true;
        } else {

            return false;
        }

    }
    
    @Override
     public double calculaBonificacao(){
        
        return ((this.salario * 1.2) + 1000 );
        
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public int getNumFuncGerenciados() {
        return numFuncGerenciados;
    }

    public void setNumFuncGerenciados(int numFuncGerenciados) {
        this.numFuncGerenciados = numFuncGerenciados;
    }

}
