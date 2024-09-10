package listaex1;

import java.util.Scanner;
import java.io.BufferedInputStream;

public class ListaEx_13 {
    public static void main(String[] args) {
        float qtpessoas,idade,peso,soma = 0;
        
        Scanner entrada = new Scanner(new BufferedInputStream(System.in));
        qtpessoas = entrada.nextFloat();
        
        while(qtpessoas != 0){
         idade = entrada.nextFloat();   
         peso = entrada.nextFloat();
         if(idade > 30){
             soma = peso + soma;
         }
         qtpessoas = qtpessoas - 1;
        }
        System.out.println(soma);
    }
}
