import java.util.Random;
import java.io.PrintWriter;
import java.io.FileWriter;

// Gera conjuntos de registros aleatorios para os experimentos
public class GeradorDados {
    private static final long SEED = 42; // Seed fixo para reproducibilidade

    // Gera um array de registros com codigos aleatorios de 9 digitos
    public static Registro[] gerarRegistros(int quantidade) {
        Random random = new Random(SEED);
        Registro[] registros = new Registro[quantidade];
        
        for (int i = 0; i < quantidade; i++) {
            int codigo = random.nextInt(900000000) + 100000000; // Gera numero entre 100000000 e 999999999
            registros[i] = new Registro(codigo);
        }
        
        return registros;
    }

    // Salva os registros em um arquivo de texto
    public static void salvarDadosEmArquivo(Registro[] registros, String nomeArquivo) throws Exception {
        PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo));
        for (Registro reg : registros) {
            writer.println(reg.getCodigo());
        }
        writer.close();
    }
}
