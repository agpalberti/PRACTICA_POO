package org.example;

import java.util.Arrays;

public class Curso implements Cursable {

    // Atributos de la clase

    private Asignatura[] asignaturas = new Asignatura[8];
    private Alumno[] alumnos = new Alumno[20];
    private Profesor tutor;
    private int anio;
    private int curso;
    private char grupo;
    private boolean graduado = false;

    // Constructor por defecto
    public Curso(){}

    // Constructor con todos los parámetros menos graduado
    public Curso(Profesor tutor,int anio, int curso, char grupo){
        this.tutor = tutor;
        this.anio = anio;
        this.curso = curso;
        this.grupo = grupo;
    }

    // Constructor más simple con el tutor y grupo
    public Curso(Profesor tutor, char grupo){
        this.tutor = tutor;
        this.anio = 2023;
        this.curso = 1;
        this.grupo = grupo;
    }

    // Getter y setters
    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getCurso() {
        return curso;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getAnio() {
        return anio;
    }

    public void setTutor(Profesor tutor) {
        this.tutor = tutor;
    }

    public Profesor getTutor() {
        return tutor;
    }

    public void setAlumnos(Alumno[] alumnos) {
        this.alumnos = alumnos;
    }

    public Alumno[] getAlumnos() {
        return alumnos;
    }

    public void setAsignaturas(Asignatura[] asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Asignatura[] getAsignaturas() {
        return asignaturas;
    }

    // toString
    @Override
    public String toString() {
        if (tutor !=null) {
           return "Tutor = " + tutor.nombre +
                    ", anio = " + anio +
                    ", grupo = " + curso + grupo+
                   ", graduado = " + graduado;
        }

        else return "Grupo = " + curso + grupo + ", anio = " + anio + ", graduado = " + graduado;

    }

    // Implementación de la interfaz
    @Override
    public boolean pasarDeCurso() {
        if (!graduado && curso < 6){
            curso++;
            anio++;
            return true;
        }
        return false;
    }

    @Override
    public boolean graduar() {
        if (!graduado && curso == 6) {
            graduado = true;
            return true;
        }
        return false;
    }
}
