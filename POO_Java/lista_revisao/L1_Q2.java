package poo;

import java.util.Scanner;

public class L1_Q2 {
     public static void main(String[] args) {
        
         Scanner entrada = new Scanner(System.in);
         
         int x,y;
         
         x = entrada.nextInt();
         y = entrada.nextInt();
         
         
        while (x != 0 && y != 0) {
            if (x > 0 && y > 0) {
                System.out.println("Primeiro");
            } else {
                if (x > 0 && y < 0) {
                    System.out.println("Quarto");
                } else {
                    if (x < 0 && y > 0) {
                        System.out.println("Segundo");
                    } else {
                        if (x < 0 && y < 0) {
                            System.out.println("Terceiro");
                        }
                    }

                }
            }
           x = entrada.nextInt();
           y = entrada.nextInt(); 
        }
        
    }
}
