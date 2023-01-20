package org.example;

public class Asignatura {

    // Atributos de la clase
    private String nombre;
    private Profesor profesor;
    private int horasSemanales;
    private String descripcion;

    // Constructor por defecto
    public Asignatura() {}

    // Constructor con todos los parámetros
    public Asignatura(String nombre, Profesor profesor, int horasSemanales, String descripcion){
        this.nombre = nombre;
        this.profesor = profesor;
        this.horasSemanales = horasSemanales;
        this.descripcion = descripcion;
    }

    // Constructor más simple con el nombre de la asignatura
    public Asignatura(String nombre){
        this.nombre = nombre;
    }

    // Getter y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    // Método toString
    @Override
    public String toString() {
        return "Nombre = " + nombre +
                ", profesor = " + profesor +
                ", horasSemanales = " + horasSemanales +
                ", descripcion = " + descripcion;
    }
}
