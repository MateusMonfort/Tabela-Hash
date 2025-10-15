// Tabela hash com resolucao de colisoes por encadeamento usando funcoes de hash por divisao ou multiplicacao
public class TabelaHashEncadeamento {
    private No[] tabela;
    private int tamanho;
    private int colisoes;
    private String tipoHash;

    // Construtor que inicializa a tabela com tamanho e tipo de hash especificados
    public TabelaHashEncadeamento(int tamanho, String tipoHash) {
        this.tamanho = tamanho;
        this.tabela = new No[tamanho];
        this.colisoes = 0;
        this.tipoHash = tipoHash;
    }

    // Calcula o indice hash usando o metodo especificado (divisao ou multiplicacao)
    private int hash(int codigo) {
        if (tipoHash.equals("DIVISAO")) {
            return Math.abs(codigo % tamanho);
        } else if (tipoHash.equals("MULTIPLICACAO")) {
            double A = 0.6180339887; // Constante relacionada ao numero de ouro
            double temp = codigo * A;
            temp = temp - Math.floor(temp);
            return (int) Math.floor(tamanho * temp);
        }
        return 0;
    }

    // Insere um registro na tabela contando colisoes ao percorrer listas encadeadas
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

    // Busca um registro pelo codigo retornando true se encontrado
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

    // Retorna o numero total de colisoes ocorridas
    public int getColisoes() {
        return colisoes;
    }

    // Retorna os tamanhos das tres maiores listas encadeadas da tabela
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

    // Calcula o menor, maior e medio gap entre posicoes ocupadas da tabela
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
        
        return new double[]{
            menorGap == Integer.MAX_VALUE ? 0 : menorGap,
            maiorGap,
            mediaGap
        };
    }
}
