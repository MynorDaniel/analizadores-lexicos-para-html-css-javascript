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
public class ResultadoAutomata {
    
    private ArrayList<Token> tokens;
    private int nuevaPosicion;

    public ResultadoAutomata(ArrayList<Token> tokens, int nuevaPosicion) {
        this.tokens = tokens;
        this.nuevaPosicion = nuevaPosicion;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public int getNuevaPosicion() {
        return nuevaPosicion;
    }
    
    
}
