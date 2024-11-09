package Atividade_Bridge;

public class TV extends Dispositivo {
    public TV(int estado_, int maximo_) {
        estado = estado_;
        maximo = maximo_;
    }

    public void botaoCinco() {
        System.out.println("Canal Down");
        estado--;
    }

    public void botaoSeis() {
        System.out.println("Canal UP");
        estado++;
    }

}