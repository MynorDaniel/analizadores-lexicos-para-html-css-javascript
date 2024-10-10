/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analizadoreslexicos.backend;

/**
 *
 * @author mynordma
 */
public class Token {
    private String tipo;
    private String valor;
    private String traduccion;

    public Token(String tipo, String valor, String traduccion) {
        this.tipo = tipo;
        this.valor = valor;
        this.traduccion = traduccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return "Token{" + "tipo=" + tipo + ", valor='" + valor + '}';
    }

    public String getTraduccion() {
        return traduccion;
    }
    
}
