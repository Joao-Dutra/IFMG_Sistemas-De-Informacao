package poo;

import java.util.Scanner;

public class Throws {

    public static void main(String[] args) {
        try {
            double num = leNumero();
            System.out.println("Voce digitou o numero : " + num);
        }catch(Exception e){
            System.out.println("Entrada Invalida!");
            e.printStackTrace();
        }

    }

    public static double leNumero() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite um numero : ");
        double num = input.nextDouble();

        return num;
    }

}
