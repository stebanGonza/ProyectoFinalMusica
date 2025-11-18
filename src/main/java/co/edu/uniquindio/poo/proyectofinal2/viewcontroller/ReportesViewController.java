package co.edu.uniquindio.poo.proyectofinal2.viewcontroller;


import co.edu.uniquindio.poo.proyectofinal2.controller.AdministracionController;
import co.edu.uniquindio.poo.proyectofinal2.modelo.Curso;
import co.edu.uniquindio.poo.proyectofinal2.modelo.Reporte;
import co.edu.uniquindio.poo.proyectofinal2.modelo.ReporteProgreso;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

public class ReportesViewController {

    @FXML private ComboBox<String> cmbCurso;
    @FXML private TextField txtNota;
    @FXML private TextArea txtComentario;

    private final AdministracionController controller = new AdministracionController();

    @FXML
    public void initialize() {
        cargarCursos();
    }

    private void cargarCursos() {
        // Carga los nombres de los cursos disponibles para el ComboBox
        List<String> nombresCursos = controller.getCursos().stream()
                .map(Curso::getNombre)
                .collect(Collectors.toList());

        cmbCurso.setItems(FXCollections.observableArrayList(nombresCursos));
    }

    @FXML
    private void handleGenerarReporteGeneral() {
        try {
            // Crea una instancia de Reporte general y lo genera (imprime a consola)
            Reporte reporteGeneral = new Reporte("Reporte General de Actividades y Listados");
            reporteGeneral.generarReporte();

            mostrarMensaje("Éxito", "Reporte general generado (Ver Consola).", AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarMensaje("Error", "Error al generar el reporte general: " + e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    private void handleGenerarReporteProgreso() {
        try {
            String cursoNombre = cmbCurso.getValue();
            double nota = Double.parseDouble(txtNota.getText());
            String comentario = txtComentario.getText();

            if (cursoNombre == null || cursoNombre.isEmpty()) {
                mostrarMensaje("Advertencia", "Debe seleccionar un curso.", AlertType.WARNING);
                return;
            }

            // Encuentra el curso por su nombre completo (Instrumento Nivel X)
            Curso cursoSeleccionado = controller.getCursos().stream()
                    .filter(c -> c.getNombre().equals(cursoNombre))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado."));

            // Crea y carga el reporte de progreso (simulación en consola)
            ReporteProgreso reporteProgreso = new ReporteProgreso(cursoSeleccionado, nota, comentario);
            reporteProgreso.cargarReporte();

            mostrarMensaje("Éxito", "Reporte de progreso generado (Ver Consola).", AlertType.INFORMATION);
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarMensaje("Error de Formato", "La nota debe ser un número válido.", AlertType.ERROR);
        } catch (Exception e) {
            mostrarMensaje("Error", "Error al generar el reporte de progreso: " + e.getMessage(), AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        cmbCurso.setValue(null);
        txtNota.clear();
        txtComentario.clear();
    }

    private void mostrarMensaje(String titulo, String contenido, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}