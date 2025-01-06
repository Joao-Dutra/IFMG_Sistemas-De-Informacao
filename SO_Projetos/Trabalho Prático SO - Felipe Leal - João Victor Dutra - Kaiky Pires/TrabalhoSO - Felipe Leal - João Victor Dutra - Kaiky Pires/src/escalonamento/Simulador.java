package escalonamento;

import org.jfree.data.category.DefaultCategoryDataset;
import java.util.*;
import java.util.concurrent.*;

public class Simulador {
    private final int numProcessadores; // numero de processadores disponiveis na simulacao
    private final int quantum; // quantum de tempo utilizado no algoritmo Round-Robin
    private final List<Processo> processos; // lista de processos a serem simulados
    private double turnaroundTimeRR; // tempo medio de turnaround no Round-Robin
    private double waitingTimeRR; // tempo medio de espera no Round-Robin
    private int contextSwitchesRR; // numero de trocas de contexto no Round-Robin
    private double turnaroundTimeSJF; // tempo medio de turnaround no Shortest Job First (SJF)
    private double waitingTimeSJF; // tempo medio de espera no SJF

    // construtor para inicializar o simulador com parametros especificos
    public Simulador(int numProcessadores, int quantum, List<Processo> processosPersonalizados, int maxProcessos) {
        this.numProcessadores = numProcessadores; // inicializa o numero de processadores
        this.quantum = quantum; // inicializa o quantum de tempo
        if (processosPersonalizados != null && !processosPersonalizados.isEmpty()) {
            this.processos = processosPersonalizados; // utiliza processos personalizados se fornecidos
        } else {
            this.processos = gerarProcessos(maxProcessos); // gera processos aleatorios caso nao sejam fornecidos
        }
    }

    // metodo para gerar uma lista de processos aleatorios
    private List<Processo> gerarProcessos(int quantidade) {
        List<Processo> lista = new ArrayList<>(); // cria uma nova lista de processos
        Random random = new Random(); // instancia o gerador de numeros aleatorios

        for (int i = 0; i < quantidade; i++) {
            int tempoChegada = random.nextInt(10); // gera um tempo de chegada aleatorio
            int duracao = random.nextInt(10) + 1; // gera uma duracao aleatoria (de 1 a 10)
            lista.add(new Processo(i + 1, tempoChegada, duracao)); // adiciona o processo gerado na lista
        }

        return lista; // retorna a lista de processos gerados
    }

    // metodo principal para executar a simulacao
    public String executar() {
        StringBuilder resultado = new StringBuilder(); // usado para construir o resultado da simulacao
        resultado.append("=== Simulacao de Escalonamento ===\n");
        resultado.append("Numero de processadores: ").append(numProcessadores).append("\n"); // exibe o numero de
                                                                                             // processadores
        resultado.append("Quantum para Round-Robin: ").append(quantum).append(" unidades\n"); // exibe o quantum
        resultado.append("\n");

        resultado.append("=== Lista de Processos ===\n");
        for (Processo p : processos) {
            resultado.append(String.format("ID: %d | Chegada: %d | Duracao Atual: %d\n",
                    p.getId(), p.getTempoChegada(), p.getDuracao())); // exibe os detalhes de cada processo
        }
        resultado.append("\n");

        resultado.append("=== Execucao Round-Robin ===\n");
        executarRoundRobin(resultado, copiarListaProcessos()); // executa o algoritmo Round-Robin
        resultado.append("\n");

        resultado.append("=== Execucao Shortest Job First (SJF) ===\n");
        executarSJF(resultado, copiarListaProcessos()); // executa o algoritmo SJF
        resultado.append("\n");

        resultado.append("=== Resumo ===\n");
        resultado.append(String.format(
                "Round-Robin -> Tempo Medio de Turnaround: %.2f | Tempo Medio de Espera: %.2f | Trocas de Contexto: %d\n",
                turnaroundTimeRR, waitingTimeRR, contextSwitchesRR)); // exibe os resultados do Round-Robin
        resultado.append(String.format("SJF         -> Tempo Medio de Turnaround: %.2f | Tempo Medio de Espera: %.2f\n",
                turnaroundTimeSJF, waitingTimeSJF)); // exibe os resultados do SJF
        resultado.append("\n");

        return resultado.toString(); // retorna o resultado completo da simulacao
    }

