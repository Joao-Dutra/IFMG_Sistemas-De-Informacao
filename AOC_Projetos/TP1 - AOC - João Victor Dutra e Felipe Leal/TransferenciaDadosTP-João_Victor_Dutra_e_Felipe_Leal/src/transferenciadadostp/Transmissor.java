package transferenciadadostp;

import java.util.Random;

public class Transmissor {

    private String mensagem;

    public Transmissor(String mensagem) {
        this.mensagem = mensagem;
    }

    //convertendo um símbolo para "vetor" de boolean (bits)
    private boolean[] streamCaracter(char simbolo) {

        //cada símbolo da tabela ASCII é representado com 8 bits
        boolean bits[] = new boolean[8];

        //convertendo um char para int (encontramos o valor do mesmo na tabela ASCII)
        int valorSimbolo = (int) simbolo;
        int indice = 7;

        //convertendo cada "bits" do valor da tabela ASCII
        while (valorSimbolo >= 2) {
            int resto = valorSimbolo % 2;
            valorSimbolo /= 2;
            bits[indice] = (resto == 1);
            indice--;
        }
        bits[indice] = (valorSimbolo == 1);

        return bits;
    }

    //não modifique (seu objetivo é corrigir esse erro gerado no receptor)
    private void geradorRuido(boolean bits[]) {
        Random geradorAleatorio = new Random();

        //pode gerar um erro ou não..
        if (geradorAleatorio.nextInt(5) > 1) {
            int indice = geradorAleatorio.nextInt(8);
            bits[indice] = !bits[indice];
        }
    }

    private boolean[] dadoBitsCRC(boolean bits[]) {

        //Retornando o bit novo ja calculado e com os numeros de controle no final, na qual foi passado pelas funcoes adicionarZeros e calcularXOR
        return adicionarZeros(bits);
    }

    private boolean[] adicionarZeros(boolean bits[]) {
        //Polinomio gerador 1 1 0 0 1
        boolean[] polinomio = {true, true, false, false, true};
        //Criando um novo vetor para armazenar os 0's adicionais junto ao dado
        boolean bitsComZeros[] = new boolean[bits.length + polinomio.length - 1];
        //Logica de adicao dos 0's ao dado original
        for (int i = 0; i < bitsComZeros.length; i++) {
            if (i < bits.length) {
                bitsComZeros[i] = bits[i];
            }
        }

        return calcularXOR(bitsComZeros);

    }

    private boolean[] calcularXOR(boolean[] bitsComZeros) {
        //Polinomio gerador 1 1 0 0 1
        boolean polinomio[] = {true, true, false, false, true};
        //Criando um novo vetor para o manuseio dos bits sem alterar o vetor original
        boolean bitsCalculados[] = new boolean[bitsComZeros.length];
        //Fazendo a copia dos bits para o vetor bitsCalculados[]
        System.arraycopy(bitsComZeros, 0, bitsCalculados, 0, bitsComZeros.length); 

        //Realizando o processo do calculo do XOR
        for (int i = 0; i < bitsComZeros.length - polinomio.length + 1; i++) {
            //Verificando se o bit atual é 1
            if (bitsComZeros[i]) {
                //Inicializando uma variável k para acompanhar a posição no polinomio
                int k = 0;
                //Loop atraves dos bits do polinomio
                for (int j = i; j < (i + polinomio.length); j++) {
                    //Realizando o XOR entre o bit atual e o correspondente no polinomio
                    bitsComZeros[j] = !(bitsComZeros[j] == polinomio[k]);
                    //Incrementando k para acompanhar o próximo bit do polinomio
                    k++;
                }
            }
        }
        //Logica para a substituicao dos 0's para os bits de controle do CRC
        for (int i = (bitsComZeros.length - polinomio.length + 1); i < bitsComZeros.length; i++) {
            bitsCalculados[i] = bitsComZeros[i];
        }

        return bitsCalculados;
    }

    public void enviaDado(Receptor receptor) {
        for (int i = 0; i < this.mensagem.length(); i++) {
            boolean bits[] = streamCaracter(this.mensagem.charAt(i));
            boolean bitsCRC[] = dadoBitsCRC(bits);

            //add ruidos na mensagem a ser enviada para o receptor
            geradorRuido(bitsCRC);

            //enviando a mensagem "pela rede" para o receptor (uma forma de testarmos esse método)
            //Uma retransmissao do dado e solicitada enquanto o receptor encontrar erros e o dado nao estiver integro
            while (!receptor.receberDadoBits(bitsCRC)) {
                bitsCRC = dadoBitsCRC(bits);
                geradorRuido(bitsCRC);
            }           
        }
    }
}
