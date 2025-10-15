import java.io.PrintWriter;
import java.io.FileWriter;

// Programa principal que executa experimentos com tabelas hash
public class Main {
    private static final int[] TAMANHOS_TABELA = {1000, 10000, 100000};
    private static final int[] TAMANHOS_CONJUNTO = {100000, 1000000, 10000000};
    private static final String[] TIPOS_HASH = {"DIVISAO", "MULTIPLICACAO", "REHASHING"};

    public static void main(String[] args) throws Exception {
        // Gera os tres conjuntos de dados com tamanhos diferentes
        Registro[][] conjuntos = new Registro[3][];
        for (int i = 0; i < TAMANHOS_CONJUNTO.length; i++) {
            conjuntos[i] = GeradorDados.gerarRegistros(TAMANHOS_CONJUNTO[i]);
        }
        
        // Cria arquivo CSV para salvar os resultados
        PrintWriter csv = new PrintWriter(new FileWriter("resultados.csv"));
        csv.println("TipoHash,TamanhoTabela,TamanhoConjunto,TempoInsercao(ms)," +
                   "Colisoes,TempoBusca(ms),MenorGap,MaiorGap,MediaGap," +
                   "MaiorLista1,MaiorLista2,MaiorLista3");
        
        // Executa experimentos para todas as combinacoes de parametros
        for (int i = 0; i < conjuntos.length; i++) {
            for (int tamanhoTabela : TAMANHOS_TABELA) {
                for (String tipoHash : TIPOS_HASH) {
                    
                    // Variaveis para armazenar resultados dos testes
                    ResultadoInsercao insercao;
                    ResultadoBusca busca;
                    double[] gaps;
                    int[] listas = {0, 0, 0};
                    
                    // Testa com rehashing (duplo hashing) ou encadeamento
                    if (tipoHash.equals("REHASHING")) {
                        insercao = Experimento.testarInsercaoRehashing(conjuntos[i], tamanhoTabela);
                        busca = Experimento.testarBuscaRehashing(conjuntos[i], tamanhoTabela);
                        gaps = Experimento.calcularGapsRehashing(conjuntos[i], tamanhoTabela);
                    } else {
                        insercao = Experimento.testarInsercaoEncadeamento(conjuntos[i], tamanhoTabela, tipoHash);
                        busca = Experimento.testarBuscaEncadeamento(conjuntos[i], tamanhoTabela, tipoHash);
                        gaps = Experimento.calcularGapsEncadeamento(conjuntos[i], tamanhoTabela, tipoHash);
                        listas = Experimento.obterTresMaioresListas(conjuntos[i], tamanhoTabela, tipoHash);
                    }
                    
                    // Converte tempos de nanosegundos para milissegundos
                    double tempoIns = insercao.getTempoNano() / 1_000_000.0;
                    double tempoBus = busca.getTempoNano() / 1_000_000.0;
                    
                    // Escreve linha de resultados no CSV
                    csv.printf("%s,%d,%d,%.2f,%d,%.2f,%.0f,%.0f,%.2f,%d,%d,%d\n",
                        tipoHash, tamanhoTabela, TAMANHOS_CONJUNTO[i],
                        tempoIns, insercao.getColisoes(), tempoBus,
                        gaps[0], gaps[1], gaps[2], listas[0], listas[1], listas[2]);
                }
            }
        }
        
        // Fecha o arquivo e exibe mensagem de conclusao
        csv.close();
        System.out.println("Concluido");
    }
}
