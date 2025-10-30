package co.edu.uniquindio.poo.proyectofinal2.modelo;

import java.util.ArrayList;
import java.util.List;

public class ClaseGrupal extends Clase {
    private int capacidad;
    private List<Estudiante> estudiantes = new ArrayList<>();

    public ClaseGrupal(String id, Curso curso, String horario, Aula aula, String profesorId, int capacidad) {
        super(id, curso, horario, aula, profesorId, false);
        this.capacidad = capacidad;
    }

    public boolean agregarEstudiante(Estudiante e) {
        if (estudiantes.size() < capacidad) {
            estudiantes.add(e);
            return true;
        }
        return false;
    }

    public int getCapacidad() { return capacidad; }
    public List<Estudiante> getEstudiantes() { return estudiantes; }

    @Override
    public String toString() {
        return "Grupal " + curso + " horario:" + horario + " aula:" + (aula != null ? aula.getNombre() : "sin aula");
    }
}