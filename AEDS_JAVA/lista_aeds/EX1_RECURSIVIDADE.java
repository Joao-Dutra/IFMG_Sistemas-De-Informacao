package aeds;

import java.util.Random;

public class EX1_RECURSIVIDADE {
    public static void ExibirArray(int v[], int inicio){
        int tamanhovetor = v.length;
        if (inicio >= tamanhovetor);
        else{
            System.out.print(v[inicio] + " ");
            ExibirArray(v,inicio+1);
        }                    
    }
    
    public static void main(String[] args) {
       Random random = new Random();
        int tamanhovetor = 10;
        int v[] = new int [tamanhovetor];
        
        for (int i = 0; i < tamanhovetor; i++) {
            v[i] = random.nextInt(100);
            System.out.print(v[i] + " ");
        }
        int inicio = 0;
        System.out.println(" ");
        ExibirArray(v,inicio);
    }
}
