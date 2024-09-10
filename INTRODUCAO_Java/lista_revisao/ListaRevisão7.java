package listarevisao;

import java.util.Scanner;

public class ListaRevisão7 {
     public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        int v[] = new int [12];
        int v2[] = new int [12];
        
         for (int i = 0; i < 12; i++) {
             v[i] = entrada.nextInt();
         }
         for (int i = 0; i < 12; i++) {
             v2[i] = v[i];
             if (v[i] == 0) {
               v2[i] = v[i] + 1;
             }
         }
         for (int i = 0; i < 12; i++) {
             System.out.print(v[i] + " ");
         }
         System.out.println(" ");
         for (int i = 0; i < 12; i++) {
             System.out.print(v2[i] + " ");
         }
    }
}
