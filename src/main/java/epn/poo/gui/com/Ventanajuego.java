package epn.poo.gui.com;

import epn.poo.controller.com.Carta;
import epn.poo.controller.com.RulerManager;
import epn.poo.controller.com.Equipo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ventanajuego extends JFrame {

    private ArrayList<Carta> mesa;
    private ArrayList<Carta> manoJugador;

    private PanelMesa panelMesa;
    private PanelMano panelMano;
    private PanelCambioTurno panelCambioTurno;

    private JButton btnLanzar;
    private JButton btnLlevar;

    private JLabel lblEquipo;
    private JLabel lblPerros;

    private RulerManager rulerManager;
    private Equipo equipoActual;

    private int turno = 1;

    public Ventanajuego(ArrayList<Carta> mesa, ArrayList<Carta> manoJugador, Equipo equipoActual) {

        this.mesa = mesa;
        this.manoJugador = manoJugador;
        this.equipoActual = equipoActual;
        this.rulerManager = new RulerManager();

        configurarVentana();
        inicializarComponentes();
        agregarComponentes();
        configurarEventos();
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
        panelMano = new PanelMano(manoJugador);
        panelCambioTurno = new PanelCambioTurno();
        panelCambioTurno.setVisible(false);

        btnLanzar = new JButton("Lanzar carta");
        btnLlevar = new JButton("Llevar cartas");

        lblEquipo = new JLabel("Jugador " + turno);
        lblPerros = new JLabel("Perros: " + equipoActual.getPerros());
    }

    private void agregarComponentes() {

        JPanel panelInfo = new JPanel(new FlowLayout());
        panelInfo.setBorder(BorderFactory.createEtchedBorder());
        panelInfo.add(lblEquipo);
        panelInfo.add(lblPerros);

        JPanel panelAcciones = new JPanel(new FlowLayout());
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

        panelCambioTurno.getBtnContinuar().addActionListener(e -> continuarTurno());
    }

    private void lanzarCarta() {

        Carta carta = panelMano.getCartaSeleccionada();

        if (carta == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una carta para lanzar");
            return;
        }

        panelMano.quitarCarta(carta);
        panelMesa.agregarCarta(carta);

        mostrarCambioTurno();
    }

    private void mostrarCambioTurno() {

        turno = (turno == 1) ? 2 : 1;

        panelCambioTurno.setJugador("Jugador " + turno);
        panelCambioTurno.setVisible(true);

        panelMesa.setVisible(false);
        panelMano.setVisible(false);
    }

    private void continuarTurno() {

        panelCambioTurno.setVisible(false);

        panelMesa.setVisible(true);
        panelMano.setVisible(true);

        lblEquipo.setText("Jugador " + turno);
        panelMano.refrescar();
    }

    public void refrescarPerros() {
        lblPerros.setText("Perros: " + equipoActual.getPerros());
    }
}
