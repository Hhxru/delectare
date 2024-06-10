package gestor;

import gestor.clases.*;

import java.time.LocalDate;
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
            System.out.println(misButacas);
            listadoSalas[i] = new Sala("gestorEventos.Clases.Sala nº" + i, 25, misButacas, 100.0);
        }


        Usuario usuario1 = new Asistente("Juan", "Perez", "juan@example.com", "juan123", "123456789", LocalDate.of(1990, 1, 1), "49607120D");
        Usuario usuario2 = new Asistente("Maria", "Gomez", "maria@example.com", "maria123", "987654321", LocalDate.of(1992, 2, 2), "46502560I");
        Usuario usuario3 = new Asistente("Carlos", "Diaz", "carlos@example.com", "carlos123", "456789123", LocalDate.of(1994, 3, 3), "49694075E");

        Usuario admin1 = new Administrador("Admin", "Admin", "admin@example.com", "admin123", "adminPass", LocalDate.of(1985, 5, 5), 10);

        listadoUsuarios.add(usuario1);
        listadoUsuarios.add(usuario2);
        listadoUsuarios.add(usuario3);
        listadoUsuarios.add(admin1);
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
                    // Si el usuario es un asistente, pasar al sistema de reserva
                    sistemaDeReserva(usuario);
                } else {
                    // Si no, mostrar menú de gestión
                    mostrarMenuAdministrador();
                }
                return; // Salir del método después de iniciar sesión correctamente
            }
        }

        System.err.println("Los datos introducidos son incorrectos.");
    }

    // Metodo para mostrar el menú de reserva para el asisntente.
    private void sistemaDeReserva(Usuario asistente) {
        Scanner sc = new Scanner(System.in);
    }

    // Metodo para mstrar el menú de gestión para el administrador
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



    public void registro(){
        System.out.println("Hola soy el Registro");
    }
}

