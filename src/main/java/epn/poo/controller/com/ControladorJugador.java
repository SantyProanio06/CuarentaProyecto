package epn.poo.controller.com;

import java.util.ArrayList;

public class ControladorJugador {
    
    public void botarCarta(ArrayList<Carta> maso, ArrayList<Carta> mesa, Carta cartaSeleccionada){
        // Buscar y remover la carta del maso
        for (int i = 0; i < maso.size(); i++) {
            if(maso.get(i).equals(cartaSeleccionada)){
                mesa.add(maso.get(i));
                maso.remove(i);
                break;
            }
        }
    }
    
    // Método actualizado: ahora recibe la carta lanzada también
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