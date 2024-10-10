/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analizadoreslexicos.backend.automatas;

import com.mycompany.analizadoreslexicos.backend.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mynordma
 */
public class AutomataHTML extends Automata {

    public AutomataHTML(String input) {
        super(input);
        this.posicion = 8;
    }

    @Override
    public ArrayList<Token> generarTokens() {
        
        ArrayList<Token> tokens = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        
        while(posicion < input.length()){
            char c = input.charAt(posicion);
            
            if(c == '<' && !estado.equals("q3")){
                errores.add(new Token("ERROR", buffer.toString(), "ERROR"));
                buffer.setLength(0);
            }else if(c == '<' && buffer.length() > 0){
                tokens.add(new Token("TEXTO", buffer.toString(), buffer.toString()));
                buffer.setLength(0);
                estado = "q0";
            }
            
            buffer.append(c);
            
            switch(estado){
                case "q0" -> {
                    if(buffer.toString().endsWith("<")){
                        estado = "q1";
                        tokens.add(new Token("APERTURA", "<", "<"));
                        buffer.setLength(0);
                    }
                }
                case "q1" -> {
                    if(esNombreEtiqueta(buffer.toString().trim())){
                        estado = "q2";
                        tokens.add(new Token("NOMBRE_ETIQUETA", buffer.toString().trim(), traduccion(buffer.toString().trim())));
                        buffer.setLength(0);
                    }
                }
                case "q2" -> {
                    if(buffer.toString().endsWith(">")){
                        estado = "q3";
                        tokens.add(new Token("CIERRE", ">", ">"));
                        buffer.setLength(0);
                    }else if(buffer.toString().endsWith("/")){
                        estado = "q4";
                        tokens.add(new Token("BARRA", "/", "/"));
                        buffer.setLength(0);
                    }else if(esPalabraReservada(buffer.toString().trim())){
                        estado = "q6";
                        tokens.add(new Token("PALABRA_RESERVADA", buffer.toString().trim(), buffer.toString().trim()));
                        buffer.setLength(0);
                    }
                }
                case "q4" -> {
                    if(buffer.toString().endsWith(">")){
                        estado = "q3";
                        tokens.add(new Token("CIERRE", ">", ">"));
                        buffer.setLength(0);
                    }
                    
                }
                case "q6" -> {
                    if(buffer.toString().endsWith("=")){
                        estado = "q7";
                        tokens.add(new Token("IGUAL", "=", "="));
                        buffer.setLength(0);
                    }
                }
                case "q7" -> {
                    if(esCadena(buffer.toString().trim())){
                        estado = "q2";
                        tokens.add(new Token("CADENA", buffer.toString().trim(), buffer.toString().trim()));
                        buffer.setLength(0);
                    }
                    
                }
            }
            
            posicion++;
        }
         
        return tokens;
    }

    private static final Map<String, String> TRADUCCIONES = new HashMap<>();

    static {
        TRADUCCIONES.put("principal", "main");
        TRADUCCIONES.put("encabezado", "header");
        TRADUCCIONES.put("navegacion", "nav");
        TRADUCCIONES.put("apartado", "aside");
        TRADUCCIONES.put("listaordenada", "ul");
        TRADUCCIONES.put("listadesordenada", "ol");
        TRADUCCIONES.put("itemlista", "li");
        TRADUCCIONES.put("anclaje", "a");
        TRADUCCIONES.put("contenedor", "div");
        TRADUCCIONES.put("seccion", "section");
        TRADUCCIONES.put("articulo", "article");
        TRADUCCIONES.put("titulo1", "h1");
        TRADUCCIONES.put("titulo2", "h2");
        TRADUCCIONES.put("titulo3", "h3");
        TRADUCCIONES.put("titulo4", "h4");
        TRADUCCIONES.put("titulo5", "h5");
        TRADUCCIONES.put("titulo6", "h6");
        TRADUCCIONES.put("parrafo", "p");
        TRADUCCIONES.put("entrada", "input");
        TRADUCCIONES.put("formulario", "form");
        TRADUCCIONES.put("label", "label");
        TRADUCCIONES.put("area", "textarea");
        TRADUCCIONES.put("boton", "button");
        TRADUCCIONES.put("piepagina", "footer");
    }

    public String traduccion(String nombreEtiqueta) {
        return TRADUCCIONES.getOrDefault(nombreEtiqueta, nombreEtiqueta);
    }

    
    
    
}
