package minhaarvore;

class No {

    int chave;
    No filhoEsquerdo;
    No filhoDireito;

    public No(int chave) {
        this.chave = chave;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public int getChave() {
        return this.chave;
    }

    public void setFilhoEsquerdo(No filhoEsquerdo) {
        this.filhoEsquerdo = filhoEsquerdo;
    }

    public void setFilhoDireito(No filhoDireito) {
        this.filhoDireito = filhoDireito;
    }

}

public class ArvoreBinaria {

    No raiz;

    public No getRaiz() {
        return raiz;
    }

    public ArvoreBinaria() {
        this.raiz = null;
    }

    private void inserirNo(int chave) {
        this.raiz = inserirNoRec(this.raiz, chave);

    }

    private No inserirNoRec(No noAtual, int chave) {
        if (noAtual == null) {
            return new No(chave);
        } else if (noAtual.getChave() > chave) {
            noAtual.filhoEsquerdo = inserirNoRec(noAtual.filhoEsquerdo, chave);
        } else {
            noAtual.filhoDireito = inserirNoRec(noAtual.filhoDireito, chave);
        }
        return noAtual;
    }

    private No buscaNo(int chave) {
        return buscaNoRec(this.raiz, chave);
    }

    private No buscaNoRec(No noAtual, int chave) {
        if (noAtual == null || noAtual.getChave() == chave) {
            return noAtual;
        } else if (noAtual.getChave() > chave) {
            return buscaNoRec(noAtual.filhoEsquerdo, chave);
        } else {
            return buscaNoRec(noAtual.filhoDireito, chave);
        }
    }

    private void removerNo(int chave) {
        removerNoRec(this.raiz, chave);
    }

    private No removerNoRec(No noAtual, int chave) {
        if (noAtual == null) {
            return null;
        }

        if (chave < noAtual.getChave()) {
            noAtual.filhoEsquerdo = removerNoRec(noAtual.filhoEsquerdo, chave);
        } else if (chave > noAtual.getChave()) {
            noAtual.filhoDireito = removerNoRec(noAtual.filhoDireito, chave);
        } else {
            // Caso 1: No folha (sem filhos)
            if (noAtual.filhoEsquerdo == null && noAtual.filhoDireito == null) {
                return null;
            } // Caso 2: No com apenas um filho
            else if (noAtual.filhoEsquerdo == null) {
                return noAtual.filhoDireito;
            } else if (noAtual.filhoDireito == null) {
                return noAtual.filhoEsquerdo;
            } // Caso 3: No com dois filhos
            else {
                // Encontrar o sucessor (menor valor na subarvore direita)
                No noSucessor = encontrarNoMinimo(noAtual.filhoDireito);
                // Substituir a chave do no atual pela chave do sucessor
                noAtual.setChave(noSucessor.getChave());
                // Remover o sucessor da subarvore direita
                noAtual.filhoDireito = removerNoRec(noAtual.filhoDireito, noSucessor.getChave());
            }
        }
        return noAtual;
    }

    private No encontrarNoMinimo(No noAtual) {
        while (noAtual.filhoEsquerdo != null) {
            noAtual = noAtual.filhoEsquerdo;
        }
        return noAtual;
    }

    private void imprimirArvore() {
        //Alterar ordenacao a gosto
        travessiaPreOrdem(this.raiz);
        System.out.println();
    }

    public void travessiaEmOrdem(No noAtual) {
        if (noAtual != null) {
            travessiaEmOrdem(noAtual.filhoEsquerdo);
            System.out.print(noAtual.getChave() + " ");
            travessiaEmOrdem(noAtual.filhoDireito);
        }
    }

    public void travessiaPreOrdem(No noAtual) {
        if (noAtual != null) {
            System.out.print(noAtual.getChave() + " ");
            travessiaPreOrdem(noAtual.filhoEsquerdo);
            travessiaPreOrdem(noAtual.filhoDireito);
        }
    }

    public void travessiaPosOrdem(No noAtual) {
        if (noAtual != null) {
            travessiaPosOrdem(noAtual.filhoEsquerdo);
            travessiaPosOrdem(noAtual.filhoDireito);
            System.out.print(noAtual.getChave() + " ");
        }
    }

    public boolean compararNoArvoreBinaria(No no1, No no2) {
        if (no1 == null && no2 == null) {
            return true;
        }

        if (no1 == null || no2 == null) {
            return false;
        }

        return (no1.getChave() == no2.getChave())
                && compararNoArvoreBinaria(no1.filhoEsquerdo, no2.filhoEsquerdo)
                && compararNoArvoreBinaria(no1.filhoDireito, no2.filhoDireito);
    }

    public boolean compararSubArvore(ArvoreBinaria s) {
        No noCorrespondente = this.buscaNo(s.getRaiz().getChave());

        if (noCorrespondente == null) {
            return false;
        } else {
            return compararNoArvoreBinaria(s.getRaiz(), noCorrespondente);
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria T = new ArvoreBinaria();
        T.inserirNo(10);
        T.inserirNo(5);
        T.inserirNo(15);
        T.inserirNo(3);
        T.inserirNo(7);
        T.inserirNo(12);
        T.inserirNo(17);
        T.imprimirArvore();

        ArvoreBinaria S = new ArvoreBinaria();
        S.inserirNo(15);
        S.inserirNo(12);
        S.inserirNo(17);
        S.imprimirArvore();

        System.out.println(T.compararSubArvore(S));
    }
}
