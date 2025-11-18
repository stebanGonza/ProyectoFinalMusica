package co.edu.uniquindio.poo.proyectofinal2.controller;

import co.edu.uniquindio.poo.proyectofinal2.modelo.*;
import java.util.List;
import java.util.Optional;

public class AdministracionController {

    private AdministradorAcademico administrador;

    public AdministracionController() {
        this.administrador = new AdministradorAcademico("Admin Principal", "123456", "admin@musica.com");
        cargarDatosIniciales();
    }

    // ===================================
    // Inicialización y Búsqueda Común
    // ===================================

    private void cargarDatosIniciales() {
        // Profesores
        Profesor p1 = new Profesor("Ana García", "P001", "ana@mail.com");
        Profesor p2 = new Profesor("Beto López", "P002", "beto@mail.com");
        administrador.agregarProfesor(p1);
        administrador.agregarProfesor(p2);

        // Estudiantes
        Estudiante e1 = new Estudiante("Carlos Ruiz", "E001", "carlos@mail.com");
        Estudiante e2 = new Estudiante("Diana Pérez", "E002", "diana@mail.com");
        administrador.agregarEstudiante(e1);
        administrador.agregarEstudiante(e2);

        // Cursos
        Curso c1 = new Curso("PIANO", 1, 10);
        Curso c2 = new Curso("GUITARRA", 2, 5);
        administrador.crearCurso(c1);
        administrador.crearCurso(c2);

        // Aulas
        Aula a1 = new Aula("A1", "Estudio Principal", 15);
        Aula a2 = new Aula("A2", "Sala Pequeña", 8);
        administrador.agregarAula(a1);
        administrador.agregarAula(a2);

        // Clases
        ClaseGrupal cg1 = new ClaseGrupal("CL01", c1, "LUN_10", a1, "P001", 5);
        cg1.agregarEstudiante(e1);
        p1.agregarClase(cg1);

        ClaseIndividual ci1 = new ClaseIndividual("CL02", c2, "MAR_14", a2, "P002", e2);
        p2.agregarClase(ci1);
    }

    public Optional<Profesor> buscarProfesor(String documento) {
        return administrador.getProfesores().stream().filter(p -> p.getDocumento().equals(documento)).findFirst();
    }

    public Optional<Estudiante> buscarEstudiante(String documento) {
        return administrador.getEstudiantes().stream().filter(e -> e.getDocumento().equals(documento)).findFirst();
    }

    public Optional<Curso> buscarCurso(String instrumento, int nivel) {
        return administrador.getCursos().stream()
                .filter(c -> c.getInstrumento().equals(instrumento) && c.getNivel() == nivel)
                .findFirst();
    }

