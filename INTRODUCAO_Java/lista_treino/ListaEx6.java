package listaex1;

import java.util.Scanner;
import java.io.BufferedInputStream;

public class ListaEx6 {
    public static void main(String[] args) {
        float peso, altura, imc;
        Scanner entrada = new Scanner(new BufferedInputStream(System.in));
        peso = entrada.nextFloat();
        altura = entrada.nextFloat();
        
        imc = peso / (altura * altura);        
        
        if(imc < 18.5){
            System.out.println("Abaixo do Peso");   
        }
        if(imc >= 18.6 && imc < 25){
            System.out.println("Peso normal");   
        }
        if(imc >= 25){
            System.out.println("Acima do Peso");   
        }
    }
}
