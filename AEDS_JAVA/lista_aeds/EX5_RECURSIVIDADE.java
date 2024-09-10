package aeds;

import java.util.Scanner;

public class EX5_RECURSIVIDADE {

    static Scanner entrada = new Scanner(System.in);

    public static int potenciacao(int n, int p) {
        if (p == 0) {
            return 1;
        }
        return n * potenciacao(n, p - 1);
    }

    public static void main(String[] args) {
        int n = entrada.nextInt();
        int p = entrada.nextInt();

        System.out.print(potenciacao(n, p));

    }
}