    private void executarRoundRobin(StringBuilder resultado, List<Processo> processosRR) {
        // verifica se o escalonamento Round-Robin sera mono ou multiprocessador
        if (numProcessadores == 1) {
            executarRoundRobinMono(resultado, processosRR); // chama o metodo para execucao mono-processador
        } else {
            executarRoundRobinMulti(resultado, processosRR); // chama o metodo para execucao multi-processador
        }
    }

    private void executarRoundRobinMono(StringBuilder resultado, List<Processo> processosRR) {
        Queue<Processo> filaProntos = new LinkedBlockingQueue<>(); // fila de processos prontos para execucao
        processosRR.sort(Comparator.comparingInt(Processo::getTempoChegada)); // ordena os processos pelo tempo de
                                                                              // chegada

        int tempoAtual = 0; // tempo atual da simulacao
        int totalTurnaroundTime = 0; // soma total do tempo de turnaround
        int totalWaitTime = 0; // soma total do tempo de espera
        int indiceProcesso = 0; // indice para percorrer os processos

        while (!filaProntos.isEmpty() || indiceProcesso < processosRR.size()) {
            // adiciona processos a fila de prontos conforme o tempo de chegada
            while (indiceProcesso < processosRR.size()
                    && processosRR.get(indiceProcesso).getTempoChegada() <= tempoAtual) {
                filaProntos.add(processosRR.get(indiceProcesso));
                indiceProcesso++;
            }

            // avanca o tempo se nao houver processos prontos
            if (filaProntos.isEmpty() && indiceProcesso < processosRR.size()) {
                tempoAtual = processosRR.get(indiceProcesso).getTempoChegada();
                continue;
            }

            Processo processoAtual = filaProntos.poll(); // pega o primeiro processo da fila
            int tempoExecucao = Math.min(processoAtual.getDuracao(), quantum); // calcula o tempo de execucao do
                                                                               // processo
            resultado.append("Tempo ").append(tempoAtual).append(": Processo ").append(processoAtual.getId())
                    .append(" executando por ").append(tempoExecucao).append(" unidades.\n");
            processoAtual.reduzirDuracao(tempoExecucao); // reduz a duracao do processo
            tempoAtual += tempoExecucao; // atualiza o tempo atual

            // adiciona novos processos a fila de prontos
            while (indiceProcesso < processosRR.size()
                    && processosRR.get(indiceProcesso).getTempoChegada() <= tempoAtual) {
                filaProntos.add(processosRR.get(indiceProcesso));
                indiceProcesso++;
            }

            if (processoAtual.getDuracao() > 0) {
                filaProntos.add(processoAtual); // reinsere o processo na fila se nao finalizado
            } else {
                int turnaround = tempoAtual - processoAtual.getTempoChegada(); // calcula o tempo de turnaround
                int waiting = turnaround - processoAtual.getDuracaoOriginal(); // calcula o tempo de espera
                totalTurnaroundTime += turnaround;
                totalWaitTime += Math.max(waiting, 0); // acumula o tempo de espera positivo
                resultado.append("Processo ").append(processoAtual.getId()).append(" finalizado no tempo ")
                        .append(tempoAtual).append(".\n");
            }

            // incrementa o contador de trocas de contexto se houver troca de processo
            if (!filaProntos.isEmpty()) {
                contextSwitchesRR++;
            }
        }

        turnaroundTimeRR = (double) totalTurnaroundTime / processosRR.size(); // calcula o tempo medio de turnaround
        waitingTimeRR = (double) totalWaitTime / processosRR.size(); // calcula o tempo medio de espera
    }

