package gestor;

import gestor.clases.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorEventos {
    private ArrayList<Usuario> listadoUsuarios;
    private Sala[] listadoSalas;
    private ArrayList<Evento> listadoEventos;
    private ArrayList<Reserva> listadoReservas;

    public GestorEventos() {
        listadoSalas = new Sala[5];
        listadoEventos = new ArrayList<>();
        listadoReservas = new ArrayList<>();
        listadoUsuarios = new ArrayList<>();
        infoInicial();
    }

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
            listadoSalas[i] = new Sala("Sala nº" + i, 25, misButacas, 100.0);
        }

        Usuario usuario1 = new Asistente("Juan", "Perez", "juan@example.com", "juan123", "123456789", LocalDate.of(1990, 1, 1), "49607120D");
        Usuario usuario2 = new Asistente("Maria", "Gomez", "maria@example.com", "maria123", "987654321", LocalDate.of(1992, 2, 2), "46502560I");
        Usuario usuario3 = new Asistente("Carlos", "Diaz", "carlos@example.com", "carlos123", "456789123", LocalDate.of(1994, 3, 3), "49694075E");

        Usuario admin1 = new Administrador("Admin", "Admin", "admin@example.com", "admin123", "adminPass", LocalDate.of(1985, 5, 5), 10);

        listadoUsuarios.add(usuario1);
        listadoUsuarios.add(usuario2);
        listadoUsuarios.add(usuario3);
        listadoUsuarios.add(admin1);

        ArrayList<Asistente> asistentes = new ArrayList<>();
        Evento evento1 = new Evento("Concierto de Rock", "Banda XYZ", listadoSalas[0], LocalDate.of(2024, 6, 15), LocalTime.of(20, 0), 50.0, "Concierto", 100, asistentes);
        Evento evento2 = new Evento("Obra de Teatro", "Grupo ABC", listadoSalas[1], LocalDate.of(2024, 6, 20), LocalTime.of(19, 0), 40.0, "Teatro", 80, asistentes);

        listadoEventos.add(evento1);
        listadoEventos.add(evento2);
    }

    public void login() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca su email:");
        String email = sc.nextLine();

        System.out.println("Introduzca su contraseña: ");
        String password = sc.nextLine();

        for (Usuario usuario : listadoUsuarios) {
            if (usuario.getEmail().equals(email) && usuario.getPassword().equals(password)) {
                if (usuario instanceof Asistente) {
                    sistemaDeReserva(usuario);
                } else {
                    mostrarMenuAdministrador();
                }
                return;
            }
        }

        System.err.println("Los datos introducidos son incorrectos.");
    }

    private void sistemaDeReserva(Usuario usuario) {
        Asistente asistente = (Asistente) usuario;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido al Sistema de Reservas.");

        Evento eventoSeleccionado = seleccionarEvento();
        if (eventoSeleccionado == null) {
            return;
        }

        Butaca butacaSeleccionada = seleccionarButaca(eventoSeleccionado.getSala());
        if (butacaSeleccionada == null) {
            return;
        }

        realizarReserva(asistente, eventoSeleccionado, butacaSeleccionada);
    }

    public void mostrarEventosDisponibles() {
        if (listadoEventos.isEmpty()) {
            System.out.println("No hay eventos disponibles.");
            return;
        }

        System.out.println("Eventos disponibles:");
        for (int i = 0; i < listadoEventos.size(); i++) {
            Evento evento = listadoEventos.get(i);
            System.out.println((i + 1) + ". " + evento.getNombre() + " - " + evento.getFecha());
        }
    }

    public Evento seleccionarEvento() {
        mostrarEventosDisponibles();
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione el número del evento al que desea asistir:");
        int eventoIndex = sc.nextInt() - 1;
        sc.nextLine();

        if (eventoIndex < 0 || eventoIndex >= listadoEventos.size()) {
            System.out.println("Selección inválida. Intente nuevamente.");
            return null;
        }

        return listadoEventos.get(eventoIndex);
    }

    public void mostrarButacasDisponibles(Sala sala) {
        System.out.println("Butacas disponibles en la sala " + sala.getNombre()+ ":");
        for (Butaca butaca : sala.getLista_butacas()) {
            if (butaca.isDisponible()) {
                System.out.println("Posición: " + butaca.getPos() + " (ID: " + butaca.getId() + ")");
            }
        }
    }

    public Butaca seleccionarButaca(Sala sala) {
        mostrarButacasDisponibles(sala);
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione el ID de la butaca que desea reservar:");
        int butacaId = sc.nextInt();
        sc.nextLine();

        for (Butaca butaca : sala.getLista_butacas()) {
            if (butaca.getId() == butacaId && butaca.isDisponible()) {
                return butaca;
            }
        }

        System.out.println("Selección inválida o butaca no disponible. Intente nuevamente.");
        return null;
    }

    public void realizarReserva(Asistente asistente, Evento evento, Butaca butaca) {
        String reservaId = Validaciones.generarUUID();
        Reserva nuevaReserva = new Reserva(reservaId, asistente, evento, butaca, LocalDate.now(), LocalTime.now());

        butaca.setDisponible(false);
        listadoReservas.add(nuevaReserva);

        System.out.println("Reserva realizada con éxito. Detalles de la reserva:");
        nuevaReserva.mostrarReserva();
    }

    private void mostrarMenuAdministrador() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("""
                                        
                    ⋆｡ﾟ☁︎｡⋆｡PANEL DE ADMINISTRADOR ⋆｡ﾟ☁ ﾟ☾ ⋆
                    ꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷
                                        
                       1.  ‧₊˚✧[Gestionar Eventos]✧˚₊‧                  
                       2.  ‧₊˚✧[Gestionar Asistentes]✧˚₊‧                   
                       3.  ‧₊˚✧[Gestionar Reservas]✧˚₊‧       
 
                      ❀° ┄────────────────────────────────╮
                         Por favor, escoja una opción: ↓                                         
                      ╰────────────────────────────────┄ °❀                                                                                                              
                    """);

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Gestionar Eventos");
                    break;
                case 2:
                    System.out.println("Gestionar Asistentes");
                    break;
                case 3:
                    System.out.println("Gestionar Reservas");
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione otra opción.");
                    break;
            }
        } while (opcion != 5);
    }


    public void registro() {
        Scanner sc = new Scanner(System.in);

        String nombre, apellido, email, password, telf, dni;
        LocalDate fechaNacimiento;
        boolean salir = false;

        System.out.println("REGISTRO ASISTENTES");

        // Pedir email, validar y comprobar si esta en uso
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


        // pedir nombre
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

        



    }
}
