package listarevisao;

import java.util.Random;

public class ListaRevisão4 {
     public static void main(String[] args) {
        Random gerador = new Random();
        
        int v[] = new int [8];
        int v2[] = new int [8];
        int v3[] = new int [8];
        
         for (int i = 0; i < 8; i++) {
             v[i] = gerador.nextInt(21);
             v2[i] = gerador.nextInt(21);
             System.out.print(v[i] + " ");
         }
         System.out.println(" ");
         for (int i = 0; i < 8; i++) {
             System.out.print(v2[i] + " ");             
         }
         System.out.println(" ");
         for (int i = 0; i < 8; i++) {
             v3[i] = v[i];
             v[i] = v2[i];
             v2[i] = v3[i];
         }
         for (int i = 0; i < 8; i++) {
             System.out.print(v[i] + " ");
         }
         System.out.println(" ");
         for (int i = 0; i < 8; i++) {
             System.out.print(v2[i] + " ");             
         }
    }
}
