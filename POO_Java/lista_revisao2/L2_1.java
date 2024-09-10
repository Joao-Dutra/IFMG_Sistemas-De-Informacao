package poo.LISTA2;

import java.util.Scanner;

public class L2_1 {
    static Scanner entrada = new Scanner(System.in);
    public static void main(String[] args) {
             
    L2_1_Contador c1 = new L2_1_Contador();
    int comando = -10, valor = 0;

    comando = entrada.nextInt();
    while(comando != -1){
    switch (comando) {
        case 1:
            System.out.println("Qual valor a ser setado ? ");
            valor = entrada.nextInt();
            c1.setValor(valor);
            comando = entrada.nextInt();
            break;
        case 2:
            System.out.println("O valor atual é : " + c1.getValor());
            comando = entrada.nextInt();
            break;
        case 9:
            System.out.println("Qual valor para incrementar ? ");
            valor = entrada.nextInt();
            c1.incrementarValor(valor);
            comando = entrada.nextInt();
            break;
        case 0:
            c1.zerarValor(valor);
            System.out.println("O valor foi zerado ! " + c1.getValor());
            comando = entrada.nextInt();
            break;
    }
    }
}
}
