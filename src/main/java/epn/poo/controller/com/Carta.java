/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.controller.com;

/**
 * Clase que representa una carta en el programa, cuenta con metodo para obtener y colocar el numero y la ruta 
 * de imagen.
 * @author santi
 * @author sebas
 */
public class Carta {
   private int numero;
   private String imagePath;
   
   /**
    * Constructor con parametros.
    * @param numero Numero que representa la carta.
    * @param imagePath Ruta de la imagen que represetna la carta.
    */
    public Carta(int numero, String imagePath) {
        this.numero = numero;
        this.imagePath = imagePath;
    }
    
    /**
     * @return Numero de la carta.
     */
    public int getNumero() {
        return numero;
    }
    
    /**
     * @param numero Numero de la carta.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    /**
     * @return Ruta de la imagen que representa la carta.
     */
    public String getImagePath() {
        return imagePath;
    }
    
    /**
     * @param imagePath Ruta de la imagen que representa la carta.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
   
   
}
