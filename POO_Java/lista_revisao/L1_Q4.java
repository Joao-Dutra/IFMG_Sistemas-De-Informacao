package poo;

import java.util.Scanner;

public class L1_Q4 {
     public static void main(String[] args) {
        
         Scanner entrada = new Scanner(System.in);
         
         char letras[] = new char [26];
         int tamanho;
         int numero = 0;      
         
         for (int i = 0; i < 26; i++) {
             letras[i] = entrada.next().charAt(0);
         }
         System.out.println("TAMANHO:");
         tamanho = entrada.nextInt();
         
         char palavra[] = new char[tamanho];
         
        int l = 0;
         for (int i = numero; l < tamanho; l++) {
             numero = entrada.nextInt();
             i = numero;
             palavra[l] = letras[i-1];
         }
         for (int i = 0; i < tamanho; i++) {
             System.out.print(palavra[i]);
         }
    }
}
