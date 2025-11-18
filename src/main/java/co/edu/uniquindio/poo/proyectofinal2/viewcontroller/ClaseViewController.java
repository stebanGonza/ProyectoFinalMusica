package co.edu.uniquindio.poo.proyectofinal2.viewcontroller;



import co.edu.uniquindio.poo.proyectofinal2.controller.AdministracionController;
import co.edu.uniquindio.poo.proyectofinal2.modelo.Clase;
import co.edu.uniquindio.poo.proyectofinal2.modelo.ClaseGrupal;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.util.stream.Collectors;

public class ClaseViewController {

    @FXML private TableView<Clase> tablaClases;
    @FXML private TableColumn<Clase, String> colTipo;
    @FXML private TableColumn<Clase, String> colCurso;
    @FXML private TableColumn<Clase, String> colProfesor;
    @FXML private TableColumn<Clase, String> colHorario;

    @FXML private ComboBox<String> cmbCurso;
    @FXML private ComboBox<String> cmbProfesor;
    @FXML private ComboBox<String> cmbAula;
    @FXML private TextField txtIdClase;
    @FXML private TextField txtHorario;
    @FXML private TextField txtCapacidad; // Solo para Grupal
    @FXML private ComboBox<String> cmbEstudiante; // Solo para Individual
    @FXML private RadioButton rbGrupal;
    @FXML private RadioButton rbIndividual;

    private final AdministracionController controller = new AdministracionController();

    @FXML
    public void initialize() {
        colCurso.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(
                cellData.getValue().getCurso().getInstrumento() + " Nivel " + cellData.getValue().getCurso().getNivel()));
        colTipo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().isIndividual() ? "Individual" : "Grupal"));
        colProfesor.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getProfesorId()));
        colHorario.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getHorario()));

        ToggleGroup grupoTipo = new ToggleGroup();
        rbGrupal.setToggleGroup(grupoTipo);
        rbIndividual.setToggleGroup(grupoTipo);

        cargarComboBoxes();
        cargarTablaClases(controller.listarProfesores().get(0).getDocumento()); // Muestra clases del primer profesor
    }

    private void cargarComboBoxes() {
        cmbCurso.setItems(FXCollections.observableArrayList(
                controller.getCursos().stream().map(c -> c.getInstrumento() + " Nivel " + c.getNivel()).collect(Collectors.toList())
        ));
        cmbProfesor.setItems(FXCollections.observableArrayList(
                controller.listarProfesores().stream().map(p -> p.getNombre() + " (" + p.getDocumento() + ")").collect(Collectors.toList())
        ));
        cmbAula.setItems(FXCollections.observableArrayList(
                controller.listarAulas().stream().map(a -> a.getNombre() + " (" + a.getId() + ")").collect(Collectors.toList())
        ));
        cmbEstudiante.setItems(FXCollections.observableArrayList(
                controller.listarEstudiantes().stream().map(e -> e.getNombre() + " (" + e.getDocumento() + ")").collect(Collectors.toList())
        ));
    }

    private void cargarTablaClases(String documentoProfesor) {
        tablaClases.setItems(FXCollections.observableArrayList(controller.listarClasesPorProfesor(documentoProfesor)));
    }

    @FXML
    private void handleCrearClase() {
        try {
            String cursoStr = cmbCurso.getValue();
            String profDoc = cmbProfesor.getValue().split("[()]")[1];
            String aulaId = cmbAula.getValue().split("[()]")[1];

            String[] partesCurso = cursoStr.split(" Nivel ");
            String instrumento = partesCurso[0];
            int nivel = Integer.parseInt(partesCurso[1]);

            if (rbGrupal.isSelected()) {
                int capacidad = Integer.parseInt(txtCapacidad.getText());
                controller.crearClaseGrupal(txtIdClase.getText(), instrumento, nivel, txtHorario.getText(), aulaId, profDoc, capacidad);

            } else if (rbIndividual.isSelected()) {
                String estDoc = cmbEstudiante.getValue().split("[()]")[1];
                controller.crearClaseIndividual(txtIdClase.getText(), instrumento, nivel, txtHorario.getText(), aulaId, profDoc, estDoc);

            } else {
                throw new IllegalStateException("Debe seleccionar Grupal o Individual.");
            }

            mostrarMensaje("Éxito", "Clase creada y asignada al profesor.", AlertType.INFORMATION);
            limpiarCampos();
            cargarTablaClases(profDoc);

        } catch (Exception e) {
            mostrarMensaje("Error", "Error al crear la clase: " + e.getMessage(), AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtIdClase.clear();
        txtHorario.clear();
        txtCapacidad.clear();
        cmbCurso.setValue(null);
        cmbProfesor.setValue(null);
        cmbAula.setValue(null);
        cmbEstudiante.setValue(null);
    }

    private void mostrarMensaje(String titulo, String contenido, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
