/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.analizadoreslexicos.backend.enums;

/**
 *
 * @author mynordma
 */
public enum Lenguaje {
    
    HTML(">>[html]"),
    CSS(">>[css]"),
    JS(">>[js]");

    private final String token;

    Lenguaje(String token) {
        this.token = token;
    }
    
    public String getToken() {
        return token;
    }
}
