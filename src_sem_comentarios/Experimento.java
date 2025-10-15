public class Experimento {
    public static ResultadoInsercao testarInsercaoEncadeamento(Registro[] registros, int tamanhoTabela, String tipoHash) {
        TabelaHashEncadeamento tabela = new TabelaHashEncadeamento(tamanhoTabela, tipoHash);
        long inicio = System.nanoTime();
        for (Registro reg : registros) {
            tabela.inserir(reg);
        }
        long fim = System.nanoTime();
        long tempoTotal = fim - inicio;
        return new ResultadoInsercao(tempoTotal, tabela.getColisoes());
    }
    public static ResultadoBusca testarBuscaEncadeamento(Registro[] registros, int tamanhoTabela, String tipoHash) {
        TabelaHashEncadeamento tabela = new TabelaHashEncadeamento(tamanhoTabela, tipoHash);
        for (Registro reg : registros) {
            tabela.inserir(reg);
        }
        long inicio = System.nanoTime();
        int encontrados = 0;
        for (Registro reg : registros) {
            if (tabela.buscar(reg.getCodigo())) {
                encontrados++;
            }
        }
        long fim = System.nanoTime();
        long tempoTotal = fim - inicio;
        return new ResultadoBusca(tempoTotal, encontrados);
    }
    public static ResultadoInsercao testarInsercaoRehashing(Registro[] registros, int tamanhoTabela) {
        TabelaHashRehashing tabela = new TabelaHashRehashing(tamanhoTabela);
        long inicio = System.nanoTime();
        for (Registro reg : registros) {
            tabela.inserir(reg);
        }
        long fim = System.nanoTime();
        long tempoTotal = fim - inicio;
        return new ResultadoInsercao(tempoTotal, tabela.getColisoes());
    }
    public static ResultadoBusca testarBuscaRehashing(Registro[] registros, int tamanhoTabela) {
        TabelaHashRehashing tabela = new TabelaHashRehashing(tamanhoTabela);
        for (Registro reg : registros) {
            tabela.inserir(reg);
        }
        long inicio = System.nanoTime();
        int encontrados = 0;
        for (Registro reg : registros) {
            if (tabela.buscar(reg.getCodigo())) {
                encontrados++;
            }
        }
        long fim = System.nanoTime();
        long tempoTotal = fim - inicio;
        return new ResultadoBusca(tempoTotal, encontrados);
    }
    public static int[] obterTresMaioresListas(Registro[] registros, int tamanhoTabela, String tipoHash) {
        TabelaHashEncadeamento tabela = new TabelaHashEncadeamento(tamanhoTabela, tipoHash);
        for (Registro reg : registros) {
            tabela.inserir(reg);
        }
        return tabela.getTresMaioresListas();
    }
    public static double[] calcularGapsEncadeamento(Registro[] registros, int tamanhoTabela, String tipoHash) {
        TabelaHashEncadeamento tabela = new TabelaHashEncadeamento(tamanhoTabela, tipoHash);
        for (Registro reg : registros) {
            tabela.inserir(reg);
        }
        return tabela.calcularGaps();
    }
    public static double[] calcularGapsRehashing(Registro[] registros, int tamanhoTabela) {
        TabelaHashRehashing tabela = new TabelaHashRehashing(tamanhoTabela);
        for (Registro reg : registros) {
            tabela.inserir(reg);
        }
        return tabela.calcularGaps();
    }
}
