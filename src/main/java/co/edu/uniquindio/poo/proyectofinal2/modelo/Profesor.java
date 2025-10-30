package co.edu.uniquindio.poo.proyectofinal2.modelo;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends Persona {
    private List<Clase> clasesAsignadas = new ArrayList<>();

    public Profesor(String nombre, String documento, String correo) {
        super(nombre, documento, correo);
    }

    public void agregarClase(Clase clase) {
        clasesAsignadas.add(clase);
    }

    public List<Clase> getClasesAsignadas() {
        return clasesAsignadas;
    }
}
