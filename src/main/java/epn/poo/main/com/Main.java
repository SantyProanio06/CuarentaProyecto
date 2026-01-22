package epn.poo.main.com;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrameInicio frame = new FrameInicio();
            frame.setVisible(true);
        });
    }
}
