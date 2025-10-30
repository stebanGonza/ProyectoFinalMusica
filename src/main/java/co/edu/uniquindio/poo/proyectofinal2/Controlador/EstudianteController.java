package co.edu.uniquindio.poo.proyectofinal2.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EstudianteController {

    @FXML
    private Button btnVerCursos;
    @FXML
    private Button btnDescargarReporte;

    @FXML
    public void verCursos() {
        System.out.println("Mostrando cursos del estudiante...");
    }

    @FXML
    public void descargarReporte() {
        System.out.println("Descargando reporte de progreso...");
    }
}
