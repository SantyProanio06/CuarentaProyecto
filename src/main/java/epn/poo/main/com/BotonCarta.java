/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.main.com;

/**
 *
 * @author HP
 */
import javax.swing.JButton;
import javax.swing.ImageIcon;
import epn.poo.controller.com.Carta;

public class BotonCarta extends JButton {

    private Carta carta;

    public BotonCarta(Carta carta) {
        this.carta = carta;

        ImageIcon icono = new ImageIcon(carta.getImagePath());
        this.setIcon(icono);

        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
    }

    public Carta getCarta() {
        return carta;
    }
}

