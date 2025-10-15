import java.util.Random;
import java.io.PrintWriter;
import java.io.FileWriter;


public class GeradorDados {
    private static final long SEED = 42; 

    
    public static Registro[] gerarRegistros(int quantidade) {
        Random random = new Random(SEED);
        Registro[] registros = new Registro[quantidade];
        
        for (int i = 0; i < quantidade; i++) {
            int codigo = random.nextInt(900000000) + 100000000; 
            registros[i] = new Registro(codigo);
        }
        
        return registros;
    }

    
    public static void salvarDadosEmArquivo(Registro[] registros, String nomeArquivo) throws Exception {
        PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo));
        for (Registro reg : registros) {
            writer.println(reg.getCodigo());
        }
        writer.close();
    }
}
