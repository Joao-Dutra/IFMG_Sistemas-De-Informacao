package listarevisao;

import java.util.Scanner;

public class ListaRevisão_21 {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String nomes[] = new String [5];
        float notas[][] = new float [4][5];
        float maiornotacada[] = new float [5];
        String nome = "";
        float maiornota1 = 0;
        int n = 0;
        float maiornotacada1 = 0;
        
         System.out.println("Digite a seguir os nomes dos candidatos : ");
         for (int i = 0; i < 5; i++) {
             System.out.println(i+1 + " candidato : ");
              nomes[i] = input.nextLine();
         }
         System.out.println("Agora digite as notas de cada candidato : ");
         for (int i = 0; i < 4; i++) {
             System.out.println("Digite a " + i+1 + "° nota de cada candidato : ");
             for (int j = 0; j < 5; j++) {
                 notas[i][j] = input.nextFloat();
                 if (notas[i][j] > maiornota1) {
                     maiornota1 = notas[i][j];
                     nome = nomes[i];
                 }
                 if (notas[i][j] > maiornotacada1) {
                     maiornotacada1 = notas[i][j];
                     
                 }
                 maiornotacada[n] = maiornotacada1;
                 
             }
             n = n + 1;
         }      
         System.out.println("Os nomes de todos candidatos são : ");
         for (int i = 0; i < 5; i++) {
             System.out.print(nomes[i] + " , ");
         }
         System.out.println(". Tais candidatos tiraram as respectivas notas : ");
         for (int i = 0; i < 4; i++) {
             for (int j = 0; j < 5; j++) {
                 System.out.print(notas[i][j] + " ");
             }
             System.out.println(" ");
         }
         System.out.println("A maior nota de cada candidato está representada respectivamente : ");
         for (int i = 0; i < 5; i++) {
             System.out.print(maiornotacada[i] + " ");
         }
         System.out.println("Sendo assim o candidato com maior nota é o : " + nome + " que alcançou a nota : " + maiornota1);
    }
}
