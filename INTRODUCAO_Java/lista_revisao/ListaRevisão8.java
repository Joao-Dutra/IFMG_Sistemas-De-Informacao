package listarevisao;

import java.util.Scanner;

public class ListaRevisão8 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        int v[] = new int [5];
        int v2[] = new int [5];
        
        for (int i = 0; i < 5; i++) {
            v[i] = entrada.nextInt();
        }
        for (int i = 4; i > -1; i--) {
            v2[i] = v[4-i];
        }
        System.out.println(" ");
        for (int i = 0; i < 5; i++) {
            System.out.print(v[i] + " ");
        }
        System.out.println(" ");
        for (int i = 0; i < 5; i++) {
            System.out.print(v2[i] + " ");
        }
    }
}
