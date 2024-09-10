/*
Faça um programa que leia 20 valores e armazene-os num vetor de inteiros. Imprima na tela o
vetor na ordem lida e depois em ordem crescente.
A seguir, um exemplo para um vetor de tamanho 5.
Entrada: v = {10, 4, 7, 1, 8}
Saída: v = {10, 4, 7, 1, 8}
v = {1, 4, 7, 8, 10}

 */
package listarevisao;

import java.util.Scanner;

public class ListaRevisão_10 {
     public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in); 
        
        int v[] = new int [5];
        int v2[] = new int[5];
        int aux = 0;
        
         for (int i = 0; i < 5; i++) {
             v[i] = entrada.nextInt();
             v2[i] = v[i];
         }
         for (int i = 0; i < 5; i++) {
             for (int j = 0; j < 4; j++) {
                 if (v2[j] > v2[j + 1]) {
                     aux = v2[j];
                     v2[j] = v2[j + 1];
                     v2[j + 1] = aux;
                 }

             }
         }
         System.out.println("Vetor desordenado : ");
         for (int i = 0; i < 5; i++) {
             System.out.print(v[i] + " ");
         }
         System.out.println(" ");
         System.out.println("Vetor em ordem : ");
         for (int i = 0; i < 5; i++) {
             System.out.print(v2[i] + " ");
         }
    }
}
