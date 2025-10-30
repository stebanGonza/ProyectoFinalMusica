package co.edu.uniquindio.poo.proyectofinal2.modelo;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String instrumento;
    private int nivel;
    private int capacidad;
    private List<Estudiante> estudiantes = new ArrayList<>();

    public Curso(String instrumento, int nivel, int capacidad) {
        this.instrumento = instrumento;
        this.nivel = nivel;
        this.capacidad = capacidad;
    }

    public boolean tieneCupo() {
        return estudiantes.size() < capacidad;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        if (tieneCupo()) {
            estudiantes.add(estudiante);
        }
    }

    public String getInstrumento() { return instrumento; }
    public int getNivel() { return nivel; }
    public int getCapacidad() { return capacidad; }
}
