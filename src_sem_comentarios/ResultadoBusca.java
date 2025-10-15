public class ResultadoBusca {
    private long tempoNano;
    private int buscasRealizadas;
    public ResultadoBusca(long tempoNano, int buscasRealizadas) {
        this.tempoNano = tempoNano;
        this.buscasRealizadas = buscasRealizadas;
    }
    public long getTempoNano() {
        return tempoNano;
    }
    public int getBuscasRealizadas() {
        return buscasRealizadas;
    }
}
