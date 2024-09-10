package poo;

public class TratamentoExcecaoGenerica {

    public static void main(String[] args) {

        int num[] = {4, 8, 16, 32, 64, 128};
        int div[] = {2, 0, 4, 8, 0};

        for (int i = 0; i < num.length; i++) {
            try {
                System.out.println(num[i] + " / " + div[i] + " = " + num[i] / div[i]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
