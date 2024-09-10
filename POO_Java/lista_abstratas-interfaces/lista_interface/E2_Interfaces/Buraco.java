package E_Interfaces.E2_Interfaces;

public class Buraco extends JogoDeCartas {

    @Override
    public int qtdCartasDistruibuidas() {
       return 102;
    }

    @Override
    public int numeroParticipantes() {
        return 2;
    }

    @Override
    public void iniciar() {
        System.out.println("Baralho embaralhado!");
    }

    @Override
    public void jogar() {
        System.out.println("Partida de buraco iniciada!");
    }

    @Override
    public void finalizar() {
        System.out.println("Jogo finalizado!");
    }
    
}
