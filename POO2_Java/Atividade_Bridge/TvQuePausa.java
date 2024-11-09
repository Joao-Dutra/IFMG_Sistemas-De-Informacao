package Atividade_Bridge;

public class TvQuePausa extends ControleRemoto {
    private boolean pausada = false;

    public void unPause() {
        pausada = false;
    }

    public TvQuePausa(Dispositivo _disp) {
        super(_disp);
    }

    public void botaoNove() {
        System.out.println("TV Pausada!");
        pausada = true;
    }

}