package epn.poo.main.com;

import javax.swing.SwingUtilities;

/**
 * Clase main que inicia el programa
 * @author santi
 * @author sebas
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {//Este merodo basicamante incializa el programa garantizando una interfaz estable
            
            //Esto de aqui es el lamba o una forma de escribir un runable, es decir algo ejecutable
            FrameInicio frame = new FrameInicio();//Se inicializa el frame principal
            frame.setVisible(true);//Se lo pone visible
        });
    }
}
