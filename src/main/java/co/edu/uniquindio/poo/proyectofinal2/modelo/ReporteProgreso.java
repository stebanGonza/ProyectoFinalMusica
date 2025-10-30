package co.edu.uniquindio.poo.proyectofinal2.modelo;

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
}
