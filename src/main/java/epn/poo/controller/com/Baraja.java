/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.controller.com;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Baraja {
    private ArrayList<Carta> baraja = new ArrayList<Carta>();  

    
    public Baraja() {
// Palo T
baraja.add(new Carta(1, "resources/1_T"));
baraja.add(new Carta(2, "resources/2_T"));
baraja.add(new Carta(3, "resources/3_T"));
baraja.add(new Carta(4, "resources/4_T"));
baraja.add(new Carta(5, "resources/5_T"));
baraja.add(new Carta(6, "resources/6_T"));
baraja.add(new Carta(7, "resources/7_T"));
baraja.add(new Carta(8, "resources/8_T"));
baraja.add(new Carta(9, "resources/9_T"));
baraja.add(new Carta(10, "resources/10_T"));

// Palo C
baraja.add(new Carta(1, "resources/1_C"));
baraja.add(new Carta(2, "resources/2_C"));
baraja.add(new Carta(3, "resources/3_C"));
baraja.add(new Carta(4, "resources/4_C"));
baraja.add(new Carta(5, "resources/5_C"));
baraja.add(new Carta(6, "resources/6_C"));
baraja.add(new Carta(7, "resources/7_C"));
baraja.add(new Carta(8, "resources/8_C"));
baraja.add(new Carta(9, "resources/9_C"));
baraja.add(new Carta(10, "resources/10_C"));

// Palo D
baraja.add(new Carta(1, "resources/1_D"));
baraja.add(new Carta(2, "resources/2_D"));
baraja.add(new Carta(3, "resources/3_D"));
baraja.add(new Carta(4, "resources/4_D"));
baraja.add(new Carta(5, "resources/5_D"));
baraja.add(new Carta(6, "resources/6_D"));
baraja.add(new Carta(7, "resources/7_D"));
baraja.add(new Carta(8, "resources/8_D"));
baraja.add(new Carta(9, "resources/9_D"));
baraja.add(new Carta(10, "resources/10_D"));

// Palo P
baraja.add(new Carta(1, "resources/1_P"));
baraja.add(new Carta(2, "resources/2_P"));
baraja.add(new Carta(3, "resources/3_P"));
baraja.add(new Carta(4, "resources/4_P"));
baraja.add(new Carta(5, "resources/5_P"));
baraja.add(new Carta(6, "resources/6_P"));
baraja.add(new Carta(7, "resources/7_P"));
baraja.add(new Carta(8, "resources/8_P"));
baraja.add(new Carta(9, "resources/9_P"));
baraja.add(new Carta(10, "resources/10_P"));

    }

    public ArrayList<Carta> getBaraja() {
        return baraja;
    }
}
