# AutoAlertas - Sistema de Monitoreo Meteorológico

## 🌦 Descripción del sistema

**AutoAlertas** es una aplicación de consola en Java que simula una estación meteorológica con funciones como:

- Registrar usuarios para recibir alertas climáticas.
- Capturar temperaturas con análisis automático.
- Enviar notificaciones meteorológicas personalizadas.
- Clonar alertas de tormenta fácilmente.
- Iterar sobre datos climáticos históricos.

Todo el sistema está diseñado aplicando patrones de diseño para garantizar flexibilidad, extensibilidad y bajo acoplamiento entre componentes.

---

## ❗ Problema a resolver

Las estaciones meteorológicas y sistemas de monitoreo climático enfrentan desafíos como:

- Enviar alertas a múltiples usuarios de forma coordinada.
- Centralizar el control de alertas críticas.
- Permitir visualización adaptable de datos.
- Reutilizar alertas comunes para situaciones similares.
- Recorrer históricos sin depender de estructuras internas.

**AutoAlertas** aborda estos desafíos mediante el uso de 5 patrones de diseño del catálogo GoF.

---

## 🧩 Patrones de diseño utilizados y justificación

### 🔷 Singleton – `EstacionMeteorologica` y `AlertaTormenta`

**¿Por qué este patrón y no otro?**  
Se usó Singleton para asegurar que exista **una única estación meteorológica y una única alerta base**, evitando inconsistencias en el envío de datos o creación de múltiples alertas no controladas.

**¿Cómo se usó?**  
Ambas clases tienen constructor privado, atributo estático e implementación `getInstancia()`.

**¿Dónde se usó?**

```java
public class EstacionMeteorologica {
    private static EstacionMeteorologica instancia;
    private EstacionMeteorologica() {}
    public static EstacionMeteorologica getInstancia() {
        if (instancia == null) {
            instancia = new EstacionMeteorologica();
        }
        return instancia;
    }
}
```
---

### 🔷 Observer – `EstacionMeteorologica` y `Usuario`

**¿Por qué este patrón y no otro?**  
Se eligió Observer porque permite que **múltiples objetos (usuarios)** se suscriban a un **sujeto (la estación meteorológica)** y reciban **notificaciones automáticas** cuando se emite una alerta climática.  
Este patrón es ideal cuando se necesita una arquitectura **reactiva y desacoplada**, donde los usuarios no necesitan consultar manualmente los cambios del clima.

**¿Cómo se usó?**  
- Se creó la interfaz `Observador` con el método `actualizar(String alerta)`.
- La clase `Usuario` implementa esa interfaz y define cómo recibe las notificaciones.
- La clase `EstacionMeteorologica` mantiene una lista de observadores y los **notifica automáticamente** cuando se genera una nueva alerta.

**¿Dónde se usó?**

```java
// Interfaz Observador
public interface Observador {
    void actualizar(String alerta);
}

// Clase Observador concreto
public class Usuario implements Observador {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String alerta) {
        System.out.println("[Notificacion para " + nombre + "] " + alerta);
    }
}
```
---

### 🔷 Adapter – `SensorTemperatura` y `VisualizadorConsola`

**¿Por qué este patrón y no otro?**  
Se eligió el patrón **Adapter** porque permite que la clase `SensorTemperatura` pueda funcionar con **distintos tipos de visualizadores** (por ejemplo, visualización en consola, archivo, o interfaz gráfica), sin necesidad de modificar su lógica interna.  
Esto promueve la **abstracción** y el **bajo acoplamiento**, ya que el sensor no depende directamente de una clase concreta, sino de una interfaz.

**¿Cómo se usó?**  
- Se definió una interfaz `Visualizador` con un método `mostrar(String mensaje)`.
- Se creó la clase `VisualizadorConsola` que implementa esa interfaz y muestra mensajes por consola.
- `SensorTemperatura` tiene una referencia a `Visualizador`, lo que le permite delegar la visualización sin saber cómo se implementa.

**¿Dónde se usó?**

```java
// Interfaz común para visualización
public interface Visualizador {
    void mostrar(String mensaje);
}

// Implementación concreta para consola
public class VisualizadorConsola implements Visualizador {
    @Override
    public void mostrar(String mensaje) {
        System.out.println("[Visualizador Consola] " + mensaje);
    }
}
```
---

### 🔷 Prototype – `AlertaTormenta.clonar()`

**¿Por qué este patrón y no otro?**  
Se usó Prototype para **crear copias de una alerta base (por ejemplo, tormenta)** sin necesidad de instanciar y configurar cada alerta desde cero.  
Esto es útil cuando se quiere **reutilizar una configuración común** (tipo de alerta, comportamiento, mensaje) y solo variar algunos detalles, manteniendo eficiencia y flexibilidad.

**¿Cómo se usó?**  
- La clase `Alerta` implementa `Cloneable` y define el método `clone()`.
- `AlertaTormenta`, que extiende `Alerta`, puede clonar una alerta base sin modificar su comportamiento original.

**¿Dónde se usó?**

```java
// Clase base con clonación
public abstract class Alerta implements Cloneable {
    protected String tipo;

    @Override
    public Object clone() {
        try {
            return super.clone(); // Clon superficial
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar alerta", e);
        }
    }

    public abstract void enviar();
}
```
---

### 🔷 Iterator – `HistoricoIterator`

**¿Por qué este patrón y no otro?**  
Se utilizó el patrón **Iterator** para **recorrer una colección de datos climáticos** (tipo array) sin exponer su estructura interna.  
Este patrón permite recorrer elementos uno por uno, ocultando los detalles de cómo están almacenados y facilitando el acceso secuencial de forma limpia y controlada.

**¿Cómo se usó?**  
- Se implementó la interfaz `Iterator<DatoClimatico>`.
- `HistoricoIterator` permite recorrer un arreglo de objetos `DatoClimatico` con los métodos `hasNext()` y `next()`.

**¿Dónde se usó?**

```java
// Iterador concreto
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
```

## ▶️ Instrucciones de compilación y ejecución

### ✅ Requisitos previos

- Java Development Kit (JDK) 8 o superior
- IDE como **NetBeans**, **Eclipse**, **IntelliJ IDEA** o consola con `javac` y `java`
- Proyecto estructurado en carpetas como:
```
  MeteoPatrone/
│
├── src/
│ ├── Main.java
│ ├── comportamiento/
│ ├── creacional/
│ ├── estructural/
│ └── libre/
```
---
- Nombre Alumno: Jorge Moncada
- Sección 2
