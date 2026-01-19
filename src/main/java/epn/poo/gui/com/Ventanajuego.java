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
    private JPanel panelJuego; // Panel contenedor

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
        
        // Panel contenedor que usará CardLayout para alternar vistas
        panelJuego = new JPanel(new CardLayout());

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

        // Panel de juego principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelMesa, BorderLayout.CENTER);
        panelPrincipal.add(panelMano, BorderLayout.SOUTH);

        // Agregar ambos paneles al CardLayout
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

        System.out.println("Lanzando carta: " + cartaSeleccionada.getNumero());
        System.out.println("Cartas en mesa antes: " + mesa.size());

        // Remover carta del maso del jugador
        jugadores.get(indiceJugadorActual).getMasoJugador().remove(cartaSeleccionada);
        
        // Agregar a la mesa
        mesa.add(cartaSeleccionada);
        
        System.out.println("Cartas en mesa después: " + mesa.size());
        
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

        System.out.println("=== LLEVAR CARTAS ===");
        System.out.println("Carta lanzada: " + cartaLanzada.getNumero());
        System.out.println("Cartas seleccionadas: " + cartasSeleccionadas.size());
        System.out.println("Cartas en mesa antes: " + mesa.size());

        // Remover carta del maso del jugador
        jugadores.get(indiceJugadorActual).getMasoJugador().remove(cartaLanzada);
        panelMano.quitarCarta(cartaLanzada);

        // NO agregamos la carta a la mesa aquí porque rulerManager lo manejará
        // El rulerManager agregará la carta al cartón junto con las seleccionadas
        
        // Ejecutar la jugada de llevar cartas
        rulerManager.rulerManagermetodo(cartaLanzada, cartasSeleccionadas, mesa, equipoActual, ultimaCartaLanzada);

        System.out.println("Cartas en mesa después: " + mesa.size());
        System.out.println("=== FIN LLEVAR ===");

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
        calcularCarton(equipo1);
        calcularCarton(equipo2);
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

    private void calcularCarton(Equipo equipo) {
        int totalCarton = equipo.getCarton().size();
        
        if (totalCarton > 19) {
            int exceso = totalCarton - 20;
            int perrosGanados = exceso + 6;
            
            if (perrosGanados % 2 != 0) {
                perrosGanados--;
            }
            
            equipo.setPerros(equipo.getPerros() + perrosGanados);
        }
    }

    private void mostrarCambioTurno() {
        indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();

        panelCambioTurno.setJugador(jugadores.get(indiceJugadorActual).getNombreJugador());
        
        // Cambiar a la vista de cambio de turno
        CardLayout cl = (CardLayout) panelJuego.getLayout();
        cl.show(panelJuego, "CAMBIO");
        
        btnLanzar.setVisible(false);
        btnLlevar.setVisible(false);
    }

    private void continuarTurno() {
        // Cambiar a la vista de juego
        CardLayout cl = (CardLayout) panelJuego.getLayout();
        cl.show(panelJuego, "JUEGO");

        lblEquipo.setText(jugadores.get(indiceJugadorActual).getNombreJugador());
        
        panelMano.actualizarMano(jugadores.get(indiceJugadorActual).getMasoJugador());
        panelMano.refrescar();
        panelMesa.refrescarMesa();
        
        actualizarBotones();
    }

    private void actualizarBotones() {
        btnLanzar.setEnabled(true);
        btnLanzar.setVisible(true);
        
        if (mesa.size() > 0) {
            btnLlevar.setEnabled(true);
            btnLlevar.setVisible(true);
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
        lblPerros.setText("Perros Equipo 1: " + equipo1.getPerros() + " | Equipo 2: " + equipo2.getPerros());
    }
}
