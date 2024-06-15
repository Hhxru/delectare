package gestor;

import gestor.clases.*;
import gestor.excepciones.ContrasenyaNotEqualsException;
import gestor.excepciones.EmailExistenteException;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorEventos {
    private ArrayList<Usuario> listadoUsuarios;
    private Sala[] listadoSalas;
    private ArrayList<Evento> listadoEventos;
    private ArrayList<Reserva> listadoReservas;

    FileOutputStream fos=null;
    ObjectOutputStream oos=null;
    FileInputStream fis=null;
    ObjectInputStream ois=null;

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
            listadoSalas[i] = new Sala("Sala nº" + i, 25, misButacas, 25.0);
        }

        Usuario usuario1 = new Asistente("Juan", "Perez", "juan@example.com", "juan123", "123456789", LocalDate.of(1990, 1, 1), "49607120D");
        Usuario usuario2 = new Asistente("Maria", "Gomez", "maria@example.com", "maria123", "987654321", LocalDate.of(1992, 2, 2), "46502560I");
        Usuario usuario3 = new Asistente("Carlos", "Diaz", "carlos@example.com", "carlos123", "456789123", LocalDate.of(1994, 3, 3), "49694075E");

        Usuario admin1 = new Administrador("Admin", "Admin", "admin@example.com", "admin123", "adminPass", LocalDate.of(1985, 5, 5), 10);

        listadoUsuarios.add(usuario1);
        listadoUsuarios.add(usuario2);
        listadoUsuarios.add(usuario3);
        listadoUsuarios.add(admin1);

        leerUsuarios();

        ArrayList<Asistente> asistentes = new ArrayList<>();
        Evento evento1 = new Evento("Evento1", "Pepe", listadoSalas[0], LocalDate.of(2024, 6, 15), LocalTime.of(20, 0), 50.0, "Concierto", 25, asistentes);
        Evento evento2 = new Evento("Evento2", "Jose", listadoSalas[1], LocalDate.of(2024, 6, 20), LocalTime.of(19, 0), 40.0, "Teatro", 25, asistentes);

        listadoEventos.add(evento1);
        listadoEventos.add(evento2);
    }
    public void leerUsuarios() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("src/main/java/gestor/data/usuarios.dat");
            ois = new ObjectInputStream(fis);
            while (true) {
                Usuario u = (Usuario) ois.readObject();
                listadoUsuarios.add(u);
            }
        } catch (EOFException e) {
            System.out.println("Fichero leído");
        } catch (FileNotFoundException e) {
            System.err.println("Error al encontrar el archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error al encontrar el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al encontrar el archivo: " + e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void escribirUsuarios() throws IOException {
        try {
            fos=new FileOutputStream("src/main/java/gestor/data/usuarios.dat", true);
            oos=new ObjectOutputStream(fos);
            for (Usuario u:listadoUsuarios){
                oos.writeObject(u);
            }
        } catch (FileNotFoundException e){
            e.getMessage();
        }finally {
            fos.close();
            oos.flush();
            oos.close();
        }
    }
    public void leerEventos() throws IOException {
        try {
            fis=new FileInputStream("src/data/eventos.dat");
            ois=new ObjectInputStream(fis);
            while (true){
                Evento e=(Evento) ois.readObject();
                listadoEventos.add(e);
            }
        } catch (EOFException e){
            System.out.println("Fichero leído");
        }catch (FileNotFoundException e){
            e.getMessage();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {

        } finally {
            fis.close();
            ois.close();
        }
    }
    public void escribirEventos() throws IOException {
        try {
            fos=new FileOutputStream("src/data/eventos.dat");
            oos=new ObjectOutputStream(fos);
            for (Evento e:listadoEventos){
                oos.writeObject(e);
            }
        } catch (FileNotFoundException e){
            e.getMessage();
        }finally {
            fos.close();
            oos.flush();
            oos.close();
        }
    }

    public void login() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca su email:");
        String email = sc.nextLine();

        System.out.println("Introduzca su contraseña: ");
        String password = sc.nextLine();

        for (Usuario usuario : listadoUsuarios) {
            System.out.println(usuario.getNombre());
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

        String nombre, apellido, email="", password, confPassword, telf, dni, fechaNacimientoString;
        LocalDate fechaNacimiento = null;
        boolean salir = false;

        System.out.println("REGISTRO ASISTENTES");

        // Pedir email, validar y comprobar si esta en uso
        while (!salir) {
            System.out.println("Introduzca su email:");
            email = sc.nextLine();
            try {
                if (Validaciones.validarGmail(email)) {
                    boolean emailUsado = false;
                    for (Usuario u : listadoUsuarios) {
                        if (email.equals(u.getEmail())) {
                            emailUsado = true;
                            break;
                        }
                    }

                    if (emailUsado == true) {
                        throw new EmailExistenteException();
                    } else {
                        System.out.println("El correo electrónico introducido esta disponible.\n");
                        salir = true;
                    }
                } else {
                    System.err.println("El correo electrónico no es valido, por favor, introdúzcalo de nuevo:");
                }
            } catch (EmailExistenteException e) {
                System.err.println(e.getMessage());
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

        // pedir apellido
        while (true) {
            System.out.println("Introduzca su apellido: ");
            apellido = sc.nextLine();
            if (Validaciones.validarNombre(apellido)) {
                System.out.println("El apellido introducido es correcto.\n");
                break;
            } else {
                System.err.println("Solo se pueden introducir letras, introdúzcalo de nuevo:");
            }
        }

        // pedir contraseña y pedir confirmacion (compara)
        while (true) {
            try {
                System.out.println("Introduzca su contraseña:");
                password = sc.nextLine();

                System.out.println("Confirmar contraseña:");
                confPassword = sc.nextLine();

                if (!(confPassword.equals(password))) {
                    throw new ContrasenyaNotEqualsException();
                }else {
                    System.out.println("Contraseñas coincidentes.");
                }
                break;
            } catch (ContrasenyaNotEqualsException e) {
                System.out.println(e.getMessage());
            }
        }

        //pedir telefono y validarlo
        while (true) {
            System.out.println("Introduzca su número de teléfono (Formato 111 111 111): ");
            telf = sc.nextLine();
            if (Validaciones.validarNumeroTelefono(telf)) {
                System.out.println("El número introducido es correcto.\n");
                break;
            } else {
                System.err.println("El número no es correcto, por favor, introdúzcalo de nuevo:");
            }
        }

        //pedir fecha de nacimiento en string y pasarlo con parse
        while (fechaNacimiento == null) {
            System.out.println("Introduzca su fecha de nacimiento (aaaa-MM-dd):");
            fechaNacimientoString = sc.nextLine();
            fechaNacimiento = Validaciones.validarFecha(fechaNacimientoString);
        }

        //pedir un dni y validarlo
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
        Asistente nuevoAsistente = new Asistente(nombre, apellido, email, password, telf, fechaNacimiento, dni);
        listadoUsuarios.add(nuevoAsistente);

        try (FileOutputStream fos = new FileOutputStream("src/main/java/gestor/data/usuarios.dat", true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(nuevoAsistente);
            System.out.println("\nAñadido correctamente");
            System.out.println(nuevoAsistente);
        } catch (IOException ex) {
            System.out.println("Error al añadir contenido al archivo");
            ex.printStackTrace();
        }

        System.out.println("Registro completado con éxito.");

    }
}
