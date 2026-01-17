/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.controller.com;

/**
 *
 * @author HP
 */
public class Carta {
   private int numero;
   private String imagePath;

    public Carta(int numero, String imagePath) {
        this.numero = numero;
        this.imagePath = imagePath;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
   
   
}
