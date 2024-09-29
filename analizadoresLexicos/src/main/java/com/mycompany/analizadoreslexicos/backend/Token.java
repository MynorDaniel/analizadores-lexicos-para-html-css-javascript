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
    private int length;

    public Token(String tipo, String valor, int length) {
        this.tipo = tipo;
        this.valor = valor;
        this.length = length;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    @Override
    public String toString() {
        return "Token{" + "tipo=" + tipo + ", valor='" + valor + '\'' + ", longitud=" + length + '}';
    }
    
}
