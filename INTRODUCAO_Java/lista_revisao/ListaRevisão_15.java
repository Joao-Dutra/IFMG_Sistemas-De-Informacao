package listarevisao;

import java.util.Scanner;

public class ListaRevisão_15 {
     public static void main(String[] args) {
        
         int m[][] = new int [4][4];
         int soma = 0;
         
         Scanner entrada = new Scanner(System.in);
         
         for (int i = 0; i < 4; i++) {
             for (int j = 0; j < 4; j++) {
                 m[i][j] = entrada.nextInt();
             }
              soma = m[i][i] + soma;    
         }
         System.out.println(soma);
    }
}
