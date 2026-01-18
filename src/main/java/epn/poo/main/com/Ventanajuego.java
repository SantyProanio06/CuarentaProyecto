/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.main.com;

/**
 *
 * @author HP
 */


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import epn.poo.controller.com.Carta;
import epn.poo.controller.com.RulerManager;
import epn.poo.controller.com.Equipo;

public class Ventanajuego extends JFrame {

    private ArrayList<Carta> mesa;
    private ArrayList<Carta> manoJugador;

    private PanelMesa panelMesa;
    private PanelMano panelMano;

    private JButton btnLanzar;
    private JButton btnLlevar;

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

        btnLanzar = new JButton("Lanzar");
        btnLlevar = new JButton("Llevar");
    }

    private void agregarComponentes() {
        JPanel panelAcciones = new JPanel(new FlowLayout());

        panelAcciones.add(btnLanzar);
        panelAcciones.add(btnLlevar);

        add(panelMesa, BorderLayout.CENTER);
        add(panelMano, BorderLayout.SOUTH);
        add(panelAcciones, BorderLayout.EAST);
    }
}