    public Optional<Aula> buscarAula(String id) {
        return administrador.getAulas().stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    // ===================================
    // CRUD PROFESOR
    // ===================================

    public List<Profesor> listarProfesores() { return administrador.getProfesores(); }

    public Profesor crearProfesor(String nombre, String documento, String correo) {
        if (buscarProfesor(documento).isPresent()) { throw new IllegalArgumentException("Ya existe un profesor con ese documento."); }
        Profesor nuevoProfesor = new Profesor(nombre, documento, correo);
        administrador.agregarProfesor(nuevoProfesor);
        return nuevoProfesor;
    }

    public boolean actualizarProfesor(String documento, String nuevoNombre, String nuevoCorreo) {
        Optional<Profesor> profesorOpt = buscarProfesor(documento);
        if (profesorOpt.isPresent()) {
            Profesor p = profesorOpt.get();
            p.setNombre(nuevoNombre);
            p.setCorreo(nuevoCorreo);
            return true;
        }
        return false;
    }

    public boolean eliminarProfesor(String documento) {
        return administrador.getProfesores().removeIf(p -> p.getDocumento().equals(documento));
    }

    // ===================================
    // CRUD ESTUDIANTE
    // ===================================

    public List<Estudiante> listarEstudiantes() { return administrador.getEstudiantes(); }

    public Estudiante crearEstudiante(String nombre, String documento, String correo) {
        if (buscarEstudiante(documento).isPresent()) { throw new IllegalArgumentException("Ya existe un estudiante con ese documento."); }
        Estudiante nuevoEstudiante = new Estudiante(nombre, documento, correo);
        administrador.agregarEstudiante(nuevoEstudiante);
        return nuevoEstudiante;
    }

    public boolean actualizarEstudiante(String documento, String nuevoNombre, String nuevoCorreo) {
        Optional<Estudiante> estudianteOpt = buscarEstudiante(documento);
        if (estudianteOpt.isPresent()) {
            Estudiante e = estudianteOpt.get();
            e.setNombre(nuevoNombre);
            e.setCorreo(nuevoCorreo);
            return true;
        }
        return false;
    }

    public boolean eliminarEstudiante(String documento) {
        return administrador.getEstudiantes().removeIf(e -> e.getDocumento().equals(documento));
    }

    // ===================================
    // CRUD CURSO
    // ===================================

    // ⭐ CORREGIDO: getCursos() es el método para listar
    public List<Curso> getCursos() { return administrador.getCursos(); }

    public Curso crearCurso(String instrumento, int nivel, int capacidad) {
        if (buscarCurso(instrumento, nivel).isPresent()) {
            throw new IllegalArgumentException("Ya existe un curso de " + instrumento + " Nivel " + nivel);
        }
        Curso nuevoCurso = new Curso(instrumento, nivel, capacidad);
        administrador.crearCurso(nuevoCurso);
        return nuevoCurso;
    }

    public boolean eliminarCurso(String instrumento, int nivel) {
        return administrador.getCursos().removeIf(c -> c.getInstrumento().equals(instrumento) && c.getNivel() == nivel);
    }

    // ===================================
    // CRUD AULA
    // ===================================

    public List<Aula> listarAulas() { return administrador.getAulas(); }

    public Aula crearAula(String id, String nombre, int capacidad) {
        if (buscarAula(id).isPresent()) { throw new IllegalArgumentException("Ya existe un aula con ese ID."); }
        Aula nuevaAula = new Aula(id, nombre, capacidad);
        administrador.agregarAula(nuevaAula);
        return nuevaAula;
    }

    public boolean eliminarAula(String id) {
        return administrador.getAulas().removeIf(a -> a.getId().equals(id));
    }

    // ===================================
    // GESTIÓN DE CLASES
    // ===================================

    public List<Clase> listarClasesPorProfesor(String documentoProfesor) {
        return buscarProfesor(documentoProfesor).map(Profesor::getClasesAsignadas).orElse(List.of());
    }

    public ClaseGrupal crearClaseGrupal(String id, String cursoInstr, int cursoNivel, String horario, String aulaId, String profId, int capacidad) {
        Curso curso = buscarCurso(cursoInstr, cursoNivel).orElseThrow(() -> new IllegalArgumentException("Curso no encontrado."));
        Aula aula = buscarAula(aulaId).orElse(null);
        Profesor profesor = buscarProfesor(profId).orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado."));

        ClaseGrupal nuevaClase = new ClaseGrupal(id, curso, horario, aula, profId, capacidad);
        profesor.agregarClase(nuevaClase);
        return nuevaClase;
    }

    public ClaseIndividual crearClaseIndividual(String id, String cursoInstr, int cursoNivel, String horario, String aulaId, String profId, String estId) {
        Curso curso = buscarCurso(cursoInstr, cursoNivel).orElseThrow(() -> new IllegalArgumentException("Curso no encontrado."));
        Aula aula = buscarAula(aulaId).orElse(null);
        Profesor profesor = buscarProfesor(profId).orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado."));
        Estudiante estudiante = buscarEstudiante(estId).orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado."));

        ClaseIndividual nuevaClase = new ClaseIndividual(id, curso, horario, aula, profId, estudiante);
        profesor.agregarClase(nuevaClase);
        return nuevaClase;
    }

    public Curso obtenerCursos() {

        return null;
    }}