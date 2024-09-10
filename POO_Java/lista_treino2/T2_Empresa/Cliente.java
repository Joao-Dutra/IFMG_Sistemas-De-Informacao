package funcionario;

public class Cliente implements Autenticavel {

    private String nome;
    private int senha;
    private String depto;

    public Cliente(String nome, int senha) {
        nome = this.nome;
        senha = this.senha;
        this.depto = "Cliente";
    }

    @Override
    public boolean autentica(int senha) {
        System.out.println("Sou o cliente " + this.nome
                + " e estou validando minha senha!");

        if (senha == this.senha && DeptosAutorizados.validaDepto(this.depto)) {

            return true;
        } else {

            return false;
        }

    }
}

