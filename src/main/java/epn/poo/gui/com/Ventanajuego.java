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

/**
 * Representa un frame swing que a su vez representa todo el juego del cuarenta, desde mostrar
 * los masos hasta manejar un poco la logica del juego.
 * @author santi
 * @author sebas
 */
public class Ventanajuego extends JFrame {
    
    //Parametros del juego--------------------------------
    private ArrayList<Carta> mesa;//Cartas de la mesa
    private ArrayList<Jugador> jugadores; //Jugadores
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
    
    /**
     * Constructor con parametros.
     * LLama a todos los metodos para mostra la ventana de juego
     * @param mesa Cartas de la mesa.
     * @param jugadores Jugadores del juego(valga la redundancia).
     * @param equipo1 Equipo de jugadores uno.
     * @param equipo2 Equipo de jugadores dos.
     * @param numJugadores Numero de jugadores.
     */
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
    
    /**
     * Configura todo lo referente a la ventana del juego.
     */
    private void configurarVentana() {
        setTitle("Cuarenta");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Al cerrar la ventana cierra el programa compleatemente
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }
    
    /**
     * Inicializa todos los componentes, igual al metodo de netbeans, solo que
     * con ciertas personalizaciones.
     */
    private void inicializarComponentes() {
        panelMesa = new PanelMesa(mesa);
        panelMano = new PanelMano(jugadores.get(indiceJugadorActual).getMasoJugador());
        panelCambioTurno = new PanelCambioTurno();
        
        panelJuego = new JPanel(new CardLayout());

        btnLanzar = new JButton("Lanzar carta");
        btnLlevar = new JButton("Llevar cartas");

        lblEquipo = new JLabel(jugadores.get(indiceJugadorActual).getNombreJugador());
        lblPerros = new JLabel("Perros Equipo 1: " + equipo1.getPerros() + " | Equipo 2: " + equipo2.getPerros());
        
        // Agrega un escuchador para detectar cuando se seleccionan cartas en la mesa
        panelMesa.addSelectionListener(() -> actualizarBotones());
        panelMano.addSelectionListener(() -> actualizarBotones());
    }
    
    /**
     * Construye y organiza la interfaz grafica del juego
     */
    private void agregarComponentes() {
        //Panel que muestra info del juego
        JPanel panelInfo = new JPanel(new FlowLayout());//Componentes pegados uno al lado del otro
        panelInfo.setBorder(BorderFactory.createEtchedBorder());//Coloca un borde para indicar que es informativo
        panelInfo.add(lblEquipo);//Label con los equipos
        panelInfo.add(lblPerros);//Label con los perros
        
        //Panel donde se muestran las acciones
        JPanel panelAcciones = new JPanel(new GridLayout(2, 1, 5, 5));//Creado con 2 filas 1 columna espaciadas por 5 px.
        panelAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
        panelAcciones.add(btnLanzar);
        panelAcciones.add(btnLlevar);

        JPanel panelPrincipal = new JPanel(new BorderLayout());//Divide el panel en regiones
        panelPrincipal.add(panelMesa, BorderLayout.CENTER);//Se coloca al panel de la mesa en el centro
        panelPrincipal.add(panelMano, BorderLayout.SOUTH);//Y al panel con la mano del jugador abajo
        
        /*
         Basicamente es un contenedor que permite mostrar solo uno de los paneles a la 
        vez
         */
        panelJuego.add(panelPrincipal, "JUEGO");//Panel donde se juega
        panelJuego.add(panelCambioTurno, "CAMBIO");//Panel donde se cambia de turno

        add(panelInfo, BorderLayout.NORTH);//Se pone la info arriba
        add(panelJuego, BorderLayout.CENTER);//Al juego en el centro
        add(panelAcciones, BorderLayout.EAST);//Y las acciones a la derecha
    }
    
    /**
     * Coloca los metodos correspondientes a cada boton.
     */
    private void configurarEventos() {
        btnLanzar.addActionListener(e -> lanzarCarta());
        btnLlevar.addActionListener(e -> llevarCartas());
        panelCambioTurno.getBtnContinuar().addActionListener(e -> continuarTurno());
    }
    
