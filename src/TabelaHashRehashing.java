// Tabela hash com resolucao de colisoes por rehashing (double hashing)
public class TabelaHashRehashing {
    private Registro[] tabela;
    private boolean[] ocupado;
    private int tamanho;
    private int colisoes;

    // Construtor que inicializa a tabela e array de controle de ocupacao
    public TabelaHashRehashing(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Registro[tamanho];
        this.ocupado = new boolean[tamanho];
        this.colisoes = 0;
    }

    // Primeira funcao hash usando metodo da divisao
    private int hash1(int codigo) {
        return Math.abs(codigo % tamanho);
    }

    // Segunda funcao hash para rehashing usando numero primo
    private int hash2(int codigo) {
        int primo = obterPrimoMenor(tamanho);
        return primo - (Math.abs(codigo) % primo);
    }

    // Retorna o maior numero primo menor que n
    private int obterPrimoMenor(int n) {
        for (int i = n - 1; i >= 2; i--) {
            if (ehPrimo(i)) {
                return i;
            }
        }
        return 1;
    }

    // Verifica se um numero e primo
    private boolean ehPrimo(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    // Insere um registro usando double hashing e conta o numero de tentativas como colisoes
    public boolean inserir(Registro registro) {
        int h1 = hash1(registro.getCodigo());
        int h2 = hash2(registro.getCodigo());
        int indice = h1;
        int tentativas = 0;

        while (tentativas < tamanho) {
            if (!ocupado[indice]) {
                tabela[indice] = registro;
                ocupado[indice] = true;
                colisoes += tentativas;
                return true;
            }
            
            if (tabela[indice] != null && 
                tabela[indice].getCodigo() == registro.getCodigo()) {
                return false;
            }

            tentativas++;
            indice = Math.abs((h1 + tentativas * h2)) % tamanho;
        }

        return false;
    }

    // Busca um registro usando double hashing
    public boolean buscar(int codigo) {
        int h1 = hash1(codigo);
        int h2 = hash2(codigo);
        int indice = h1;
        int tentativas = 0;

        while (tentativas < tamanho) {
            if (!ocupado[indice]) {
                return false;
            }
            
            if (tabela[indice] != null && tabela[indice].getCodigo() == codigo) {
                return true;
            }

            tentativas++;
            indice = Math.abs((h1 + tentativas * h2)) % tamanho;
        }

        return false;
    }

    // Retorna o numero total de colisoes (soma de todas as tentativas)
    public int getColisoes() {
        return colisoes;
    }

    // Calcula o menor, maior e medio gap entre posicoes ocupadas da tabela
    public double[] calcularGaps() {
        int menorGap = Integer.MAX_VALUE;
        int maiorGap = 0;
        int somaGaps = 0;
        int quantidadeGaps = 0;
        
        int ultimoOcupado = -1;
        
        for (int i = 0; i < tamanho; i++) {
            if (ocupado[i]) {
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
