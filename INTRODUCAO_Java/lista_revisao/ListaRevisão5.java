/* Escreva um algoritmo que leia dois vetores de 10 posições e faça a multiplicação dos
elementos de mesmo índice, colocando o resultado em um terceiro vetor. Mostre o vetor
resultante.
Entrada: A = {4, 6, 9, 2, 0, 12, 2, 10, 8, 2}
B = {6, 3, 8, 5, 1, 0, 8, 4, 1, 6}
Saída: C = {24, 18, 72, 10, 0, 0, 16, 40, 8, 12}
*/
package listarevisao;

import java.util.Scanner;

public class ListaRevisão5 {
     public static void main(String[] args) {
        
         Scanner entrada = new Scanner(System.in);
         
         int v[] = new int [10];
         int v2[] = new int [10];
         int v3[] = new int [10];
         
         for (int i = 0; i < 10; i++) {
             System.out.println("Escreva um número pro primeiro vetor : ");
             v[i] = entrada.nextInt();
             System.out.println("Agora um número pro segundo vetor : ");
             v2[i] = entrada.nextInt();
         }
         
         for (int i = 0; i < 5; i++) {
             v3[i] = v[i] * v2[i];
         }
         System.out.print("O produto dos mesmos índices são : ");
         for (int i = 0; i < 5; i++) {
             System.out.print( v3[i] + " ");
         }
    }
}
