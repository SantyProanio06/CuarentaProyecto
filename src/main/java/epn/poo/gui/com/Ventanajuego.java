package epn.poo.gui.com;

import epn.poo.controller.com.Carta;
import epn.poo.controller.com.RulerManager;
import epn.poo.controller.com.Equipo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

public class Ventanajuego extends JFrame {

    private ArrayList<Carta> mesa;
    private ArrayList<Carta> manoJugador;

    private PanelMesa panelMesa;
    private PanelMano panelMano;

    private JButton btnLanzar;
    private JButton btnLlevar;

    private JLabel lblEquipo;
    private JLabel lblPerros;

    private RulerManager rulerManager;
    private Equipo equipoActual;

    public Ventanajuego(ArrayList<Carta> mesa, ArrayList<Carta> manoJugador, Equipo equipoActual) {

        this.mesa = mesa;
        this.manoJugador = manoJugador;
        this.equipoActual = equipoActual;
        this.rulerManager = new RulerManager();

        configurarVentana();
        inicializarComponentes();
        agregarComponentes();
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

        btnLanzar = new JButton("Lanzar carta");
        btnLlevar = new JButton("Llevar cartas");

        lblEquipo = new JLabel("Equipo actual");
        lblPerros = new JLabel("Perros: " + equipoActual.getPerros());
    }

    private void agregarComponentes() {
        // Panel superior (info)
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInfo.setBorder(BorderFactory.createEtchedBorder());
        panelInfo.add(lblEquipo);
        panelInfo.add(lblPerros);

        // Panel acciones
        JPanel panelAcciones = new JPanel(new FlowLayout());
        panelAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
        panelAcciones.add(btnLanzar);
        panelAcciones.add(btnLlevar);

        add(panelInfo, BorderLayout.NORTH);
        add(panelMesa, BorderLayout.CENTER);
        add(panelMano, BorderLayout.SOUTH);
        add(panelAcciones, BorderLayout.EAST);
    }

    // Llamar este método cuando aumenten perros
    public void refrescarPerros() {
        lblPerros.setText("Perros: " + equipoActual.getPerros());
    }
}
