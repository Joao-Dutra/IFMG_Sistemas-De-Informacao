package Atividade_Bridge;

public abstract class Dispositivo {

    public int estado, volume, maximo;

    public abstract void botaoCinco();

    public abstract void botaoSeis();

    public void feedbackDispositivo() {
        if (estado > maximo || estado < 0)
            estado = 0;
        System.out.println("Atualmente em: " + estado);
    }

    public void botaoSete() {
        volume++;
        System.out.println("Volume em: " + volume);
    }

    public void botaoOito() {
        volume--;
        System.out.println("Volume em: " + volume);
    }

}