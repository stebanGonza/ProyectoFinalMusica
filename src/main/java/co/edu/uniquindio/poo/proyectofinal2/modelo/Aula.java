package co.edu.uniquindio.poo.proyectofinal2.modelo;

public class Aula {
    private String id;
    private String nombre;
    private int capacidad;

    public Aula(String id, String nombre, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }

    @Override
    public String toString() { return nombre + " (cap " + capacidad + ")"; }

    public String getCodigo() {

        return "";
    }}