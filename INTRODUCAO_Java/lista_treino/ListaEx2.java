package listaex1;

import java.util.Scanner;
import java.io.BufferedInputStream;

public class ListaEx2 {
        
        public static void main(String[] args) {
            Scanner teclado = new Scanner(new BufferedInputStream(System.in));
            float salario = teclado.nextFloat();
            float salarioporcentagem;
            salarioporcentagem = salario * (30/100);
            if(salario >= salarioporcentagem){
                System.out.println("Sim");
             }else{
                System.out.println("No");
            }
    }
}
