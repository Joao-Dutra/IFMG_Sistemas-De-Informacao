package Atividade_Bridge;

public class BlueRay extends Dispositivo {

    public BlueRay(int estado_, int maximo_) {
        estado = estado_;
        maximo = maximo_;
    }

    @Override
    public void botaoCinco() {
        System.out.println("Ligando o BlueRay.");
    }

    @Override
    public void botaoSeis() {
        System.out.println("Pausando o BlueRay");
    }

}