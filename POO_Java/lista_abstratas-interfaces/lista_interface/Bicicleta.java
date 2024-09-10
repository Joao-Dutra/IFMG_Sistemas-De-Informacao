package E_Interfaces;

public class Bicicleta implements CarbonFootprint {
    private String marca;
    private int tamanhoAro;

    @Override
    public int getCarbonFootprint() {
       return 10;
    }
}
