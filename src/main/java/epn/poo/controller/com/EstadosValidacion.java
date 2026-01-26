package epn.poo.controller.com;

import java.util.ArrayList;
/**
 * Clase que valida los diferentes estados que pueden ocurrir durante un juego osea se jugadas siendo estas que pueden darse al escoger una carta de la mesa
 * o varias teniendo en cuenta que estos estados pueden ser bonuses de otros o pueden ser excluyentes
 * @author Santi
 * @author Sebas
 */
public class EstadosValidacion {
    RulerController rc = new RulerController();
    
    public EstadosValidacion(){}
    
    /**
     * Este metodo valida el estado de alzar una carta de la mesa teniendo en cuenta que se pueden sumar los bonuses de caida y limpia
     * @param cLanz es la carta que el jugador lanza desde su maso
     * @param cSelec la carta que el jugador planea alzar de la mesa
     * @param cUlt es la carta que lanzo el jugador inmediatamente anterior(no contando si ya paso un turno desde que se lanzo)
     * @param e Es el equipo al que pertenece el jugador
     * @param m son las cartas que se encuentran en mesa
     * @return 
     */
    public boolean unaCartaSeleccionada(Carta cLanz, Carta cSelec , Carta cUlt, Equipo e, ArrayList<Carta> m){
        System.out.println(">>> LLAMADA a unaCartaSeleccionada()");
        if(rc.isAgarrarCarta(cLanz, cSelec)){
            
            ArrayList<Carta> selec = new ArrayList<>();
            selec.add(cSelec);
            
            // Verificar si es caída
            if(cUlt != null && rc.esCaida(cSelec, cLanz, cUlt)){
                System.out.println("CAÍDA DETECTADA - Perros antes: " + e.getPerros());
                e.aumentarPerros();
                System.out.println("CAÍDA DETECTADA - Perros antes: " + e.getPerros());
            }
            
            // Siempre llevar las cartas si los números coinciden
            cogerCartasValidarLimpia(cLanz, m, selec, e);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Este metodo valida el caso en el que el jugador quiere alzar mas de una cartas de la mesa validando si es posibles escaleras o no
     * @param cSelec son las cartas que el jugador planea alzar de la mesa
     * @param cLanz es la carta que el jugador lanza desde su maso
     * @param cSum son las cartas que si fuese el caso el jugador sumaria para alzar las cartas a sumar y su carta lanzada
     * @param m son las cartas de la mesa
     * @param cUlt es la carta que lanzo el jugador inmediatamente anterior(no contando si ya paso un turno desde que se lanzo)
     * @param e Es el equipo al que pertenece el jugador
     * @return 
     */
    public boolean masCartasSeleccionadas(ArrayList<Carta> cSelec, Carta cLanz, 
            ArrayList<Carta> cSum, ArrayList<Carta> m, Carta cUlt, Equipo e){
        Carta firstC = cSelec.get(0);
        
        if(firstC.getNumero() < cLanz.getNumero()){
            if(rc.isSuma(cSum, cLanz)){
                if(cSelec.size() == 2){
                    cogerCartasValidarLimpia(cLanz, m, cSelec, e);
                    return true;
                }else if(rc.esEscalera(2, cSelec, cLanz)){
                    cogerCartasValidarLimpia(cLanz, m, cSelec, e);
                    return true;
                }
            }
        }else{
            if(rc.isAgarrarCarta(cLanz, firstC)){
                if(rc.esCaida(firstC, cLanz, cUlt)) e.aumentarPerros();
                if(rc.esEscalera(1, cSelec, cLanz)) cogerCartasValidarLimpia(cLanz, m, cSelec, e);
             return true;
            }else return false;
        }
        return false;
    }
    
    /**
     * tiene la logica de alzar las cartas de la mesa y validar la limpia
     * @param cartaLanzada es la carta que se seleccioina del maso del jugador
     * @param mesa son las cartas que se encuentran en la mesa
     * @param cartasSeleccionadasMesa son las cartas de la mesa seleccionadas para alzar
     * @param e es el equipo al que pertenece el jugador
     * el metodo tambien contiene partes de debugging
     */
    // Método actualizado: ahora recibe la carta lanzada
    public void cogerCartasValidarLimpia(Carta cartaLanzada, ArrayList<Carta> mesa, ArrayList<Carta> cartasSeleccionadasMesa, Equipo e){
        System.out.println("ANTES de cojerCartas - Tamaño mesa: " + mesa.size());
        System.out.println("Cartas a remover: " + cartasSeleccionadasMesa.size());
        ControladorJugador conJug = new ControladorJugador();
        conJug.cojerCartas(cartaLanzada, cartasSeleccionadasMesa, e.getCarton(), mesa);
        System.out.println("DESPUÉS de cojerCartas - Tamaño mesa: " + mesa.size());
        if(rc.esLimpia(mesa)) {
            System.out.println("LIMPIA DETECTADA - Perros antes: " + e.getPerros());
            e.aumentarPerros();
        System.out.println("LIMPIA DETECTADA - Perros antes: " + e.getPerros());
        System.out.println("LIMPIA DETECTADA - Perros después: " + e.getPerros());
        } else {
            System.out.println("NO ES LIMPIA - No se suman perros");
        }
    }
}
