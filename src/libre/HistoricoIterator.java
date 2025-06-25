package libre;
//Patrón iterator
import java.util.Iterator;

public class HistoricoIterator implements Iterator<DatoClimatico> {
    private DatoClimatico[] datos;
    private int posicion = 0;
    
    public HistoricoIterator(DatoClimatico[] datos) {
        this.datos = datos;
    }
    
    @Override
    public boolean hasNext() {
        return posicion < datos.length && datos[posicion] != null;
    }
    
    @Override
    public DatoClimatico next() {
        return datos[posicion++];
    }
}
