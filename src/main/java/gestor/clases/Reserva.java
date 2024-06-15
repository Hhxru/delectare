package gestor.clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva implements Serializable {
    String id;
    Asistente asistente;
    Evento evento;
    Butaca butaca;
    LocalDate fecha;
    LocalTime hora;

    public Reserva(String id, Asistente asistente, Evento evento, Butaca butaca, LocalDate fecha, LocalTime hora) {
        this.id = id;
        this.asistente = asistente;
        this.evento = evento;
        this.butaca = butaca;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Asistente getAsistente() {
        return asistente;
    }

    public void setAsistente(Asistente asistente) {
        this.asistente = asistente;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Butaca getButaca() {
        return butaca;
    }

    public void setButaca(Butaca butaca) {
        this.butaca = butaca;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void mostrarReserva(){
        System.out.println("Id: "+id);
        System.out.println("Nombre de evento: "+ evento.getNombre());
        System.out.println("Posicion gestor.clases.Butaca: "+ butaca.getPos());
        System.out.println("Fecha: "+ fecha);
        System.out.println("Hora: " + hora );


    }

}
