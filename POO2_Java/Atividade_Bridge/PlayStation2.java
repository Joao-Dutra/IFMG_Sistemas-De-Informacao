package Atividade_Bridge;

public class PlayStation2 extends ControleRemoto {

    public PlayStation2(Dispositivo novo_) {
        super(novo_);
    }

    @Override
    public void botaoNove() {
        System.out.println("Abrindo a tampa...");
    }

}