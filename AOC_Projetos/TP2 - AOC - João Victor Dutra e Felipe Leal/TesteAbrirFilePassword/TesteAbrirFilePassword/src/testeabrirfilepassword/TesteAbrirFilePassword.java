package testeabrirfilepassword;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

public class TesteAbrirFilePassword {

    //Caminho absoluto da pasta onde os arquivos estao localizados
    private static final String CAMINHO = "C:\\Users\\usuario\\Desktop\\senha\\arquivosTP\\";
    
    //Lista com os caminhos dos arquivos ZIP que serao testados
    private static final String[] arquivos_zip = {
        CAMINHO + "doc1.zip",
        CAMINHO + "doc2.zip",
        CAMINHO + "doc3.zip",
        CAMINHO + "doc4.zip",
    };
    
    //Caminho do arquivo ZIP final que sera extraido apos encontrar as senhas dos arquivos anteriores
    private static final String arquivo_final = CAMINHO + "final.zip";
    
    //Valores dos caracteres ASCII que serao utilizados para gerar as senhas (32 a 127)
    private static final int inicio_ascii = 32; // 32 e o codigo ASCII de '[SPACE]'
    private static final int fim_ascii = 127; // 127 e o codigo ASCII de 'DEL'
    
    //Criacao de uma lista para armazenar as senhas encontradas para os arquivos ZIP
    private static final List<String> senhas_encontradas = new ArrayList<>();
    
    //Criacao de uma lista de controladores (flags) indicando se a senha de um arquivo ZIP foi encontrada
    private static final List<Boolean> senha_encontrada = new ArrayList<>();

    public static void main(String[] args) {
        //Inicializacao da cronometragem para apuracao do desempenho do codigo
        long inicioTempo = System.currentTimeMillis();
        
        //Cria um pool de threads com o numero de threads encontrados, utilizando a lib java.util.concurrent (Executor e ExecutorService)
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        
        //Inicializacao de uma lista de controle de senhas encontradas com valores 'false'
        for (int i = 0; i < arquivos_zip.length; i++) {
            senha_encontrada.add(false);
        }
        
        //Chamando o metodo que ira comecar o processo de quebra de senha por forca bruta
        forcaBrutaAgoraVai(executor);
        
        //Chamando o metodo que ira manter o controle de sincronizacao das threads (para nao encerrar o programa enquanto alguma thread ainda esteja executando)
        encerramentoDaPoolDeThreads(executor);

        //Chamando a funcao final para extrair o arquivo final
        extrairArquivoFinal();
        
        //Finalizacao do cronometro
        long fimTempo = System.currentTimeMillis();
        System.out.println("Tempo total: " + (fimTempo - inicioTempo) + " ms");
    }
    
    //Funcao de forca bruta que gera todas as possiveis combinacoes de senhas com o uso da pool de threads criada anteriormente
    private static void forcaBrutaAgoraVai(ExecutorService executor) {
        //Itera sobre cada arquivo ZIP
        for (int index = 0; index < arquivos_zip.length; index++) {
            final int arquivoIndex = index; 

            //Gera todas as combinacoes de tres caracteres ASCII
            for (int i = inicio_ascii; i <= fim_ascii; i++) {
                for (int j = inicio_ascii; j <= fim_ascii; j++) {
                    for (int k = inicio_ascii; k <= fim_ascii; k++) {
                        final char caractere1 = (char) i;
                        final char caractere2 = (char) j;
                        final char caractere3 = (char) k;

                        String senha = "" + caractere1 + caractere2 + caractere3;

                        //Submete uma tarefa ao pool de threads para testar a senha no arquivo ZIP
                        executor.submit(() -> tentarSenha(arquivoIndex, senha));
                    }
                }
            }
        }
    }
    
    //Funcao que aguarda a conclusao de todas as tarefas enviadas para as threads para nao ocorrer problemas de concorrencia
    private static void encerramentoDaPoolDeThreads(ExecutorService executor) {
        executor.shutdown();
        try {
            //Espera ate 1 hora para todas as tarefas terminarem
            if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
                executor.shutdownNow(); //Forca o encerramento das tarefas caso o tempo limite seja atingido
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
    
    //Funcao que ira testar as senhas nos arquivos (Saulo) porem com algumas pequenas modificacoes comentadas
    private static void tentarSenha(int arquivoIndex, String senha) {
        //Verifica se a senha ja foi encontrada para o arquivo especifico
        if (senha_encontrada.get(arquivoIndex)) {
            return;
        }

        //Pega o caminho do arquivo ZIP a ser testado
        String arquivoZip = arquivos_zip[arquivoIndex];
        
        ZipFile zipFile = new ZipFile(new File(arquivoZip));
        try {
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(senha.toCharArray());
            }
            
            List<FileHeader> fileHeaderList = zipFile.getFileHeaders();
            for (FileHeader fileHeader : fileHeaderList) {
                zipFile.extractFile(fileHeader, CAMINHO);
                synchronized (senhas_encontradas) {
                    //Se a senha estiver certa adiciona na lista de senhas encontradas e marca como encontrada
                    if (!senhas_encontradas.contains(senha)) {
                        senhas_encontradas.add(senha);
                        senha_encontrada.set(arquivoIndex, true);
                        System.out.println("Senha encontrada para " + arquivoZip + ": " + senha);
                    }
                }
                return; //Sai do metodo apos encontrar a senha
            }
        } catch (ZipException ex) {
        }
    }
    
    //Extrai o arquivo final usando as senhas encontradas
    private static void extrairArquivoFinal() {
        ZipFile zipFile = new ZipFile(new File(arquivo_final));
        
        //Verifica se todas as senhas foram encontradas
        if (senhas_encontradas.size() == arquivos_zip.length) {
            //Concatena todas as senhas em uma unica string
            StringBuilder senhaFinalBuilder = new StringBuilder();
            for (String senha : senhas_encontradas) {
                senhaFinalBuilder.append(senha);
            }
            String senhaFinal = senhaFinalBuilder.toString();
            
            try {
                if (zipFile.isEncrypted()) {
                    zipFile.setPassword(senhaFinal.toCharArray());
                }
                
                List<FileHeader> fileHeaderList = zipFile.getFileHeaders();
                for (FileHeader fileHeader : fileHeaderList) {
                    zipFile.extractFile(fileHeader, CAMINHO);
                    System.out.println("Arquivo final.zip extraido com a senha: " + senhaFinal);
                    return; //Sai do metodo apos extrair o arquivo final
                }
            } catch (ZipException ex) {
                System.out.println("Erro ao extrair o arquivo final com a senha combinada: " + senhaFinal);
            }
        } else {
            System.out.println("Nem todas as senhas foram encontradas.");
        }
    }
}
//Codigo desenvolvido por Joao Victor Dutra e Felipe Leal
