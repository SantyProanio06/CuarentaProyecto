/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.controller.com;

import java.util.ArrayList;
import java.util.Collections;

/**
 *Clase que representa la reparticion de cartas a los masos de los jugadores, cuenta con un
 * metodo para ello.
 * @author santi
 * @author sebas
 */
public class ControladorCartas {
    
    /**
     * Randomiza las cartas, las reparte a los masos y las remueve de la baraja.
     * @param baraja Contenedor de todas las cartas.
     * @return ArrayList de las cartas randomizadas para el maso.
     */
    public ArrayList<Carta> repartirCartas(ArrayList<Carta> baraja){
        Collections.shuffle(baraja);//Randomiza las cartas.
        ArrayList<Carta> cartasRepartidas = new ArrayList<>();
        int cont = 0;
        while (cont < 5){//Son 5 cartas por maso
            cartasRepartidas.add(baraja.get(cont));//Las reparte
            baraja.remove(cont);//Las remueve
            cont ++;
        }
        return cartasRepartidas;
    }
}
