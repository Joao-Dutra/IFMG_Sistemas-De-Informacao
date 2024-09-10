package listaex1;

import java.util.Scanner;
import java.io.BufferedInputStream;

public class ListaEx_11 {
    public static void main(String[] args) {
        float numero = 1, soma = 1;
        
        Scanner entrada = new Scanner(new BufferedInputStream(System.in));
        
        while(numero != 0){
            numero = entrada.nextFloat();
            soma = numero + soma;
        }
        soma = soma - 1;
        System.out.println(soma); 
    }
}
