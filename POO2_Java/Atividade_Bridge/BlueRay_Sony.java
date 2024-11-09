package Atividade_Bridge;

public class BlueRay_Sony extends ControleRemoto {

    public BlueRay_Sony(Dispositivo novo_) {
        super(novo_);
    }

    @Override
    public void botaoNove() {
        System.out.println("Ejetando o disco");
    }

}