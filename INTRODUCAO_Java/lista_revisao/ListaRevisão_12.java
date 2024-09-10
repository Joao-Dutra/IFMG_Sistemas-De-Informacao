/*
Faça um programa que leia 10 valores e armazene-os num vetor. Calcule e exiba na tela os 3
maiores elementos do vetor.
Entrada: v1 = {4, 6, 9, 2, 0, 12, 2, 10, 7, 1}
Saída: 12, 10, 9
 */
package listarevisao;

import java.util.Scanner;

public class ListaRevisão_12 {
        public static void main(String[] args) {
        
            Scanner entrada = new Scanner(System.in);
            
            int v[] = new int [10];
            int maior1 = 0;
            int maior2 = 0;
            int maior3  = 0;
            
            for (int i = 0; i < 10; i++) {
                v[i] = entrada.nextInt();
                if(v[i] > maior1){
                    maior3 = maior2;
                    maior2 = maior1;
                    maior1 = v[i];
                }else if(v[i] > maior2){
                    maior3 = maior2;
                    maior2 = v[i];                 
                }else if(v[i] > maior3){
                    maior3 = v[i];
                }
            }
            System.out.println(maior1 + " " + maior2 + " " + maior3);
    }
}
