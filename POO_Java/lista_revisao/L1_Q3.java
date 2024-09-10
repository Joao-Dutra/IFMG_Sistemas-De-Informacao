package poo;

import java.util.Scanner;

public class L1_Q3 {
     public static void main(String[] args) {
        
         
         Scanner entrada = new Scanner(System.in);
         
         int mes, dia,calculo = 0;                
                 
         mes = entrada.nextInt();
         dia = entrada.nextInt();
         
         
         if (mes == 12 && dia == 25) {
             System.out.println("E natal!");
         }else{
             if (mes == 12 && dia == 24) {
                 System.out.println("E vespera de natal!");
             }else{
                 if (mes == 12 && dia > 25) {
                     System.out.println("Ja passou!");
                 }else{
                    switch (mes){
                        case 1:
                            calculo = dia;
                            break;
                        case 2:
                            calculo = dia + 31;
                            break;
                        case 3:
                            calculo = dia + 60;
                            break;
                        case 4:
                            calculo = dia + 91;
                            break;
                        case 5:
                            calculo = dia + 121;
                            break;
                        case 6:
                            calculo = dia + 152;
                            break;
                        case 7:
                            calculo = dia + 182;
                            break;
                        case 8:
                            calculo = dia + 213;
                            break;
                        case 9:
                            calculo = dia + 244;
                            break;
                        case 10:
                            calculo = dia + 274;
                            break;
                        case 11:
                            calculo = dia + 305;
                            break;
                        case 12:
                            calculo = dia + 336;
                            break;
                                                        
                    }
                 }
             }
             
         }
         calculo = 360 - calculo;
         System.out.println(calculo);
    }
}
