package poo;

import java.util.Scanner;

public class Pessoa {

    //atributos
    int idade;
    int dia, mes, ano;
    String nome;

    Scanner entrada = new Scanner(System.in);
    
    //metodos
    void calculaIdade(int anotual) {
        this.idade = ano;
        this.idade -= anotual;
    }

    void informaIdade() {
        System.out.println(this.nome + " iria ter " + this.idade + " anos, se ainda estivesse vivo!");
    }
    
    void informaNome() {
        System.out.println("Informe uma data para calcular a idade de " + this.nome);
    }
    
    void ajustaDataDeNascimento(){
        System.out.println("Informe o dia : ");
        this.dia = entrada.nextInt();
        System.out.println("Informe o mes : ");
        this.mes = entrada.nextInt();
        System.out.println("Informe o ano : ");
        this.ano = entrada.nextInt();
    }
 }
