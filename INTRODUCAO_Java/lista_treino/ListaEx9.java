package listaex1;

import java.util.Scanner;
import java.io.BufferedInputStream;

public class ListaEx9 {
    public static void main(String[] args) {
        
        int hora, minutos, segundos,calculo;
        Scanner entrada = new Scanner(new BufferedInputStream(System.in));
        
        System.out.println("Informe as horas com minutos e segundos : ");
        hora = entrada.nextInt();
        minutos = entrada.nextInt();
        segundos = entrada.nextInt();
       
        
        int horaS = hora * 3600;
        int minutoS = minutos * 60;
        
        calculo = horaS + minutoS + segundos;        
        
        System.out.println(calculo);
    }
}
