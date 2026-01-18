package epn.poo.controller.com;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author santi
 */
public class RulerManager {
  
    public void manager(ArrayList<Carta> mesa, ArrayList<Carta> cartaSelecMesa, 
            Carta cLanzada, Equipo equipo, Carta ultLanzada){
       RulerController rc = new RulerController();
       ControladorJugador cj = new ControladorJugador();
        
       ArrayList<Carta> cartasSuma = new ArrayList<>();
       cartasSuma.add(cartaSelecMesa.get(0));
       cartasSuma.add(cartaSelecMesa.get(1));
        
       for (Carta c : cartaSelecMesa){
           if(rc.isAgarrarCarta(c, cLanzada)){
               if(rc.esCaida(c, cLanzada, ultLanzada)) equipo.setPerros(equipo.getPerros() + 2);
               if(rc.esEscalera(1, cartaSelecMesa, cLanzada)){
                   cj.cojerCartas(cartaSelecMesa, equipo.getCarton(), mesa);
               }
               else {
                   cj.cojerCartas(cartaSelecMesa, equipo.getCarton(), mesa);
               }
               if(rc.esLimpia(mesa)) equipo.setPerros(equipo.getPerros() + 2);
           }else if(rc.isSuma(cartasSuma, cLanzada)){
               if(rc.esEscalera(2, cartaSelecMesa, cLanzada)){
                   cj.cojerCartas(cartaSelecMesa, equipo.getCarton(), mesa);
               }
               else {
                   cj.cojerCartas(cartaSelecMesa, equipo.getCarton(), mesa);
               }
               if(rc.esLimpia(mesa)) equipo.setPerros(equipo.getPerros() + 2); 
           }
       } 
    }
}
