package poo.LISTA2;

public class L2_2 {
    public static void main(String[] args) {
        L2_2_Ponto2D p1 = new L2_2_Ponto2D();
        
        L2_2_Ponto2D p2 = new L2_2_Ponto2D(5.0,12.0);
        
        L2_2_Ponto2D p3 = new L2_2_Ponto2D(p1);
        
        p1.movimentacao();
        System.out.println(p1.toString());
        p2.movimentacao(5, 13);
        System.out.println(p2.toString());
        p3.movimentacao(p2);
        System.out.println(p3.toString());
        p3.toString();  
        
        if(p2.equals(p1)){
            System.out.println("Sao iguais ");
        }else{
            System.out.println("Nao sao iguais");
        }
        
        System.out.println("A distancia entre os pontos eh " + p1.distanciaPontos(p2));
        
        L2_2_Ponto2D p4 = p1.clone();
        System.out.println(p4.toString());
        
        if(p1.equals(p4)){
            System.out.println("Sao iguais ");
        }else{
            System.out.println("Nao sao iguais");
        }
    }
}
