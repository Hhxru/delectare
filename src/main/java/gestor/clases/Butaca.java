package gestor.clases;

import java.io.Serializable;

public class Butaca implements Serializable {
    String pos;
    int id;
    boolean accesibilidad;
    boolean disponible;

    public Butaca(String pos, int id, boolean accesibilidad, boolean disponible) {
        this.pos = pos;
        this.id = id;
        this.accesibilidad = accesibilidad;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public boolean isAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(boolean accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