    /**
     * Permite quitar una carta de la mano y agregarla a la mesa
     */
    private void lanzarCarta() {
        //Dehabilita los botones para evitar doble clic
        btnLanzar.setEnabled(false);
        btnLlevar.setEnabled(false);
        
        Carta cartaSeleccionada = panelMano.getCartaSeleccionada();

        if (cartaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una carta para lanzar");
            actualizarBotones();
            return;
        }

        //Quita la carta del maso del jugador
        jugadores.get(indiceJugadorActual).getMasoJugador().remove(cartaSeleccionada);
        
        //Y la añade a la mesa
        mesa.add(cartaSeleccionada);
        
        //Cambia la ultima carta lanzada
        ultimaCartaLanzada = cartaSeleccionada;
        
        //Actualiza la mano de dicho jugador y de la mesa
        panelMano.quitarCarta(cartaSeleccionada);
        panelMesa.refrescarMesa();
        
        actualizarBotones();

        // Verificar si se acabaron las cartas del jugador
        verificarFinRonda();
    }
    
    /**
     * Permite agarrar las cartas seleccionadas siempre y cuando sea valido.
     */
    private void llevarCartas() {
        // Deshabilita los botones
        btnLlevar.setEnabled(false);
        btnLanzar.setEnabled(false);
        
        Carta cartaLanzada = panelMano.getCartaSeleccionada();
        ArrayList<Carta> cartasSeleccionadas = panelMesa.getCartasSeleccionadas();

        if (cartaLanzada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una carta de tu mano para lanzar");
            actualizarBotones();
            return;
        }

        if (cartasSeleccionadas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos una carta de la mesa");
            actualizarBotones();
            return;
        }

        Equipo equipoActual = obtenerEquipoActual();

        //Crea una copia para evitar referencias erroneas
        ArrayList<Carta> cartasSeleccionadasCopia = new ArrayList<>(cartasSeleccionadas);

        //Quita la carta del maso del jugador
        jugadores.get(indiceJugadorActual).getMasoJugador().remove(cartaLanzada);
        panelMano.quitarCarta(cartaLanzada);

        //Ejecuta la clase encargada de validar la jugada con la copia
        boolean jugadaValida = rulerManager.rulerManagermetodo(cartaLanzada, cartasSeleccionadasCopia, mesa, equipoActual, ultimaCartaLanzada);

        if (!jugadaValida) {
            // Si la jugada no es válida se retornan las cartas al maso
            jugadores.get(indiceJugadorActual).getMasoJugador().add(cartaLanzada);
            panelMano.actualizarMano(jugadores.get(indiceJugadorActual).getMasoJugador());
            panelMano.refrescar();
            JOptionPane.showMessageDialog(this, "Jugada inválida. No puedes llevarte esas cartas.");
            actualizarBotones();
            return;
        }

        //Actualiza ultima carta lanzada
        ultimaCartaLanzada = null;

        //Actualiza la mesa y los perros
        panelMesa.refrescarMesa();
        refrescarPerros();

        //Actualzia los botones
        actualizarBotones();

        //Verificaa si se acabaron las cartas
        verificarFinRonda();
    }
    
    /**
     * Metodo que verifica si se acabaron las cartas para poder terminar la ronda
     */
    private void verificarFinRonda() {
        
        //Veridica que todos los jugadores no tengan cartas
        if (jugadores.get(indiceJugadorActual).getMasoJugador().isEmpty()) {
            boolean todosVacios = true;
            for (Jugador j : jugadores) {
                if (!j.getMasoJugador().isEmpty()) {
                    todosVacios = false;
                    break;
                }
            }
            
            //En ese caso:
            if (todosVacios) {
                rondasJugadas++;
                int rondasPorMano = (numJugadores == 2) ? 4 : 2;
                //Se verifica que no se hayan acabado las rondas
                if (rondasJugadas < rondasPorMano) {
                    //Si no se reparte
                    repartirNuevaRonda();
                } else {
                    //Si si se finaliza la mano
                    finalizarMano();
                }
                return;
            }
        }
        
        //Se muestra el panel del siguiente turno
        mostrarCambioTurno();
    }
    
    /**
     * Reparte las cartas a los jugadores
     */
    private void repartirNuevaRonda() {
        JOptionPane.showMessageDialog(this, "Ronda terminada. Repartiendo nuevas cartas...");
        
        //Randomiza la baraja y la reparte en 5, añadiendo esas 5 a la mano del jugador
        for (Jugador j : jugadores) {
            ArrayList<Carta> nuevaMano = controladorCartas.repartirCartas(baraja.getBaraja());
            j.setMasoJugador(nuevaMano);
        }
        
        //Actualiza el maso del jugador
        panelMano.actualizarMano(jugadores.get(indiceJugadorActual).getMasoJugador());
        panelMano.refrescar();
        actualizarBotones();
    }
    
