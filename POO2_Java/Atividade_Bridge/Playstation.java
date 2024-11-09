package Atividade_Bridge;

public class Playstation extends Dispositivo {

    public Playstation(int estado_, int maximo_) {
        estado = estado_;
        maximo = maximo_;
    }

    @Override
    public void botaoCinco() {
        System.out.println("Ligando o Play.");
    }

    @Override
    public void botaoSeis() {
        System.out.println("Iniciando o jogo");
    }

}