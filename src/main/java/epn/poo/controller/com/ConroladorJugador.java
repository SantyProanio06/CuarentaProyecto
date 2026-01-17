package epn.poo.controller.com;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author santi
 */
public class ConroladorJugador {
    public void botarCarta(ArrayList<Carta> maso, ArrayList<Carta> mesa, Carta cartaSeleccionada){
        for (Carta s : maso){
            if(s.equals(cartaSeleccionada)){
                mesa.add(s);
                maso.remove(s);
            }
        }
    }
    
    public void cojerCartas(ArrayList<Carta> cartasLlevadas, ArrayList<Carta> cartonEquipo, ArrayList<Carta> mesa){
        for(Carta c : cartasLlevadas){
            cartonEquipo.add(c);
            mesa.remove(c);
        }
    }
}
