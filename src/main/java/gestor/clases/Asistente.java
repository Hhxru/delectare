package gestor.clases;

import java.time.LocalDate;

public class Asistente extends Usuario{
    private String dni;

    public Asistente(String nombre, String apellido, String email, String password, String telf, LocalDate fecha_nacimiento, String dni) {
        super(nombre, apellido, email, password, telf, fecha_nacimiento);
        this.dni = dni;
    }

    public Asistente() {
        super();
    }

    @Override
    public String toString() {
        return "gestor.clases.Asistente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telf='" + telf + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                '}';
    }
}
