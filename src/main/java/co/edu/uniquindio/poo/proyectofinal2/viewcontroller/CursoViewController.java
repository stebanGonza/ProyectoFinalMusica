package co.edu.uniquindio.poo.proyectofinal2.viewcontroller;

import co.edu.uniquindio.poo.proyectofinal2.controller.AdministracionController;
import co.edu.uniquindio.poo.proyectofinal2.modelo.Curso;
import co.edu.uniquindio.poo.proyectofinal2.modelo.Instrumento;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CursoViewController {

    @FXML private TableView<Curso> tablaCursos;
    @FXML private TableColumn<Curso, String> colInstrumento;
    @FXML private TableColumn<Curso, Number> colNivel;
    @FXML private TableColumn<Curso, Number> colCapacidad;

    @FXML private ComboBox<String> cmbInstrumento;
    @FXML private TextField txtNivel;
    @FXML private TextField txtCapacidad;

    private final AdministracionController controller = new AdministracionController();

    @FXML
    public void initialize() {
        // Mapeo de columnas
        colInstrumento.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getInstrumento()));
        colNivel.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getNivel()));
        colCapacidad.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getCapacidad()));

        // CORRECCIÓN: Envolver la lista de strings del Enum en ObservableList
        // El método .collect(Collectors.toList()) devuelve un List que debe ser convertido.
        cmbInstrumento.setItems(FXCollections.observableArrayList(
                Arrays.stream(Instrumento.values())
                        .map(Enum::name)
                        .collect(Collectors.toList())
        ));

        cargarTablaCursos();
    }

    private void cargarTablaCursos() {
        tablaCursos.setItems(FXCollections.observableArrayList(controller.getCursos()));
    }

    @FXML
    private void handleCrearCurso() {
        try {
            String instrumento = cmbInstrumento.getValue();

            if (instrumento == null || instrumento.isBlank()) {
                throw new IllegalArgumentException("Debe seleccionar un instrumento.");
            }

            int nivel = Integer.parseInt(txtNivel.getText());
            int capacidad = Integer.parseInt(txtCapacidad.getText());

            controller.crearCurso(instrumento, nivel, capacidad);
            mostrarMensaje("Éxito", "Curso creado correctamente.", AlertType.INFORMATION);
            limpiarCampos();
            cargarTablaCursos();
        } catch (NumberFormatException e) {
            mostrarMensaje("Error de Formato", "Nivel y Capacidad deben ser números enteros válidos.", AlertType.ERROR);
        } catch (Exception e) {
            mostrarMensaje("Error", "Error al crear curso: " + e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    private void handleEliminarCurso() {
        Curso seleccionado = tablaCursos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            if (controller.eliminarCurso(seleccionado.getInstrumento(), seleccionado.getNivel())) {
                mostrarMensaje("Éxito", "Curso eliminado.", AlertType.INFORMATION);
                cargarTablaCursos();
            } else {
                mostrarMensaje("Error", "No se pudo eliminar el curso.", AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Advertencia", "Selecciona un curso de la tabla para eliminar.", AlertType.WARNING);
        }
    }

    private void limpiarCampos() {
        cmbInstrumento.setValue(null);
        txtNivel.clear();
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