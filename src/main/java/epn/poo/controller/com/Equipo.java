package epn.poo.controller.com;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *Clase que representa a un Equipo en el sistema
 * @author santi
 */
public class Equipo {
    public ArrayList<Jugador> jugadores = new ArrayList<>();
    private int perros;
    private ArrayList<Carta> carton = new ArrayList<>();
    
    public Equipo() {}

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = (jugadores.size() > 2)? jugadores : null ;
    }

    public int getPerros() {
        return perros;
    }

    public void setPerros(int perros) {
        this.perros = perros;
    }

    public ArrayList<Carta> getCarton() {
        return carton;
    }

    public void setCarton(ArrayList<Carta> carton) {
        this.carton = carton;
    }
    
    public void aumentarPerros(){
        setPerros(getPerros() + 2);
    }
}
