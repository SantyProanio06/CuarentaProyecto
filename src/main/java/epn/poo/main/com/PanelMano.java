/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.main.com;

/**
 *
 * @author HP
 */



import epn.poo.controller.com.Carta;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PanelMano extends JPanel {

    private ArrayList<Carta> mano;

    public PanelMano(ArrayList<Carta> manoJugador) {
        this.mano = manoJugador;
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        refrescar();
    }

    private void refrescar() {
        removeAll();

        for (Carta c : mano) {
            BotonCarta boton = new BotonCarta(c);
            add(boton);
        }

        revalidate();
        repaint();
    }

    public void quitarCarta(Carta carta) {
        mano.remove(carta);
        refrescar();
    }
}


