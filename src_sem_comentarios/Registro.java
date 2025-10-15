public class Registro {
    private int codigo;
    public Registro(int codigo) {
        this.codigo = codigo;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    @Override
    public String toString() {
        return String.format("%09d", codigo);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Registro registro = (Registro) obj;
        return codigo == registro.codigo;
    }
    @Override
    public int hashCode() {
        return codigo;
    }
}
