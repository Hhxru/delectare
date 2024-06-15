package gestor.clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Evento implements Serializable {
    private String nombre;
    private String invitado;
    private Sala sala;
    private LocalDate fecha;
    private LocalTime hora;
    private double precio;
    private String tipoEvento;
    private int asistentesMaximos;
    private ArrayList<Asistente> listaAsistentes;

    public Evento(String nombre, String invitado, Sala sala, LocalDate fecha, LocalTime hora,double precio, String tipoEvento, int asistentesMaximos, ArrayList<Asistente> listaAsistentes) {
        this.nombre = nombre;
        this.invitado = invitado;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.tipoEvento = tipoEvento;
        this.asistentesMaximos = asistentesMaximos;
        this.listaAsistentes = listaAsistentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInvitado() {
        return invitado;
    }

    public void setInvitado(String invitado) {
        this.invitado = invitado;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getAsistentesMaximos() {
        return asistentesMaximos;
    }

    public void setAsistentesMaximos(int asistentesMaximos) {
        this.asistentesMaximos = asistentesMaximos;
    }

    public ArrayList<Asistente> getListaAsistentes() {
        return listaAsistentes;
    }

    public void setListaAsistentes(ArrayList<Asistente> listaAsistentes) {
        this.listaAsistentes = listaAsistentes;
    }

    public void mostrarEvento(){
        System.out.println(" Nombre");
        System.out.println(" Invitado");
        System.out.println("gestor.clases.Sala");
        System.out.println("Fecha");
        System.out.println();

    }
}