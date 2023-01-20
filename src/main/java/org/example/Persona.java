package org.example;

public abstract class Persona {

    // Atributos de la clase

    protected String dni;
    protected String nombre;
    protected int edad;
    protected char sexo;
    protected String direccion;


    // Getter y setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {

        if (sexo == 'H' || sexo == 'M'){
            this.sexo = sexo;
        } else sexo = 'H';

    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // toString
    @Override
    public String toString() {
        return " DNI = " + dni +
                ", nombre = " + nombre +
                ", edad = " + edad +
                ", sexo = " + sexo +
                ", direccion = " + direccion ;
    }
}
