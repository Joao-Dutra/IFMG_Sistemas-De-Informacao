package listaex1;

import java.util.Scanner;

public class ListaEx3 {
    
        public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite a sigla do seu estado : ");
        String sigla = entrada.nextLine();
        
        switch(sigla) {
            case "RJ" :    
                System.out.println("Carioca");
                break;
            case "MG" :    
                System.out.println("Mineiro");
                break;
            case "SP" :     
                System.out.println("Paulista");
                break;
            default :     
                System.out.println("Outro");
                break;
        }
        
    }
}
