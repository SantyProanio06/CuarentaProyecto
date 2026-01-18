/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.controller.com;

import java.util.ArrayList;


/**
 *
 * @author santi
 */
public class EstadosValidacion {
    RulerController rc = new RulerController();
    
    public EstadosValidacion(){}
    
    public void unaCartaSeleccionada(Carta cLanz, Carta cSelec , Carta cUlt, Equipo e, ArrayList<Carta> m){
        if(rc.isAgarrarCarta(cLanz, cSelec)){
            if(rc.esCaida(cSelec, cLanz, cUlt)){
                e.aumentarPerros();
                ArrayList<Carta> selec = new ArrayList<>();
                selec.add(cSelec);
                cogerCartasValidarLimpia(m, selec, e);
            }
        }
    }
    
    public void masCartasSeleccionadas(ArrayList<Carta> cSelec, Carta cLanz, 
            ArrayList<Carta> cSum, ArrayList<Carta> m, Carta cUlt, Equipo e){
        Carta firstC = cSelec.get(0);
        
        if(firstC.getNumero() < cLanz.getNumero()){
            if(rc.isSuma(cSum, cLanz)){
                if(cSelec.size() == 2){
                    cogerCartasValidarLimpia(m, cSelec, e);
                }else if(rc.esEscalera(2, cSelec, cLanz)){
                    cogerCartasValidarLimpia(m, cSelec, e);
                }
            }
        }else{
            if(rc.isAgarrarCarta(cLanz, firstC)){
                if(rc.esCaida(firstC, cLanz, cUlt)) e.aumentarPerros();
                if(rc.esEscalera(1, cSelec, cLanz)) cogerCartasValidarLimpia(m, cSelec, e);
            }
        }
    }
    public void cogerCartasValidarLimpia(ArrayList<Carta> mesa, ArrayList<Carta> cartasSeleccionadasMesa, Equipo e){
        ControladorJugador conJug = new ControladorJugador();
        conJug.cojerCartas(cartasSeleccionadasMesa, e.getCarton(), mesa);
        if(rc.esLimpia(mesa))e.aumentarPerros();
    }
}
