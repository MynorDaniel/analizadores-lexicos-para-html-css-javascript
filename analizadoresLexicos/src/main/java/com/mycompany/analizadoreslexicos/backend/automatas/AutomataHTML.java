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
    }

    @Override
    public ResultadoAutomata generarTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        
        while (posicion < input.length()) {
            char c = input.charAt(posicion);
            
            
            switch(estado){
                case "q0" -> {
                    if(cambioDeEstado(buffer.toString())){
                        break;
                    }
                    
                    if(c == ' '){
                        estado = "q0";
                    }else if(c == '<'){
                        tokens.add(new Token("APERTURA", "<", posicion));
                        estado = "q1";
                    }
                    
                    posicion++;
                }
                case "q1" -> {
                    if(cambioDeEstado(buffer.toString())){
                        break;
                    }
                    
                    if(c == ' '){
                        estado = "q1";
                    }else if(esPalabraReservada(buffer.toString())){
                        tokens.add(new Token("PALABRA_RESERVADA", buffer.toString(), posicion));
                        buffer.setLength(0);
                        estado = "q2";
                    }else if((c >= 'a' && c <= 'z') || (c >= '1' && c <= '6')){
                        buffer.append(c);
                    }
                    
                    posicion++;
                }
                case "q2" -> {
                    if(cambioDeEstado(buffer.toString())){
                        break;
                    }
                    
                    if(c == ' '){
                        estado = "q5";
                    }else if(c == '>'){
                        estado = "q3";
                        tokens.add(new Token("CIERRE", ">", posicion));
                    }else if(c == '/'){
                        estado = "q4";
                        tokens.add(new Token("BARRA", "/", posicion));
                    }else{
                        estado = "error";
                    }
                    
                    posicion++;
                }
                case "q3" -> {
                    if(cambioDeEstado(buffer.toString())){
                        break;
                    }
                }
                case "q4" -> {
                    if(cambioDeEstado(buffer.toString())){
                        break;
                    }
                }
                case "q5" -> {
                    if(cambioDeEstado(buffer.toString())){
                        break;
                    }
                }
                case "q6" -> {
                    if(cambioDeEstado(buffer.toString())){
                        break;
                    }
                }
                case "q7" -> {
                    if(cambioDeEstado(buffer.toString())){
                        break;
                    }
                }
                case "q8" -> {
                    if(cambioDeEstado(buffer.toString())){
                        break;
                    }
                }
                case "error" -> {
                    if(cambioDeEstado(buffer.toString())){
                        break;
                    }
                }
            }
        }
        
        return new ResultadoAutomata(tokens, posicion - buffer.length());
    }
    
    
    
}
