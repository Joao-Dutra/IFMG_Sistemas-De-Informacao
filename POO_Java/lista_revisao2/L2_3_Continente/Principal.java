package L2_7_Continente;



public class Principal {

    public static void main(String[] args) {
        //Continentes
        Continente AmericaDoSul = new Continente("America Do Sul");
        Continente Europa = new Continente("Europa");
        //Paises

        Pais Brasil = new Pais(8510000, 214.300000, "Brasil");
        Pais Argentina = new Pais(2780000, 45.810000, "Argentina");
        Pais Chile = new Pais(756626, 19.490000, "Chile");

        Pais França = new Pais(551695, 67.750000, "França");
        Pais Italia = new Pais(302073, 59.110000, "Italia");
        Pais ReinoUnido = new Pais(243610, 67.330000, "Reino Unido");

        //Paises da AmericaDoSul
        AmericaDoSul.addPais(Brasil);
        AmericaDoSul.addPais(Argentina);
        AmericaDoSul.addPais(Chile);
        //Paises da Europa
        Europa.addPais(França);
        Europa.addPais(Italia);
        Europa.addPais(ReinoUnido);

        //Teste de metodos dos continentes
        System.out.println("Dimensao do Continente " + AmericaDoSul.getNome() + " : " + AmericaDoSul.calcDimensao() + " Km²");
        System.out.println("Populacao do Continente " + AmericaDoSul.getNome() + " : " + AmericaDoSul.calcPopulacao() + " Milhoes");

        System.out.println("Dimensao do Continente " + Europa.getNome() + " : " + Europa.calcDimensao() + " Km²");
        System.out.println("Populacao do Continente " + Europa.getNome() + " : " + Europa.calcPopulacao() + " Milhoes");

        System.out.println("Densidade populacional do Continente " + AmericaDoSul.getNome() + " : Aproximadamente - > " + AmericaDoSul.calcDensidadePopulacional());
        System.out.println("Densidade populacional do Continente " + Europa.getNome() + " : Aproximadamente - > " + Europa.calcDensidadePopulacional() + "\n");

        System.out.println("O pais com maior população do Continente " + AmericaDoSul.getNome() + " : " + AmericaDoSul.maiorPopulacao().getNome());
        System.out.println("O pais com menor população do Continente " + AmericaDoSul.getNome() + " : " + AmericaDoSul.menorPopulacao().getNome() + " \n");

        System.out.println("O pais com maior população do Continente " + Europa.getNome() + " : " + Europa.maiorPopulacao().getNome());
        System.out.println("O pais com menor população do Continente " + Europa.getNome() + " : " + Europa.menorPopulacao().getNome() + " \n");

        System.out.println("O maior pais de dimensao do Continente " + AmericaDoSul.getNome() + " : " + AmericaDoSul.maiorDimensao().getNome());
        System.out.println("O menor pais de dimensao do Continente " + AmericaDoSul.getNome() + " : " + AmericaDoSul.menorDimensao().getNome() + " \n");

        System.out.println("O maior pais de dimensao do Continente " + Europa.getNome() + " : " + Europa.maiorDimensao().getNome());
        System.out.println("O menor pais de dimensao do Continente " + Europa.getNome() + " : " + Europa.menorDimensao().getNome() + " \n");

        System.out.println("A razao territorial do maior pais em relação ao menor pais do Continente " + AmericaDoSul.getNome() + " : " + AmericaDoSul.razaoTerritorialMaioreMenor() + " Km²");
        System.out.println("A razao territorial do maior pais em relação ao menor pais do Continente " + Europa.getNome() + " : " + Europa.razaoTerritorialMaioreMenor() + " Km²");
        
    }
}
