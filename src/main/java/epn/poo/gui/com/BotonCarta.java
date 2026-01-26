package epn.poo.gui.com;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import epn.poo.controller.com.Carta;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.awt.Color;
import javax.swing.BorderFactory;

/**
 * Clase que represnta un boton de formato swing que a su vez represnta una carta del sistema.
 * @author santi
 * @author sebas
 */
public class BotonCarta extends JButton {
    
    //Tamaño del boton
    private static final int ANCHO = 80;
    private static final int ALTO = 120;

    private Carta carta;
    private boolean seleccionada = false;//Permite ver si ha sido seleccionada
    
    /**
     * Constructor con parametros del boton.
     * @param carta Carta dada para representarla como boton.
     */
    public BotonCarta(Carta carta) {
        this.carta = carta;
        /*
         Usa el parametro de ruta de imagen de la carta dada para buscar dicho
         archivo dentro de la carpeta classpath del proyecto, por eso usa una
        variable de tipo URL
         */
        URL imgURL = getClass().getResource(carta.getImagePath());
        if (imgURL != null) {
            ImageIcon icono = new ImageIcon(imgURL); //Carga dicha imagen como icono.
            
            //La dimensiona segun el amcho y el alto del boton y el scale smooth lo hace con mejor calidad
            Image img = icono.getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH);
            
            //Coloca el icono dentro del boton
            setIcon(new ImageIcon(img));
        }

        setPreferredSize(new Dimension(ANCHO, ALTO));//Coloca un tamaño "fijo" al boton.
        setBorder(BorderFactory.createEmptyBorder()); //Quita el borde del boton.
        setContentAreaFilled(false);//Tambien quita el fondo.
        setFocusPainted(false);//Quita los efectos azules del boton al ser clickeado.
    }
    
    /**
     * @return Carta que es representada por el boton.
     */
    public Carta getCarta() {
        return carta;
    }
    
    /**
     * @return El estado de validez de que la carta haya sido clickeada.
     */
    public boolean isSeleccionada() {
        return seleccionada;
    }
    
    /**
     * Cambia la seleccion del boton(de true a false y viceversa) y coloca un borde
     * a dicho boton.
     */
    public void toggleSeleccion() {
        seleccionada = !seleccionada;
        if (seleccionada) {
            setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));//Coloca un borde de color amarillo y anchura 3 pixeles.
        } else {
            setBorder(BorderFactory.createEmptyBorder());//Quita cualquier borde.
        }
    }
    
    /**
     * Coloca o no un borde al boton dependiendo si fue o no seleccionada.
     * @param sel True si fue seleccionada false sino.
     */
    public void setSeleccionada(boolean sel) {
    if (sel) {
        setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED, 3));//Coloca un borde rojo de anchura 3 pixeles.
    } else {
        setBorder(null);
    }
}
}
