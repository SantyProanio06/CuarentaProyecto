
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.controller.com;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class RulerController {
    public boolean isAgarrarCarta(Carta cSeleccionada, Carta cSelecMesa){
        return (cSeleccionada.getNumero() == cSelecMesa.getNumero());
    }
    
    public boolean isSuma(ArrayList<Carta> cartasSelecSuma, Carta cartaSelec ){
        int suma = 0;
        for(Carta c: cartasSelecSuma){
            suma += c.getNumero();
        }
        if(suma > 7) return false;
        if(suma == cartaSelec.getNumero())return true;
        return false;
    }
    
    public boolean esCaida(Carta cSeleccionada, Carta cLanzada, Carta cUltimaLanzada){
    return (cSeleccionada.getNumero() == cUltimaLanzada.getNumero() && 
            cLanzada.getNumero() == cUltimaLanzada.getNumero());
    }
    
    public boolean esLimpia(ArrayList<Carta> mesa){
        return mesa.isEmpty(); 
    }
    
    public boolean esEscalera(int index,ArrayList<Carta> cartasSelecMesa, Carta cartaMazo){
        if (cartasSelecMesa.isEmpty()) return false;
        
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
