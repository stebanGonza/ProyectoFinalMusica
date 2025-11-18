package co.edu.uniquindio.poo.proyectofinal2.modelo;




public interface Gestionable {
    void crear();
    void actualizar();
    void eliminar();

    // El método de reporte se deja sin argumentos de UI (ActionEvent)
    void generarReporte();
}