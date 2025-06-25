package comportamiento;

import java.util.ArrayList;
import java.util.List;

public class EstacionMeteorologica {
    // Lista de observadores registrados
    private List<Observador> observadores = new ArrayList<>();
    // Estado que será notificado a los observadores
    private String ultimaAlerta;
    
    // Método para agregar un observador
    public void agregarObservador(Observador o) {
        if (o != null && !observadores.contains(o)) {
            observadores.add(o);
        }
    }
    
    // Método para remover un observador si ya no quiere recibir notificaciones
    public void removerObservador(Observador o) {
        observadores.remove(o);
    }
    
    // Notifica a todos los observadores con la última alerta
    public void notificarObservadores() {
        for (Observador o : observadores) {
            o.actualizar(ultimaAlerta);
        }
    }
    
    // Cambia el estado y notifica a los observadores
    public void setAlerta(String alerta) {
        this.ultimaAlerta = alerta;
        notificarObservadores();
    }
}

