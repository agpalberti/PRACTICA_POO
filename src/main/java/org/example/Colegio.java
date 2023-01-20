package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Colegio implements Beca {

    // Atributos de la clase
    private String nombre;
    private String direccion;
    private int numMaxAlumnos;
    private int numMaxProfesores;
    private Profesor[] profesores;
    private Alumno[] alumnos;

    private Curso[] cursos;

    // Constructor por defecto
    public Colegio() {
    }

    // Constructor con todos los parámetros
    public Colegio(String nombre, String direccion, int numMaxAlumnos, int numMaxProfesores, Curso[] cursos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numMaxAlumnos = numMaxAlumnos;
        this.numMaxProfesores = numMaxProfesores;
        this.profesores = new Profesor[numMaxProfesores];
        this.alumnos = new Alumno[numMaxAlumnos];
        setCursos(cursos);
    }

    // Constructor sin cursos
    public Colegio(String nombre, String direccion, int numMaxAlumnos, int numMaxProfesores) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numMaxAlumnos = numMaxAlumnos;
        this.numMaxProfesores = numMaxProfesores;
        this.profesores = new Profesor[numMaxProfesores];
        this.alumnos = new Alumno[numMaxAlumnos];
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumMaxAlumnos() {
        return numMaxAlumnos;
    }

    public void setNumMaxAlumnos(int numMaxAlumnos) {
        this.numMaxAlumnos = numMaxAlumnos;
    }

    public int getNumMaxProfesores() {
        return numMaxProfesores;
    }

    public void setNumMaxProfesores(int numMaxProfesores) {
        this.numMaxProfesores = numMaxProfesores;
    }

    public Alumno[] getAlumnos() {
        return alumnos;
    }

    public Profesor[] getProfesores() {
        return profesores;
    }

    public Curso[] getCursos() {
        return cursos;
    }

    public void setCursos(Curso[] cursos) {
        this.cursos = cursos;

        for (Curso curso : cursos) {
            if (curso!=null) matricularCurso(curso);
        }
    }

    public int getNumeroAlumnos() {
        int numeroAlumnos = 0;

        for (Alumno alumno : alumnos) {
            if (alumno != null) numeroAlumnos++;
        }

        return numeroAlumnos;
    }

    public int getNumeroProfesores() {
        int numeroProfesores = 0;

        for (Profesor profesor : profesores) {
            if (profesor != null) numeroProfesores++;
        }

        return numeroProfesores;
    }


    // Métodos públicos

    // Busca un alumno según su DNI. Si no lo encuentra devuelve null
    public Alumno getAlumnoPorDNI(String dni) {
        if (!Arrays.stream(alumnos).toList().isEmpty()) {
            for (Alumno alumno : alumnos) {
                if (alumno != null) {
                    if (Objects.equals(alumno.dni, dni)) return alumno;
                }

            }
        }
        return null;
    }

    // Busca un profesor según su DNI. Si no lo encuentra devuelve null
    public Profesor getProfesorPorDNI(String dni) {
        if (!Arrays.stream(profesores).toList().isEmpty()) {
            for (Profesor profesor : profesores) {
                if (profesor != null) {
                    if (Objects.equals(profesor.dni, dni)) return profesor;
                }
            }
        }
        return null;
    }

    // Busca un curso según su denominación. Si no lo encuentra devuelve null
    public Curso getCursoPorCursoYGrupo(int cursoNumerico, char grupo) {
        if (!Arrays.stream(cursos).toList().isEmpty()) {
            for (Curso curso : cursos) {
                if (curso != null) {
                    if (Objects.equals(curso.getCurso(), cursoNumerico) && Objects.equals(curso.getGrupo(), grupo))
                        return curso;
                }
            }
        }
        return null;
    }

    // Intenta matricular a un estudiante a un curso. Si no existe ningún curso, lo crea
    public boolean matricularEstudiante(Alumno alumno) {
        int primeraPosicionVacia = encontrarPrimeraPosicionVacia(Arrays.stream(alumnos).toList());
        boolean matriculado = false;

        if (primeraPosicionVacia != -1) {

            comprobarExistenciaCurso();

            for (Curso curso : cursos) {

                int posicionAlumno = encontrarPrimeraPosicionVacia(Arrays.stream(curso.getAlumnos()).toList());
                if (posicionAlumno != -1) {

                    alumno.setCurso(curso.getCurso());
                    alumno.setGrupo(curso.getGrupo());
                    alumno.setMatriculado(true);

                    Alumno[] nuevosAlumnos = new Alumno[]{(curso.getAlumnos()[posicionAlumno] = alumno)};

                    curso.setAlumnos(nuevosAlumnos);
                    alumnos[primeraPosicionVacia] = alumno;

                    matriculado = true;

                    break;

                }
            }

            return matriculado;
        } else {
            return false;
        }
    }

    public boolean contratarProfesor(Profesor profesor) {

        int primeraPosicionVacia = encontrarPrimeraPosicionVacia(Arrays.stream(profesores).toList());

        if (primeraPosicionVacia != -1) {
            profesores[primeraPosicionVacia] = profesor;

            for (Curso curso : cursos) {
                if (curso.getTutor() != null) {
                    curso.setTutor(profesor);
                    break;
                }

            }

            return true;
        } else {
            return false;
        }

    }

    public boolean expulsarEstudiante(String dni) {

        Alumno alumno = getAlumnoPorDNI(dni);
        if (alumno != null) {
            int posicionAlumno = Arrays.stream(alumnos).toList().indexOf(alumno);

            if (posicionAlumno != -1) {
                alumnos[posicionAlumno] = null;

                for (Curso curso : cursos) {


                    if (Arrays.stream(curso.getAlumnos()).toList().contains(alumno)) {
                        int posicionAlumnoCurso = Arrays.stream(curso.getAlumnos()).toList().indexOf(alumno);

                        Alumno[] nuevosAlumnos = new Alumno[]{(curso.getAlumnos()[posicionAlumnoCurso] = null)};

                        curso.setAlumnos(nuevosAlumnos);

                    }
                }

                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public boolean despedirProfesor(String dni) {
        Profesor profesor = getProfesorPorDNI(dni);
        int posicionProfesor = Arrays.stream(profesores).toList().indexOf(profesor);

        if (posicionProfesor != -1) {
            profesores[posicionProfesor] = null;

            for (Curso curso : cursos) {


                if (curso.getTutor() == profesor) {

                    curso.setTutor(null);
                }

                for (Asignatura asignatura : curso.getAsignaturas()) {
                    if (asignatura.getProfesor() == profesor) {
                        asignatura.setProfesor(null);
                    }
                }
            }


            return true;
        } else {
            return false;
        }
    }

    public boolean crearCurso(Curso curso) {
        int primeraPosicionVacia = encontrarPrimeraPosicionVacia(Arrays.stream(cursos).toList());

        if (primeraPosicionVacia != -1) {
            cursos[primeraPosicionVacia] = curso;

            matricularCurso(curso);

            return true;
        }
        return false;

    }


    public boolean pasarDeCurso(int cursoNumerico, char grupo) {
        Curso curso = getCursoPorCursoYGrupo(cursoNumerico, grupo);
        if (curso != null) {
            int index = Arrays.stream(cursos).toList().indexOf(curso);
            return cursos[index].pasarDeCurso();
        } else return false;

    }

    public boolean graduarCurso(int cursoNumerico, char grupo){
        Curso curso = getCursoPorCursoYGrupo(cursoNumerico,grupo);
        if (curso!=null){
            int index = Arrays.stream(cursos).toList().indexOf(curso);
            return cursos[index].graduar();
        } else return false;
    }

    public boolean aniadirAsignaturaACurso(Asignatura asignatura, int cursoNumerico, char grupo){
        Curso curso = getCursoPorCursoYGrupo(cursoNumerico,grupo);
        if (curso!=null){
            Asignatura[] nuevasAsignaturas = curso.getAsignaturas();
            if(nuevasAsignaturas != null){
                int indexNuevaAsignatura = encontrarPrimeraPosicionVacia(Arrays.stream(nuevasAsignaturas).toList());
                if (indexNuevaAsignatura != -1){
                    nuevasAsignaturas[indexNuevaAsignatura] = asignatura;
                    int indexCurso = Arrays.stream(cursos).toList().indexOf(curso);
                    cursos[indexCurso].setAsignaturas(nuevasAsignaturas);
                    return true;
                }
            }
        }
        return false;
    }

    // Métodos privados

    private boolean matricularCurso(Curso curso) {

        int primeraPosicionVaciaAlumno = encontrarPrimeraPosicionVacia(Arrays.stream(alumnos).toList());
        int primeraPosicionVaciaProfesor = encontrarPrimeraPosicionVacia(Arrays.stream(profesores).toList());
        if (primeraPosicionVaciaProfesor != -1 && primeraPosicionVaciaAlumno != -1) {


            for (Alumno alumno : curso.getAlumnos()) {
                if (alumno != null && primeraPosicionVaciaAlumno != -1) {
                    alumnos[primeraPosicionVaciaAlumno] = alumno;
                }
                primeraPosicionVaciaAlumno = encontrarPrimeraPosicionVacia(Arrays.stream(alumnos).toList());
            }

            profesores[primeraPosicionVaciaProfesor] = curso.getTutor();
            primeraPosicionVaciaProfesor = encontrarPrimeraPosicionVacia(Arrays.stream(profesores).toList());
            for (Asignatura asignatura : curso.getAsignaturas()) {
               if (asignatura!= null && primeraPosicionVaciaProfesor != -1){
                   if (asignatura.getProfesor() != null){
                       profesores[primeraPosicionVaciaProfesor] = asignatura.getProfesor();
                   }
                   primeraPosicionVaciaProfesor = encontrarPrimeraPosicionVacia(Arrays.stream(profesores).toList());
               }

            }
            return true;
        }
        return false;


    }

    private void comprobarExistenciaCurso() {

        boolean existeCurso = false;

        for (Curso curso : cursos) {
            if (curso != null) {
                existeCurso = true;
                break;
            }
        }

        if (!existeCurso) {
            crearCursoNuevo();
        }

    }

    private void crearCursoNuevo() {
        int primeraPosicionVacia = encontrarPrimeraPosicionVacia(Arrays.stream(cursos).toList());
        cursos[primeraPosicionVacia] = new Curso();
    }

    private int encontrarPrimeraPosicionVacia(List<?> lista) {
        return lista.indexOf(null);
    }

    // Implementación de la inferfaz
    @Override
    public boolean darBeca(String dni) {
        boolean becaDada = false;
        Alumno alumno = getAlumnoPorDNI(dni);

        if (alumno != null) {
            int posicionAlumno = Arrays.stream(alumnos).toList().indexOf(alumno);
            alumnos[posicionAlumno].setBecado(true);

            for (Curso curso : cursos) {

                if (Arrays.stream(curso.getAlumnos()).toList().contains(alumno)) {

                    int posicionAlumnoCurso = Arrays.stream(curso.getAlumnos()).toList().indexOf(alumno);

                    alumno.setBecado(true);
                    Alumno[] nuevosAlumnos = new Alumno[]{(curso.getAlumnos()[posicionAlumnoCurso] = alumno)};

                    curso.setAlumnos(nuevosAlumnos);

                    becaDada = true;
                    break;
                }
            }
            return becaDada;
        }
        return false;

    }

}
