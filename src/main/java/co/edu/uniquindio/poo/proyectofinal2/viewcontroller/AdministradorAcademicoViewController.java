package co.edu.uniquindio.poo.proyectofinal2.viewcontroller;

import co.edu.uniquindio.poo.proyectofinal2.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class AdministradorAcademicoViewController {

    @FXML
    private TableView<Profesor> tablaProfesores;
    @FXML
    private TableColumn<Profesor, String> colNombreProfesor;
    @FXML
    private TableColumn<Profesor, String> colIdProfesor;
    @FXML
    private TableColumn<Profesor, String> colCorreoProfesor;

    @FXML
    private TableView<Estudiante> tablaEstudiantes;
    @FXML
    private TableColumn<Estudiante, String> colNombreEstudiante;
    @FXML
    private TableColumn<Estudiante, String> colIdEstudiante;
    @FXML
    private TableColumn<Estudiante, String> colCorreoEstudiante;

    @FXML
    private TableView<Curso> tablaCursos;
    @FXML
    private TableColumn<Curso, String> colNombreCurso;
    @FXML
    private TableColumn<Curso, String> colCodigoCurso;

    @FXML
    private TableView<Aula> tablaAulas;
    @FXML
    private TableColumn<Aula, String> colCodigoAula;
    @FXML
    private TableColumn<Aula, Integer> colCapacidadAula;

    @FXML
    private TextField txtNombreProfesor, txtIdProfesor, txtCorreoProfesor;
    @FXML
    private TextField txtNombreEstudiante, txtIdEstudiante, txtCorreoEstudiante;
    @FXML
    private TextField txtNombreCurso, txtCreditosCurso;
    @FXML
    private TextField txtCodigoAula, txtNombreAula, txtCapacidadAula;

    private AdministradorAcademico administrador;

    private ObservableList<Profesor> listaProfesores;
    private ObservableList<Estudiante> listaEstudiantes;
    private ObservableList<Curso> listaCursos;
    private ObservableList<Aula> listaAulas;

    @FXML
    public void initialize() {
        administrador = new AdministradorAcademico("Admin", "000", "admin@correo.com");

        listaProfesores = FXCollections.observableArrayList(administrador.getProfesores());
        listaEstudiantes = FXCollections.observableArrayList(administrador.getEstudiantes());
        listaCursos = FXCollections.observableArrayList(administrador.getCursos());
        listaAulas = FXCollections.observableArrayList(administrador.getAulas());

        tablaProfesores.setItems(listaProfesores);
        tablaEstudiantes.setItems(listaEstudiantes);
        tablaCursos.setItems(listaCursos);
        tablaAulas.setItems(listaAulas);

        colNombreProfesor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colIdProfesor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDocumento()));
        colCorreoProfesor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCorreo()));

        colNombreEstudiante.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colIdEstudiante.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDocumento()));
        colCorreoEstudiante.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCorreo()));

        colNombreCurso.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colCodigoCurso.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCodigo())); // Ajustar según tu getter real

        colCodigoAula.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCodigo())); // Ajustar según tu getter real
        colCapacidadAula.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getCapacidad()).asObject());
    }

    @FXML
    private void agregarProfesor() {
        Profesor profesor = new Profesor(txtNombreProfesor.getText(), txtIdProfesor.getText(), txtCorreoProfesor.getText());
        administrador.agregarProfesor(profesor);
        listaProfesores.add(profesor);
        txtNombreProfesor.clear(); txtIdProfesor.clear(); txtCorreoProfesor.clear();
    }

    @FXML
    private void agregarEstudiante() {
        Estudiante estudiante = new Estudiante(txtNombreEstudiante.getText(), txtIdEstudiante.getText(), txtCorreoEstudiante.getText());
        administrador.agregarEstudiante(estudiante);
        listaEstudiantes.add(estudiante);
        txtNombreEstudiante.clear(); txtIdEstudiante.clear(); txtCorreoEstudiante.clear();
    }

    @FXML
    private void agregarCurso() {
        int creditos = Integer.parseInt(txtCreditosCurso.getText());
        Curso curso = new Curso(txtNombreCurso.getText(), creditos); // Ajustar según constructor real
        administrador.crearCurso(curso);
        listaCursos.add(curso);
        txtNombreCurso.clear(); txtCreditosCurso.clear();
    }

    @FXML
    private void agregarAula() {
        int capacidad = Integer.parseInt(txtCapacidadAula.getText());
        Aula aula = new Aula(txtCodigoAula.getText(), txtNombreAula.getText(), capacidad); // Ajustar según constructor real
        administrador.agregarAula(aula);
        listaAulas.add(aula);
        txtCodigoAula.clear(); txtNombreAula.clear(); txtCapacidadAula.clear();
    }

}
