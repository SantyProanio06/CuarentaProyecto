package epn.poo.gui.com;

import javax.swing.*;
import java.awt.*;

/**
 * Representa el panel para cambiar de turno, basicamente una panatall en negro con letras blancas
 * que indica a que jugador le toca jugar.
 * @author santi
 * @author sebas
 */
public class PanelCambioTurno extends JPanel {
    
    private JLabel lblJugador;
    private JButton btnContinuar;
    
    /**
     * Constructor por defecto donde se
     */
    public PanelCambioTurno() {

        setLayout(new BorderLayout());//Inicializacion de las zonas del panel
        setBackground(Color.BLACK);//Color del fondo
        
        //label con el nombre del jugador correspondiente
        lblJugador = new JLabel("", SwingConstants.CENTER);//Inicializacion y alineacion al centro
        lblJugador.setForeground(Color.WHITE);//Letras color blanco
        lblJugador.setFont(new Font("Arial", Font.BOLD, 28));//Fuente y en negrilla con tamaño de 28
        
        //Boton para continuar
        btnContinuar = new JButton("Continuar");
        
        //Añade dichos elementos al panel
        add(lblJugador, BorderLayout.CENTER);//Nombre centrado
        add(btnContinuar, BorderLayout.SOUTH);//Boton hacia el sur

        setVisible(false);//Inicia invisible
    }
    
    /**
     * @param nombre Nombre del jugador con el turno siguiente.
     */
    public void setJugador(String nombre) {
        lblJugador.setText("Turno de: " + nombre);
    }
    
    /**
     * @return Boton para continuar con el juego.
     */
    public JButton getBtnContinuar() {
        return btnContinuar;
    }
}

