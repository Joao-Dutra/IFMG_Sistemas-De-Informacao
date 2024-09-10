/*
Faça um programa em Java, que leia dois vetores ordenados, de 5 posições cada. Gere um
terceiro vetor, também ordenado, com todos os elementos dos vetores anteriores.
Entrada: v1 = {1, 4, 5, 9, 111}
v2 = (3, 3, 5, 8, 300}
Saída: v3 = {1, 3, 3, 4, 5, 5, 8, 9, 111, 300}
 */
package listarevisao;

import java.util.Scanner;

public class ListaRevisão_11 {
     public static void main(String[] args) {
        
         int v[] = new int [5];
         int v2[] = new int [5];
         int v3[] = new int [10];
         int aux = 0 ;
         
         Scanner entrada = new Scanner(System.in);
         
         for (int i = 0; i < 5; i++) {
             System.out.println("V1 : ");
             v[i] = entrada.nextInt();
             System.out.println("V2 : ");
             v2[i] = entrada.nextInt();
         }
         for (int i = 0; i < 5; i++) {
             for (int j = 0; j < 4; j++) {
                 if (v[j] > v[j + 1]) {
                     aux = v[j];
                     v[j] = v[j + 1];
                     v[j + 1] = v[j];
                 }
                 
             }
         }
         aux = 0 ;
         for (int i = 0; i < 5; i++) {
             for (int j = 0; j < 4; j++) {
                 if (v2[j] > v2[j + 1]) {
                     aux = v2[j];
                     v2[j] = v2[j + 1];
                     v2[j + 1] = v2[j];
                 }

             }
         }
         aux = 0 ;
         for (int i = 0; i < 5; i++) {
             v3[i] = v[i];
         }
         for (int i = 0; i < 5; i++) {
             v3[i + 5] = v2[i];
         }
         
         for (int i = 0; i < 10; i++) {
             for (int j = 0; j < 9; j++) {
                 if (v3[j] > v3[j + 1]) {
                     aux = v3[j];
                     v3[j] = v3[j + 1];
                     v3[j + 1] = aux;
                 }
             }
         }
         for (int i = 0; i < 10; i++) {
             System.out.print(v3[i] + " ");
         }
    }
}
