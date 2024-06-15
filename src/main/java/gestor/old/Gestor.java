package gestor.old;

import gestor.clases.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Gestor {
    private ArrayList<Evento> listadoEventos;
    private Sala[] listadoSalas;
    private ArrayList<Reserva> listadoReservas;
    private ArrayList<Usuario> listadoUsuarios;

    public Gestor() {
        listadoSalas = new Sala[5];
        listadoEventos = new ArrayList<>();
        listadoReservas = new ArrayList<>();
        listadoUsuarios = new ArrayList<>();
        infoInicial();


        try {
            cargarUsuarios();
        } catch (IOException ex) {
            System.out.println("Problema en la comunicación con el fichero");
        }
    }

    public ArrayList<Usuario> getListadoUsuarios() {
        return listadoUsuarios;
    }

    // Método para cargar información inicial de usuarios desde un archivo
    public void cargarUsuarios() throws IOException {
        listadoUsuarios.add(new Asistente("Lolo", "Pepo", "pepe@gmail.com", "12345", "699838377", null, "12345678Z"));
        listadoUsuarios.add(new Asistente("Pepa", "Pepo", "pepa@gmail.com", "12345", "699150111", null, "48728296J"));
        listadoUsuarios.add(new Administrador("Lola", "Pepo", "lola@gmail.com", "12345", "699150111", null, 2));

        try (FileInputStream fis = new FileInputStream("src/gestor/data/usuarios.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                try {
                    Usuario u = (Usuario) ois.readObject();
                    listadoUsuarios.add(u);
                    System.out.println(u instanceof Asistente ? "gestor.clases.Asistente" : "gestor.data.clases.Administrador");
                } catch (EOFException eof) {
                    System.out.println("Fichero leído");
                    break;
                } catch (ClassNotFoundException cnf) {
                    cnf.printStackTrace();
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    // Método para cargar información inicial de salas y eventos
    public void infoInicial() {
        int identificador = 0;

        for (int i = 0; i < 5; i++) {
            ArrayList<Butaca> misButacas = new ArrayList<>();
            for (char fila = 'A'; fila < 'E'; fila++) {
                for (int columna = 1; columna < 5; columna++) {
                    identificador++;
                    String pos = fila + "" + columna;
                    misButacas.add(new Butaca(pos, identificador, false, true));
                }
            }
            System.out.println(misButacas);
            listadoSalas[i] = new Sala("gestor.clases.Sala nº" + i, 25, misButacas, 100.0);
        }

        listadoEventos.add(new Evento("Concierto", "Artista1", listadoSalas[0], LocalDate.of(2024, 3, 15), LocalTime.of(7, 20), 25.0, "Musical", 24, new ArrayList<>()));
        listadoEventos.add(new Evento("Conferencia", "Orador2", listadoSalas[1], LocalDate.of(2024, 4, 20), LocalTime.of(17, 20), 15.0, "Educacional", 24, new ArrayList<>()));
        listadoEventos.add(new Evento("Fiesta", "DJ3", listadoSalas[2], LocalDate.of(2024, 5, 10), LocalTime.of(22, 20), 20.0, "Social", 24, new ArrayList<>()));
    }

    // Método para iniciar sesión de un asistente
    public Asistente login() {
        Scanner sc = new Scanner(System.in);
        String email, password;

        while (true) {
            System.out.println("Introduzca su email:");
            email = sc.nextLine();
            if (Validaciones.validarGmail(email)) {
                System.out.println("El gmail introducido es correcto.\n");
                break;
            } else {
                System.err.println("El gmail no es correcto, por favor, introdúzcalo de nuevo:");
            }
        }

        System.out.println("Introduzca su contraseña: ");
        password = sc.nextLine();
        Validaciones.validarPassLog(password);

        for (Usuario u : listadoUsuarios) {
            if (email.equals(u.getEmail()) && password.equals(u.getPassword())) {
                return new Asistente();
            }
        }

        System.err.println("Los datos introducidos son incorrectos.");
        return null;
    }

    // Método para registrar un nuevo asistente
    public Asistente signin() {
        Scanner sc = new Scanner(System.in);
        String nombre, apellidos, email, password, telefono, dni;
        LocalDate fechaNacimiento;
        boolean salir = false;

        while (true) {
            System.out.println("Introduzca su email:");
            email = sc.nextLine();
            if (Validaciones.validarGmail(email)) {
                System.out.println("El gmail introducido es correcto.\n");
                break;
            } else {
                System.err.println("El gmail no es correcto, por favor, introdúzcalo de nuevo:");
            }
        }

        for (Usuario u : listadoUsuarios) {
            if (email.equals(u.getEmail())) {
                System.err.println("Correo electronico en uso, elija otro.");
                salir = true;
                break;
            }
        }

        if (salir) return null;

        while (true) {
            System.out.println("Introduzca su nombre: ");
            nombre = sc.nextLine();
            if (Validaciones.validarNombre(nombre)) {
                System.out.println("El nombre introducido es correcto.\n");
                break;
            } else {
                System.err.println("Solo se pueden introducir letras, introdúzcalo de nuevo:");
            }
        }



        while (true) {
            System.out.println("Introduzca sus apellidos: ");
            apellidos = sc.nextLine();
            if (Validaciones.validarApellido(apellidos)) {
                System.out.println("Los apellidos introducido es correcto.\n");
                break;
            } else {
                System.err.println("Solo se pueden introducir letras, introdúzcalo de nuevo:");
            }
        }

        System.out.println("Introduzca su contraseña: ");
        password = sc.nextLine();
        Validaciones.validarPass(password);

        while (true) {
            System.out.println("Introduzca su número de teléfono: ");
            telefono = sc.nextLine();
            if (Validaciones.validarNumeroTelefono(telefono)) {
                System.out.println("El número introducido es correcto.\n");
                break;
            } else {
                System.err.println("El número no es correcto, por favor, introdúzcalo de nuevo:");
            }
        }

        while (true) {
            System.out.println("Por favor, introduzca el DNI del titular (en caso de NIE, sustituir de la siguiente manera: X=0 Y=1 Z=2):");
            dni = sc.nextLine();
            if (Validaciones.validarDNI(dni)) {
                System.out.println("El DNI introducido es correcto.\n");
                break;
            } else {
                System.err.println("El DNI no es correcto, por favor, introdúzcalo de nuevo:");
            }
        }

        while (true) {
            System.out.println("Introduzca su fecha de nacimiento (AAAA-MM-DD): ");
            String fecha = sc.nextLine();
            try {
                fechaNacimiento = LocalDate.parse(fecha);
                System.out.println("La fecha de nacimiento introducida es correcta.\n");
                break;
            } catch (Exception e) {
                System.err.println("La fecha no es correcta, por favor, introdúzcalo de nuevo:");
            }
        }

        Asistente nuevoAsistente = new Asistente(nombre, apellidos, email, password, telefono, fechaNacimiento, dni);
        listadoUsuarios.add(nuevoAsistente);
        return nuevoAsistente;
    }


}
