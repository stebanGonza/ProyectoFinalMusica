package co.edu.uniquindio.poo.proyectofinal2.viewcontroller;


import co.edu.uniquindio.poo.proyectofinal2.controller.AdministracionController;
import co.edu.uniquindio.poo.proyectofinal2.modelo.Aula;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class AulaViewController {

    @FXML private TableView<Aula> tablaAulas;
    @FXML private TableColumn<Aula, String> colId;
    @FXML private TableColumn<Aula, String> colNombre;
    @FXML private TableColumn<Aula, Number> colCapacidad;

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCapacidad;

    private final AdministracionController controller = new AdministracionController();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getId()));
        colNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
        colCapacidad.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getCapacidad()));
        cargarTablaAulas();
    }

    private void cargarTablaAulas() {
        tablaAulas.setItems(FXCollections.observableArrayList(controller.listarAulas()));
    }

    @FXML
    private void handleCrearAula() {
        try {
            controller.crearAula(txtId.getText(), txtNombre.getText(), Integer.parseInt(txtCapacidad.getText()));
            mostrarMensaje("Éxito", "Aula creada correctamente.", AlertType.INFORMATION);
            limpiarCampos();
            cargarTablaAulas();
        } catch (Exception e) {
            mostrarMensaje("Error", "Error al crear aula: " + e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    private void handleEliminarAula() {
        Aula seleccionado = tablaAulas.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            if (controller.eliminarAula(seleccionado.getId())) {
                mostrarMensaje("Éxito", "Aula eliminada.", AlertType.INFORMATION);
                cargarTablaAulas();
            } else {
                mostrarMensaje("Error", "No se pudo eliminar el aula.", AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Advertencia", "Selecciona un aula de la tabla para eliminar.", AlertType.WARNING);
        }
    }

    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtCapacidad.clear();
    }

    private void mostrarMensaje(String titulo, String contenido, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
