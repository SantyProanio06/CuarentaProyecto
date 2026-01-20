package epn.poo.gui.com;

import epn.poo.controller.com.Carta;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class PanelMano extends JPanel {

    private ArrayList<Carta> mano;
    private Carta cartaSeleccionada;
    private Runnable selectionListener;

    public PanelMano(ArrayList<Carta> manoJugador) {
        this.mano = manoJugador;
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Tu mano"));
        refrescar();
    }

    public void addSelectionListener(Runnable listener) {
        this.selectionListener = listener;
    }

    public void actualizarMano(ArrayList<Carta> nuevaMano) {
        this.mano = nuevaMano;
        this.cartaSeleccionada = null;
    }

    public void refrescar() {
        removeAll();

        for (Carta c : mano) {
            BotonCarta btn = new BotonCarta(c);

            btn.addActionListener(e -> {
                cartaSeleccionada = c;
                refrescar();
                
                // Notificar que cambió la selección
                if (selectionListener != null) {
                    selectionListener.run();
                }
            });

            // Marcar visualmente la seleccionada
            if (c.equals(cartaSeleccionada)) {
                btn.setSeleccionada(true);
            }

            add(btn);
        }

        revalidate();
        repaint();
    }

    public Carta getCartaSeleccionada() {
        return cartaSeleccionada;
    }

    public void quitarCarta(Carta carta) {
        mano.remove(carta);
        cartaSeleccionada = null;
        refrescar();
    }
}