// Representa um registro com codigo de 9 digitos
public class Registro {
    private int codigo;

    // Construtor que inicializa o registro com um codigo
    public Registro(int codigo) {
        this.codigo = codigo;
    }

    // Retorna o codigo do registro
    public int getCodigo() {
        return codigo;
    }

    // Define um novo codigo para o registro
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Retorna o codigo formatado com 9 digitos
    @Override
    public String toString() {
        return String.format("%09d", codigo);
    }

    // Verifica se dois registros sao iguais comparando seus codigos
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Registro registro = (Registro) obj;
        return codigo == registro.codigo;
    }

    // Retorna o hash code baseado no codigo do registro
    @Override
    public int hashCode() {
        return codigo;
    }
}
