// Armazena os resultados de um experimento de insercao
public class ResultadoInsercao {
    private long tempoNano; // Tempo de execucao em nanosegundos
    private int colisoes; // Numero de colisoes ocorridas

    // Construtor que inicializa tempo e colisoes
    public ResultadoInsercao(long tempoNano, int colisoes) {
        this.tempoNano = tempoNano;
        this.colisoes = colisoes;
    }

    // Retorna o tempo de execucao em nanosegundos
    public long getTempoNano() {
        return tempoNano;
    }

    // Retorna o numero de colisoes
    public int getColisoes() {
        return colisoes;
    }
}
