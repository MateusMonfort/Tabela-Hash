// Representa um no da lista encadeada usada na resolucao de colisoes
public class No {
    private Registro registro;
    private No proximo;

    // Construtor que cria um no com um registro
    public No(Registro registro) {
        this.registro = registro;
        this.proximo = null;
    }

    // Retorna o registro armazenado no no
    public Registro getRegistro() {
        return registro;
    }

    // Define o registro do no
    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    // Retorna o proximo no da lista
    public No getProximo() {
        return proximo;
    }

    // Define o proximo no da lista
    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}
