package aeds;

import java.util.Scanner;

public class EX3_RECURSIVIDADE {

    static Scanner entrada = new Scanner(System.in);

    public static void ExibirArray(int n,int inicio,int fim) {
        if (inicio >= n) { return;           
        }else{
            if (n % inicio != 0) {
                fim += 1;               
            }
            ExibirArray(n,inicio+1,fim);
        }if (fim == n - 2) {
            System.out.println("O numero e primo");
        }
        
    }

    public static void main(String[] args) {
      
        int n = entrada.nextInt();
        
        int aux = 2;
        int aux2 = 0;

        while (aux < n) {
            if (n % aux != 0) {
                aux2 += 1;
            }
            aux += 1;
        }
        if (aux2 == n - 2) {
                System.out.println("O numero e primo");

        }
        int inicio = 2;
        int fim = 0;
        ExibirArray(n,inicio,fim);
    }
}
