package E_Interfaces.E2_Interfaces;

public class Truco extends JogoDeCartas {

    @Override
    public int qtdCartasDistruibuidas() {
      return this.numeroParticipantes() * 4;
    }

    @Override
    public int numeroParticipantes() {
        return 4;
    }

    @Override
    public void iniciar() {
        System.out.println("Pronto para mais uma partida de truco ladrao?");
    }

    @Override
    public void jogar() {
        System.out.println("Vem 6 entao");
    }

    @Override
    public void finalizar() {
        System.out.println("Jogo finalizado, pediram truco na mao de 10");
    }
    
}
