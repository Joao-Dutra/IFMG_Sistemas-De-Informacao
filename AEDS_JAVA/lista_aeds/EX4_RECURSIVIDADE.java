package aeds;

import java.util.Scanner;

public class EX4_RECURSIVIDADE {
    static Scanner entrada = new Scanner(System.in);
    public static void Par(int min,int max){
        if(min > max) return;
        if(min % 2 == 0){
            System.out.print(min + " ");
        }
        Par(min+1,max);
    }
    public static void Impar(int min, int max){
        if (min > max) return;
        if(min % 2 != 0){
            System.out.print(min + " ");
        }
        Impar(min+1,max);
    }
    public static void ParEImpar(int min,int max){
        System.out.print("Numeros Pares : ");
        Par(min,max);
        System.out.print("\nNumeros Impares : ");
        Impar(min,max);
    }
    public static void main(String[] args) {
        int min = entrada.nextInt();
        int max = entrada.nextInt();
        ParEImpar(min,max);
    }
}
