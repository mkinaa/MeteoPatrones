package libre;

public class DatoClimatico {
    private String fecha;
    private double valor;
    
    public DatoClimatico(String fecha, double valor) {
        this.fecha = fecha;
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return fecha + ": " + valor;
    }
}
