package trabalho.aeds1;

import java.util.Random;

public class TesteAlgoritmosOrdenacao {

    public static void main(String[] args) {
        int[] tamanhos = {100, 1000, 100000, 500000};

        for (int tamanho : tamanhos) {
            int[] arrAleatorio = gerarArrayAleatorio(tamanho);
            int[] arrCrescente = gerarArrayCrescente(tamanho);
            int[] arrDecrescente = gerarArrayDecrescente(tamanho);

            System.out.println("Testando com tamanho do vetor: " + tamanho);

            testarOrdenacao("BubbleSort (Aleatório)", arrAleatorio.clone());
            testarOrdenacao("SelectionSort (Aleatório)", arrAleatorio.clone());
            testarOrdenacao("InsertionSort (Aleatório)", arrAleatorio.clone());
            testarOrdenacao("MergeSort (Aleatório)", arrAleatorio.clone());
            testarOrdenacao("QuickSort (Aleatório)", arrAleatorio.clone());

            testarOrdenacao("BubbleSort (Crescente)", arrCrescente.clone());
            testarOrdenacao("SelectionSort (Crescente)", arrCrescente.clone());
            testarOrdenacao("InsertionSort (Crescente)", arrCrescente.clone());
            testarOrdenacao("MergeSort (Crescente)", arrCrescente.clone());
            testarOrdenacao("QuickSort (Crescente)", arrCrescente.clone());

            testarOrdenacao("BubbleSort (Decrescente)", arrDecrescente.clone());
            testarOrdenacao("SelectionSort (Decrescente)", arrDecrescente.clone());
            testarOrdenacao("InsertionSort (Decrescente)", arrDecrescente.clone());
            testarOrdenacao("MergeSort (Decrescente)", arrDecrescente.clone());
            testarOrdenacao("QuickSort (Decrescente)", arrDecrescente.clone());

            System.out.println();
        }
    }

    public static void testarOrdenacao(String algoritmo, int[] arr) {
        long tempoInicial = System.nanoTime();
        switch (algoritmo) {
            case "BubbleSort (Aleatório)":
                bubbleSort(arr, arr.length);
                break;
            case "SelectionSort (Aleatório)":
                selectionSort(arr, arr.length);
                break;
            case "InsertionSort (Aleatório)":
                insertionSort(arr, arr.length);
                break;
            case "MergeSort (Aleatório)":
                mergeSort(arr, 0, arr.length - 1);
                break;
            case "QuickSort (Aleatório)":
                quickSort(arr, 0, arr.length - 1);
                break;
            case "BubbleSort (Crescente)":
                bubbleSort(arr, arr.length);
                break;
            case "SelectionSort (Crescente)":
                selectionSort(arr, arr.length);
                break;
            case "InsertionSort (Crescente)":
                insertionSort(arr, arr.length);
                break;
            case "MergeSort (Crescente)":
                mergeSort(arr, 0, arr.length - 1);
                break;
            case "QuickSort (Crescente)":
                quickSort(arr, 0, arr.length - 1);
                break;
            case "BubbleSort (Decrescente)":
                bubbleSort(arr, arr.length);
                break;
            case "SelectionSort (Decrescente)":
                selectionSort(arr, arr.length);
                break;
            case "InsertionSort (Decrescente)":
                insertionSort(arr, arr.length);
                break;
            case "MergeSort (Decrescente)":
                mergeSort(arr, 0, arr.length - 1);
                break;
            case "QuickSort (Decrescente)":
                quickSort(arr, 0, arr.length - 1);
                break;
        }
        long tempoFinal = System.nanoTime();
        System.out.println(algoritmo + " levou " + (tempoFinal - tempoInicial) + " nanossegundos");
    }

    public static int[] gerarArrayAleatorio(int tamanho) {
        int[] arr = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

    public static int[] gerarArrayCrescente(int tamanho) {
        int[] arr = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static int[] gerarArrayDecrescente(int tamanho) {
        int[] arr = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            arr[i] = tamanho - i;
        }
        return arr;
    }

    public static void selectionSort(int arr[], int size) {
        for (int i = 0; i < size - 1; i++) {
            int menorElemento = i;
            for (int j = i + 1; j < size; j++) {
                if (arr[j] < arr[menorElemento]) {
                    menorElemento = j;
                }
            }
            int swap = arr[i];
            arr[i] = arr[menorElemento];
            arr[menorElemento] = swap;
        }
    }

    public static void insertionSort(int arr[], int size) {
        int temp, j;
        for (int i = 1; i < size; i++) {
            temp = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = temp;
        }
    }

    public static void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public static void merge(int arr[], int l, int m, int r) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void quickSort(int arr[], int low, int high) {
        while (low < high) {
            // Escolha o elemento do meio como o pivot
            int middle = low + (high - low) / 2;
            int pivot = arr[middle];

            // Coloque o pivot no final
            swap(arr, middle, high);

            int pivotIndex = partition(arr, low, high);

            if (pivotIndex - low < high - pivotIndex) {
                quickSort(arr, low, pivotIndex - 1);
                low = pivotIndex + 1;
            } else {
                quickSort(arr, pivotIndex + 1, high);
                high = pivotIndex - 1;
            }
        }
    }

    public static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void bubbleSort(int arr[], int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
