/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.controller.com;

import java.util.ArrayList;

/**
 * Clase que representa un contenedor para todas las cartas de los naipes, cuenta
 * con un metodo para obtener dicha baraja y un constructor donde se le agregan las 
 * imagenes correspondientes.
 * @author santi
 * @author sebas
 */
public class Baraja {
    private ArrayList<Carta> baraja = new ArrayList<Carta>();  

    /**
     * Constrcutor por defectom donde se crean instancias de las 40 cartas para el juego
     * del cuartena, con su numero y su direccion de imagen.
     */
    public Baraja() {
// Palo T
baraja.add(new Carta(1, "/1_T.png"));
baraja.add(new Carta(2, "/2_T.png"));
baraja.add(new Carta(3, "/3_T.png"));
baraja.add(new Carta(4, "/4_T.png"));
baraja.add(new Carta(5, "/5_T.png"));
baraja.add(new Carta(6, "/6_T.png"));
baraja.add(new Carta(7, "/7_T.png"));
baraja.add(new Carta(8, "/8_T.png"));
baraja.add(new Carta(9, "/9_T.png"));
baraja.add(new Carta(10, "/10_T.png"));

// Palo C
baraja.add(new Carta(1, "/1_C.png"));
baraja.add(new Carta(2, "/2_C.png"));
baraja.add(new Carta(3, "/3_C.png"));
baraja.add(new Carta(4, "/4_C.png"));
baraja.add(new Carta(5, "/5_C.png"));
baraja.add(new Carta(6, "/6_C.png"));
baraja.add(new Carta(7, "/7_C.png"));
baraja.add(new Carta(8, "/8_C.png"));
baraja.add(new Carta(9, "/9_C.png"));
baraja.add(new Carta(10, "/10_C.png"));

// Palo D
baraja.add(new Carta(1, "/1_D.png"));
baraja.add(new Carta(2, "/2_D.png"));
baraja.add(new Carta(3, "/3_D.png"));
baraja.add(new Carta(4, "/4_D.png"));
baraja.add(new Carta(5, "/5_D.png"));
baraja.add(new Carta(6, "/6_D.png"));
baraja.add(new Carta(7, "/7_D.png"));
baraja.add(new Carta(8, "/8_D.png"));
baraja.add(new Carta(9, "/9_D.png"));
baraja.add(new Carta(10, "/10_D.png"));

// Palo P
baraja.add(new Carta(1, "/1_P.png"));
baraja.add(new Carta(2, "/2_P.png"));
baraja.add(new Carta(3, "/3_P.png"));
baraja.add(new Carta(4, "/4_P.png"));
baraja.add(new Carta(5, "/5_P.png"));
baraja.add(new Carta(6, "/6_P.png"));
baraja.add(new Carta(7, "/7_P.png"));
baraja.add(new Carta(8, "/8_P.png"));
baraja.add(new Carta(9, "/9_P.png"));
baraja.add(new Carta(10, "/10_P.png"));

    }
    
    /**
     * @return Cartas de la baraja.
     */
    public ArrayList<Carta> getBaraja() {
        return baraja;
    }
}
