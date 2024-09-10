package poo;

import java.util.Scanner;

public class Principal {
    static Scanner entrada = new Scanner(System.in);
    public static void main(String[] args) {
        
        Conta c1,c2;
        
        c1 = cadastrarConta();
        c2 = cadastrarConta();
        int operacao;
        double valor;
        boolean retorno;
        
        
        do{
        System.out.println("\n*** O que você deseja fazer: ***");
        System.out.println(" 1 : Depositar \n 2 : Sacar \n 3 : Transferencia \n 0 : Sair");
        operacao = entrada.nextInt();
        
        switch(operacao){
            case 1:
                System.out.println("Qual o valor a ser depositado?");
                valor = entrada.nextDouble();
                c1.depositarValor(valor);
                System.out.println("O saldo atual da conta eh " + c1.getSaldo());
            break;
            
            case 2:
                System.out.println("Qual o valor a ser sacado?");
                valor = entrada.nextDouble();
                retorno = c1.sacarValor(valor);
                if (retorno) {
                    System.out.println("Saque efetuado com sucesso!");
                }else{
                    System.out.println("O saque nao foi efetuado!");
                }
            break;
            
            case 3:
                System.out.println("Qual o valor a ser transferido?");
                valor = entrada.nextDouble();
                c1.transferirValor(valor, c2);
                System.out.println("O saldo da conta c1 apos a transferencia eh : " + c1.getSaldo());
                System.out.println("O saldo da conta c2 apos a transferia eh : " + c2.getSaldo());
            break;
            
            case 0:
                System.out.println("Você encerrou o programa!");
            break;    
            
            default:
                System.out.println("Opcao Invalida!");
        }
        
        }while(operacao != 0);
        
        entrada.close();
        
    }
    public static Cliente cadastrarCliente(){
        
        Cliente cli = new Cliente();
        System.out.println("Informe o nome do Cliente: ");
        String nome = entrada.next();
        System.out.println("Informe o CPF: ");
        String cpf = entrada.next();
        System.out.println("Informe o Endereço: ");
        String endereço = entrada.next();
        System.out.println("Informe o Fone: ");
        String fone = entrada.next();
        
        cli.setNome(nome);
        cli.setCpf(cpf);
        cli.setEndereço(endereço);
        cli.setFone(fone);
        
        return cli;
    }
    
    public static Conta cadastrarConta(){
        
        Conta c = new Conta();
        System.out.println("*** Cadastro de Conta ***");
        System.out.println("Informe o numero da conta: ");
        int numConta = entrada.nextInt();
        System.out.println("Informe o saldo: ");
        double saldo = entrada.nextDouble();
        System.out.println("*** Informe os dados do Titular ***");
        Cliente cli = cadastrarCliente();
        
        c.setNumero(numConta);
        c.setSaldo(saldo);
        c.setTitular(cli);
        
        return c;
    }
}
