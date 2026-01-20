package epn.poo.gui.com;

import epn.poo.controller.com.Carta;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class PanelMesa extends JPanel {

    private ArrayList<Carta> mesa;
    private ArrayList<Carta> cartasSeleccionadas;
    private Runnable selectionListener;

    public PanelMesa(ArrayList<Carta> mesa) {
        this.mesa = mesa;
        this.cartasSeleccionadas = new ArrayList<>();

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Mesa"));
        setBackground(new Color(34, 139, 34));
        setPreferredSize(new Dimension(800, 200));
        setMinimumSize(new Dimension(800, 200));
        refrescarMesa();
    }

    public void addSelectionListener(Runnable listener) {
        this.selectionListener = listener;
    }

    public void refrescarMesa() {
        removeAll();
        cartasSeleccionadas.clear();

        for (int i = 0; i < mesa.size(); i++) {
            Carta c = mesa.get(i);
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
            boton.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        }
        
        // Notificar que cambió la selección
        if (selectionListener != null) {
            selectionListener.run();
        }
    }

    public ArrayList<Carta> getCartasSeleccionadas() {
        return new ArrayList<>(cartasSeleccionadas);
    }
    
    public void agregarCarta(Carta carta) {
        mesa.add(carta);
        refrescarMesa();
    }
}