import gestor.clases.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestorEventos {
    private ArrayList<Usuario> listadoUsuarios;
    private ArrayList<Evento> listadoEventos;
    private ArrayList<Reserva> listadoReservas;

    public GestorEventos() {
        listadoEventos = new ArrayList<>();
        listadoReservas = new ArrayList<>();
        listadoUsuarios = new ArrayList<>();
        infoInicial();
    }

    public void infoInicial() {

        Usuario usuario1 = new Usuario("Juan", "Perez", "juan@example.com", "juan123", "123456789", LocalDate.of(1990, 1, 1));
        Usuario usuario2 = new Usuario("Maria", "Gomez", "maria@example.com", "maria123", "987654321", LocalDate.of(1992, 2, 2));
        Usuario usuario3 = new Usuario("Carlos", "Diaz", "carlos@example.com", "carlos123", "456789123", LocalDate.of(1994, 3, 3));

        Administrador admin1 = new Administrador("Admin", "Admin", "admin@example.com", "admin123", "adminPass", LocalDate.of(1985, 5, 5), 10);

        listadoUsuarios.add(usuario1);
        listadoUsuarios.add(usuario2);
        listadoUsuarios.add(usuario3);
        listadoUsuarios.add(admin1);
    }
    
    public void login(){
        System.out.println("Hola soy el Login");
    }

    public void registro(){
        System.out.println("Hola soy el Registro");
    }
}
