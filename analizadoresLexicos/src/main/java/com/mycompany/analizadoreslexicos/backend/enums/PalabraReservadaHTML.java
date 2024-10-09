/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.analizadoreslexicos.backend.enums;

/**
 *
 * @author mynordma
 */
public enum PalabraReservadaHTML {
    CLASS("class"),
    EQUALS("="),
    HREF("href"),
    ONCLICK("onClick"),
    ID("id"),
    STYLE("style"),
    TYPE("type"),
    PLACEHOLDER("placeholder"),
    REQUIRED("required"),
    NAME("name");

    private final String palabra;

    PalabraReservadaHTML(String palabra) {
        this.palabra = palabra;
    }

    public String getPalabra() {
        return palabra;
    }
}
