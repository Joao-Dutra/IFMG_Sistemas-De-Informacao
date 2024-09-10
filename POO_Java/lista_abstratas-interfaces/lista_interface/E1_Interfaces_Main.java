package E_Interfaces;

import java.util.ArrayList;
import java.util.Iterator;

public class E1_Interfaces_Main {
    public static void main(String[] args) {
        ArrayList listaCarbon = new ArrayList();
        
        
        Construcao c1 = new Casa();
        Construcao c2 = new Escola();
        Bicicleta b1 = new Bicicleta();
        Bicicleta b2 = new Bicicleta();
        Carro car1 = new Carro();
        Carro car2 = new Carro();
        
        listaCarbon.add(c1);
        listaCarbon.add(c2);
        listaCarbon.add(b1);
        listaCarbon.add(b2);
        listaCarbon.add(car1);
        listaCarbon.add(car2);
        
       Iterator<CarbonFootprint>  iterator = listaCarbon.iterator();
       
        while(iterator.hasNext()){
            CarbonFootprint e = iterator.next();
            System.out.println(e.getCarbonFootprint());         
        }
    }
}
