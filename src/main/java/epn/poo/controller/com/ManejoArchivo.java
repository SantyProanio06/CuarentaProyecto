/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package epn.poo.controller.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author santi
 */
public class ManejoArchivo {
    public ManejoArchivo (){}
    
    public void escribirArchivo(String path, String nuevo) throws IOException{
     try (BufferedWriter bw = new BufferedWriter(new FileWriter(path,true))){
         bw.write(nuevo);
         bw.newLine();
     }
   }
    
    public ArrayList<String> obtenerTodos(String path) throws IOException{
       ArrayList<String> archivos = new ArrayList<>();
       try(BufferedReader br = new BufferedReader(new FileReader(path))){
           String linea;
           while((linea = br.readLine()) != null){
               archivos.add(linea);
           }
       }
       return archivos;
    }
}
