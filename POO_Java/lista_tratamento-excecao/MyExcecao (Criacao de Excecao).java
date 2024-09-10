package poo;

public class MyExcecao extends Exception {
    
    private double num;
    
    public MyExcecao(double num,String msg){
        super(msg);
        this.num = num;
    }

    @Override
    public String toString() {
        return "Impossivel depositar, pois o " + this.num + " é negativo";
    }        
}
