package comportamiento;
//Patrón observer
public class Usuario implements Observador {
    private String nombre;
    
    public Usuario(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public void actualizar(String alerta) {
        System.out.println("\n[Notificacion para " + nombre + "] " + alerta);
    }
    
    public String getNombre() {
        return nombre;
    }
}