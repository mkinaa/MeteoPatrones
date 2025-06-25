package estructural;

public class VisualizadorConsola implements Visualizador {
    @Override
    public void mostrar(String datos) {
        System.out.println("\n--- Visualización ---");
        System.out.println(datos);
        System.out.println("---------------------\n");
    }
}
