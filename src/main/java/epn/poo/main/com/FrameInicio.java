package epn.poo.main.com;

import epn.poo.controller.com.*;
import epn.poo.gui.com.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author sebas
 */
public class FrameInicio extends JFrame {

    private PanelInicio panelInicio;
    private int numJugadoresSeleccionado;

    public FrameInicio() {
        configurarVentana();
        inicializarComponentes();
        configurarEventos();
    }

    /**
     * Configura la ventana de juego 
     */
    private void configurarVentana() {
        setTitle("Cuarenta - Juego de Cartas");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Inicia el juego de manera visual con panle Inicio
     */
    private void inicializarComponentes() {
        panelInicio = new PanelInicio();
        setContentPane(panelInicio);
    }

    /**
     * configura los otones del panel inicio
     */
    private void configurarEventos() {
        // Eventos del Panel Inicio
        panelInicio.getBtnJugar().addActionListener(e -> abrirPanelNumeroJugadores());
        panelInicio.getBtnComoJugar().addActionListener(e -> abrirPanelComoJugar());
    }

    /**
     * sirve para abrir paneles de juego 
     * @param titulo
     * @param panel 
     */
    private void abrirPanel(String titulo, JPanel panel) {
        JFrame f = new JFrame(titulo);
        f.setContentPane(panel);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(900, 600);
        f.setLocationRelativeTo(this);
        f.setVisible(true);
    }

    /**
     * abre el tutorial
     */
    private void abrirPanelComoJugar() {
        PanelComoJugar panelComoJugar = new PanelComoJugar();
        
        JFrame f = new JFrame("Como Jugar");
        f.setContentPane(panelComoJugar);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(900, 600);
        f.setLocationRelativeTo(this);
        
        // Configurar evento del botón salir
        panelComoJugar.getBtnSalir().addActionListener(e -> f.dispose());
        
        f.setVisible(true);
    }

    /**
     * abre el panel numero de jugadores
     */
    private void abrirPanelNumeroJugadores() {
        PanelNumeroJugadores panelNumeroJugadores = new PanelNumeroJugadores();
        
        JFrame f = new JFrame("Número de Jugadores");
        f.setContentPane(panelNumeroJugadores);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(500, 300);
        f.setLocationRelativeTo(this);
        
        // Configurar eventos de los botones
        panelNumeroJugadores.getBtn2Personas().addActionListener(e -> {
            numJugadoresSeleccionado = 2;
            abrirPanelRegistro(2);
            f.dispose();
        });
        
        panelNumeroJugadores.getBtn4Personas().addActionListener(e -> {
            numJugadoresSeleccionado = 4;
            abrirPanelRegistro(4);
            f.dispose();
        });
        
        f.setVisible(true);
    }

    /**
     * Abre el registro de jugadores
     * @param numJugadores 
     */
    private void abrirPanelRegistro(int numJugadores) {
        try {
            PanelRegisroJugadores panelRegistro = new PanelRegisroJugadores(numJugadores);
            
            JFrame f = new JFrame("Registro de Jugadores");
            f.setContentPane(panelRegistro);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setSize(500, 400);
            f.setLocationRelativeTo(this);
            
            // Configurar evento del botón Listo
            panelRegistro.getBtnListo().addActionListener(e -> {
                iniciarJuego(numJugadores);
                f.dispose();
            });
            
            f.setVisible(true);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al crear panel de registro: " + ex.getMessage());
        }
    }

    /**
     * inia los parametros de inicio de juego
     * @param numJugadores 
     */
    private void iniciarJuego(int numJugadores) {
    try {
        // Leer nombres de jugadores del archivo
        ManejoArchivo ma = new ManejoArchivo();
        ArrayList<String> nombresJugadores = ma.obtenerTodos("Archivos/jugadores.txt");
        
        if (nombresJugadores.size() < numJugadores) {
            JOptionPane.showMessageDialog(this, "Error: No hay suficientes jugadores registrados");
            return;
        }
        
        // CAMBIO: Tomar los ÚLTIMOS jugadores registrados
        ArrayList<String> jugadoresSeleccionados = new ArrayList<>();
        int inicio = nombresJugadores.size() - numJugadores;
        for (int i = inicio; i < nombresJugadores.size(); i++) {
            jugadoresSeleccionados.add(nombresJugadores.get(i));
        }
        
        // Crear equipos
        Equipo equipo1 = new Equipo();
        Equipo equipo2 = new Equipo();

        // Crear jugadores
        ArrayList<Jugador> jugadores = new ArrayList<>();
        
        for (int i = 0; i < numJugadores; i++) {
            Jugador j = new Jugador();
            j.setNombreJugador(jugadoresSeleccionados.get(i));
            jugadores.add(j);

            if (numJugadores == 2) {
                if (i == 0) equipo1.getJugadores().add(j);
                else equipo2.getJugadores().add(j);
            } else { // 4 jugadores
                if (i < 2) equipo1.getJugadores().add(j);
                else equipo2.getJugadores().add(j);
            }
        }

        // Crear baraja y repartir
        Baraja baraja = new Baraja();
        ControladorCartas controladorCartas = new ControladorCartas();

        // Repartir cartas a cada jugador
        for (Jugador j : jugadores) {
            ArrayList<Carta> mano = controladorCartas.repartirCartas(baraja.getBaraja());
            j.setMasoJugador(mano);
        }

        // Mesa vacía
        ArrayList<Carta> mesa = new ArrayList<>();

        // Abrir ventana del juego
        Ventanajuego juego = new Ventanajuego(mesa, jugadores, equipo1, equipo2, numJugadores);
        juego.setVisible(true);

        // Cerrar ventana principal
        dispose();
        
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error al iniciar juego: " + ex.getMessage());
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrameInicio frame = new FrameInicio();
            frame.setVisible(true);
        });
    }
}