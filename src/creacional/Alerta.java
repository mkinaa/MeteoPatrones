package creacional;

public abstract class Alerta implements Cloneable {
    protected String tipo;
    
    public abstract void enviar();
    
    @Override
    public Alerta clone() {
        try {
            return (Alerta) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    public String getTipo() {
        return tipo;
    }
}
