package processimageblackwhite;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;

public class ProcessImageBlackWhite {

    //Funcao para ler os pixels de uma imagem e converter para escala de cinza (Saulo)
    public static int[][] lerPixels(String caminho) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File(caminho));
            int largura = bufferedImage.getWidth();
            int altura = bufferedImage.getHeight();

            int[][] pixels = new int[largura][altura];

            for (int i = 0; i < largura; i++) {
                for (int j = 0; j < altura; j++) {

                    float vermelho = new Color(bufferedImage.getRGB(i, j)).getRed();
                    float verde = new Color(bufferedImage.getRGB(i, j)).getGreen();
                    float azul = new Color(bufferedImage.getRGB(i, j)).getBlue();

                    int escalaCinza = (int) (vermelho + verde + azul) / 3;

                    pixels[i][j] = escalaCinza;
                }
            }
            return pixels;
        } catch (IOException ex) {

            System.err.println("Erro no caminho indicado pela imagem");
        }
        return null;
    }

    //Funcao para gravar a matriz de pixels em um arquivo de imagem (Saulo)
    public static void gravarPixels(String caminhoGravar, int[][] pixels) {
        caminhoGravar = caminhoGravar
                .replace(".png", "_modificado.png")
                .replace(".jpg", "_modificado.jpg");

        int largura = pixels.length;
        int altura = pixels[0].length;

        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_GRAY);

        byte[] bytesPixels = new byte[largura * altura];

        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                bytesPixels[y * largura + x] = (byte) pixels[x][y];
            }
        }

        imagem.getRaster().setDataElements(0, 0, largura, altura, bytesPixels);

        File imageFile = new File(caminhoGravar);
        try {
            ImageIO.write(imagem, "png", imageFile);
            System.out.println("Nova Imagem disponivel em: " + caminhoGravar);
        } catch (IOException e) {

            System.err.println("Erro no caminho indicado pela imagem");
        }
    }

    //Funcao para corrigir a imagem aplicando uma Mascara
    public static int[][] corrigirImagem(int[][] imgMat) {
        int largura = imgMat.length; //Uma nova var para armazenar a largura da matriz de pixels da imagem
        int altura = imgMat[0].length; //Uma nova var para armazenar a altura da matriz de pixels da imagem
        int[][] novaImgMat = new int[largura][altura]; //Cria uma nova matriz para armazenar a imagem corrigida e evitar manipular dados na matriz original

        //Copia os valores originais para a nova matriz
        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                novaImgMat[i][j] = imgMat[i][j];
            }
        }

        //Aplica a correção na nova matriz de pixels
        for (int i = 0; i < largura; i++) {//Comeca a percorrer a largura da matriz
            for (int j = 0; j < altura; j++) {//Comeca a percorrer a altura da matriz

                // Verifica se o pixel é preto (0) ou branco (255)
                if (imgMat[i][j] == 0 || imgMat[i][j] == 255) {
                    int somaVizinhos = 0; //Uma nova var para armazenar a soma dos valores (cores) dos pixels vizinhos
                    int contadorVizinhos = 0; //Contador de pixels vizinhos para utilizar nos calculos da media
                    int contadorPretos = 0; //Contador de pixels pretos

                    //Inicializacao da mascara percorrendo os pixels vizinhos em uma matriz 3x3
                    for (int i1 = -1; i1 <= 1; i1++) {
                        for (int j1 = -1; j1 <= 1; j1++) {
                            int x = i + i1; //Posicao x do pixel vizinho
                            int y = j + j1; //Posicao y do pixel vizinho

                            //Verifica se o pixel vizinho esta dentro dos limites da imagem
                            if (x >= 0 && x < largura && y >= 0 && y < altura) {
                                //Ignorando o pixel central para melhorar a assertividade dos calculos
                                if (!(i1 == 0 && j1 == 0)) {
                                    int valorPixelVizinho = imgMat[x][y]; //Pega o valor do pixel vizinho
                                    somaVizinhos += valorPixelVizinho; //Adiciona o valor a soma
                                    contadorVizinhos++; //Incrementa o contador de vizinhos
                                    if (valorPixelVizinho == 0) {
                                        contadorPretos++; //Incrementa o contador de pixels pretos
                                    }
                                }
                            }
                        }
                    }

                    //Se a maioria dos pixels vizinhos for preto, define o pixel central como preto
                    if (contadorPretos > contadorVizinhos / 2) {
                        novaImgMat[i][j] = 0;
                    } else {
                        //Caso contrario, calcula a media das cores dos pixels vizinhos e define a nova cor para o pixel "faltante"
                        if (contadorVizinhos > 0) {
                            novaImgMat[i][j] = somaVizinhos / contadorVizinhos;
                        }
                    }
                }
            }
        }

        return novaImgMat; //Retorna a matriz de pixels corrigida
    }

    public static void main(String[] args) {
        //Cria um objeto File com o diretorio onde as imagens estao localizadas (Necessario trocar o local para rodar o codigo em sua maquina)
        File directory = new File("C:\\Users\\usuario\\Desktop\\projeto e arquivos para o problema de imagens\\Imagens\\modificadas");
        //Obtem uma lista de arquivos de imagem no diretorio
        File[] imageFiles = directory.listFiles();

        //Verifica se foram encontrados arquivos
        if (imageFiles == null) {
            System.err.println("Nenhum arquivo encontrado no diretorio.");
            return;
        }

        //Obtem o numero de threads disponiveis
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        //Cria um pool de threads com o numero de threads encontrados, utilizando a lib java.util.concurrent (Executor e ExecutorService)
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        try {
            //Envia uma tarefa para cada arquivo de imagem no pool de threads
            for (File imgFile : imageFiles) {
                executorService.submit(() -> {
                    if (imgFile.exists()) { // Verifica se o arquivo existe
                        int[][] imgMat = lerPixels(imgFile.getAbsolutePath()); //Le os pixels da imagem usando a funcao do Saulo
                        if (imgMat != null) {
                            imgMat = corrigirImagem(imgMat); //Corrige a imagem aplicando a funcao com a "mascara"
                            gravarPixels(imgFile.getAbsolutePath(), imgMat); //Grava a imagem corrigida usando a funcao do Saulo
                        }
                    } else {
                        System.err.println("Arquivo de imagem nao encontrado: " + imgFile.getAbsolutePath());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown(); //Encerra o pool de threads inicializado anteriormente (ExecutorService)

            try {
                //Aguarda a conclusao de todas as tarefas ou forca o encerramento se o tempo limite for atingido
                if (!executorService.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow(); //Forca o encerramento em caso de erro
            }
        }
    }
}
//Codigo desenvolvido por Joao Victor Dutra e Felipe Leal