    private void executarRoundRobinMulti(StringBuilder resultado, List<Processo> processosRR) {
        Queue<Processo> filaProntos = new LinkedBlockingQueue<>(); // fila de processos prontos para execucao
        processosRR.sort(Comparator.comparingInt(Processo::getTempoChegada)); // ordena os processos pelo tempo de
                                                                              // chegada

        int tempoAtual = 0; // tempo atual da simulacao
        int totalTurnaroundTime = 0; // soma total do tempo de turnaround
        int totalWaitTime = 0; // soma total do tempo de espera
        int indiceProcesso = 0; // indice para percorrer os processos

        ExecutorService threadPool = Executors.newFixedThreadPool(numProcessadores); // cria um pool de threads com o
                                                                                     // numero de processadores
        Processo ultimoProcessoExecutado = null; // referencia para o ultimo processo executado

        while (!filaProntos.isEmpty() || indiceProcesso < processosRR.size()) {
            // adiciona processos a fila de prontos conforme o tempo de chegada
            while (indiceProcesso < processosRR.size()
                    && processosRR.get(indiceProcesso).getTempoChegada() <= tempoAtual) {
                filaProntos.add(processosRR.get(indiceProcesso));
                indiceProcesso++;
            }

            // avanca o tempo se nao houver processos prontos
            if (filaProntos.isEmpty() && indiceProcesso < processosRR.size()) {
                tempoAtual = processosRR.get(indiceProcesso).getTempoChegada();
                continue;
            }

            List<Processo> processosParaExecutar = new ArrayList<>(); // lista de processos para execucao
            for (int i = 0; i < numProcessadores && !filaProntos.isEmpty(); i++) {
                processosParaExecutar.add(filaProntos.poll()); // retira processos da fila para execucao
            }

            List<Future<?>> futuros = new ArrayList<>(); // lista de tarefas futuras

            for (Processo processoAtual : processosParaExecutar) {
                int finalTempoAtual = tempoAtual; // copia do tempo atual para uso na lambda

                if (ultimoProcessoExecutado != null && ultimoProcessoExecutado != processoAtual) {
                    contextSwitchesRR++; // incrementa o contador de trocas de contexto
                }

                ultimoProcessoExecutado = processoAtual; // atualiza o ultimo processo executado

                Future<?> futuro = threadPool.submit(() -> {
                    int tempoExecucao = Math.min(processoAtual.getDuracao(), quantum); // calcula o tempo de execucao do
                                                                                       // processo

                    synchronized (resultado) {
                        resultado.append("Tempo ").append(finalTempoAtual).append(": Processo ")
                                .append(processoAtual.getId()).append(" executando por ")
                                .append(tempoExecucao).append(" unidades.\n"); // registra a execucao no resultado
                    }

                    processoAtual.reduzirDuracao(tempoExecucao); // reduz a duracao do processo
                });

                futuros.add(futuro); // adiciona a tarefa futura na lista
            }

            for (Future<?> futuro : futuros) {
                try {
                    futuro.get(); // aguarda a conclusao de todas as tarefas
                } catch (InterruptedException | ExecutionException e) {
                    Thread.currentThread().interrupt(); // interrompe a thread em caso de erro
                }
            }

            tempoAtual += quantum; // incrementa o tempo atual pelo quantum

            // adiciona novos processos a fila de prontos
            while (indiceProcesso < processosRR.size()
                    && processosRR.get(indiceProcesso).getTempoChegada() <= tempoAtual) {
                filaProntos.add(processosRR.get(indiceProcesso));
                indiceProcesso++;
            }

            for (Processo processoAtual : processosParaExecutar) {
                if (processoAtual.getDuracao() > 0) {
                    filaProntos.add(processoAtual); // reinsere o processo na fila se nao finalizado
                } else {
                    int turnaround = tempoAtual - processoAtual.getTempoChegada(); // calcula o tempo de turnaround
                    int waiting = turnaround - processoAtual.getDuracaoOriginal(); // calcula o tempo de espera
                    totalTurnaroundTime += turnaround;
                    totalWaitTime += Math.max(waiting, 0); // acumula o tempo de espera positivo

                    synchronized (resultado) {
                        resultado.append("Processo ").append(processoAtual.getId()).append(" finalizado no tempo ")
                                .append(tempoAtual).append(".\n"); // registra a finalizacao no resultado
                    }
                }
            }
        }

        threadPool.shutdown(); // encerra o pool de threads

        turnaroundTimeRR = (double) totalTurnaroundTime / processosRR.size(); // calcula o tempo medio de turnaround
        waitingTimeRR = (double) totalWaitTime / processosRR.size(); // calcula o tempo medio de espera
    }

    private void executarSJF(StringBuilder resultado, List<Processo> processosSJF) {
        // verifica o numero de processadores e chama o metodo adequado para execucao
        // SJF
        if (numProcessadores == 1) {
            executarSJFMono(resultado, processosSJF);
        } else {
            executarSJFMulti(resultado, processosSJF);
        }
    }

