// Classe responsavel por executar os experimentos de desempenho das tabelas hash
public class Experimento {
    
    // Testa insercao em tabela com encadeamento medindo tempo e colisoes
    public static ResultadoInsercao testarInsercaoEncadeamento(
            Registro[] registros, int tamanhoTabela, String tipoHash) {
        
        TabelaHashEncadeamento tabela = new TabelaHashEncadeamento(tamanhoTabela, tipoHash);
        
        long inicio = System.nanoTime();
        
        for (Registro reg : registros) {
            tabela.inserir(reg);
        }
        
        long fim = System.nanoTime();
        long tempoTotal = fim - inicio;
        
        return new ResultadoInsercao(tempoTotal, tabela.getColisoes());
    }

    // Testa busca em tabela com encadeamento medindo tempo e numero de buscas
    public static ResultadoBusca testarBuscaEncadeamento(
            Registro[] registros, int tamanhoTabela, String tipoHash) {
        
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

    // Testa insercao em tabela com rehashing medindo tempo e colisoes
    public static ResultadoInsercao testarInsercaoRehashing(
            Registro[] registros, int tamanhoTabela) {
        
        TabelaHashRehashing tabela = new TabelaHashRehashing(tamanhoTabela);
        
        long inicio = System.nanoTime();
        
        for (Registro reg : registros) {
            tabela.inserir(reg);
        }
        
        long fim = System.nanoTime();
        long tempoTotal = fim - inicio;
        
        return new ResultadoInsercao(tempoTotal, tabela.getColisoes());
    }

    // Testa busca em tabela com rehashing medindo tempo e numero de buscas
    public static ResultadoBusca testarBuscaRehashing(
            Registro[] registros, int tamanhoTabela) {
        
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

    // Retorna os tamanhos das tres maiores listas encadeadas da tabela
    public static int[] obterTresMaioresListas(
            Registro[] registros, int tamanhoTabela, String tipoHash) {
        
        TabelaHashEncadeamento tabela = new TabelaHashEncadeamento(tamanhoTabela, tipoHash);
        
        for (Registro reg : registros) {
            tabela.inserir(reg);
        }
        
        return tabela.getTresMaioresListas();
    }

    // Calcula estatisticas de gaps para tabela com encadeamento
    public static double[] calcularGapsEncadeamento(
            Registro[] registros, int tamanhoTabela, String tipoHash) {
        
        TabelaHashEncadeamento tabela = new TabelaHashEncadeamento(tamanhoTabela, tipoHash);
        
        for (Registro reg : registros) {
            tabela.inserir(reg);
        }
        
        return tabela.calcularGaps();
    }

    // Calcula estatisticas de gaps para tabela com rehashing
    public static double[] calcularGapsRehashing(
            Registro[] registros, int tamanhoTabela) {
        
        TabelaHashRehashing tabela = new TabelaHashRehashing(tamanhoTabela);
        
        for (Registro reg : registros) {
            tabela.inserir(reg);
        }
        
        return tabela.calcularGaps();
    }
}
