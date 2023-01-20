package org.example;

public class Alumno extends Persona{

    private boolean repetidor;
    private int notaMedia;
    private boolean matriculado;
    private boolean becado;
    private int curso;
    private char grupo;


    public Alumno(){}

    public Alumno(String dni,
                    String nombre,
                    int edad,
                    char sexo,
                    String direccion,
                    boolean repetidor,
                    int notaMedia,
                    boolean matriculado,
                    boolean becado) {

        this.repetidor = repetidor;
        this.notaMedia = notaMedia;
        this.matriculado = matriculado;
        this.becado = becado;
        super.dni = dni;
        super.nombre = nombre;
        super.edad = edad;
        super.sexo = sexo;
        super.direccion = direccion;
    }

    public Alumno(String dni,
                  String nombre,
                  int edad,
                  char sexo,
                  String direccion) {

        this.repetidor = false;
        this.notaMedia = 0;
        this.matriculado = false;
        this.becado = false;
        super.dni = dni;
        super.nombre = nombre;
        super.edad = edad;
        super.sexo = sexo;
        super.direccion = direccion;
    }

    public boolean getRepetidor() {
        return repetidor;
    }

    public void setRepetidor(boolean repetidor) {
        this.repetidor = repetidor;
    }

    public int getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(int notaMedia) {
        this.notaMedia = notaMedia;
    }

    public boolean getMatriculado() {
        return matriculado;
    }

    public void setMatriculado(boolean matriculado) {
        this.matriculado = matriculado;
    }

    public boolean getBecado() {
        return becado;
    }

    public void setBecado(boolean becado) {
        this.becado = becado;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return super.toString() +  ", repetidor = " + repetidor
                +  ", nota media = " + notaMedia
                +  ", matriculado = " + matriculado
                +  ", becado = " + becado;
    }
}
