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
public class AutomataJS extends Automata {

    public AutomataJS(String input) {
        super(input);
    }

    @Override
    public ArrayList<Token> generarTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(new Token("PRUEBAJS", "Prueba", ""));
        return tokens;
    }

    
}
