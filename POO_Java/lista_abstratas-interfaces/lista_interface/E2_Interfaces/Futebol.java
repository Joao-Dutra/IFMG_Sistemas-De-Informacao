package E_Interfaces.E2_Interfaces;

public class Futebol extends JogoDeBola {

    @Override
    public void setNomesEquipes(String equipe1, String equipe2) {
        
    }

    @Override
    public void iniciar() {
        System.out.println("Jogo pronto para mais uma partida de futebol!");
    }

    @Override
    public void jogar() {
        System.out.println("Eeee comecou, bola rolando!");
    }

    @Override
    public void finalizar() {
        System.out.println("Jogo finalizado!");
    }
    
}
