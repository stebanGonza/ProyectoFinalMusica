package co.edu.uniquindio.poo.proyectofinal2.modelo;

public class Reporte {
    private String descripcion;

    public Reporte(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return "Reporte: " + descripcion;
    }
}