    private void executarSJFMono(StringBuilder resultado, List<Processo> processosSJF) {
        // ordena os processos pela ordem de chegada
        processosSJF.sort(Comparator.comparingInt(Processo::getTempoChegada));

        // cria a fila de processos prontos para execucao
        List<Processo> filaProntos = new ArrayList<>();
        // inicializa o tempo atual em 0
        int tempoAtual = 0;
        // variavel para acumular o turnaround time total
        int totalTurnaroundTime = 0;
        // variavel para acumular o wait time total
        int totalWaitTime = 0;

        // repete enquanto houver processos com duracao maior que 0
        while (processosSJF.stream().anyMatch(p -> p.getDuracao() > 0)) {
            // adiciona processos que chegaram ao tempo atual na fila de prontos
            for (Processo processo : processosSJF) {
                if (processo.getTempoChegada() <= tempoAtual && !filaProntos.contains(processo)
                        && processo.getDuracao() > 0) {
                    filaProntos.add(processo);
                }
            }

            // ordena os processos prontos pela duracao mais curta
            filaProntos.sort(Comparator.comparingInt(Processo::getDuracao));

            if (!filaProntos.isEmpty()) {
                // seleciona o primeiro processo da fila para execucao
                Processo processoAtual = filaProntos.remove(0);

                // pega a duracao do processo atual
                int tempoExecucao = processoAtual.getDuracao();
                // adiciona informacao sobre a execucao do processo ao resultado
                resultado.append("Tempo ").append(tempoAtual).append(": Processo ").append(processoAtual.getId())
                        .append(" executando por ").append(tempoExecucao).append(" unidades.\n");

                // atualiza o tempo atual com a duracao do processo
                tempoAtual += tempoExecucao;

                // calcula o turnaround time do processo atual
                int turnaroundTime = tempoAtual - processoAtual.getTempoChegada();
                // calcula o wait time do processo atual
                int waitTime = turnaroundTime - processoAtual.getDuracaoOriginal();

                // acumula os tempos calculados
                totalTurnaroundTime += turnaroundTime;
                totalWaitTime += waitTime;

                // reduz a duracao do processo atual para simular que ele foi executado
                processoAtual.reduzirDuracao(tempoExecucao);
                // adiciona informacao sobre a finalizacao do processo ao resultado
                resultado.append("Processo ").append(processoAtual.getId()).append(" finalizado no tempo ")
                        .append(tempoAtual).append(".\n");
            } else {
                // incrementa o tempo atual se nenhum processo estiver pronto
                tempoAtual++;
            }
        }

        // calcula os tempos medios de turnaround e espera
        turnaroundTimeSJF = (double) totalTurnaroundTime / processosSJF.size();
        waitingTimeSJF = (double) totalWaitTime / processosSJF.size();
    }

