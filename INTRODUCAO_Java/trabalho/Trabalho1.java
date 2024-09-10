package Trabalho;

import java.util.Scanner;
import java.io.BufferedInputStream;

public class Trabalho1 {

    public static void main(String[] args) {
        int combustivel = 0,alcool = 0,gasolina = 0,diesel = 0;
        Scanner entrada = new Scanner(new BufferedInputStream(System.in));
        
        combustivel = entrada.nextInt();
        switch (combustivel) {
            case 1:
                alcool = alcool + 1;
                break;
            case 2:
                gasolina = gasolina + 1;
                break;
            case 3:
                diesel = diesel + 1;
                break;
        }
        
        while (combustivel != 4) {
            if (combustivel < 1 && combustivel > 4) {
                combustivel = entrada.nextInt();
                switch (combustivel) {
                    case 1:
                        alcool = alcool + 1;
                        break;
                    case 2:
                        gasolina = gasolina + 1;
                        break;
                    case 3:    
                        diesel = diesel + 1;
                        break;
               }

            } else {
                combustivel = entrada.nextInt();
                switch (combustivel) {
                    case 1:
                        alcool = alcool + 1;
                        break;
                    case 2:
                        gasolina = gasolina + 1;
                        break;
                    case 3:    
                        diesel = diesel + 1;
                        break;
                }
            }
        }
        System.out.println("MUITO OBRIGADO \nAlcool: " + alcool +"\nGasolina: "+ gasolina +"\nDiesel: " + diesel );
    }
    
}
