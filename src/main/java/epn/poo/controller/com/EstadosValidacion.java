package epn.poo.controller.com;

import java.util.ArrayList;

public class EstadosValidacion {
    RulerController rc = new RulerController();
    
    public EstadosValidacion(){}
    
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
