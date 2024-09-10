package listaex1;

import java.util.Scanner;
import java.io.BufferedInputStream;

public class ListaEx5 {
    public static void main(String[] args) {
        
        float AV1 , AV2;
        int frequenciaP;
        
        Scanner entrada = new Scanner(new BufferedInputStream(System.in));
        System.out.println("Informe as notas das avaliacoes e a frequencia : ");
        
        AV1 = entrada.nextFloat();
        AV2 = entrada.nextFloat();
        frequenciaP = entrada.nextInt();
        
        float media = (AV1 + AV2) / 2;
        
        if(frequenciaP < 50 || AV1 < 4 && AV2 < 4){
            System.out.println("Reprovado");
        }else if(AV1 < 4 || AV2 < 4 || media < 6){
            System.out.println("Recuperacao");
        }else{
            System.out.println("Aprovado");   
        }
        
    }
}
