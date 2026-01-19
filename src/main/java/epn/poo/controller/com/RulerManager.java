package epn.poo.controller.com;

import java.util.ArrayList;

public class RulerManager {

    ControladorJugador conJug = new ControladorJugador();
        
    RulerController rulerCont = new RulerController();
    
    EstadosValidacion eVal = new EstadosValidacion();
    
    public RulerManager() {
    }
  
    public void rulerManagermetodo(Carta cartaLanzada, ArrayList<Carta> cartasSeleccionadasMesa,
    ArrayList<Carta> mesa, Equipo equipoActual, Carta ultimaCartaLanzada){
        ArrayList<Carta> cartasSumar = new ArrayList<Carta>();
        if(cartasSeleccionadasMesa.size() > 1){
            cartasSumar.add(cartasSeleccionadasMesa.get(0));
            cartasSumar.add(cartasSeleccionadasMesa.get(1));
        }
        int tipoJugadavalidador = cartasSeleccionadasMesa.size();
     
        // Validación de si es que solo se escoge una carta de la mesa
        if(tipoJugadavalidador == 1){
            eVal.unaCartaSeleccionada(cartaLanzada, cartasSeleccionadasMesa.get(0), ultimaCartaLanzada, equipoActual, mesa);
        }
        // Si se escoge más de una carta de la mesa
        else if(tipoJugadavalidador > 1){
            eVal.masCartasSeleccionadas(cartasSeleccionadasMesa, cartaLanzada, cartasSumar, mesa, ultimaCartaLanzada, equipoActual);
        }
    }
}