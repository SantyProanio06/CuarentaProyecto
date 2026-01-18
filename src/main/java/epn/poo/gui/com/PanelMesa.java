package epn.poo.gui.com;

import epn.poo.controller.com.Carta;
import java.awt.FlowLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class PanelMesa extends JPanel {

    private ArrayList<Carta> mesa;
    private ArrayList<Carta> cartasSeleccionadas;

    public PanelMesa(ArrayList<Carta> mesa) {
        this.mesa = mesa;
        this.cartasSeleccionadas = new ArrayList<>();

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Mesa"));
        refrescarMesa();
    }

    public void refrescarMesa() {
        removeAll();
        cartasSeleccionadas.clear();

        for (Carta c : mesa) {
            BotonCarta boton = new BotonCarta(c);

            boton.addActionListener(e -> manejarSeleccion(boton));

            add(boton);
        }

        revalidate();
        repaint();
    }

    private void manejarSeleccion(BotonCarta boton) {
        Carta carta = boton.getCarta();

        if (cartasSeleccionadas.contains(carta)) {
            cartasSeleccionadas.remove(carta);
            boton.setBorder(null);
        } else {
            cartasSeleccionadas.add(carta);
            boton.setBorder(
                BorderFactory.createLineBorder(Color.RED, 3)
            );
        }
    }

    public ArrayList<Carta> getCartasSeleccionadas() {
        return cartasSeleccionadas;
    }
    public void agregarCarta(Carta carta) {
    mesa.add(carta);
    refrescarMesa();
}
}
