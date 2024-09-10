package listaex1;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class ListaEx4 {
    
    public static void main(String[] args) {
        System.out.println("Escreva tres numeros a seguir : ");
        
        Scanner teclado = new Scanner(new BufferedInputStream(System.in));
        float n1 = teclado.nextFloat();
        float n2 = teclado.nextFloat();
        float n3 = teclado.nextFloat();
        
        float aux = 0;
        
        if( n1 > n2 ){
            aux = n1;
            n1 = n2;
            n2 = aux;        
        }
        if( n2 > n3 ){
            aux = n2;
            n2 = n3;
            n3 = aux;
        }
         if( n1 > n2 ){
            aux = n1;
            n1 = n2;
            n2 = aux;        
        }
        
        System.out.println("Os numeros em ordem decrescente é : \n" + n3 + "\n" + n2 + "\n" + n1 );
    }
}
