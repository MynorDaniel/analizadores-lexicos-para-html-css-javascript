/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analizadoreslexicos;

import com.mycompany.analizadoreslexicos.backend.Token;
import com.mycompany.analizadoreslexicos.backend.automatas.*;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class Lexer {
    
    private final String input;
    private final ArrayList<Token> tokensHTML;
    private final ArrayList<Token> tokensCSS;
    private final ArrayList<Token> tokensJS;

    public Lexer(String input) {
        this.input = input.trim();
        this.tokensHTML = new ArrayList<>();
        this.tokensCSS = new ArrayList<>();
        this.tokensJS = new ArrayList<>();
    }
    
    public void generarTokens(){
        ArrayList<String> inputs = separarInput(input);
        for (String inputSeparado : inputs) {
            if(inputSeparado.startsWith(">>[html]")){
                AutomataHTML automata = new AutomataHTML(inputSeparado);
                tokensHTML.addAll(automata.generarTokens());
            }else if(inputSeparado.startsWith(">>[css]")){
                AutomataCSS automata = new AutomataCSS(inputSeparado);
                tokensCSS.addAll(automata.generarTokens());
            }else if(inputSeparado.startsWith(">>[js]")){
                AutomataJS automata = new AutomataJS(inputSeparado);
                tokensJS.addAll(automata.generarTokens());
            }else{
                System.out.println("Error");
            }
        }
    }
    
    private ArrayList<String> separarInput(String texto){
        String[] palabrasEspeciales = {">>[html]", ">>[css]", ">>[js]"};
        ArrayList<String> partesSeparadas = new ArrayList<>();

        StringBuilder parteActual = new StringBuilder();
        String palabraActual = null;

        String[] lineas = texto.split("\n");

        for (String linea : lineas) {
            for (String palabraEspecial : palabrasEspeciales) {
                if (linea.startsWith(palabraEspecial)) {
                    if (parteActual.length() > 0) {
                        partesSeparadas.add(parteActual.toString().trim());
                    }
                    parteActual = new StringBuilder();
                    palabraActual = palabraEspecial;
                    parteActual.append(linea).append("\n");
                    break;
                }
            }
            if (palabraActual != null && !linea.startsWith(palabraActual)) {
                parteActual.append(linea).append("\n");
            }
        }

        if (parteActual.length() > 0) {
            partesSeparadas.add(parteActual.toString().trim());
        }

        return partesSeparadas;
    }

    public ArrayList<Token> getTokensHTML() {
        return tokensHTML;
    }

    public ArrayList<Token> getTokensCSS() {
        return tokensCSS;
    }

    public ArrayList<Token> getTokensJS() {
        return tokensJS;
    }

    
}
