package listarevisao;

import java.util.Random;

public class ListaRevisão {

    public static void main(String[] args) {
            Random gerador = new Random();
            int v[] = new int [10];
            int soma = 0;
            
           for (int i = 0; i < 10; i++) {
               v[i] = gerador.nextInt(13); 
               System.out.print(v[i] + " ");
            if( i % 2 == 0){
                soma = v[i] + soma;
            }
        }
            System.out.println("A soma dos valores nas posições pares é : " + soma);
    }
    
}
