import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import creacional.AlertaTormenta;
import estructural.SensorTemperatura;
import estructural.VisualizadorConsola;
import comportamiento.EstacionMeteorologica;
import comportamiento.Usuario;
import libre.DatoClimatico;
import libre.HistoricoIterator;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        VisualizadorConsola vis = new VisualizadorConsola();
        SensorTemperatura sensor = new SensorTemperatura(vis);
        EstacionMeteorologica estacion = new EstacionMeteorologica();  // Sin singleton
        
        // Registrar usuario con validación
        String nombreUsuario = leerCadenaNoVacia("Ingrese nombre de usuario a registrar: ");
        estacion.agregarObservador(new Usuario(nombreUsuario));

        // Ingresar temperatura con validación
        double temperatura = leerTemperaturaValida("Ingrese temperatura actual (C): ", -50, 60);
        sensor.setTemperatura(temperatura);
        
        // Mostrar mensaje automático basado en la temperatura
        mostrarMensajeTemperatura(temperatura);

        // Configurar alerta con validación
        String mensajeAlerta = leerCadenaNoVacia("Ingrese mensaje de alerta meteorologica: ");
        estacion.setAlerta(mensajeAlerta);  // Notifica a todos los observadores registrados

        // Usar la única instancia Singleton de AlertaTormenta
        System.out.println("\nUsando alerta singleton...");
        AlertaTormenta alertaSingleton = AlertaTormenta.getInstancia();
        alertaSingleton.enviar();

        scanner.close();
    }

    private static void mostrarMensajeTemperatura(double temperatura) {
        System.out.println("\n--- ANALISIS AUTOMATICO DE TEMPERATURA ---");
        System.out.printf("Con %.1f°C: ", temperatura);
        
        if (temperatura <= -20) {
            System.out.println("¡PELIGRO! Frio extremo. Riesgo de hipotermia.");
        } else if (temperatura <= 0) {
            System.out.println("¡Hielo y escarcha! Abrigate con multiples capas.");
        } else if (temperatura <= 10) {
            System.out.println("Frio intenso. Perfecto para chocolate caliente.");
        } else if (temperatura <= 18) {
            System.out.println("Fresco agradable. Ideal para caminatas.");
        } else if (temperatura <= 25) {
            System.out.println("Templado perfecto. Disfruta actividades al aire libre.");
        } else if (temperatura <= 30) {
            System.out.println("¡Calor! Bañate y usa bloqueador solar.");
        } else if (temperatura <= 35) {
            System.out.println("¡Mucho calor! Evita el sol entre 11am-3pm.");
        } else {
            System.out.println("¡PELIGRO! Temperatura extrema. Quedate en lugares con aire acondicionado.");
        }
        
        if (temperatura > 25) {
            System.out.println("Recomendacion: Bebe al menos 2 litros de agua hoy");
        }
        if (temperatura < 15) {
            System.out.println("Recomendacion: Una bebida caliente te ayudara a entrar en calor");
        }
    }

    private static String leerCadenaNoVacia(String mensaje) {
        String entrada;
        do {
            System.out.print(mensaje);
            entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("Error: Este campo no puede estar vacío");
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    private static double leerTemperaturaValida(String mensaje, double min, double max) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.printf("Error: La temperatura debe estar entre %.1f y %.1f °C\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un valor numérico válido");
            }
        }
    }

    private static int leerEnteroPositivo(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor > 0) {
                    return valor;
                }
                System.out.println("Error: Debe ingresar un número positivo");
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido");
            }
        }
    }

    private static String leerFechaValida(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String fechaStr = scanner.nextLine().trim();
            try {
                LocalDate.parse(fechaStr, dateFormatter);
                return fechaStr;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha inválido. Use YYYY-MM-DD");
            }
        }
    }
}
