/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.controller.com;

import java.util.ArrayList;

/**
 *Clase que representa un Jugador en el sistema
 * @author santi
 */
public class Jugador {
    private String nombreJugador;
    private ArrayList<Carta> masoJugador;
    
    public Jugador(){};

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public ArrayList<Carta> getMasoJugador() {
        return masoJugador;
    }

    public void setMasoJugador(ArrayList<Carta> masoJugador) {
        this.masoJugador = masoJugador;
    }
  
}
