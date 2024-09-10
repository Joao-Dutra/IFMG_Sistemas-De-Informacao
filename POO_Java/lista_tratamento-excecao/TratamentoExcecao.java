package poo;

public class TratamentoExcecao {

    public static void main(String[] args) {
        try {
            int vet[] = new int[4];
            System.out.println("Msg antes da excecao:");
            vet[4] = 1;
            System.out.println("Esta mensagem nao vai ser exibida"); // está linha nunca vai ser executada.
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ocorreu uma excecao ao tentar acessar uma variavel inexistente");
        }
    }

}
