public class TabelaHashEncadeamento {
    private No[] tabela;
    private int tamanho;
    private int colisoes;
    private String tipoHash;
    public TabelaHashEncadeamento(int tamanho, String tipoHash) {
        this.tamanho = tamanho;
        this.tabela = new No[tamanho];
        this.colisoes = 0;
        this.tipoHash = tipoHash;
    }
    private int hash(int codigo) {
        if (tipoHash.equals("DIVISAO")) {
            return Math.abs(codigo % tamanho);
        } else if (tipoHash.equals("MULTIPLICACAO")) {
            double A = 0.6180339887;
            double temp = codigo * A;
            temp = temp - Math.floor(temp);
            return (int) Math.floor(tamanho * temp);
        }
        return 0;
    }
    public void inserir(Registro registro) {
        int indice = hash(registro.getCodigo());
        if (tabela[indice] == null) {
            tabela[indice] = new No(registro);
        } else {
            No atual = tabela[indice];
            int colisoesNaInsercao = 0;
            while (atual.getProximo() != null) {
                colisoesNaInsercao++;
                if (atual.getRegistro().getCodigo() == registro.getCodigo()) {
                    return;
                }
                atual = atual.getProximo();
            }
            colisoesNaInsercao++;
            if (atual.getRegistro().getCodigo() == registro.getCodigo()) {
                return;
            }
            atual.setProximo(new No(registro));
            colisoes += colisoesNaInsercao;
        }
    }
    public boolean buscar(int codigo) {
        int indice = hash(codigo);
        No atual = tabela[indice];
        while (atual != null) {
            if (atual.getRegistro().getCodigo() == codigo) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }
    public int getColisoes() {
        return colisoes;
    }
    public int[] getTresMaioresListas() {
        int[] maiores = new int[3];
        for (int i = 0; i < tamanho; i++) {
            int tamanhoLista = 0;
            No atual = tabela[i];
            while (atual != null) {
                tamanhoLista++;
                atual = atual.getProximo();
            }
            if (tamanhoLista > maiores[0]) {
                maiores[2] = maiores[1];
                maiores[1] = maiores[0];
                maiores[0] = tamanhoLista;
            } else if (tamanhoLista > maiores[1]) {
                maiores[2] = maiores[1];
                maiores[1] = tamanhoLista;
            } else if (tamanhoLista > maiores[2]) {
                maiores[2] = tamanhoLista;
            }
        }
        return maiores;
    }
    public double[] calcularGaps() {
        int menorGap = Integer.MAX_VALUE;
        int maiorGap = 0;
        int somaGaps = 0;
        int quantidadeGaps = 0;
        int ultimoOcupado = -1;
        for (int i = 0; i < tamanho; i++) {
            if (tabela[i] != null) {
                if (ultimoOcupado != -1) {
                    int gap = i - ultimoOcupado - 1;
                    if (gap < menorGap) menorGap = gap;
                    if (gap > maiorGap) maiorGap = gap;
                    somaGaps += gap;
                    quantidadeGaps++;
                }
                ultimoOcupado = i;
            }
        }
        double mediaGap = quantidadeGaps > 0 ? (double) somaGaps / quantidadeGaps : 0;
        return new double[]{menorGap == Integer.MAX_VALUE ? 0 : menorGap, maiorGap, mediaGap};
    }
}
