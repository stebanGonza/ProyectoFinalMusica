package co.edu.uniquindio.poo.proyectofinal2.modelo;

import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Persona {
    private List<Curso> cursosMatriculados = new ArrayList<>();

    public Estudiante(String nombre, String documento, String correo) {
        super(nombre, documento, correo);
    }

    public void inscribirCurso(Curso curso) {
        if (curso.tieneCupo()) {
            cursosMatriculados.add(curso);
            curso.agregarEstudiante(this);
        } else {
            System.out.println("El curso no tiene cupos disponibles.");
        }
    }

    public List<Curso> getCursosMatriculados() {
        return cursosMatriculados;
    }
}
