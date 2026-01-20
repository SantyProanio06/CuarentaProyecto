package epn.poo.controller.com;

import java.util.ArrayList;

public class EstadosValidacion {
    RulerController rc = new RulerController();
    
    public EstadosValidacion(){}
    
    public boolean unaCartaSeleccionada(Carta cLanz, Carta cSelec , Carta cUlt, Equipo e, ArrayList<Carta> m){
        if(rc.isAgarrarCarta(cLanz, cSelec)){
            
            ArrayList<Carta> selec = new ArrayList<>();
            selec.add(cSelec);
            
            // Verificar si es caída
            if(cUlt != null && rc.esCaida(cSelec, cLanz, cUlt)){
                e.aumentarPerros();
            }
            
            // Siempre llevar las cartas si los números coinciden
            cogerCartasValidarLimpia(cLanz, m, selec, e);
            return true;
        } else {
            return false;
        }
    }
    
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
    
    // Método actualizado: ahora recibe la carta lanzada
    public void cogerCartasValidarLimpia(Carta cartaLanzada, ArrayList<Carta> mesa, ArrayList<Carta> cartasSeleccionadasMesa, Equipo e){
        ControladorJugador conJug = new ControladorJugador();
        conJug.cojerCartas(cartaLanzada, cartasSeleccionadasMesa, e.getCarton(), mesa);
        if(rc.esLimpia(mesa)) e.aumentarPerros();
    }
}
