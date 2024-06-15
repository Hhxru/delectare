package gestor.clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Sala implements Serializable {
    String nombre;
    int capacidad;
    ArrayList<Butaca> lista_butacas = new ArrayList<>();
    double espacio;
    boolean accesibilidad;
    String acustica;
    String observaciones;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public ArrayList<Butaca> getLista_butacas() {
        return lista_butacas;
    }

    public void setLista_butacas(ArrayList<Butaca> lista_butacas) {
        this.lista_butacas = lista_butacas;
    }

    public double getEspacio() {
        return espacio;
    }

    public void setEspacio(double espacio) {
        this.espacio = espacio;
    }

    public boolean isAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(boolean accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public String getAcustica() {
        return acustica;
    }

    public void setAcustica(String acustica) {
        this.acustica = acustica;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Sala() {
    }

    public Sala(String nombre, int capacidad, ArrayList<Butaca> lista_butacas, double espacio) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.lista_butacas = lista_butacas;
        this.espacio = espacio;
    }

    void mostrar_butacas() {
        System.out.println("Butacas disponibles en la sala " + nombre + ":");
        for (Butaca b : lista_butacas) {
            System.out.println("Posición: " + b.pos + " (ID: " + b.id + "), Estado: " + (b.disponible ? "Disponible" : "Ocupada"));
        }
    }
}

