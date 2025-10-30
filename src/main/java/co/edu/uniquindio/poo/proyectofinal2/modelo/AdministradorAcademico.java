package co.edu.uniquindio.poo.proyectofinal2.modelo;

import java.util.ArrayList;
import java.util.List;

public class AdministradorAcademico extends Persona {

    private List<Profesor> profesores = new ArrayList<>();
    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Curso> cursos = new ArrayList<>();
    private List<Aula> aulas = new ArrayList<>();

    public AdministradorAcademico(String nombre, String id, String correo) {
        super(nombre, id, correo);
    }

    public void agregarProfesor(Profesor profesor) {
        profesores.add(profesor);
    }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public void crearCurso(Curso curso) {
        cursos.add(curso);
    }

    public void agregarAula(Aula aula) {
        aulas.add(aula);
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Aula> getAulas() {
        return aulas;
    }
}