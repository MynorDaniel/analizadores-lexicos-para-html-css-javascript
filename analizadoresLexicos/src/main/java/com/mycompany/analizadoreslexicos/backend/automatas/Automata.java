/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analizadoreslexicos.backend.automatas;

import com.mycompany.analizadoreslexicos.backend.enums.NombreEtiqueta;

/**
 *
 * @author mynordma
 */
public abstract class Automata {
    protected String input;
    protected int posicion;
    protected String estado;

    public Automata(String input) {
        this.input = input;
        this.posicion = 0;
        this.estado = "q0";
    }

    public abstract ResultadoAutomata generarTokens();
    
    protected boolean cambioDeEstado(String buffer){
        return buffer.equals(">>[css]") || buffer.equals(">>[js]") || buffer.equals(">>[html]");
    }
    
    protected boolean esPalabraReservada(String palabra) {
        try {
            NombreEtiqueta.valueOf(palabra.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
