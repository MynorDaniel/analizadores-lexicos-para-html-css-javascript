/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analizadoreslexicos;

import com.mycompany.analizadoreslexicos.backend.Token;
import com.mycompany.analizadoreslexicos.backend.automatas.*;
import com.mycompany.analizadoreslexicos.backend.enums.EstadoEnum;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class Lexer {
    
    private final String input;
    private int posicionActual;
    private EstadoEnum lenguajeActual;
    private ArrayList<Token> tokens;

    public Lexer(String input) {
        this.input = input;
        this.posicionActual = 0;
        this.lenguajeActual = null;
        this.tokens = new ArrayList<>();
    }
    
    public void generarTokens(){
        while (posicionActual < input.length()) {
            if (esTokenDeEstado()) {
                cambiarLenguaje();
            } else {
                // Si ya hay un lexer activo, tokeniza con el lexer actual
                if (lenguajeActual != null) {
                    tokens.addAll(usarAutomataActual());
                } else {
                    posicionActual++;
                }
            }
        }
    }
    
    private boolean esTokenDeEstado() {
        return input.startsWith(">>[html]", posicionActual) ||
               input.startsWith(">>[css]", posicionActual) ||
               input.startsWith(">>[js]", posicionActual);
    }
     
    private void cambiarLenguaje() {
        if (input.startsWith(">>[html]", posicionActual)) {
            lenguajeActual = EstadoEnum.HTML;
            posicionActual += ">>[html]".length();
        } else if (input.startsWith(">>[css]", posicionActual)) {
            lenguajeActual = EstadoEnum.CSS;
            posicionActual += ">>[css]".length();
        } else if (input.startsWith(">>[js]", posicionActual)) {
            lenguajeActual = EstadoEnum.JS;
            posicionActual += ">>[js]".length();
        }
    }
    
    private ArrayList<Token> usarAutomataActual() {
        String inputSobrante = input.substring(posicionActual);
        ArrayList<Token> tokensGenerados = new ArrayList<>();
        int posicionAvanzada = posicionActual;

        switch (lenguajeActual) {
            case HTML -> {
                AutomataHTML htmlLexer = new AutomataHTML(inputSobrante);
                ResultadoAutomata htmlResultado = htmlLexer.generarTokens();
                tokensGenerados = htmlResultado.getTokens();
                posicionAvanzada += htmlResultado.getNuevaPosicion();
            }

            case CSS -> {
                AutomataCSS cssLexer = new AutomataCSS(inputSobrante);
                ResultadoAutomata cssResultado = cssLexer.generarTokens();
                tokensGenerados = cssResultado.getTokens();
                posicionAvanzada += cssResultado.getNuevaPosicion();
            }

            case JS -> {
                AutomataJS jsLexer = new AutomataJS(inputSobrante);
                ResultadoAutomata jsResultado = jsLexer.generarTokens();
                tokensGenerados = jsResultado.getTokens();
                posicionAvanzada += jsResultado.getNuevaPosicion();
            }
        }
        
        posicionActual = posicionAvanzada;
        
        return tokensGenerados;
    }
}
