/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.controller.com;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author santi
 */
public class ControladorCartas {
    
    /**
     * Randomiza las cartas, las reparte y las remueve de la baraja
     * @param baraja
     * @return
     */
    public ArrayList<Carta> repartirCartas(ArrayList<Carta> baraja){
        Collections.shuffle(baraja);
        ArrayList<Carta> cartasRepartidas = new ArrayList<>();
        int cont = 0;
        while (cont < 5){
            cartasRepartidas.add(baraja.get(cont));
            baraja.remove(cont);
            cont ++;
        }
        return cartasRepartidas;
    }
}
