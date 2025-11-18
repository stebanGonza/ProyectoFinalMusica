package co.edu.uniquindio.poo.proyectofinal2.viewcontroller;


import co.edu.uniquindio.poo.proyectofinal2.controller.AdministracionController;
import co.edu.uniquindio.poo.proyectofinal2.modelo.Profesor;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class ProfesorViewController {

    @FXML private TableView<Profesor> tablaProfesores;
    @FXML private TableColumn<Profesor, String> colNombre;
    @FXML private TableColumn<Profesor, String> colDocumento;
    @FXML private TableColumn<Profesor, String> colCorreo;

    @FXML private TextField txtNombre;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtCorreo;

    private final AdministracionController controller = new AdministracionController();

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
        colDocumento.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDocumento()));
        colCorreo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCorreo()));

        cargarTablaProfesores();
    }

    private void cargarTablaProfesores() {
        tablaProfesores.setItems(FXCollections.observableArrayList(controller.listarProfesores()));
    }

    @FXML
    private void handleCrearProfesor() {
        try {
            controller.crearProfesor(txtNombre.getText(), txtDocumento.getText(), txtCorreo.getText());
            mostrarMensaje("Éxito", "Profesor creado correctamente.", AlertType.INFORMATION);
            limpiarCampos();
            cargarTablaProfesores();
        } catch (Exception e) {
            mostrarMensaje("Error", "Error al crear profesor: " + e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    private void handleEliminarProfesor() {
        Profesor seleccionado = tablaProfesores.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            if (controller.eliminarProfesor(seleccionado.getDocumento())) {
                mostrarMensaje("Éxito", "Profesor eliminado.", AlertType.INFORMATION);
                cargarTablaProfesores();
            } else {
                mostrarMensaje("Error", "No se pudo eliminar al profesor.", AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Advertencia", "Selecciona un profesor de la tabla para eliminar.", AlertType.WARNING);
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
