package E_Interfaces;

public class Carro implements CarbonFootprint {
    private String combustivel;
    private float cilindrada;  

    @Override
    public int getCarbonFootprint() {
       return 500;
    }
}
