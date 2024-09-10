package listaex1;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class ListaEx1 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(new BufferedInputStream(System.in));
        int alturamaior = 0, numeromaior = 0;
        for (int qntatleta = 1; qntatleta <= 5; qntatleta++) {
            System.out.println("Digite o numero do atleta e em seguida sua altura em cm's : ");
            int atleta = teclado.nextInt();
            int altura = teclado.nextInt();
            if (altura >= alturamaior) {
                alturamaior = altura;
                numeromaior = atleta;
            }

        }
        teclado.close();
        System.out.println("O numero do maior atleta é " + numeromaior + " e sua altura é " + alturamaior);
    }

}
