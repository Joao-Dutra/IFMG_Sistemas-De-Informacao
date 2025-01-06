package escalonamento;

public class Processo implements Runnable {
    private final int id; //identificador unico do processo
    private final int tempoChegada; //tempo em que o processo chega na fila de prontos
    private int duracao; //duracao atual do processo
    private final int duracaoOriginal; //duracao inicial do processo (inalterada)
    private boolean finalizado; //indica se o processo foi concluido

    //construtor para inicializar os atributos do processo
    public Processo(int id, int tempoChegada, int duracao) {
        this.id = id; //inicializa o identificador unico do processo
        this.tempoChegada = tempoChegada; //define o tempo de chegada
        this.duracao = duracao; //define a duracao inicial
        this.duracaoOriginal = duracao; //armazena a duracao inicial para referencia futura
        this.finalizado = false; //inicialmente, o processo nao esta finalizado
    }

    //construtor de copia para criar uma copia do processo
    public Processo(Processo outro) {
        this.id = outro.id; //copia o identificador unico
        this.tempoChegada = outro.tempoChegada; //copia o tempo de chegada
        this.duracao = outro.duracao; //copia a duracao atual
        this.duracaoOriginal = outro.duracaoOriginal; //copia a duracao original
        this.finalizado = false; //a copia do processo nao esta finalizada inicialmente
    }

    //metodo para obter o id do processo
    public int getId() {
        return id; //retorna o identificador unico do processo
    }

    //metodo para obter a duracao atual do processo
    public synchronized int getDuracao() {
        return duracao; //retorna a duracao atual
    }

    //metodo para reduzir a duracao do processo em um valor especificado
    public synchronized void reduzirDuracao(int quantum) {
        this.duracao -= quantum; //reduz a duracao pelo valor do quantum
        if (this.duracao <= 0) {
            this.finalizado = true; //marca o processo como finalizado se a duracao for zero ou menor
            this.duracao = 0; //garante que a duracao nao seja negativa
        }
    }

    //metodo para verificar se o processo foi finalizado
    public synchronized boolean isFinalizado() {
        return finalizado; //retorna true se o processo estiver finalizado
    }

    //metodo para obter o tempo de chegada do processo
    public int getTempoChegada() {
        return tempoChegada; //retorna o tempo de chegada
    }

    //metodo para obter a duracao original do processo
    public int getDuracaoOriginal() {
        return duracaoOriginal; //retorna a duracao inicial armazenada
    }

    //metodo para redefinir a duracao para o valor original
    public void redefinirDuracao() {
        this.duracao = this.duracaoOriginal; //redefine a duracao para o valor original
        this.finalizado = false; //marca o processo como nao finalizado
    }

    //metodo para executar o processo em uma thread (simula a execucao)
    @Override
    public void run() {
        try {
            while (!isFinalizado()) { //enquanto o processo nao estiver finalizado
                Thread.sleep(100); //simula o tempo de execucao do processo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); //interrompe a thread em caso de excecao
        }
    }

    //metodo para representar o processo como uma string legivel
    @Override
    public String toString() {
        return "Processo " + id + " - Chegada: " + tempoChegada + " - Duracao Atual: " + duracao +
                " (Original: " + duracaoOriginal + ")"; //retorna uma representacao legivel do processo
    }
}
