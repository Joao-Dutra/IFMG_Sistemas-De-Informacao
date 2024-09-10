/*
Faça um programa que leia uma sequência de 10 números e armazene-os num vetor. Informe o
total de ocorrências do último número lido.
Entrada: V = {12, 38, 4, 23, 5, 6, 7, 4, 12, 4}
Saída: O número 4 foi informado 3 vezes
 */
package listarevisao;

import java.util.Scanner;

public class ListaRevisão9 {
     public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        int v[] = new int [10];
        int occ = 0;
        
         for (int i = 0; i < 10; i++) {
             v[i] = entrada.nextInt(); 
         }
         for (int i = 0; i < 10; i++) {
             if(v[i] == v[9]){
                 occ = occ + 1;
             }             
         }
         System.out.println("O número " + v[9] + " foi informado " + occ + " vezes ");
     }
    
}
