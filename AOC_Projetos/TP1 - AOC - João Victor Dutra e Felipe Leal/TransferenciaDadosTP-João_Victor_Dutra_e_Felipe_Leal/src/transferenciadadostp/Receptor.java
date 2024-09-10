package transferenciadadostp;

public class Receptor {

    //mensagem recebida pelo transmissor
    private String mensagem;

    public Receptor() {
        //mensagem vazia no inicio da execução
        this.mensagem = "";
    }

    public String getMensagem() {
        return mensagem;
    }

    private boolean decodificarDado(boolean bits[]) {
        int codigoAscii = 0;
        int expoente = bits.length - 1;

        //converntendo os "bits" para valor inteiro para então encontrar o valor tabela ASCII
        for (int i = 0; i < bits.length; i++) {
            if (bits[i]) {
                codigoAscii += Math.pow(2, expoente);
            }
            expoente--;
        }

        //concatenando cada simbolo na mensagem original
        this.mensagem += (char) codigoAscii;

        //esse retorno precisa ser pensado... será que o dado sempre chega sem ruído???
        return true;
    }

    private boolean decodificarDadoCRC(boolean[] bits) {
        //Polinomio gerador 1 1 0 0 1
        boolean[] polinomio = {true, true, false, false, true};
        //Criando um array para armazenar os bits finais do processo sem os bits de controle
        boolean[] bitsDado = new boolean[8];
        //Realizando a copia dos 8 primeiros bits do dado original para o vetor dos bits finais
        for (int i = 0; i < bits.length - polinomio.length + 1; i++) {
            bitsDado[i] = bits[i];
        }
        //Realizando o processo do calculo do XOR agora no receptor
        for (int i = 0; i < bits.length - polinomio.length + 1; i++) {
            //Verificando se o bit atual é 1
            if (bits[i] == true) {
                //Inicializando uma variável k para acompanhar a posição no polinomio
                int k = 0;
                //Loop atraves dos bits do polinômio
                for (int j = i; j < i + 5; j++) {
                    //Realizando o XOR entre o bit atual e o correspondente no polinomio
                    bits[j] = !(bits[j] == polinomio[k]);
                    //Incrementando k para acompanhar o próximo bit do polinomio
                    k++;
                }

            }

        }
        
        boolean indicadorCRC = false;
        //Verificando se o codigo CRC no final deu 0 0 0 0 (Integro)
        for (int i = bits.length - polinomio.length + 1; i < bits.length; i++) {
            if (bits[i] == true) {
                indicadorCRC = true;
                break;
            }
        }
        //Se o dado final estiver integro, o dado sera decodificado
        if (indicadorCRC == false) {
            return decodificarDado(bitsDado);
        } else {
            //Caso o dado final nao estiver integro, o indicador retornara o valor false, na qual ira solicitar uma retransmissao
            return false;
        }
        
    }

    //recebe os dados do transmissor
    public boolean receberDadoBits(boolean bits[]) {

        //aqui você deve trocar o médodo decofificarDado para decoficarDadoCRC (implemente!!)
        return decodificarDadoCRC(bits);
    }
}
