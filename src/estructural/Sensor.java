package estructural;

public abstract class Sensor {
    protected Visualizador visualizador;
    
    public Sensor(Visualizador v) {
        this.visualizador = v;
    }
    
    public abstract void actualizar();
}
