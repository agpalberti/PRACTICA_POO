package org.example;

public class Asignatura {

    String nombre;
    Profesor profesor;
    int horasSemanales;
    String descripcion;


    public Asignatura() {}

    public Asignatura(String nombre, Profesor profesor, int horasSemanales, String descripcion){
        this.nombre = nombre;
        this.profesor = profesor;
        this.horasSemanales = horasSemanales;
        this.descripcion = descripcion;
    }

    public Asignatura(String nombre, Profesor profesor){
        this.nombre = nombre;
        this.profesor = profesor;
    }


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

    @Override
    public String toString() {
        return "Nombre = " + nombre +
                ", profesor = " + profesor +
                ", horasSemanales = " + horasSemanales +
                ", descripcion = " + descripcion;
    }
}
