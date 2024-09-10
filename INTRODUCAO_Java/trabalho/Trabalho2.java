package Trabalho;

import java.util.Scanner;
import java.io.BufferedInputStream;

public class Trabalho2 {
    
        public static void main(String[] args) {
        Scanner entrada = new Scanner(new BufferedInputStream(System.in));
        
        int n = entrada.nextInt();
        int contador = 0;
        
            for (int i = 0; i < n;) {
                contador++;
                if(contador % 4 == 0){
                    System.out.println("PULA");   
                    i++;
                } else {
                    System.out.printf(contador + " ");   
                }
            }
    }
}