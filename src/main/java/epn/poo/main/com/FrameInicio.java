package epn.poo.main.com;

import epn.poo.controller.com.*;
import epn.poo.gui.com.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FrameInicio extends JFrame {

    private JButton btn2Jugadores;
    private JButton btn4Jugadores;

    public FrameInicio() {
        configurarVentana();
        inicializarComponentes();
        agregarComponentes();
        acciones();
    }

    private void configurarVentana() {
        setTitle("Cuarenta - Inicio");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        btn2Jugadores = new JButton("2 Jugadores");
        btn4Jugadores = new JButton("4 Jugadores");
    }

    private void agregarComponentes() {
        JLabel titulo = new JLabel("¿Cuántos jugadores?", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btn2Jugadores);
        panelBotones.add(btn4Jugadores);

        add(titulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
    }

    private void acciones() {

        btn2Jugadores.addActionListener(e -> iniciarJuego(2));
        btn4Jugadores.addActionListener(e -> iniciarJuego(4));
    }

    private void iniciarJuego(int numJugadores) {

        // === Crear equipos ===
        Equipo equipo1 = new Equipo();
        Equipo equipo2 = new Equipo();

        // === Crear jugadores ===
        ArrayList<Jugador> jugadores = new ArrayList<>();
        
        for (int i = 1; i <= numJugadores; i++) {
            Jugador j = new Jugador();
            j.setNombreJugador("Jugador " + i);
            jugadores.add(j);

            if (numJugadores == 2) {
                if (i == 1) equipo1.getJugadores().add(j);
                else equipo2.getJugadores().add(j);
            } else { // 4 jugadores
                if (i <= 2) equipo1.getJugadores().add(j);
                else equipo2.getJugadores().add(j);
            }
        }

        // === Crear baraja y repartir ===
        Baraja baraja = new Baraja();
        ControladorCartas controladorCartas = new ControladorCartas();

        // Repartir cartas a cada jugador
        for (Jugador j : jugadores) {
            ArrayList<Carta> mano = controladorCartas.repartirCartas(baraja.getBaraja());
            j.setMasoJugador(mano);
        }

        // Mesa vacía
        ArrayList<Carta> mesa = new ArrayList<>();

        // === Abrir ventana del juego ===
        Ventanajuego juego = new Ventanajuego(mesa, jugadores, equipo1, equipo2, numJugadores);
        juego.setVisible(true);

        // Cerrar menú
        dispose();
    }
}
