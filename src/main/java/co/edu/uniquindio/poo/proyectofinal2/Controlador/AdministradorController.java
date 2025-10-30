package co.edu.uniquindio.poo.proyectofinal2.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdministradorController {

    @FXML
    private Button btnCursos;
    @FXML
    private Button btnProfesores;
    @FXML
    private Button btnEstudiantes;

    @FXML
    public void gestionarCursos() {
        System.out.println("Gestionando cursos...");
    }

    @FXML
    public void gestionarProfesores() {
        System.out.println("Gestionando profesores...");
    }

    @FXML
    public void gestionarEstudiantes() {
        System.out.println("Gestionando estudiantes...");
    }
}
