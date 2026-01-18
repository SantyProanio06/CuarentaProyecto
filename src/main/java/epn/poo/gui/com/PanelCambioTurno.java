package epn.poo.gui.com;

import javax.swing.*;
import java.awt.*;

public class PanelCambioTurno extends JPanel {

    private JLabel lblJugador;
    private JButton btnContinuar;

    public PanelCambioTurno() {

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        lblJugador = new JLabel("", SwingConstants.CENTER);
        lblJugador.setForeground(Color.WHITE);
        lblJugador.setFont(new Font("Arial", Font.BOLD, 28));

        btnContinuar = new JButton("Continuar");

        add(lblJugador, BorderLayout.CENTER);
        add(btnContinuar, BorderLayout.SOUTH);

        setVisible(false);
    }

    public void setJugador(String nombre) {
        lblJugador.setText("Turno de: " + nombre);
    }

    public JButton getBtnContinuar() {
        return btnContinuar;
    }
}

