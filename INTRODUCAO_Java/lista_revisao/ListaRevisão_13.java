/*
Faça um programa que leia um conjunto de 10 valores inteiros para um vetor A. Em seguida,
leia mais 10 valores inteiros para um vetor B. Seu programa deve substituir por 0, todos os
valores que existem no vetor A e que não existem no vetor B.
Entrada: A = {10, 3, 5, 8, 9, 2, 4, 5, 7, 12}
B = {13, 5, 9, 6, 15, 2, 7, 20, 9, 10}
Saída: A = {10, 0, 5, 0, 9, 2, 0, 5, 7, 0}
B = {13, 5, 9, 6, 15, 2, 7, 20, 9, 10}
 */
package listarevisao;

import java.util.Scanner;

public class ListaRevisão_13 {
     public static void main(String[] args) {
        
         Scanner entrada = new Scanner(System.in);
         int v1[] = new int [10];
         int v2[] = new int [10];
         
         for (int i = 0; i < 10; i++) {
             System.out.println("A");
             v1[i] = entrada.nextInt();
             System.out.println("B");
             v2[i] = entrada.nextInt();
         }
         for (int i = 0; i < 10; i++) {            
             if (v1[i] != v2[i]) {
                 v1[i] = 0; 
             }
         }
         for (int i = 0; i < 10; i++) {
             System.out.print(v1[i] + " ");
         }
         System.out.println(" ");
         for (int i = 0; i < 10; i++) {
             System.out.print(v2[i] + " ");
         }
                      
    }
}
