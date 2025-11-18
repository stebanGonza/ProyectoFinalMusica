package co.edu.uniquindio.poo.proyectofinal2.viewcontroller;

import co.edu.uniquindio.poo.proyectofinal2.controller.AdministracionController;
import co.edu.uniquindio.poo.proyectofinal2.modelo.Estudiante;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class EstudianteViewController {

    @FXML private TableView<Estudiante> tablaEstudiantes;
    @FXML private TableColumn<Estudiante, String> colNombre;
    @FXML private TableColumn<Estudiante, String> colDocumento;
    @FXML private TableColumn<Estudiante, String> colCorreo;

    @FXML private TextField txtNombre;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtCorreo;

    private final AdministracionController controller = new AdministracionController();

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
        colDocumento.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDocumento()));
        colCorreo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCorreo()));
        cargarTablaEstudiantes();
    }

    private void cargarTablaEstudiantes() {
        tablaEstudiantes.setItems(FXCollections.observableArrayList(controller.listarEstudiantes()));
    }

    @FXML
    private void handleCrearEstudiante() {
        try {
            controller.crearEstudiante(txtNombre.getText(), txtDocumento.getText(), txtCorreo.getText());
            mostrarMensaje("Éxito", "Estudiante creado correctamente.", AlertType.INFORMATION);
            limpiarCampos();
            cargarTablaEstudiantes();
        } catch (Exception e) {
            mostrarMensaje("Error", "Error al crear estudiante: " + e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    private void handleEliminarEstudiante() {
        Estudiante seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            if (controller.eliminarEstudiante(seleccionado.getDocumento())) {
                mostrarMensaje("Éxito", "Estudiante eliminado.", AlertType.INFORMATION);
                cargarTablaEstudiantes();
            } else {
                mostrarMensaje("Error", "No se pudo eliminar al estudiante.", AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Advertencia", "Selecciona un estudiante de la tabla para eliminar.", AlertType.WARNING);
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtDocumento.clear();
        txtCorreo.clear();
    }

    private void mostrarMensaje(String titulo, String contenido, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
