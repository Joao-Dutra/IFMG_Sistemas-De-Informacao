package listarevisao;

import java.util.Scanner;

public class ListaRevisão3 {
     public static void main(String[] args) {
        
         Scanner entrada = new Scanner(System.in);
         int v[] = new int [10];
         int v2[] = new int [10];
         
         for (int i = 0; i < 10; i++) {
             v[i] = entrada.nextInt();
             v2[i] = v[i] * 2;
         }
         
         for (int i = 0; i < 10; i++) {
             System.out.print(v[i] + " ");
         }
         System.out.println(" ");
         for (int i = 0; i < 10; i++) {
             System.out.print(v2[i] + " ");
         }
    }
}
