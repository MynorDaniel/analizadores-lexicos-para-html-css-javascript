/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analizadoreslexicos.backend.automatas;

import com.mycompany.analizadoreslexicos.backend.Token;
import java.util.ArrayList;

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
                errores.add(new Token("ERROR", buffer.toString()));
                buffer.setLength(0);
            }else if(c == '<' && buffer.length() > 0){
                tokens.add(new Token("TEXTO", buffer.toString()));
                buffer.setLength(0);
                estado = "q0";
            }
            
            buffer.append(c);
            
            switch(estado){
                case "q0" -> {
                    if(buffer.toString().endsWith("<")){
                        estado = "q1";
                        tokens.add(new Token("APERTURA", "<"));
                        buffer.setLength(0);
                    }
                }
                case "q1" -> {
                    if(esNombreEtiqueta(buffer.toString().trim())){
                        estado = "q2";
                        tokens.add(new Token("NOMBRE_ETIQUETA", buffer.toString().trim()));
                        buffer.setLength(0);
                    }
                }
                case "q2" -> {
                    if(buffer.toString().endsWith(">")){
                        estado = "q3";
                        tokens.add(new Token("CIERRE", ">"));
                        buffer.setLength(0);
                    }else if(buffer.toString().endsWith("/")){
                        estado = "q4";
                        tokens.add(new Token("BARRA", "/"));
                        buffer.setLength(0);
                    }else if(esPalabraReservada(buffer.toString().trim())){
                        estado = "q6";
                        tokens.add(new Token("PALABRA_RESERVADA", buffer.toString().trim()));
                        buffer.setLength(0);
                    }
                }
                case "q4" -> {
                    if(buffer.toString().endsWith(">")){
                        estado = "q3";
                        tokens.add(new Token("CIERRE", ">"));
                        buffer.setLength(0);
                    }
                    
                }
                case "q6" -> {
                    if(buffer.toString().endsWith("=")){
                        estado = "q7";
                        tokens.add(new Token("IGUAL", "="));
                        buffer.setLength(0);
                    }
                }
                case "q7" -> {
                    if(esCadena(buffer.toString().trim())){
                        estado = "q2";
                        tokens.add(new Token("CADENA", buffer.toString().trim()));
                        buffer.setLength(0);
                    }
                    
                }
            }
            
            posicion++;
        }
         
        return tokens;
    }

    
    
    
}
