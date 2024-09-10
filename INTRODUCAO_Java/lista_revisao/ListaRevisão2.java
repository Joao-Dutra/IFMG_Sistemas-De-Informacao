package listarevisao;

import java.util.Scanner;

public class ListaRevisão2 {
     public static void main(String[] args) {
         
        Scanner entrada = new Scanner(System.in);
        float media;
        float soma = 0;
        float count = 0;
        float v[] = new float [10];
        
         for (int i = 0; i < 10; i++) {
             v[i] = entrada.nextFloat();
             if(i % 2 == 1){
                 soma = v[i] + soma;
                 count = count + 1;
             }
         }
         media = soma / count;
         System.out.println("A média dos elementos armazenados nas posições ímpares é : " + media);
    }
}
