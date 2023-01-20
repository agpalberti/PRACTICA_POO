package org.example;

import java.util.Arrays;

public class Curso implements Cursable {

    private Asignatura[] asignaturas = new Asignatura[8];
    private Alumno[] alumnos = new Alumno[20];
    private Profesor tutor;
    private int anio;
    private int curso;
    private char grupo;
    private boolean graduado = false;

    public Curso(){}

    public Curso(Profesor tutor,int anio, int curso, char grupo){
        this.tutor = tutor;
        this.anio = anio;
        this.curso = curso;
        this.grupo = grupo;
    }

    public Curso(Profesor tutor, char grupo){
        this.tutor = tutor;
        this.anio = 2023;
        this.curso = 1;
        this.grupo = grupo;
    }

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

    @Override
    public String toString() {
        return "Tutor = " + tutor.nombre +
                ", anio = " + anio +
                ", grupo = " + curso + grupo;
    }

    @Override
    public boolean pasarDeCurso() {
        if (!graduado){
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
