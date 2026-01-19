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
        System.out.println("  [cojerCartas] Agregando carta lanzada al cartón: " + cartaLanzada.getNumero());
        System.out.println("  [cojerCartas] Tamaño del cartón antes: " + cartonEquipo.size());
        
        // Agregar la carta lanzada al cartón
        cartonEquipo.add(cartaLanzada);
        
        // Agregar las cartas de la mesa al cartón
        for(Carta c : cartasLlevadas){
            System.out.println("  [cojerCartas] Agregando carta de mesa al cartón: " + c.getNumero());
            cartonEquipo.add(c);
        }
        
        System.out.println("  [cojerCartas] Tamaño del cartón después: " + cartonEquipo.size());
        System.out.println("  [cojerCartas] Tamaño de mesa antes de remover: " + mesa.size());
        
        // Remover las cartas llevadas de la mesa
        for(Carta c : cartasLlevadas){
            mesa.remove(c);
            System.out.println("  [cojerCartas] Removida de mesa: " + c.getNumero());
        }
        
        System.out.println("  [cojerCartas] Tamaño de mesa después de remover: " + mesa.size());
    }
}