    private void executarSJFMulti(StringBuilder resultado, List<Processo> processosSJF) {
        // ordena os processos pela ordem de chegada
        processosSJF.sort(Comparator.comparingInt(Processo::getTempoChegada));

        // cria a fila de processos prontos para execucao
        List<Processo> filaProntos = new ArrayList<>();
        // inicializa o tempo atual em 0
        int tempoAtual = 0;
        // variavel para acumular o turnaround time total
        int totalTurnaroundTime = 0;
        // variavel para acumular o wait time total
        int totalWaitTime = 0;

        // cria um pool de threads para execucao em paralelo
        ExecutorService threadPool = Executors.newFixedThreadPool(numProcessadores);

        // repete enquanto houver processos com duracao maior que 0
        while (processosSJF.stream().anyMatch(p -> p.getDuracao() > 0)) {
            // adiciona processos que chegaram ao tempo atual na fila de prontos
            for (Processo processo : processosSJF) {
                if (processo.getTempoChegada() <= tempoAtual && !filaProntos.contains(processo)
                        && processo.getDuracao() > 0) {
                    filaProntos.add(processo);
                }
            }

            // ordena os processos prontos pela duracao mais curta
            filaProntos.sort(Comparator.comparingInt(Processo::getDuracao));

            // seleciona processos para execucao de acordo com o numero de processadores
            List<Processo> processosParaExecutar = new ArrayList<>();
            for (int i = 0; i < numProcessadores && !filaProntos.isEmpty(); i++) {
                processosParaExecutar.add(filaProntos.remove(0));
            }

            // lista de futuros para acompanhar as execucoes paralelas
            List<Future<Integer>> futuros = new ArrayList<>();

            for (Processo processoAtual : processosParaExecutar) {
                int finalTempoAtual = tempoAtual;

                // submete o processo para execucao em uma thread separada
                Future<Integer> futuro = threadPool.submit(() -> {
                    int tempoExecucao = processoAtual.getDuracao();

                    synchronized (resultado) {
                        resultado.append("Tempo ").append(finalTempoAtual).append(": Processo ")
                                .append(processoAtual.getId()).append(" executando por ")
                                .append(tempoExecucao).append(" unidades.\n");
                    }

                    // simula o termino do processo e retorna o tempo de execucao
                    processoAtual.reduzirDuracao(tempoExecucao);
                    return tempoExecucao;
                });

                futuros.add(futuro);
            }

            // aguarda todas as threads terminarem e atualiza o tempo global
            int maiorTempoExecucao = 0;
            for (Future<Integer> futuro : futuros) {
                try {
                    maiorTempoExecucao = Math.max(maiorTempoExecucao, futuro.get());
                } catch (InterruptedException | ExecutionException e) {
                    Thread.currentThread().interrupt();
                }
            }

            tempoAtual += maiorTempoExecucao;

            // calcula turnaround e wait time para processos finalizados
            for (Processo processoAtual : processosParaExecutar) {
                if (processoAtual.getDuracao() == 0) {
                    int turnaroundTime = tempoAtual - processoAtual.getTempoChegada();
                    int waitTime = turnaroundTime - processoAtual.getDuracaoOriginal();
                    totalTurnaroundTime += turnaroundTime;
                    totalWaitTime += waitTime;

                    synchronized (resultado) {
                        resultado.append("Processo ").append(processoAtual.getId()).append(" finalizado no tempo ")
                                .append(tempoAtual).append(".\n");
                    }
                }
            }
        }

        threadPool.shutdown();

        // calcula as metricas finais
        turnaroundTimeSJF = (double) totalTurnaroundTime / processosSJF.size();
        waitingTimeSJF = (double) totalWaitTime / processosSJF.size();
    }

    private List<Processo> copiarListaProcessos() {
        //cria uma nova lista para armazenar a copia dos processos
        List<Processo> copia = new ArrayList<>();
        //itera sobre todos os processos na lista original
        for (Processo p : processos) {
            //adiciona uma copia de cada processo na nova lista
            copia.add(new Processo(p));
        }
        //retorna a nova lista com os processos copiados
        return copia;
    }
    
    public DefaultCategoryDataset getDataset() {
        //cria um dataset para armazenar os dados de desempenho
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //adiciona o tempo de turnaround do Round-Robin ao dataset
        dataset.addValue(turnaroundTimeRR, "Round-Robin", "Tempo de Execução");
        //adiciona o tempo de espera do Round-Robin ao dataset
        dataset.addValue(waitingTimeRR, "Round-Robin", "Tempo de Espera");
        //adiciona o numero de trocas de contexto do Round-Robin ao dataset
        dataset.addValue(contextSwitchesRR, "Round-Robin", "Trocas de Contexto");
        //adiciona o tempo de turnaround do SJF ao dataset
        dataset.addValue(turnaroundTimeSJF, "SJF", "Tempo de Execução");
        //adiciona o tempo de espera do SJF ao dataset
        dataset.addValue(waitingTimeSJF, "SJF", "Tempo de Espera");
        //retorna o dataset preenchido
        return dataset;
    }
    
    public double getTurnaroundTimeRR() {
        //retorna o tempo medio de turnaround do Round-Robin
        return turnaroundTimeRR;
    }
    
    public double getWaitingTimeRR() {
        //retorna o tempo medio de espera do Round-Robin
        return waitingTimeRR;
    }
    
    public int getContextSwitchesRR() {
        //retorna o numero total de trocas de contexto do Round-Robin
        return contextSwitchesRR;
    }
    
    public double getTurnaroundTimeSJF() {
        //retorna o tempo medio de turnaround do SJF
        return turnaroundTimeSJF;
    }
    
    public double getWaitingTimeSJF() {
        //retorna o tempo medio de espera do SJF
        return waitingTimeSJF;
    }
}    