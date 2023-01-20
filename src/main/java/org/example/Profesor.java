package org.example;

public class Profesor extends Persona{

    // Atributos de la clase

    private String especialidad;
    private int antiguedad;
    private String materiasImpartidas;
    private boolean disponibilidad;

    // Constructor por defecto
    public Profesor() {
    }

    // Constructor con todos los parámetros
    public Profesor(String dni,
                    String nombre,
                    int edad,
                    char sexo,
                    String direccion,
                    String especialidad,
                    int antiguedad,
                    String materiasImpartidas,
                    boolean disponibilidad) {

        this.especialidad = especialidad;
        this.antiguedad = antiguedad;
        this.materiasImpartidas = materiasImpartidas;
        this.disponibilidad = disponibilidad;
        super.dni = dni;
        super.nombre = nombre;
        super.edad = edad;
        super.sexo = sexo;
        super.direccion = direccion;

    }

    // Constructor con todos los parámetros menos disponibilidad
    public Profesor(String dni,
                    String nombre,
                    int edad,
                    char sexo,
                    String direccion,
                    String especialidad,
                    int antiguedad,
                    String materiasImpartidas) {

        this.especialidad = especialidad;
        this.antiguedad = antiguedad;
        this.materiasImpartidas = materiasImpartidas;
        this.disponibilidad = true;
        super.dni = dni;
        super.nombre = nombre;
        super.edad = edad;
        super.sexo = sexo;
        super.direccion = direccion;

    }

    // Constructor para un profesor nuevo sin antigüedad
    public Profesor(String dni,
                    String nombre,
                    int edad,
                    char sexo,
                    String direccion,
                    String especialidad,
                    String materiasImpartidas) {

        this.especialidad = especialidad;
        this.antiguedad = 0;
        this.materiasImpartidas = materiasImpartidas;
        this.disponibilidad = true;
        super.dni = dni;
        super.nombre = nombre;
        super.edad = edad;
        super.sexo = sexo;
        super.direccion = direccion;

    }

    // Getter y setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getMateriasImpartidas() {
        return materiasImpartidas;
    }

    public void setMateriasImpartidas(String materiasImpartidas) {
        this.materiasImpartidas = materiasImpartidas;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + ", especialidad = " + especialidad +
                ", antiguedad = " + antiguedad +
                ", materias impartidas = " + materiasImpartidas +
                ", disponibilidad = " + disponibilidad;
    }

}
