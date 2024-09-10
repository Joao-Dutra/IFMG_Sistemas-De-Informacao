package poo;

public class Conta {

    private int numero;
    private String titular;
    private double saldo;

    public Conta(int numero, String titular, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        try {
            if (valor > 0) {
                this.saldo += valor;
            } else {
//              throw new IllegalArgumentException("Impossivel depositar!");
                throw new MyExcecao(valor,"Valor negativo!");
            }
        } catch (MyExcecao e) {
            System.out.println("Ocorreu um erro!");
            e.getMessage();
            e.printStackTrace();
        }
    }

}
