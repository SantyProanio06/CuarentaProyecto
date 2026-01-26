package epn.poo.gui.com;

import epn.poo.controller.com.Carta;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

/**
 * Calse que representa un panel swing, que a su vez representa la mesa del juego que es donde estan, basicamente,
 * las cartas tiradas.
 * @author santi
 * @author sebas
 */
public class PanelMesa extends JPanel {

    private ArrayList<Carta> mesa;
    private ArrayList<Carta> cartasSeleccionadas;
    private Runnable selectionListener;//Es baiscamente un panel que notifica al panel actual si hubo algun cambio, ej. un click
    //Como analogia se le podria asemejar a un timbre, no sabe quien toca solo avisa que estan tocando.
    
    /**
     * Constructor con parametros.
     * @param mesa Cartas que estaran en la mesa.
     */
    public PanelMesa(ArrayList<Carta> mesa) {
        this.mesa = mesa;
        this.cartasSeleccionadas = new ArrayList<>();

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));//Coloca las cartas una detra de otra centradas, con separacion de 10px.
        setBorder(BorderFactory.createTitledBorder("Mesa"));//Coloca un titulo en el borde
        setBackground(new Color(34, 139, 34));//Coloca el fondo verde por medio de RGB
        //Pone el tamaño fijo del panel usando el tamaño deseado y el minimo como iguales
        setPreferredSize(new Dimension(800, 200));
        setMinimumSize(new Dimension(800, 200));
        refrescarMesa();//Metodo que refresca la mesa
    }
    
    /**
     * Coloca un escuchador que verifique si algo cambio usando la variable runabble.
     * @param listener Escucha y notifica cambios.
     */
    public void addSelectionListener(Runnable listener) {
        this.selectionListener = listener;
    }
    
    /**
     * Actualiza la mesa añadiendo las cartas como botones al panel.
     */
    public void refrescarMesa() {
        removeAll();//Quita los botones del panel
        cartasSeleccionadas.clear();//Limpia las cartas seleccionadas
        
        //Agrega las cartas de la mesa como botones
        for (int i = 0; i < mesa.size(); i++) {
            Carta c = mesa.get(i);//Carta actual
            BotonCarta boton = new BotonCarta(c);//Crea un boton con dicha carta
            boton.addActionListener(e -> manejarSeleccion(boton));//Al hacer click llama a manejar seleccion
            add(boton);//Agrega el boton
        }
        
        //Del panel
        revalidate();//Recalcula los tamaños del layout
        repaint();//Vuelve a dibujar(colores, bordes, etc) lo visible e el panel
    }
    
    /**
     * Coloca o quita el borde a un boton de una carta seleccionada y notifica este cambio
     * @param boton Boton que representa una carta.
     */
    private void manejarSeleccion(BotonCarta boton) {
        Carta carta = boton.getCarta();//Carta representada por el boton.
        
        //Cambia el estado de seleccion de la carta
        if (cartasSeleccionadas.contains(carta)) {//Si ya fue seleccioanda se quita el borde.
            cartasSeleccionadas.remove(carta);
            boton.setBorder(null);
        } else {//Sino se agrega a las cartas seleccionadas y se le agrega un borde rojo de anchura 3 pix.
            cartasSeleccionadas.add(carta);
            boton.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        }
        
        // Si hubo alguna accion, ej, un click, se ejecuta run, el cual notifica un cambio.
        if (selectionListener != null) {
            selectionListener.run();
        }
    }
    
    /**
     * @return Cartas seleccionadas de la mesa
     */
    public ArrayList<Carta> getCartasSeleccionadas() {
        return new ArrayList<>(cartasSeleccionadas);
    }
    
    /**
     * Añade una carta e inmediatamente actualiza la mesa.
     * @param carta Carta a añadir.
     */
    public void agregarCarta(Carta carta) {
        mesa.add(carta);
        refrescarMesa();
    }
}