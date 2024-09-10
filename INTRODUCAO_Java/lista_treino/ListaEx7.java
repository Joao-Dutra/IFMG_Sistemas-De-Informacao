package listaex1;

import java.util.Scanner;

public class ListaEx7 {
        public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        float numero = teclado.nextFloat();
        
        if(numero % 2 == 0 && numero % 5 == 0){
            numero = numero + 5;
        }else if(numero % 2 == 1){
            numero = numero + 8;
        }
        System.out.println(numero);
    }
}
