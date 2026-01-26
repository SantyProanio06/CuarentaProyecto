package epn.poo.controller.com;

import java.util.ArrayList;

/**
 * describe las acciones del jugador
 * @author santi
 * @author sebas
 */
public class ControladorJugador {
    
    
    
    /**
     * Alza las cartas tanto las que se seleccionaron de la mesa como la que se selecciono del maso
     * @param cartaLanzada cata seleccionada del mazo
     * @param cartasLlevadas son las cartas que se seleccionan para alzar de la mesa
     * @param cartonEquipo es el carton que tiene el equipo en ese momento
     * @param mesa las cartas de la mesa tiene la utilidad en el debugging
     */
    public void cojerCartas(Carta cartaLanzada, ArrayList<Carta> cartasLlevadas, ArrayList<Carta> cartonEquipo, ArrayList<Carta> mesa){
        System.out.println("DEBUG cojerCartas - Mesa antes: " + mesa.size() + " cartas");
        System.out.println("DEBUG cojerCartas - Llevando: " + cartasLlevadas.size() + " cartas de la mesa");
        // Agregar la carta lanzada al cartón
        cartonEquipo.add(cartaLanzada);
        
        // Agregar las cartas de la mesa al cartón
        for(Carta c : cartasLlevadas){

            cartonEquipo.add(c);
        }

        
        // Remover las cartas llevadas de la mesa
        mesa.removeAll(cartasLlevadas);
    }
}