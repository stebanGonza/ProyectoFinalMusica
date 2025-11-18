package co.edu.uniquindio.poo.proyectofinal2.modelo;



// Se elimina la importación de javafx.event.ActionEvent

public class Reporte implements Reportable {
    private String descripcion;

    public Reporte(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return "Reporte: " + descripcion;
    }

    /**
     * Implementación del contrato Reportable.
     * Simula la generación de un reporte general.
     */
    @Override
    public void generarReporte() {
        System.out.println("--- Generando Reporte General ---");
        System.out.println("Descripción: " + descripcion);
        System.out.println("Fecha de generación: " + java.time.LocalDateTime.now());
        System.out.println("---------------------------------");
    }
}