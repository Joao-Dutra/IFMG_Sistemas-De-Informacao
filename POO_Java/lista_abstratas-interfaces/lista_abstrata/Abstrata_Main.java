package Exercicios_Classes_Abstratas_Polimorfismo_Interfaces;

import java.util.ArrayList;

public class E1_Polimorfismo_Abstrata_Main {
    public static void main(String[] args) {
        ArrayList<Empregado> ListaEmpregados = new ArrayList<Empregado>();
        
        Empregado Joao = new Assalariado("Joao Victor","Dutra","1234",2500);
        Empregado Franz = new Assalariado("Franz","Duarte","1235",2200);
        Empregado Gui = new Comissionado("Gui","Marcio","1236",1500,1.50);
        Empregado Kauan = new Comissionado("Kauan","Santos","1237",3000,1.50);
        Empregado Giaca = new Horista("Giaca","Henrique","1238",50,50);
        Empregado Creuto = new Horista("Creuto","Batman","1239",50,100);
        
        ListaEmpregados.add(Joao);
        ListaEmpregados.add(Franz);
        ListaEmpregados.add(Gui);
        ListaEmpregados.add(Kauan);
        ListaEmpregados.add(Giaca);
        ListaEmpregados.add(Creuto);
        
        //Logica para calculo TotalDeVencimentos
        double totalVencimentos = 0;
        for (int i = 0; i < ListaEmpregados.size(); i++) {
            totalVencimentos += ListaEmpregados.get(i).vencimento();
            System.out.println(ListaEmpregados.get(i).vencimento());
        }
        System.out.println(totalVencimentos);
    }
}
