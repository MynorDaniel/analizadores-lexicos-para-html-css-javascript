/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analizadoreslexicos.backend;

import java.awt.Desktop;
import java.io.*;

/**
 *
 * @author mynordma
 */
public class GeneradorHTML {
    
    public void generarHTML(String contenidoHtml, String nombreArchivo) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(nombreArchivo));
            writer.write(contenidoHtml);
            System.out.println("Archivo HTML creado exitosamente: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el escritor: " + e.getMessage());
            }
        }
    }
    
    public void abrirHTML(String rutaArchivo) {
        try {
            File archivoHtml = new File(rutaArchivo);
            if (archivoHtml.exists()) {
                Desktop.getDesktop().browse(archivoHtml.toURI());
                System.out.println("Abriendo archivo HTML en el navegador: " + rutaArchivo);
            } else {
                System.err.println("El archivo no existe: " + rutaArchivo);
            }
        } catch (IOException e) {
            System.err.println("Error al intentar abrir el archivo: " + e.getMessage());
        }
    }
}
