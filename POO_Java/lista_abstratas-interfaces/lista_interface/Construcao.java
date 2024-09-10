package E_Interfaces;

public abstract class Construcao implements CarbonFootprint {
    private int pessoas;
    private boolean energiaRenovavel;
    private int lampadas;
    private boolean arcondicionado;

    @Override
    public int getCarbonFootprint() {
        return 200;
    }
    
    
}
