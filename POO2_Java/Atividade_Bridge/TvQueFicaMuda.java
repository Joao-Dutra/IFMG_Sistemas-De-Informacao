package Atividade_Bridge;

public class TvQueFicaMuda extends ControleRemoto {
    private boolean mute;

    private void unMute() {
        mute = false;
    }

    public TvQueFicaMuda(Dispositivo _disp) {
        super(_disp);
    }

    public void botaoNove() {
        System.out.println("TV Ficou muda!");
        mute = true;
    }

}