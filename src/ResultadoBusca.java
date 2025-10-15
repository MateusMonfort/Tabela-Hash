// Armazena os resultados de um experimento de busca
public class ResultadoBusca {
    private long tempoNano; // Tempo de execucao em nanosegundos
    private int buscasRealizadas; // Numero de buscas realizadas

    // Construtor que inicializa tempo e numero de buscas
    public ResultadoBusca(long tempoNano, int buscasRealizadas) {
        this.tempoNano = tempoNano;
        this.buscasRealizadas = buscasRealizadas;
    }

    // Retorna o tempo de execucao em nanosegundos
    public long getTempoNano() {
        return tempoNano;
    }

    // Retorna o numero de buscas realizadas
    public int getBuscasRealizadas() {
        return buscasRealizadas;
    }
}
