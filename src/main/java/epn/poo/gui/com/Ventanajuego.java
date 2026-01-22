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
    private JPanel panelJuego;

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
        
        panelJuego = new JPanel(new CardLayout());

        btnLanzar = new JButton("Lanzar carta");
        btnLlevar = new JButton("Llevar cartas");

        lblEquipo = new JLabel(jugadores.get(indiceJugadorActual).getNombreJugador());
        lblPerros = new JLabel("Perros Equipo 1: " + equipo1.getPerros() + " | Equipo 2: " + equipo2.getPerros());
        
        // Agregar listener para detectar cuando se seleccionan cartas en la mesa
        panelMesa.addSelectionListener(() -> actualizarBotones());
        panelMano.addSelectionListener(() -> actualizarBotones());
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

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelMesa, BorderLayout.CENTER);
        panelPrincipal.add(panelMano, BorderLayout.SOUTH);

        panelJuego.add(panelPrincipal, "JUEGO");
        panelJuego.add(panelCambioTurno, "CAMBIO");

        add(panelInfo, BorderLayout.NORTH);
        add(panelJuego, BorderLayout.CENTER);
        add(panelAcciones, BorderLayout.EAST);
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
        jugadores.get(indiceJugadorActual).getMasoJugador().remove(cartaSeleccionada);
        
        // Agregar a la mesa
        mesa.add(cartaSeleccionada);
        
        // Actualizar última carta lanzada
        ultimaCartaLanzada = cartaSeleccionada;
        
        // Actualizar interfaz
        panelMano.quitarCarta(cartaSeleccionada);
        panelMesa.refrescarMesa();
        
        // Actualizar botones
        actualizarBotones();

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

        // Remover carta del maso del jugador
        jugadores.get(indiceJugadorActual).getMasoJugador().remove(cartaLanzada);
        panelMano.quitarCarta(cartaLanzada);

        // Ejecutar la jugada de llevar cartas
        boolean jugadaValida = rulerManager.rulerManagermetodo(cartaLanzada, cartasSeleccionadas, mesa, equipoActual, ultimaCartaLanzada);

        if (!jugadaValida) {
            // Si la jugada no es válida, devolver la carta al maso
            jugadores.get(indiceJugadorActual).getMasoJugador().add(cartaLanzada);
            panelMano.actualizarMano(jugadores.get(indiceJugadorActual).getMasoJugador());
            panelMano.refrescar();
            JOptionPane.showMessageDialog(this, "Jugada inválida. No puedes llevarte esas cartas.");
            actualizarBotones();
            return;
        }

        // Actualizar última carta lanzada
        ultimaCartaLanzada = cartaLanzada;

        // Refrescar después de la jugada
        panelMesa.refrescarMesa();
        refrescarPerros();

        // Actualizar botones
        actualizarBotones();

        // Verificar si se acabaron las cartas
        verificarFinRonda();
    }

    private void verificarFinRonda() {
        if (jugadores.get(indiceJugadorActual).getMasoJugador().isEmpty()) {
            boolean todosVacios = true;
            for (Jugador j : jugadores) {
                if (!j.getMasoJugador().isEmpty()) {
                    todosVacios = false;
                    break;
                }
            }

            if (todosVacios) {
                rondasJugadas++;
                int rondasPorMano = (numJugadores == 2) ? 4 : 2;
                
                if (rondasJugadas < rondasPorMano) {
                    repartirNuevaRonda();
                } else {
                    finalizarMano();
                }
                return;
            }
        }

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
        rulerManager.calcularCarton(equipo1);
        rulerManager.calcularCarton(equipo2);
        refrescarPerros();

        if (equipo1.getPerros() >= 40) {
            JOptionPane.showMessageDialog(this, "¡Equipo 1 gana con " + equipo1.getPerros() + " perros!");
            System.exit(0);
        } else if (equipo2.getPerros() >= 40) {
            JOptionPane.showMessageDialog(this, "¡Equipo 2 gana con " + equipo2.getPerros() + " perros!");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Mano terminada.\nEquipo 1: " + equipo1.getPerros() + " perros\nEquipo 2: " + equipo2.getPerros() + " perros");
            
            rondasJugadas = 0;
            baraja = new Baraja();
            repartirNuevaRonda();
        }
    }

    private void mostrarCambioTurno() {
        indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();

        panelCambioTurno.setJugador(jugadores.get(indiceJugadorActual).getNombreJugador());
        
        CardLayout cl = (CardLayout) panelJuego.getLayout();
        cl.show(panelJuego, "CAMBIO");
        
        btnLanzar.setVisible(false);
        btnLlevar.setVisible(false);
    }

    private void continuarTurno() {
        CardLayout cl = (CardLayout) panelJuego.getLayout();
        cl.show(panelJuego, "JUEGO");

        lblEquipo.setText(jugadores.get(indiceJugadorActual).getNombreJugador());
        
        panelMano.actualizarMano(jugadores.get(indiceJugadorActual).getMasoJugador());
        panelMano.refrescar();
        panelMesa.refrescarMesa();
        
        actualizarBotones();
    }

    private void actualizarBotones() {
        // Siempre mostrar el botón lanzar
        btnLanzar.setEnabled(true);
        btnLanzar.setVisible(true);
        
        // Verificar si se puede llevar cartas
        Carta cartaSeleccionadaMano = panelMano.getCartaSeleccionada();
        ArrayList<Carta> cartasSeleccionadasMesa = panelMesa.getCartasSeleccionadas();
        
        // Solo mostrar "Llevar cartas" si:
        // 1. Hay cartas en la mesa
        // 2. Hay una carta seleccionada de la mano
        // 3. Hay al menos una carta seleccionada de la mesa
        // 4. La jugada es válida según el RulerManager
        
        if (mesa.size() > 0 && cartaSeleccionadaMano != null && !cartasSeleccionadasMesa.isEmpty()) {
            Equipo equipoActual = obtenerEquipoActual();
            
            // Crear una copia temporal de la mesa para validar sin modificar
            ArrayList<Carta> mesaTemp = new ArrayList<>(mesa);
            
            // Verificar si la jugada sería válida
            boolean esJugadaValida = rulerManager.rulerManagermetodo(
                cartaSeleccionadaMano, 
                cartasSeleccionadasMesa, 
                mesaTemp, 
                equipoActual, 
                ultimaCartaLanzada
            );
            
            if (esJugadaValida) {
                btnLlevar.setEnabled(true);
                btnLlevar.setVisible(true);
            } else {
                btnLlevar.setEnabled(false);
                btnLlevar.setVisible(false);
            }
        } else {
            btnLlevar.setEnabled(false);
            btnLlevar.setVisible(false);
        }
    }

    private Equipo obtenerEquipoActual() {
        if (numJugadores == 2) {
            return (indiceJugadorActual == 0) ? equipo1 : equipo2;
        } else {
            return (indiceJugadorActual < 2) ? equipo1 : equipo2;
        }
    }

    public void refrescarPerros() {
    String nombreEquipo1 = equipo1.getJugadores().get(0).getNombreJugador();
    String nombreEquipo2 = equipo2.getJugadores().get(0).getNombreJugador();
    
    if (numJugadores == 4) {
        nombreEquipo1 += " & " + equipo1.getJugadores().get(1).getNombreJugador();
        nombreEquipo2 += " & " + equipo2.getJugadores().get(1).getNombreJugador();
    }
    
    lblPerros.setText("Perros " + nombreEquipo1 + ": " + equipo1.getPerros() + 
                      " | " + nombreEquipo2 + ": " + equipo2.getPerros());
}
}