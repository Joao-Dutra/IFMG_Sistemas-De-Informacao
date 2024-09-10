package poo;

public class MultiplasExececoes {

    public static void main(String[] args) {

        int num[] = {4, 8, 16, 32, 64, 128};
        int div[] = {2, 0, 4, 8, 0};
        for (int i = 0; i < num.length; i++) {
            try {
                System.out.println(num[i] + " / " + div[i] + " = " + num[i] / div[i]);
            } catch (ArithmeticException e) {
                System.out.println("Ocorreu excecao, tentou dividir por 0");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ocorreu uma excecao ao tentar acessar uma variavel inexistente");
            }
        }
    }
}
