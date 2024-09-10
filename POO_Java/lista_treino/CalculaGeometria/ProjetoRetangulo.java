package poo;

public class ProjetoRetangulo {
     public static void main(String[] args) {
        
         Retangulo r = new Retangulo();
         
         r.setAltura(5);
         r.setBase(5);
         
         
         System.out.println("O retangulo com base " + r.getBase() + " e com altura " + r.getAltura() + " tem o perímetro de " + r.calcularPerimetro() + " e área de " + r.calcularArea());
    }
}