    /**
     * Acaba la mano del jugador actual
     */
    private void finalizarMano() {
        
        //Calcula los perros ganados en funcion del carton
        rulerManager.calcularCarton(equipo1);
        rulerManager.calcularCarton(equipo2);
        refrescarPerros();
        
        //Termina el juego si logran obtener 40
        if (equipo1.getPerros() >= 40) {
            JOptionPane.showMessageDialog(this, "¡Equipo 1 gana con " + equipo1.getPerros() + " perros!");
            System.exit(0);
        } else if (equipo2.getPerros() >= 40) {
            JOptionPane.showMessageDialog(this, "¡Equipo 2 gana con " + equipo2.getPerros() + " perros!");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Mano terminada.\nEquipo 1: " + equipo1.getPerros() + " perros\nEquipo 2: " + equipo2.getPerros() + " perros");
            
            //Reinicia algunos parametros
            rondasJugadas = 0;
            baraja = new Baraja();
            repartirNuevaRonda();
            System.out.println("reiniciando mesa");
            mesa.clear();
            panelMesa.refrescarMesa();
            System.out.println("mesa vacia");
        }
    }
    
    /**
     * Muestra el panel de cambio de turno
     */
    private void mostrarCambioTurno() {
        
        //Cambia de jugador al siguiente
        indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
        
        //Coloca el nuevo jugador al panel
        panelCambioTurno.setJugador(jugadores.get(indiceJugadorActual).getNombreJugador());
        
        //Cambia el unico panel visible al del cambio de juego
        CardLayout cl = (CardLayout) panelJuego.getLayout();
        cl.show(panelJuego, "CAMBIO");
            
        btnLanzar.setVisible(false);
        btnLlevar.setVisible(false);
    }
    
    /**
     * Continua el turno al siguiente jugador
     */
    private void continuarTurno() {
        
        //Cambia el unico panel visible al del juego
        CardLayout cl = (CardLayout) panelJuego.getLayout();
        cl.show(panelJuego, "JUEGO");
        
        //Cambia el nombre del equipo al del jugador acutal
        lblEquipo.setText(jugadores.get(indiceJugadorActual).getNombreJugador());
        
        //Actualiza todo lo visual
        panelMano.actualizarMano(jugadores.get(indiceJugadorActual).getMasoJugador());
        panelMano.refrescar();
        panelMesa.refrescarMesa();
        
        actualizarBotones();
    }
    
    /**
     * Actualiza los botones del juego
     */
   private void actualizarBotones() {
        //Muestra y habilita el boton de lanzar
        btnLanzar.setEnabled(true);
        btnLanzar.setVisible(true);
        
        //Muestra el boton de llevat carta solo si hay por lo menos una carta en la mesa
        if (mesa.size() > 0) {
            btnLlevar.setEnabled(true);
            btnLlevar.setVisible(true);
        } else {
            btnLlevar.setEnabled(false);
            btnLlevar.setVisible(false);
        }
    }
   
   /**
    * Consigue el equipo actual
    * @return El equipo del jugador actual
    */
    private Equipo obtenerEquipoActual() {
        if (numJugadores == 2) {//Si son dos jugadores
            return (indiceJugadorActual == 0) ? equipo1 : equipo2;//Si es el jugador 0 e 1 sino e 2
        } else {//Si son 4 jugadores
            return (indiceJugadorActual < 2) ? equipo1 : equipo2;///****CAmbiaaar AAAAA*******
        }
    }
    
    /**
     * Actualiza los puntos de cada equipo
     */
    public void refrescarPerros() {
        
        //Coloca los nombres de los primeros jugadores
    String nombreEquipo1 = equipo1.getJugadores().get(0).getNombreJugador();
    String nombreEquipo2 = equipo2.getJugadores().get(0).getNombreJugador();
        //Si son 4 juagores
    if (numJugadores == 4) {
        
        //Agrega al nombre del equipo el compañero
        nombreEquipo1 += " & " + equipo1.getJugadores().get(1).getNombreJugador();
        nombreEquipo2 += " & " + equipo2.getJugadores().get(1).getNombreJugador();
    }
    
    //Se muestran los perros
    lblPerros.setText("Perros " + nombreEquipo1 + ": " + equipo1.getPerros() + 
                      " | " + nombreEquipo2 + ": " + equipo2.getPerros());
}
}