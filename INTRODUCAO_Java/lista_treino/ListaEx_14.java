package listaex1;

import java.util.Scanner;
import java.io.BufferedInputStream;

public class ListaEx_14 {
    public static void main(String[] args) {
        int n, somaimpar = 0;
        
        Scanner entrada = new Scanner(new BufferedInputStream(System.in));
        n = entrada.nextInt();
        
        while(n != 0){
            if(n % 2 == 1){
               somaimpar = somaimpar + n;
            }
            n = n - 1;
        }
        System.out.println(somaimpar);
    }
}
