package epn.poo.gui.com;


import javax.swing.JButton;
import javax.swing.ImageIcon;
import epn.poo.controller.com.Carta;
import java.awt.Dimension;
import java.net.URL;

public class BotonCarta extends JButton {

    private Carta carta;

    public BotonCarta(Carta carta) {
        this.carta = carta;

        // Cargar imagen desde resources
        URL imgURL = getClass().getResource("/" + carta.getImagePath() + ".png");

        if (imgURL != null) {
            setIcon(new ImageIcon(imgURL));
        } else {
            System.out.println("No se pudo cargar: " + carta.getImagePath());
        }

        // Tamaño fijo del botón (MUY IMPORTANTE)
        setPreferredSize(new Dimension(80, 120));

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    public Carta getCarta() {
        return carta;
    }
}

