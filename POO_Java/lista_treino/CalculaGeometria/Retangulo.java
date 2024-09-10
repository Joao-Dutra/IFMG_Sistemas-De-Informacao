package poo;

public class Retangulo {
    
    private int base = 1, altura = 1;

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        if (base >= 1 && base <= 20) {
            this.base = base;
        }else{
            System.out.println("Impossivel inserir esse valor como base!");
        }
        
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
        if (altura >= 1 && altura <= 20) {
            this.altura = altura;
        }else{
            System.out.println("Imposivel inserir esse valor como altura!");
        }
    }
    
    public int calcularArea() {
        int area;
        area = this.altura*this.base;
        return area;
    }
    
    public int calcularPerimetro() {
        int perimetro;
        perimetro = 2*(this.altura+this.base);
        return perimetro;
    }
    
}
