
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.controller.com;

import java.util.ArrayList;

/**
 * Clase que representa el verificador de las reglas cuenta con metodos para validar 
 * ciertos estados de una partida, ya sea, caida(llevarse la ultima carta lanzada), limpia
 * (Que no quede cartas en la mesa), escalera o solo llevarse carta.
 * @author Santi
 * @author Sebas
 */
public class RulerController {
    
    /**
     * Valida que se puedan llevar las cartas seleccionadas.
     * @param cSeleccionada Carta lanzada del maso del jugador.
     * @param cSelecMesa Carta seleccionada de la mesa.
     * @return True si es valido, false si no.
     */
    public boolean isAgarrarCarta(Carta cSeleccionada, Carta cSelecMesa){
        return (cSeleccionada.getNumero() == cSelecMesa.getNumero());
    }
    
    /**
     * Valida que la carta lanzada equivalga a la suma de los numeros de las cartas dadas.
     * @param cartasSelecSuma Dos cartas que supuestamente suman la lanzaada.
     * @param cartaSelec Carta lanzada del maso del jugador
     * @return True si es la suma, false sino.
     */
    public boolean isSuma(ArrayList<Carta> cartasSelecSuma, Carta cartaSelec ){
        int suma = 0;
        for(Carta c: cartasSelecSuma){
            suma += c.getNumero();//Numero de la suma de numeros de las cartas.
        }
        if(suma > 7) return false;//La suma no puede ser mas de 7.
        if(suma == cartaSelec.getNumero())return true;
        return false;
    }
    
    /**
     * Valida que la ultima carta lanzada este en las cartas seleccionadas de la mesa
     * y que el numero de la carta del maso del jugador sea el mismo.
     * @param cSeleccionada Carta seleccionada de la mesa.
     * @param cLanzada Carta lanzada del maso del jugador.
     * @param cUltimaLanzada Ultima carta lanzada a la mesa.
     * @return True si es caida, false si no.
     */
    public boolean esCaida(Carta cSeleccionada, Carta cLanzada, Carta cUltimaLanzada){
    if(cUltimaLanzada != null){
        return (cSeleccionada.getNumero() == cUltimaLanzada.getNumero() && 
            cLanzada.getNumero() == cUltimaLanzada.getNumero());
        }
    return false;
    }
    
    /**
     * Valida que no queden cartas en la mesa.
     * @param mesa Cartas en la mesa.
     * @return True si no quedanm, false si si quedan.
     */
    public boolean esLimpia(ArrayList<Carta> mesa){
        System.out.println("DEBUG esLimpia - Verificando mesa con " + mesa.size() + " cartas");
        System.out.println("DEBUG esLimpia - ¿Está vacía? " + mesa.isEmpty());
        return mesa.isEmpty(); 
    }
    
    /**
     * Valida que las cartas seleccionadas sean una escalera, es decir
     * que el numero de la siguiente carta sea solo 1 mayor a la anterior.
     * @param index Valor que ayuda a determinar la carta actual.
     * @param cartasSelecMesa Cartas seleccionadas de la mesa.
     * @param cartaMazo Carta lanzada del maso del jugador.
     * @return True si es escalera, false sino.
     */
    public boolean esEscalera(int index,ArrayList<Carta> cartasSelecMesa, Carta cartaMazo){
        if (cartasSelecMesa.isEmpty()) return false;
        
        //Obtiene las carta del maso por un index, pues esta funcion esta pensada para un for.
        if (cartaMazo.getNumero() != cartasSelecMesa.get(index).getNumero() - 1) {
            return false;
        }

        for (int i = index; i < cartasSelecMesa.size() - 1; i++) {
            if (cartasSelecMesa.get(i).getNumero() + 1 != cartasSelecMesa.get(i + 1).getNumero()) {
                return false;
            }
        }

        return true;
    }
}
