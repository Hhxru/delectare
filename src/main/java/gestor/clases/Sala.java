package gestor.clases;

import java.util.ArrayList;

public class Sala {
    String nombre;
    int capacidad;
    ArrayList<Butaca> lista_butacas = new ArrayList<>();
    double espacio;
    boolean accesibilidad;
    String acustica;
    String observaciones;


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

