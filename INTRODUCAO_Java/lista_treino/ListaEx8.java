package listaex1;

import java.util.Scanner;
import java.io.BufferedInputStream;

public class ListaEx8 {
    public static void main(String[] args) {
        float n1,n2,n3,n4,media;
        Scanner entrada = new Scanner(new BufferedInputStream(System.in));
        n1 = entrada.nextFloat();
        n2 = entrada.nextFloat();
        n3 = entrada.nextFloat();
        n4 = entrada.nextFloat();
        
        media = (n1 + n2 + n3 + n4) / 4;
        
        if( media >= 7){
            System.out.println("Aprovado");   
        }
        if( media < 7 && media >= 5){
            System.out.println("Recuperacao");   
        }
        if( media < 5){
            System.out.println("Reprovado");   
        }
    }
}
