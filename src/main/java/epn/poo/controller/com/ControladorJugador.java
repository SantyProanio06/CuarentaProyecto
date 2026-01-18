package epn.poo.controller.com;

import java.util.ArrayList;

public class ControladorJugador {
    
    public void botarCarta(ArrayList<Carta> maso, ArrayList<Carta> mesa, Carta cartaSeleccionada){
        // Buscar y remover la carta del maso
        for (int i = 0; i < maso.size(); i++) {
            if(maso.get(i).equals(cartaSeleccionada)){
                mesa.add(maso.get(i));
                maso.remove(i);
                break; // Salir después de encontrar y remover
            }
        }
    }
    
    public void cojerCartas(ArrayList<Carta> cartasLlevadas, ArrayList<Carta> cartonEquipo, ArrayList<Carta> mesa){
        // Primero agregar al cartón
        for(Carta c : cartasLlevadas){
            cartonEquipo.add(c);
        }
        
        // Luego remover de la mesa
        for(Carta c : cartasLlevadas){
            mesa.remove(c);
        }
    }
}
