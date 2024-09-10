package poo;

import java.util.Scanner;

public class L1_Q5 {
    
    public static void main(String[] args) {
        
        
        int i,j;
        int soma = 0;
        
        Scanner teclado = new Scanner(System.in);
        
        i = teclado.nextInt();
        j = teclado.nextInt();
        
        int mat[][] = new int[i][j];
        int calculo[][] = new int[i][j];
        
        for(int l = 0; l< i; l++){
            for(int c=0; c< j; c++){
                mat[l][c] = teclado.nextInt();
            }
        }
        
      
        for(int l = 0; l< i; l++){
            for(int c=0; c< j; c++){
                
                if(mat[l][c] == 1){
                    calculo[l][c] = 9;
                }else{
                    
                    if((-1+c)>= 0){
                        soma += mat[l][-1+c]; 
                    }
                    
                    if((c+1)< j){
                        soma += mat[l][c+1]; 
                    }
                    
                    if((-1+l)>= 0){
                        soma += mat[-1+l][c];
                    }
                    
                    if((1+l)< i){
                        soma += mat[l+1][c];
                    }
                    
                    calculo[l][c] = soma;
                    soma = 0;
                    
                }
                
            }
        }
        
        for(int l = 0; l< i; l++){
            for(int c=0; c< j; c++){
                
                System.out.print(calculo[l][c]);
                
            }
            System.out.println();
        }
        
        
        
    }
    
}
