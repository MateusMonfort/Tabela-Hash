public class ResultadoInsercao {
    private long tempoNano;
    private int colisoes;
    public ResultadoInsercao(long tempoNano, int colisoes) {
        this.tempoNano = tempoNano;
        this.colisoes = colisoes;
    }
    public long getTempoNano() {
        return tempoNano;
    }
    public int getColisoes() {
        return colisoes;
    }
}
