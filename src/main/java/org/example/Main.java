package org.example;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Alumno alumno1 = new Alumno("21332433N", "Alejandro González Parra", 22, 'H', "Calle Navarra N2", false, 5, true, false);
        Alumno alumno2 = new Alumno("31324567A", "Ricardo Gallego Domínguez", 20, 'H', "Calle Jamón N5", false, 9, true, false);
        Alumno[] nuevosAlumnos = new Alumno[20];
        nuevosAlumnos[0] = alumno1;
        nuevosAlumnos[1] = alumno2;


        Profesor profesor1 = new Profesor("34999888F", "Diego Martínez Rodríguez", 29, 'H', "Calle de los patos N9", "Ingeniería Informática", 0, "Matemáticas");
        Profesor profesor2 = new Profesor("98765432A", "Carla Rodríguez Cabeza de Vaca", 35, 'M', "Camino de los Hornos", "Economía", 4, "Tutoría");
        Profesor profesor3 = new Profesor("12345678Z", "Alberto Ramírez González", 30, 'H', "Calle Los Vendavales", "Ciencias de la Salud y el Deporte", 2, "Educación Física");

        Asignatura asignatura1 = new Asignatura("Matemáticas", profesor1, 8, "Matemáticas II");
        Asignatura asignatura2 = new Asignatura("Economía", profesor2, 6, "Economía de la Empresa");
        Asignatura[] asignaturas = new Asignatura[]{asignatura1, asignatura2};


        Curso curso1 = new Curso(profesor3, 2023, 1, 'B');

        curso1.setAsignaturas(asignaturas);
        curso1.setAlumnos(nuevosAlumnos);

        Colegio colegio = new Colegio("IES Rafael Alberti", "Calle Balenciaga", 1000, 200, new Curso[]{curso1});

        System.out.println("Bienvenido al sistema de gestión de IES Rafael Alberti\n");

        boolean salir = false;
        Scanner readln = new Scanner(System.in);

        while (!salir) {
            System.out.println("""
                    Elija una opción:
                    1. Gestionar alumnos
                    2. Gestionar profesorado
                    3. Gestionar cursos
                    4. Salir
                                    
                    """);

            int input;

            try {
                input = Integer.parseInt(readln.nextLine());
            } catch (Exception e) {
                input = -1;
            }


            switch (input) {
                default -> {
                    System.out.println("Entrada incorrecta, prueba de nuevo");
                }

                case 1 -> {
                    System.out.println("""
                            Elija otra opción:
                            1. Imprimir todos los alumnos
                            2. Mostrar información de un alumno
                            3. Matricular alumno
                            4. Dar beca a un alumno
                            5. Expulsar alumno
                            6. Volver atrás
                                                        
                            """);

                    try {
                        input = Integer.parseInt(readln.nextLine());
                    } catch (Exception e) {
                        input = -1;
                    }

                    switch (input) {
                        default -> System.out.println("Entrada incorrecta. Se volverá al menú inicial\n");
                        case 1 -> {
                            Stream<Alumno> alumnos = Arrays.stream(colegio.getAlumnos()).filter(Objects::nonNull);
                            alumnos.forEach(it -> System.out.println(it + "\n"));

                        }

                        case 2 -> {
                            System.out.println("Introduzca el DNI del alumno\n");
                            String dni = readln.nextLine();
                            Alumno alumno = colegio.getAlumnoPorDNI(dni);
                            if (alumno != null) {
                                System.out.println(alumno);
                            } else System.out.println("No hay un estudiante con ese DNI.\n");

                        }

                        case 3 -> {
                            try {

                                Alumno alumno = crearAlumno();
                                boolean matriculado = colegio.matricularEstudiante(alumno);

                                if (matriculado) {
                                    System.out.println("Alumno matriculado correctamente\n");
                                } else {
                                    System.out.println("El alumno no se pudo matricular\n");
                                }


                            } catch (Exception e) {
                                System.out.println("Entrada incorrecta. Se volverá al menú inicial\n");
                            }
                        }
                        case 4 -> {
                            System.out.println("Introduzca el DNI del alumno\n");
                            String dni = readln.nextLine();
                            boolean becado = colegio.darBeca(dni);
                            if (becado) {
                                System.out.println("Se le ha concedido la beca al alumno con el DNI " + dni + "\n");
                            } else
                                System.out.println("No se ha podido conceder la beca al alumno con DNI " + dni + "\n");
                        }
                        case 5 -> {
                            System.out.println("Introduzca el DNI del alumno\n");
                            String dni = readln.nextLine();
                            boolean expulsado = colegio.expulsarEstudiante(dni);
                            if (expulsado) {
                                System.out.println("Se ha expulsado al alumno con el DNI " + dni + "\n");
                            } else System.out.println("No se ha podido expulsar al alumno con DNI" + dni + "\n");
                        }
                        case 6 -> {
                            System.out.println("Se volverá al menú inicial");
                        }
                    }


                }

                case 2 -> {
                    System.out.println("""
                            Elija otra opción:
                            1. Imprimir todos los profesores
                            2. Mostrar información de un profesor
                            3. Contratar profesor
                            4. Despedir profesor
                            5. Volver
                            
                            """);

                    try {
                        input = Integer.parseInt(readln.nextLine());
                    } catch (Exception e) {
                        input = -1;
                    }

                    switch (input) {
                        default -> {
                            System.out.println("Entrada incorrecta. Se volverá al menú inicial\n");
                        }
                        case 1 -> {
                            for (Profesor profesor : colegio.getProfesores()) {
                                if (profesor != null) System.out.println(profesor + "\n");
                            }
                        }

                        case 2 -> {
                            System.out.println("Introduzca el DNI del profesor\n");
                            String dni = readln.nextLine();
                            Profesor profesor = colegio.getProfesorPorDNI(dni);
                            if (profesor != null) {
                                System.out.println(profesor);
                            } else System.out.println("No hay un profesor con ese DNI.\n");
                        }

                        case 3 -> {

                            try {
                                Profesor profesor = crearProfesor();
                                boolean profesorContratado = colegio.contratarProfesor(profesor);

                                if (profesorContratado) {
                                    System.out.println("Profesor contratado con éxito\n");
                                } else {
                                    System.out.println("No se pudo contratar al profesor\n");
                                }
                            } catch (Exception e){
                                System.out.println("Entrada incorrecta. Se volverá al menú inicial");
                            }

                        }

                        case 4 -> {
                            System.out.println("Introduzca el DNI del docente\n");
                            String dni = readln.nextLine();
                            boolean despedido = colegio.despedirProfesor(dni);
                            if (despedido) {
                                System.out.println("Se ha despedido al profesor con DNI " + dni + "\n");
                            } else System.out.println("No se ha podido despedir al profesor con DNI" + dni + "\n");
                        }

                        case 5 -> {
                            System.out.println("Se volverá al menú inicial\n");
                        }

                    }
                }

                case 3 -> {
                    System.out.println("""
                            Elija otra opción:
                            1. Imprimir todos los cursos
                            2. Mostrar información de un curso
                            3. Crear curso
                            4. 
                            5. Volver
                            
                            """);

                    try {
                        input = Integer.parseInt(readln.nextLine());
                    } catch (Exception e) {
                        input = -1;
                    }

                    switch (input){
                        default -> {
                            System.out.println("Entrada incorrecta. Se volverá al menú inicial\n");
                        }

                        case 1 -> {
                            for (Curso curso : colegio.getCursos()) {
                                if (curso!= null) System.out.println(curso + "\n");
                            }
                        }

                        case 2 -> {
                            try {
                                System.out.println("Introduzca el curso numérico\n");
                                int cursoNumerico = Integer.parseInt(readln.nextLine());
                                System.out.println("Introduzca el grupo");
                                char grupo = readln.nextLine().toUpperCase(Locale.ROOT).charAt(0);
                                Curso curso = colegio.getCursoPorCursoYGrupo(cursoNumerico, grupo);
                                if (curso != null) {
                                    System.out.println(curso);
                                    System.out.println("Asignaturas:");
                                    for (Asignatura asignatura : curso.getAsignaturas()) {
                                        System.out.println(asignatura);
                                    }
                                    System.out.println("Alumnos");
                                    for (Alumno alumno : curso.getAlumnos()) {
                                        System.out.println(alumno);
                                    }

                                    System.out.println();
                                } else System.out.println("El curso especificado no existe.\n");
                            }catch (Exception e){
                                System.out.println("Entrada incorrecta. Se volverá al menú inicial\n");
                            }
                        }

                        case 3 -> {

                        }

                        case 4 -> {
                            salir = true;
                        }

                        }
                    }

                }



            }

        System.out.println("El programa terminará");

    }

    private static Profesor crearProfesor(){
        Scanner readln = new Scanner(System.in);
        System.out.println("Introduzca el DNI del docente\n");
        String dni = readln.nextLine();
        System.out.println("Introduzca el nombre y los apellidos\n");
        String nombre = readln.nextLine();
        System.out.println("Introduzca la edad del docente\n");
        int edad = Integer.parseInt(readln.nextLine());
        System.out.println("Introduzca el sexo (H/M)");
        char sexo = readln.nextLine().toUpperCase(Locale.ROOT).charAt(0);
        System.out.println("Introduzca la dirección");
        String direccion = readln.nextLine();
        System.out.println("Introduzca la especialidad");
        String especialidad = readln.nextLine();
        System.out.println("Introduzca las materias impartidas");
        String materias = readln.nextLine();
        readln.close();

        return new Profesor(dni, nombre, edad, sexo, direccion, especialidad, nombre);
    }

    private static Alumno crearAlumno(){
        Scanner readln = new Scanner(System.in);
        System.out.println("Introduzca el DNI del alumno\n");
        String dni = readln.nextLine();
        System.out.println("Introduzca el nombre y los apellidos del alumno\n");
        String nombre = readln.nextLine();
        System.out.println("Introduzca la edad del alumno\n");
        int edad = Integer.parseInt(readln.nextLine());
        System.out.println("Introduzca el sexo del alumno (H/M)");
        char sexo = readln.nextLine().toUpperCase(Locale.ROOT).charAt(0);
        System.out.println("Introduzca la dirección");
        String direccion = readln.nextLine();
        readln.close();

        return new Alumno(dni, nombre, edad, sexo, direccion);
    }


}
