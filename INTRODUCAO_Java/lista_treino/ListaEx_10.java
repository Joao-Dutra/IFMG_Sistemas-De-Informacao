package listaex1;

import java.util.Scanner;
import java.io.BufferedInputStream;

public class ListaEx_10 {
    
    public static void main(String[] args) {
        System.out.println("Digite a seguir as alturas dos 4 atletas :");
        Scanner teclado = new Scanner(new BufferedInputStream(System.in));
        int a1 = teclado.nextInt();
        int a2 = teclado.nextInt();
        int a3 = teclado.nextInt();
        int a4 = teclado.nextInt();
        
        int maioraltura = 0;
        int menoraltura = 500;
        float media;
        
        media = (a1 + a2 + a3 + a4) / 4;
    
        if (a1 >= maioraltura) {
            maioraltura = a1;
        } else if (a1 <= menoraltura) {
            menoraltura = a1;
        }
        if (a2 >= maioraltura) {
            maioraltura = a2;
        } else if (a2 <= menoraltura) {
            menoraltura = a2;
        }
        if (a3 >= maioraltura) {
            maioraltura = a3;
        } else if (a3 <= menoraltura) {
            menoraltura = a3;
        }
        if (a4 >= maioraltura) {
            maioraltura = a4;
        } else if (a4 <= menoraltura) {
            menoraltura = a4;
        }
        System.out.println("Maior altura : " + maioraltura + " Menor altura : " + menoraltura + " Altura media : " + media);
    }
}
