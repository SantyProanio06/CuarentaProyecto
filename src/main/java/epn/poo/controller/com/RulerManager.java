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

    public RulerManager() {
    }
  
    public void rulerManagermetodo(Carta cartaLanzada, ArrayList<Carta> cartasSeleccionadasMesa,
    ArrayList<Carta> mesa,Equipo equipoActual,Carta ultimaCartaLanzada){
        
        ControladorJugador conJug = new ControladorJugador();
        
        RulerController rulerCont = new RulerController();
        
        ArrayList<Carta> cartasSumar = new ArrayList<Carta>();
        if(cartasSeleccionadasMesa.size() > 1){
        cartasSumar.add(cartasSeleccionadasMesa.get(0));
        cartasSumar.add(cartasSeleccionadasMesa.get(1));
        }
        int tipoJugadavalidador = cartasSeleccionadasMesa.size();
        
        boolean jugadaValidaSumar;
        boolean jugadaValidaLLevar;
        //Validacion de si es que solo se escoge una carta de la mesa
        if(tipoJugadavalidador == 1){
            jugadaValidaLLevar = rulerCont.isAgarrarCarta(cartaLanzada, cartasSeleccionadasMesa.get(0));
            if(jugadaValidaLLevar == true){
                if(rulerCont.esCaida(cartasSeleccionadasMesa.get(0), cartaLanzada, ultimaCartaLanzada))
                    equipoActual.aumentarPerros();
                cogerCartasValidarLimpia(mesa, cartasSeleccionadasMesa); 
            }
        }
        //Si se escoge mas de una carta de la mesa
        else if(tipoJugadavalidador > 1){
            //Se verifica que pueda existir una suma
            if(cartasSeleccionadasMesa.get(0).getNumero() < cartaLanzada.getNumero()){
                jugadaValidaSumar = rulerCont.isSuma(cartasSumar, cartaLanzada);
                if(jugadaValidaSumar == true){
                    //En caso de poder existir suma y esta sea valida se verifica si puede existir una escalera
                    if(tipoJugadavalidador == 2){
                        cogerCartasValidarLimpia(mesa, cartasSeleccionadasMesa);
                    }else if(rulerCont.esEscalera(2, cartasSeleccionadasMesa, cartaLanzada)== true){
                        cogerCartasValidarLimpia(mesa, cartasSeleccionadasMesa);
                    }
                }
            //Si no puede existir suma se verifica que te puedas llevar las cartas y la escalera que se intenta hacer
            }else{
                jugadaValidaLLevar = rulerCont.isAgarrarCarta(cartaLanzada, cartasSeleccionadasMesa.get(0));
                if(jugadaValidaLLevar == true){
                if(rulerCont.esCaida(cartasSeleccionadasMesa.get(0), cartaLanzada, ultimaCartaLanzada))
                    equipoActual.aumentarPerros();
                if(rulerCont.esEscalera(1, cartasSeleccionadasMesa, cartaLanzada)== true){
                        cogerCartasValidarLimpia(mesa, cartasSeleccionadasMesa);
                    }
                }
            }
        }
    }
    public void cogerCartasValidarLimpia(ArrayList<Carta> mesa, ArrayList<Carta> cartasSeleccionadasMesa){
        ControladorJugador conJug = new ControladorJugador();
        RulerController rulerCont = new RulerController();
        Equipo e = new Equipo();
        conJug.cojerCartas(cartasSeleccionadasMesa, e.getCarton(), mesa);
        if(rulerCont.esLimpia(mesa))e.aumentarPerros();
        
    }
}
