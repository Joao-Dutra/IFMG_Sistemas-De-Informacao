package E_Interfaces.E2_Interfaces;

import java.util.ArrayList;
import java.util.Iterator;

public class E2_interfaces_main {
    public static void main(String[] args) {
        ArrayList ListaDeJogos = new ArrayList();
        
        Jogo f1 = new Futebol();
        Jogo v1 = new Volei();
        Jogo t1 = new Truco();
        Jogo b1 = new Buraco();
        Jogo b2 = new Buraco();
        
        ListaDeJogos.add(f1);
        ListaDeJogos.add(v1);
        ListaDeJogos.add(t1);
        ListaDeJogos.add(b1);
        ListaDeJogos.add(b2);
        
        Iterator<Jogo> it = ListaDeJogos.iterator();
        int nJB = 0, nJC = 0;
        while(it.hasNext()){
            Jogo e = it.next();
            if (e instanceof JogoDeBola) {
                nJB += 1;
            }
            if(e instanceof JogoDeCartas) {
                nJC += 1;
            }          
        }
        System.out.println("A lista possui " + nJB + " jogos de bola e " + nJC + " jogos de cartas!");
    }
}
