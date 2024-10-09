/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analizadoreslexicos.backend.automatas;

import com.mycompany.analizadoreslexicos.backend.Token;
import com.mycompany.analizadoreslexicos.backend.enums.*;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public abstract class Automata {
    protected String input;
    protected int posicion;
    protected String estado;
    protected ArrayList<Token> errores;

    public Automata(String input) {
        this.input = input.trim();
        this.estado = "q0";
        this.errores = new ArrayList<>();
    }

    public abstract ArrayList<Token> generarTokens();
    
    protected boolean esNombreEtiqueta(String palabra){
        try {
            NombreEtiqueta.valueOf(palabra.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    protected boolean esPalabraReservada(String palabra) {
        try {
            PalabraReservadaHTML.valueOf(palabra.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    protected boolean esCadena(String palabra) {
        if (palabra != null && palabra.length() >= 2) {
            return palabra.startsWith("\"") && palabra.endsWith("\"");
        }
        return false;
    }

}
