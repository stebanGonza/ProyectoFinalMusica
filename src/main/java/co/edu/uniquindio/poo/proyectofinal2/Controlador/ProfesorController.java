package co.edu.uniquindio.poo.proyectofinal2.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ProfesorController {

    @FXML
    private Button btnRegistrarAsistencia;
    @FXML
    private Button btnEvaluarProgreso;

    @FXML
    public void registrarAsistencia() {
        System.out.println("Asistencia registrada.");
    }

    @FXML
    public void evaluarProgreso() {
        System.out.println("Evaluando progreso de estudiantes...");
    }
}
