package epn.poo.gui.com;

import epn.poo.controller.com.Carta;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class PanelMano extends JPanel {

    private ArrayList<Carta> mano;

    public PanelMano(ArrayList<Carta> manoJugador) {
        this.mano = manoJugador;
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Tu mano"));
        refrescar();
    }

    public void refrescar() {
        removeAll();

        for (Carta c : mano) {
            add(new BotonCarta(c));
        }

        revalidate();
        repaint();
    }

    public void quitarCarta(Carta carta) {
        mano.remove(carta);
        refrescar();
    }
}
