package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        // Llamamos al setter para que matricule al curso completo
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

    // Métodos para saber el número de alumnos y profesores
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
            // Para cada alumno, si no es null, comprueba que el dni de estos sean iguales que el del parámetro. Si coinciden, lo devuelve
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
            // Para cada profesor, si no es null, comprueba que el dni de estos sean iguales que el del parámetro. Si coinciden, lo devuelve
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
                // Para cada curso, si no es null, comprueba que los datos de estos sean iguales que los de los parámetros. Si coinciden, lo devuelve
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
        // Buscamos la primera posición del array donde hay un nulo
        int primeraPosicionVacia = encontrarPrimeraPosicionVacia(Arrays.stream(alumnos).toList());
        boolean matriculado = false;

        // Si no hay ningún espacio libre en el array alumnos, primeraPosicionVacia será -1 y acabará el método
        if (primeraPosicionVacia != -1) {

            // Comprobamos que el array de cursos no esté vacío, y si lo está, crea un curso
            comprobarExistenciaCurso();

            for (Curso curso : cursos) {
                // Buscamos en todos los cursos alguno en el que haya algún nulo en la lista de alumnos para poder meter al estudiante

                int posicionAlumno = encontrarPrimeraPosicionVacia(Arrays.stream(curso.getAlumnos()).toList());
                if (posicionAlumno != -1) {

                    // Una vez buscado, se rellenan los datos del estudiante con los datos del curso
                    alumno.setCurso(curso.getCurso());
                    alumno.setGrupo(curso.getGrupo());
                    alumno.setMatriculado(true);

                    Alumno[] nuevosAlumnos = new Alumno[]{(curso.getAlumnos()[posicionAlumno] = alumno)};

                    // Y actualizamos el array de alumnos del curso
                    curso.setAlumnos(nuevosAlumnos);
                    // Y se mete al alumno en la lista global de alumnos
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

        // Similar al método anterior
        int primeraPosicionVacia = encontrarPrimeraPosicionVacia(Arrays.stream(profesores).toList());

        if (primeraPosicionVacia != -1) {
            // Se añade a la lista completa de profesores
            profesores[primeraPosicionVacia] = profesor;

            for (Curso curso : cursos) {
                // Si hay algún curso sin tutor, se le asigna automáticamente como tutor, si no, se ignora este paso.
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
            // Buscamos el índice del array en el que se encuentra el alumno
            int posicionAlumno = Arrays.stream(alumnos).toList().indexOf(alumno);

            if (posicionAlumno != -1) {
                // Lo borramos de la lista
                alumnos[posicionAlumno] = null;

                for (Curso curso : cursos) {

                    // Y lo borramos de los cursos
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
        // Similar al método anterior
        int posicionProfesor = Arrays.stream(profesores).toList().indexOf(profesor);

        if (posicionProfesor != -1) {
            // Se borra
            profesores[posicionProfesor] = null;

            for (Curso curso : cursos) {

                // Si está como tutor en algún grupo, se borra también
                if (curso.getTutor() == profesor) {

                    curso.setTutor(null);
                }

                // Y si está como profesor también
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

    // Crea un curso proporcionado por parámetro y lo matricula
    public boolean crearYMatricularCurso(Curso curso) {
        int primeraPosicionVacia = encontrarPrimeraPosicionVacia(Arrays.stream(cursos).toList());

        if (primeraPosicionVacia != -1) {
            cursos[primeraPosicionVacia] = curso;

            matricularCurso(curso);

            return true;
        }
        return false;

    }

    // Hace que un curso pase de número de curso (de 1º a 2º por ejemplo)
    public boolean pasarDeCurso(int cursoNumerico, char grupo) {
        Curso curso = getCursoPorCursoYGrupo(cursoNumerico, grupo);
        if (curso != null) {
            // Busca el índice del curso y llama al método pasarDeCurso()
            int index = Arrays.stream(cursos).toList().indexOf(curso);
            return cursos[index].pasarDeCurso();
        } else return false;

    }

    // Sólo funcionará si el curso está en sexto, y hará que el curso en cuestion se gradue
    public boolean graduarCurso(int cursoNumerico, char grupo){
        Curso curso = getCursoPorCursoYGrupo(cursoNumerico,grupo);
        if (curso!=null){
            // Busca el índice del curso y llama al método graduar()
            int index = Arrays.stream(cursos).toList().indexOf(curso);
            return cursos[index].graduar();
        } else return false;
    }

    public boolean aniadirAsignaturaACurso(Asignatura asignatura, int cursoNumerico, char grupo){
        // Busca el curso
        Curso curso = getCursoPorCursoYGrupo(cursoNumerico,grupo);
        if (curso!=null){
            // Si no es null, obtenemos sus asignaturas
            Asignatura[] nuevasAsignaturas = curso.getAsignaturas();
            if(nuevasAsignaturas != null){
                // Obtenemos el índice donde podemos insertar la asignatura
                int indexNuevaAsignatura = encontrarPrimeraPosicionVacia(Arrays.stream(nuevasAsignaturas).toList());
                if (indexNuevaAsignatura != -1){
                    // Y lo insertamos en la lista de asignaturas
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

        // Comprobamos que haya espacio en los arrays de alumnos y profesores
        int primeraPosicionVaciaAlumno = encontrarPrimeraPosicionVacia(Arrays.stream(alumnos).toList());
        int primeraPosicionVaciaProfesor = encontrarPrimeraPosicionVacia(Arrays.stream(profesores).toList());
        if (primeraPosicionVaciaProfesor != -1 && primeraPosicionVaciaAlumno != -1) {


            // Tras eso, para cada alumno del curso que queremos insertar, comprobamos que no sea null y lo insertamos en el array. Además, volvemos a calcular la posición donde el array de alumnos es null
            for (Alumno alumno : curso.getAlumnos()) {
                if (alumno != null && primeraPosicionVaciaAlumno != -1) {
                    alumnos[primeraPosicionVaciaAlumno] = alumno;
                }
                primeraPosicionVaciaAlumno = encontrarPrimeraPosicionVacia(Arrays.stream(alumnos).toList());
            }

            // Hacemos lo mismo para los profesores
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

    // Comprueba que el array de cursos no esté vacío, y si lo está, crea un curso
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

    // Encuentra el primer null de una lista de tipo genérico
    private int encontrarPrimeraPosicionVacia(List<?> lista) {
        return lista.indexOf(null);
    }

    // Implementación de la inferfaz
    @Override
    public boolean darBeca(String dni) {
        boolean becaDada = false;
        // Busca el alumno
        Alumno alumno = getAlumnoPorDNI(dni);

        if (alumno != null) {
            // Si existe, cambiamos sus datos en el array de alumnos globales
            int posicionAlumno = Arrays.stream(alumnos).toList().indexOf(alumno);
            alumnos[posicionAlumno].setBecado(true);

            for (Curso curso : cursos) {

                // Y cambiamos su valor en su curso
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
