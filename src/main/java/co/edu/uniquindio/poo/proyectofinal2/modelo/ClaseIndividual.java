package co.edu.uniquindio.poo.proyectofinal2.modelo;

public class ClaseIndividual extends Clase {
    private Estudiante estudiante;

    public ClaseIndividual(String id, Curso curso, String horario, Aula aula, String profesorId, Estudiante estudiante) {
        super(id, curso, horario, aula, profesorId, true);
        this.estudiante = estudiante;
    }

    public Estudiante getEstudiante() { return estudiante; }

    @Override
    public String toString() {
        return "Individual " + curso + " para " + (estudiante != null ? estudiante.getNombre() : "sin estudiante");
    }
}