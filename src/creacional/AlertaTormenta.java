package creacional;

public class AlertaTormenta extends Alerta {
    private static AlertaTormenta instancia;

    // Constructor privado para evitar nuevas instancias
    private AlertaTormenta() {
        this.tipo = "TORMENTA";
    }

    // Método público para obtener la única instancia
    public static AlertaTormenta getInstancia() {
        if (instancia == null) {
            instancia = new AlertaTormenta();
        }
        return instancia;
    }

    @Override
    public void enviar() {
        System.out.println("¡TEN CUIDADO! EL CLIMA PUEDE CAMBIAR EN CUALQUIER MOMENTO....");
    }
}


