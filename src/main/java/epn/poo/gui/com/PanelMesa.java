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

    public void refrescarMesa() {
        System.out.println("=== REFRESCAR MESA ===");
        System.out.println("Cartas en mesa: " + mesa.size());
        
        removeAll();
        cartasSeleccionadas.clear();

        for (int i = 0; i < mesa.size(); i++) {
            Carta c = mesa.get(i);
            System.out.println("Creando botón para carta " + (i+1) + ": " + c.getNumero() + " - " + c.getImagePath());
            BotonCarta boton = new BotonCarta(c);
            boton.addActionListener(e -> manejarSeleccion(boton));
            add(boton);
        }
        
        System.out.println("Total de componentes agregados: " + getComponentCount());

        revalidate();
        repaint();
        
        System.out.println("=== FIN REFRESCAR ===");
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
    }

    public ArrayList<Carta> getCartasSeleccionadas() {
        return new ArrayList<>(cartasSeleccionadas);
    }
    
    public void agregarCarta(Carta carta) {
        mesa.add(carta);
        refrescarMesa();
    }
}