package co.edu.uniquindio.poo.proyectofinal2.modelo;

public abstract class Clase {
    protected String id;
    protected Curso curso;
    protected String horario; // representación simple "LUN_09" o fecha/intervalo
    protected Aula aula;
    protected String profesorId;
    protected boolean individual;

    public Clase(String id, Curso curso, String horario, Aula aula, String profesorId, boolean individual) {
        this.id = id;
        this.curso = curso;
        this.horario = horario;
        this.aula = aula;
        this.profesorId = profesorId;
        this.individual = individual;
    }

    public String getId() { return id; }
    public Curso getCurso() { return curso; }
    public String getHorario() { return horario; }
    public Aula getAula() { return aula; }
    public String getProfesorId() { return profesorId; }
    public boolean isIndividual() { return individual; }
}
