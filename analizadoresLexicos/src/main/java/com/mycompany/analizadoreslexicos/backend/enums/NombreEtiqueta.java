/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.analizadoreslexicos.backend.enums;

/**
 *
 * @author mynordma
 */
public enum NombreEtiqueta {
    PRINCIPAL("principal"),
    ENCABEZADO("encabezado"),
    NAVEGACION("navegacion"),
    APARTADO("apartado"),
    LISTAORDENADA("listaordenada"),
    LISTADESORDENADA("listadesordenada"),
    ITEMLISTA("itemlista"),
    ANCLAJE("anclaje"),
    CONTENEDOR("contenedor"),
    SECCION("seccion"),
    ARTICULO("articulo"),
    TITULO1("titulo1"),
    TITULO2("titulo2"),
    TITULO3("titulo3"),
    TITULO4("titulo4"),
    TITULO5("titulo5"),
    TITULO6("titulo6"),
    PARRAFO("parrafo"),
    SPAN("span"),
    ENTRADA("entrada"),
    FORMULARIO("formulario"),
    LABEL("label"),
    AREA("area"),
    BOTON("boton"),
    PIEPAGINA("piepagina");

    private final String etiqueta;

    NombreEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
