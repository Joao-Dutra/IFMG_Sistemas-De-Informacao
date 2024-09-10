package L2_7_Continente;



import java.util.ArrayList;

public class Continente {

    private String nome;
    private ArrayList<Pais> listadepaises = new ArrayList<Pais>();

    public Continente(String nome) {
        this.setNome(nome);
    }

    public void addPais(Pais paisadicionado) {
        this.listadepaises.add(paisadicionado);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public ArrayList<Pais> getListadepaises() {
        return this.listadepaises;
    }

    public double calcDimensao() {
        double DimensaoTotal = 0;
        for (int i = 0; i < this.listadepaises.size(); i++) {
            DimensaoTotal += this.listadepaises.get(i).getDimensao();
        }
        return DimensaoTotal;
    }

    public double calcPopulacao() {
        double PopulacaoTotal = 0;
        for (int i = 0; i < this.listadepaises.size(); i++) {
            PopulacaoTotal += this.listadepaises.get(i).getPopulacao();
        }
        return PopulacaoTotal;
    }

    public double calcDensidadePopulacional() {
        double DensidadePopulacional = this.calcPopulacao() / this.calcDimensao();
        return DensidadePopulacional;
    }

    public Pais maiorPopulacao() {
        double maior = this.listadepaises.get(0).getPopulacao();
        Pais aux = null;
        for (int i = 0; i < this.listadepaises.size(); i++) {
            if (this.listadepaises.get(i).getPopulacao() >= maior) {
                maior = this.listadepaises.get(i).getPopulacao();
                aux = this.listadepaises.get(i);
            }
        }
        return aux;
    }

    public Pais menorPopulacao() {
        double menor = this.listadepaises.get(0).getPopulacao();
        Pais aux = null;
        for (int i = 0; i < this.listadepaises.size(); i++) {
            if (this.listadepaises.get(i).getPopulacao() <= menor) {
                menor = this.listadepaises.get(i).getPopulacao();
                aux = this.listadepaises.get(i);
            }
        }
        return aux;
    }

    public Pais maiorDimensao() {
        double maior = this.listadepaises.get(0).getDimensao();
        Pais aux = null;
        for (int i = 0; i < this.listadepaises.size(); i++) {
            if (this.listadepaises.get(i).getDimensao() >= maior) {
                maior = this.listadepaises.get(i).getDimensao();
                aux = this.listadepaises.get(i);
            }
        }
        return aux;
    }

    public Pais menorDimensao() {
        double menor = this.listadepaises.get(0).getDimensao();
        Pais aux = null;
        for (int i = 0; i < listadepaises.size(); i++) {
            if (this.listadepaises.get(i).getDimensao() <= menor) {
                menor = this.listadepaises.get(i).getDimensao();
                aux = this.listadepaises.get(i);
            }
        }
        return aux;
    }

    public double razaoTerritorialMaioreMenor() {
        double razao = this.maiorDimensao().getDimensao() / this.menorDimensao().getDimensao();
        return razao;
    }

}
