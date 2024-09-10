package empresa;

public class Secretaria extends Funcionario {

    private int telefone;

    public Secretaria(String nome, String cpf, int telefone) {
        super(nome, cpf);
        this.telefone = telefone;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    @Override
    public double calculaBonificacao() {
        
        return(this.salario * 1.25);
    }


}
