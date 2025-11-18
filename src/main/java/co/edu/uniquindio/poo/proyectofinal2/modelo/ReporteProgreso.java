package co.edu.uniquindio.poo.proyectofinal2.modelo;


// Se elimina la importación de javafx.event.ActionEvent para separar la lógica de negocio de la UI.

public class ReporteProgreso {
    private Curso curso;
    private String comentario;
    private double nota;

    public ReporteProgreso(Curso curso, double nota, String comentario) {
        this.curso = curso;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Curso getCurso() { return curso; }
    public double getNota() { return nota; }
    public String getComentario() { return comentario; }

    /**
     * Simula la carga de un reporte de progreso.
     * En una aplicación real, esto guardaría datos en una BD o un archivo.
     */
    public void cargarReporte() {
        System.out.println("--- Reporte de Progreso Cargado ---");
        System.out.println("Curso: " + curso.getNombre());
        System.out.println("Nota Final: " + nota);
        System.out.println("Comentario: " + comentario);
        System.out.println("-----------------------------------");
    }
}