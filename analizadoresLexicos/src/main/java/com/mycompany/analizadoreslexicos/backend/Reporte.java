/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analizadoreslexicos.backend;

import java.awt.Desktop;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class Reporte {

    
     public void generarReporte(ArrayList<Token> tokensHTML, ArrayList<Token> tokensCSS, ArrayList<Token> tokensJS) {
        StringBuilder html = new StringBuilder();

        // Encabezado HTML
        html.append("<!DOCTYPE html>");
        html.append("<html lang='es'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>Reporte de Tokens</title>");
        html.append("<style>");
        html.append("table { border-collapse: collapse; width: 100%; }");
        html.append("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
        html.append("th { background-color: #f2f2f2; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        // Título
        html.append("<h1>Reporte de Tokens</h1>");

        // Tabla
        html.append("<table>");
        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>Valor</th>");
        html.append("<th>Tipo</th>");
        html.append("<th>Lenguaje</th>");
        html.append("</tr>");
        html.append("</thead>");
        html.append("<tbody>");

        // Agregar tokens de HTML
        agregarTokensATabla(html, tokensHTML);
        // Agregar tokens de CSS
        agregarTokensATabla(html, tokensCSS);
        // Agregar tokens de JS
        agregarTokensATabla(html, tokensJS);

        html.append("</tbody>");
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");

        // Escribir el HTML en un archivo
        escribirArchivo("reporte_tokens.html", html.toString());
        
        abrirHTML("reporte_tokens.html");
    }

    private void agregarTokensATabla(StringBuilder html, ArrayList<Token> tokens) {
        for (Token token : tokens) {
            html.append("<tr>");
            html.append("<td>").append(token.getValor()).append("</td>");
            html.append("<td>").append(token.getTipo()).append("</td>");
            html.append("<td>").append(token.getLenguaje()).append("</td>");
            html.append("</tr>");
        }
    }

    private void escribirArchivo(String nombreArchivo, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(contenido);
            System.out.println("Archivo " + nombreArchivo + " creado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
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
