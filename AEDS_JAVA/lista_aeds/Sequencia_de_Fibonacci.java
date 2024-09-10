package aeds;

public class Sequencia_de_Fibonacci {
    public static void main(String[] args) {
       int v1 = 1 , v2 = 1, v = 0,contador = 3;
       System.out.println("Sequencia de Fibonacci: \n" + v+"\n"+ v1 + "\n" + v2);
       Calculo(v1,v2,v,contador);
       
       
    }
    public static void Calculo(int v1, int v2, int v,int contador) {
        if(contador > 46){
           
        }else{
        v = v1 + v2;
        v1 = v2;
        v2 = v;
        System.out.println(v);
        contador += 1;
        Calculo(v1,v2,v,contador);           
        }       
    }
}
