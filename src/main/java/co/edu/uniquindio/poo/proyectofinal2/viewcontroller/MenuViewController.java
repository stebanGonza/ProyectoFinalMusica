package co.edu.uniquindio.poo.proyectofinal2.viewcontroller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class MenuViewController {

    @FXML private BorderPane mainLayout;

    private void cargarVista(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane vista = loader.load();
            mainLayout.setCenter(vista);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error de Carga", "No se pudo cargar la vista: " + fxmlPath, AlertType.ERROR);
        }
    }

    private void mostrarMensaje(String titulo, String contenido, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    private void handleGestionProfesores() {
        cargarVista("/co/edu/uniquindio/poo/proyectofinal2/view/ProfesorView.fxml");
    }

    @FXML
    private void handleGestionEstudiantes() {
        cargarVista("/co/edu/uniquindio/poo/proyectofinal2/view/EstudianteView.fxml");
    }

    @FXML
    private void handleGestionCursos() {
        cargarVista("/co/edu/uniquindio/poo/proyectofinal2/view/CursoView.fxml");
    }

    @FXML
    private void handleGestionAulas() {
        cargarVista("/co/edu/uniquindio/poo/proyectofinal2/view/AulaView.fxml");
    }

    @FXML
    private void handleGestionClases() {
        cargarVista("/co/edu/uniquindio/poo/proyectofinal2/view/ClaseView.fxml");
    }
    @FXML
    private void handleGenerarReportes() {
        cargarVista("/co/edu/uniquindio/poo/proyectofinal2/view/ReportesView.fxml");
    }
    @FXML
    private void handleAdministrarAcademico() {
        // El 'onAction' en MenuView.fxml busca este método.
        // Aquí cargamos la vista de Cursos, una opción central de la administración académica.
        cargarVista("/co/edu/uniquindio/poo/proyectofinal2/view/AdministradorAcademicoView.fxml");
    }
}