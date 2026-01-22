package epn.poo.controller.com;

import java.util.ArrayList;
import java.util.Comparator;

public class RulerManager {

    ControladorJugador conJug = new ControladorJugador();
        
    RulerController rulerCont = new RulerController();
    
    EstadosValidacion eVal = new EstadosValidacion();
    
    public RulerManager() {
    }
  
    public boolean rulerManagermetodo(Carta cartaLanzada, ArrayList<Carta> cartasSeleccionadasMesa,
    ArrayList<Carta> mesa, Equipo equipoActual, Carta ultimaCartaLanzada){
        cartasSeleccionadasMesa.sort(Comparator.comparingInt(c -> c.getNumero()));
        ArrayList<Carta> cartasSumar = new ArrayList<Carta>();
        if(cartasSeleccionadasMesa.size() > 1){
            cartasSumar.add(cartasSeleccionadasMesa.get(0));
            cartasSumar.add(cartasSeleccionadasMesa.get(1));
        }
        int tipoJugadavalidador = cartasSeleccionadasMesa.size();
     
        // Validación de si es que solo se escoge una carta de la mesa
        if(tipoJugadavalidador == 1){
            return eVal.unaCartaSeleccionada(cartaLanzada, cartasSeleccionadasMesa.get(0), ultimaCartaLanzada, equipoActual, mesa);
        }
        // Si se escoge más de una carta de la mesa
        else if(tipoJugadavalidador > 1){
            return eVal.masCartasSeleccionadas(cartasSeleccionadasMesa, cartaLanzada, cartasSumar, mesa, ultimaCartaLanzada, equipoActual);
        }
        return false;
    }
    
     public void calcularCarton(Equipo e) {
        int totalCarton = e.getCarton().size();
        
        if (totalCarton > 19) {
            int cartonSumar = totalCarton - 20;
            int perrosGanados = 6;
            while (cartonSumar != 0){
                perrosGanados ++;
                cartonSumar--;
            }
            if(perrosGanados %2 != 0)perrosGanados++;
            e.setPerros(e.getPerros() + perrosGanados);
        }
    }
}