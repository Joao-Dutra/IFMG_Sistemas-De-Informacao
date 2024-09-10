/*
Escreva um algoritmo que leia um vetor de 10 posições de números inteiros e imprima-o na
tela. Em seguida, gerar 2 vetores a partir dele, um contendo os elementos de posições ímpares
do vetor e o outro os elementos de posições pares. Imprimi-los no final.
Entrada: V = {4, 6, 9, 2, 0, 12, 2, 10, 8, 2}
Saída: P = {4, 9, 0, 2, 8}
I = {6, 2, 12, 10, 2}
 */
package listarevisao;

import java.util.Scanner;

public class ListaRevisão6 {
     public static void main(String[] args) {
        
         Scanner entrada = new Scanner(System.in);
         
         int v[] = new int [10];
         int vp[] = new int [5];
         int vi[] = new int[5];
         int count = 0;
         
         for (int i = 0; i < 10; i++) {
             v[i] = entrada.nextInt();
         }
         for (int i = 0; i < 5; i++) {     
                 vp[i] = v[i + count];
                 count = count + 1;
             
         }
         count = 1;
         for (int i = 0; i < 5; i++) {
                 vi[i] = v[i + count];
                 count = count + 1;         
         }
         System.out.println(" Pares : ");
         for (int i = 0; i < 5; i++) {
             System.out.print(vp[i] + " ");
         }
         System.out.println(" ");
         System.out.println(" Impares : ");   
         for (int i = 0; i < 5; i++) {
             System.out.print(vi[i] + " ");
         }
     }
}