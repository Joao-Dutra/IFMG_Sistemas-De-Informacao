package poo;

public class Conta {

    // atributos
    private int numero;
    private Cliente titular;
    private double saldo;

    // metodos
    public void setSaldo(double s) {
        if (s >= 0) {
            this.saldo = s;
        } else {
            this.saldo = 0;
            System.out.println("Impossivel inserir um valor negativo");
        }
    }

    public double getSaldo(){
        return this.saldo;
    }
    
    public void setNumero(int n) {
        this.numero = n;
    }
    
    public int getNumero(){
        return this.numero;
    }
    
    public void setTitular(Cliente t) {
        this.titular = t;
    }
    
    public Cliente getTitular(){
        return this.titular;
    }
    
    public void consultarSaldo() {
        System.out.println("O saldo eh " + this.saldo);
    }

    public boolean sacarValor(double valorSaque) {
        if (valorSaque > this.saldo) {
            System.out.println("Nao tem como realizar essa operação com esse saldo.");
            return false;
        } else {
            this.saldo -= valorSaque;
            System.out.println("Saldo atual em sua conta : " + this.saldo);
            return true;
        }
    }

    public void depositarValor(double valorDepositar) {
        this.saldo += valorDepositar;
        System.out.println("Saldo atual em sua conta : " + this.saldo);
    }

    public void transferirValor(double valorTransferir, Conta destino) {
        
        boolean retirou = this.sacarValor(valorTransferir);
        
        if (retirou == false) {
            System.out.println("Saldo Insuficiente. Impossivel Transferir!");
        } else {
            destino.depositarValor(valorTransferir);
        }
    }
}
