package E_Interfaces.E2_Interfaces;

public class Volei extends JogoDeBola {

    @Override
    public void setNomesEquipes(String equipe1, String equipe2) {
        
    }

    @Override
    public void iniciar() {
        System.out.println("Jogo pronto para mais uma partida de volei!");
    }

    @Override
    public void jogar() {
        System.out.println("Eeee comecou, bola voando!");
    }

    @Override
    public void finalizar() {
        System.out.println("Jogo finalizado!");
    }
      
}
