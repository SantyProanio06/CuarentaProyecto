package epn.poo.gui.com;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import epn.poo.controller.com.Carta;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.awt.Color;
import javax.swing.BorderFactory;

public class BotonCarta extends JButton {

    private static final int ANCHO = 80;
    private static final int ALTO = 120;

    private Carta carta;
    private boolean seleccionada = false;

    public BotonCarta(Carta carta) {
        this.carta = carta;

        URL imgURL = getClass().getResource(carta.getImagePath());
        if (imgURL != null) {
            ImageIcon icono = new ImageIcon(imgURL);
            Image img = icono.getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(img));
        }

        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    public Carta getCarta() {
        return carta;
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public void toggleSeleccion() {
        seleccionada = !seleccionada;
        if (seleccionada) {
            setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        } else {
            setBorder(BorderFactory.createEmptyBorder());
        }
    }
    public void setSeleccionada(boolean sel) {
    if (sel) {
        setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED, 3));
    } else {
        setBorder(null);
    }
}
}
