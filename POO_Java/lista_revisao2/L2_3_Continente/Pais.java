package L2_7_Continente;



public class Pais {

    private String nome;
    private double dimensao;
    private double populacao;

    public Pais(double dimensao, double populacao,String nome) {
        this.setDimensao(dimensao);
        this.setPopulacao(populacao);
        this.setNome(nome);
    }

    public void setDimensao(double dimensao) {
        this.dimensao = dimensao;
    }

    public double getDimensao() {
        return this.dimensao;
    }

    public double getPopulacao() {
        return this.populacao;
    }

    public void setPopulacao(double populacao) {
        this.populacao = populacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
