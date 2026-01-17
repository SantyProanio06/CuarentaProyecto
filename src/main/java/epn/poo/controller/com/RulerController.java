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
        boolean valor = false;
        if(cSelecMesa.equals(cSeleccionada))valor = true;
        return valor;
    }
    
    public boolean isSuma(ArrayList<Carta> cartasSelecSuma, Carta cartaSelec ){
        boolean valor = false;
        int suma = 0;
        for(Carta c: cartasSelecSuma){
            suma += c.getNumero();
        }
        if(suma == cartaSelec.getNumero())valor = true;
        return valor;
    }
    
    public boolean esCaida(Carta cSeleccionada, Carta cLanzada, Carta cUltimaLanzada){
    return (cSeleccionada.equals(cUltimaLanzada) && cLanzada.equals(cUltimaLanzada))? true : false;
    }
    
    public boolean esLimpia(ArrayList<Carta> mesa){
        return (mesa.isEmpty() || mesa.size() == 0)? true :  false; 
    }
    
    public boolean esEscalera(int index, ArrayList<Carta> cartaSelectMesa, Carta cartaSelectMaso){
        boolean valor = false;
        if(cartaSelectMaso.getNumero() == (cartaSelectMesa.get(index).getNumero() -1 )){
            for(int i = 0; i < (cartaSelectMesa.size() - 1); i++){
                if(cartaSelectMesa.get(i).getNumero() == (cartaSelectMesa.get(i + 1).getNumero() - 1)) valor = true;
            }
        }
        return valor;
    }
}

