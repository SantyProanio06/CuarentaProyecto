package epn.poo.gui.com;

import epn.poo.controller.com.Carta;
import epn.poo.controller.com.RulerManager;
import epn.poo.controller.com.Equipo;
import epn.poo.controller.com.Jugador;
import epn.poo.controller.com.ControladorCartas;
import epn.poo.controller.com.Baraja;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ventanajuego extends JFrame {

    private ArrayList<Carta> mesa;
    private ArrayList<Jugador> jugadores;
    private Baraja baraja;

    private PanelMesa panelMesa;
    private PanelMano panelMano;
    private PanelCambioTurno panelCambioTurno;

    private JButton btnLanzar;
    private JButton btnLlevar;

    private JLabel lblEquipo;
    private JLabel lblPerros;

    private RulerManager rulerManager;
    private Equipo equipo1;
    private Equipo equipo2;

    private int indiceJugadorActual = 0;
    private Carta ultimaCartaLanzada;
    private ControladorCartas controladorCartas;
    private int rondasJugadas = 0;
    private int numJugadores;

    public Ventanajuego(ArrayList<Carta> mesa, ArrayList<Jugador> jugadores, Equipo equipo1, Equipo equipo2, int numJugadores) {

        this.mesa = mesa;
        this.jugadores = jugadores;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.numJugadores = numJugadores;
        this.rulerManager = new RulerManager();
        this.controladorCartas = new ControladorCartas();
        this.baraja = new Baraja();

        configurarVentana();
        inicializarComponentes();
        agregarComponentes();
        configurarEventos();
        actualizarBotones();
    }

    private void configurarVentana() {
        setTitle("Cuarenta");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {

        panelMesa = new PanelMesa(mesa);
        panelMano = new PanelMano(jugadores.get(indiceJugadorActual).getMasoJugador());
        panelCambioTurno = new PanelCambioTurno();
        panelCambioTurno.setVisible(false);

        btnLanzar = new JButton("Lanzar carta");
        btnLlevar = new JButton("Llevar cartas");

        lblEquipo = new JLabel(jugadores.get(indiceJugadorActual).getNombreJugador());
        lblPerros = new JLabel("Perros Equipo 1: " + equipo1.getPerros() + " | Equipo 2: " + equipo2.getPerros());
    }

    private void agregarComponentes() {

        JPanel panelInfo = new JPanel(new FlowLayout());
        panelInfo.setBorder(BorderFactory.createEtchedBorder());
        panelInfo.add(lblEquipo);
        panelInfo.add(lblPerros);

        JPanel panelAcciones = new JPanel(new GridLayout(2, 1, 5, 5));
        panelAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
        panelAcciones.add(btnLanzar);
        panelAcciones.add(btnLlevar);

        add(panelInfo, BorderLayout.NORTH);
        add(panelMesa, BorderLayout.CENTER);
        add(panelMano, BorderLayout.SOUTH);
        add(panelAcciones, BorderLayout.EAST);
        add(panelCambioTurno, BorderLayout.CENTER);
    }

    private void configurarEventos() {

        btnLanzar.addActionListener(e -> lanzarCarta());
        btnLlevar.addActionListener(e -> llevarCartas());
        panelCambioTurno.getBtnContinuar().addActionListener(e -> continuarTurno());
    }

    private void lanzarCarta() {

        Carta cartaSeleccionada = panelMano.getCartaSeleccionada();

        if (cartaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una carta para lanzar");
            return;
        }

        // Remover carta del maso del jugador
        ArrayList<Carta> masoActual = jugadores.get(indiceJugadorActual).getMasoJugador();
        masoActual.remove(cartaSeleccionada);
        
        // Agregar a la mesa
        mesa.add(cartaSeleccionada);
        
        // Actualizar interfaz
        panelMano.quitarCarta(cartaSeleccionada);
        panelMesa.refrescarMesa();
        
        ultimaCartaLanzada = cartaSeleccionada;

        // Verificar si se acabaron las cartas del jugador
        verificarFinRonda();
    }

    private void llevarCartas() {
        Carta cartaLanzada = panelMano.getCartaSeleccionada();
        ArrayList<Carta> cartasSeleccionadas = panelMesa.getCartasSeleccionadas();

        if (cartaLanzada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una carta de tu mano para lanzar");
            return;
        }

        if (cartasSeleccionadas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos una carta de la mesa");
            return;
        }

        Equipo equipoActual = obtenerEquipoActual();

        // Primero lanzar la carta a la mesa
        ArrayList<Carta> masoActual = jugadores.get(indiceJugadorActual).getMasoJugador();
        masoActual.remove(cartaLanzada);
        mesa.add(cartaLanzada);
        panelMano.quitarCarta(cartaLanzada);

        // Luego ejecutar la jugada de llevar cartas
        rulerManager.rulerManagermetodo(cartaLanzada, cartasSeleccionadas, mesa, equipoActual, ultimaCartaLanzada);

        // Actualizar última carta lanzada
        ultimaCartaLanzada = cartaLanzada;

        panelMesa.refrescarMesa();
        refrescarPerros();

        // Verificar si se acabaron las cartas
        verificarFinRonda();
    }

    private void verificarFinRonda() {
        // Verificar si el jugador actual no tiene más cartas
        if (jugadores.get(indiceJugadorActual).getMasoJugador().isEmpty()) {
            
            // Verificar si todos los jugadores terminaron sus cartas
            boolean todosVacios = true;
            for (Jugador j : jugadores) {
                if (!j.getMasoJugador().isEmpty()) {
                    todosVacios = false;
                    break;
                }
            }

            if (todosVacios) {
                rondasJugadas++;
                
                // Si es de 2 jugadores son 4 rondas, si es de 4 jugadores son 2 rondas
                int rondasPorMano = (numJugadores == 2) ? 4 : 2;
                
                if (rondasJugadas < rondasPorMano) {
                    // Repartir nuevas cartas
                    repartirNuevaRonda();
                } else {
                    // Fin de la mano, calcular cartón
                    finalizarMano();
                }
                return;
            }
        }

        // Pasar al siguiente jugador
        mostrarCambioTurno();
    }

    private void repartirNuevaRonda() {
        JOptionPane.showMessageDialog(this, "Ronda terminada. Repartiendo nuevas cartas...");

        for (Jugador j : jugadores) {
            ArrayList<Carta> nuevaMano = controladorCartas.repartirCartas(baraja.getBaraja());
            j.setMasoJugador(nuevaMano);
        }

        panelMano.actualizarMano(jugadores.get(indiceJugadorActual).getMasoJugador());
        panelMano.refrescar();
        actualizarBotones();
    }

    private void finalizarMano() {
        // Calcular cartón
        calcularCarton(equipo1);
        calcularCarton(equipo2);

        refrescarPerros();

        // Verificar ganador
        if (equipo1.getPerros() >= 40) {
            JOptionPane.showMessageDialog(this, "¡Equipo 1 gana con " + equipo1.getPerros() + " perros!");
            System.exit(0);
        } else if (equipo2.getPerros() >= 40) {
            JOptionPane.showMessageDialog(this, "¡Equipo 2 gana con " + equipo2.getPerros() + " perros!");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Mano terminada.\nEquipo 1: " + equipo1.getPerros() + " perros\nEquipo 2: " + equipo2.getPerros() + " perros");
            
            // Reiniciar para nueva mano
            rondasJugadas = 0;
            baraja = new Baraja();
            repartirNuevaRonda();
        }
    }

    private void calcularCarton(Equipo equipo) {
        int totalCarton = equipo.getCarton().size();
        
        if (totalCarton > 19) {
            int exceso = totalCarton - 20;
            int perrosGanados = exceso + 6;
            
            // Redondear a par más cercano
            if (perrosGanados % 2 != 0) {
                perrosGanados--;
            }
            
            equipo.setPerros(equipo.getPerros() + perrosGanados);
        }
    }

    private void mostrarCambioTurno() {

        indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();

        panelCambioTurno.setJugador(jugadores.get(indiceJugadorActual).getNombreJugador());
        panelCambioTurno.setVisible(true);

        panelMesa.setVisible(false);
        panelMano.setVisible(false);
        btnLanzar.setVisible(false);
        btnLlevar.setVisible(false);
    }

    private void continuarTurno() {

        panelCambioTurno.setVisible(false);

        panelMesa.setVisible(true);
        panelMano.setVisible(true);

        lblEquipo.setText(jugadores.get(indiceJugadorActual).getNombreJugador());
        
        // Actualizar el panel de mano con las cartas del jugador actual
        panelMano.actualizarMano(jugadores.get(indiceJugadorActual).getMasoJugador());
        panelMano.refrescar();
        panelMesa.refrescarMesa();
        
        actualizarBotones();
    }

    private void actualizarBotones() {
        // Siempre mostrar el botón lanzar
        btnLanzar.setEnabled(true);
        btnLanzar.setVisible(true);
        
        // Solo mostrar "Llevar cartas" si hay cartas en la mesa
        if (mesa.size() > 0) {
            btnLlevar.setEnabled(true);
            btnLlevar.setVisible(true);
        } else {
            btnLlevar.setEnabled(false);
            btnLlevar.setVisible(false);
        }
    }

    private Equipo obtenerEquipoActual() {
        // Si es de 2 jugadores: jugador 0 = equipo1, jugador 1 = equipo2
        // Si es de 4 jugadores: jugadores 0,1 = equipo1, jugadores 2,3 = equipo2
        if (numJugadores == 2) {
            return (indiceJugadorActual == 0) ? equipo1 : equipo2;
        } else {
            return (indiceJugadorActual < 2) ? equipo1 : equipo2;
        }
    }

    public void refrescarPerros() {
        lblPerros.setText("Perros Equipo 1: " + equipo1.getPerros() + " | Equipo 2: " + equipo2.getPerros());
    }
}