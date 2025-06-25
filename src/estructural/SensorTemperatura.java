package estructural;

public class SensorTemperatura extends Sensor {
    private double temperatura;
    
    public SensorTemperatura(Visualizador visualizador) {
        super(visualizador);
    }
    
    public void setTemperatura(double temp) {
        this.temperatura = temp;
        actualizar();  // Cambiado de super.actualizar() a actualizar()
    }
    
    @Override
    public void actualizar() {
        visualizador.mostrar("Temperatura: " + temperatura + "°C");
    }
}
