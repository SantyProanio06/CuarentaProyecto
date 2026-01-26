package epn.poo.controller.com;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Clase que representa el controlador de las reglas, basicamente verifica que se cumplan o no las 
 * reglas del juego del cuarenta, cuenta con dos metodos uno que valida todo y el segundo que cuenta
 * los puntos ganados al final de cada ronda.
 * @author santi
 * @author sebas
 */
public class RulerManager {
    //Instancias auxiliares
    ControladorJugador conJug = new ControladorJugador();
        
    RulerController rulerCont = new RulerController();
    
    EstadosValidacion eVal = new EstadosValidacion();
    
    /**
     * Constructor por defecto
     */
    public RulerManager() {
    }
  
    /**
     * Valdida, segun el tamaño de las cartas seleccionadas de la mesa, si la jugada
     * es valida, ya sea caida(llevarse ultima carta lanzada), limpia(sin cartas en
     * la mesa), escalera o si es posible llevarse dichas cartas.
     * @param cartaLanzada Carta seleccionada del maso del jugador.
     * @param cartasSeleccionadasMesa Todas las cartas seleccionadas de la mesa.
     * @param mesa Todas las cartas que hayan en la mesa.
     * @param equipoActual Equipo que lanza la carta.
     * @param ultimaCartaLanzada Ultima carta lanzada por el otro equipo.
     * @return True si la jugada es valda, false si no.
     */
    public boolean rulerManagermetodo(Carta cartaLanzada, ArrayList<Carta> cartasSeleccionadasMesa,
    ArrayList<Carta> mesa, Equipo equipoActual, Carta ultimaCartaLanzada){
        //Ordena las cartas seleccionadas de menor a mayor en base al numero de getNumero(), usando el metodo sort.
        cartasSeleccionadasMesa.sort(Comparator.comparingInt(c -> c.getNumero()));
        ArrayList<Carta> cartasSumar = new ArrayList<Carta>();
        
        //Si selecciona mas de 1 puede ser una suma por lo que se cogen las 2 primeras cartas.
        if(cartasSeleccionadasMesa.size() > 1){
            cartasSumar.add(cartasSeleccionadasMesa.get(0));
            cartasSumar.add(cartasSeleccionadasMesa.get(1));
        }
        int tipoJugadavalidador = cartasSeleccionadasMesa.size();
     
        // Si solo escoge una carta de la mesa, se llama al metodo en referencia a esto.
        if(tipoJugadavalidador == 1){
            return eVal.unaCartaSeleccionada(cartaLanzada, cartasSeleccionadasMesa.get(0), ultimaCartaLanzada, equipoActual, mesa);
        }
        // Si se escoge más de una carta de la mesa, se llama al metodo referencial al suceso.
        else if(tipoJugadavalidador > 1){
            return eVal.masCartasSeleccionadas(cartasSeleccionadasMesa, cartaLanzada, cartasSumar, mesa, ultimaCartaLanzada, equipoActual);
        }
        return false;
    }
    
    
    /**
     * Calcula los perros ganados, en base al carton, de un equipo determinado.
     * @param e Equipo a calcular.
     */
     public void calcularCarton(Equipo e) {
        int totalCarton = e.getCarton().size();
        
        //Solo si tiene mas de 19 cartas agarradas se puede calcular.
        if (totalCarton > 19) {
            int cartonSumar = totalCarton - 20;//Cartas excedentes.
            int perrosGanados = 6; 
            while (cartonSumar != 0){
                perrosGanados ++;
                cartonSumar--;
            }
            if(perrosGanados %2 != 0)perrosGanados++;//Si la suma final es impar
            e.setPerros(e.getPerros() + perrosGanados);
        }
    }
}