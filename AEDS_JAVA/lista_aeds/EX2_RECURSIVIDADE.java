package aeds;

import java.util.Random;

public class EX2_RECURSIVIDADE {
    public static int NumMaior(int v[],int inicio, int maior){
        if (inicio >= v.length) {
            return maior;
        }else{
            if (v[inicio] >= maior) {
                maior = v[inicio];              
            }
            return NumMaior(v,inicio+1,maior);
        }
    }
    public static void main(String[] args) {
        Random random = new Random();
        int tamanhovetor = 10;
        int v[] = new int [tamanhovetor];
        int maior = 0,inicio = 0;

        for (int i = 0; i < tamanhovetor; i++) {
            v[i] = random.nextInt(100);
            System.out.print(v[i] + " ");
        }
        System.out.println(" ");
        System.out.print(NumMaior(v,inicio,maior) + "\n");
        
    }
}
