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
public class AutomataCSS extends Automata {

    public AutomataCSS(String input) {
        super(input);
        this.posicion = 7;
    }

    @Override
    public ArrayList<Token> generarTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        
        while(posicion < input.length()){
            System.out.println(posicion + " < " + input.length());
            char c = input.charAt(posicion);
            
            
            
            buffer.append(c);
            String palabra = buffer.toString().trim();
            
            if(esColor(palabra)){
                tokens.add(new Token("COLOR", palabra, palabra));
                buffer.setLength(0);
            }else if(esOtro(palabra)){
                tokens.add(new Token("OTRO", palabra, palabra));
                buffer.setLength(0);
            }else if(esCombinador(palabra)){
                tokens.add(new Token("COMBINADOR", palabra, palabra));
                buffer.setLength(0);
            }else if(esRegla(palabra)){
                tokens.add(new Token("REGLA", palabra, palabra));
                buffer.setLength(0);
            }else if(esSelector(palabra)){
                tokens.add(new Token("SELECTOR", palabra, palabra));
                buffer.setLength(0);
            }else if(esEntero(palabra)){
                tokens.add(new Token("ENTERO", palabra, palabra));
                buffer.setLength(0);
            }else if(esCadenaCss(palabra)){
                tokens.add(new Token("CADENA", palabra, palabra));
                buffer.setLength(0);
            }
            
            posicion++;
            
            if((c == ';' || c == ',') && buffer.toString().trim().length() > 0){
                String palabra2 = buffer.toString().trim();
                tokens.add(new Token("IDENTIFICADOR", palabra2.substring(0, palabra2.length() - 1), palabra));
                buffer.setLength(0);
            }
        }
        return tokens;
    }

    private boolean esColor(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return false;
        }

        if (esHexadecimal(palabra)) {
            return true;
        }

        return esRgba(palabra);
    }

    private boolean esHexadecimal(String palabra) {
        if (!palabra.startsWith("#")) {
            return false;
        }

        String hex = palabra.substring(1);

        if (hex.length() != 3 && hex.length() != 6) {
            return false;
        }

        for (char c : hex.toCharArray()) {
            if (!esCaracterHexadecimal(c)) {
                return false;
            }
        }

        return true;
    }

    private boolean esCaracterHexadecimal(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }

    private boolean esRgba(String palabra) {
        if (!palabra.startsWith("rgba(") || !palabra.endsWith(")")) {
            return false;
        }

        String contenido = palabra.substring(5, palabra.length() - 1);

        String[] valores = contenido.split(",");

        if (valores.length != 4) {
            return false;
        }

        for (int i = 0; i < 3; i++) {
            if (!esEnteroEnRango(valores[i].trim(), 0, 255)) {
                return false;
            }
        }

        String alpha = valores[3].trim();
        if (!esDecimalEnRango(alpha, 0, 1)) {
            return false;
        }

        return true;
    }

    private boolean esEnteroEnRango(String valor, int min, int max) {
        try {
            int numero = Integer.parseInt(valor);
            return numero >= min && numero <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean esDecimalEnRango(String valor, double min, double max) {
        try {
            double numero = Double.parseDouble(valor);
            return numero >= min && numero <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean esOtro(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return false;
        }

        palabra = palabra.trim();

        String[] otros = {
            "px", "%", "rem", "em", "vw", "vh", ":hover", ":active", ":not()", ":nth-child()", "odd", "even",
            "::before", "::after", ":", ";", ",", "[", "]", "(", ")", "{", "}"
        };

        for (String otro : otros) {
            if (palabra.equals(otro)) {
                return true;
            }
        }

        return false;
    }

    private boolean esCombinador(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return false;
        }

        palabra = palabra.trim();

        return palabra.equals(">") || palabra.equals("+") || palabra.equals("~");
    }

    private boolean esRegla(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return false;
        }

        palabra = palabra.trim();

        String[] reglas = {
            "color", "background-color", "background", "font-size", "font-weight", "font-family", "font-align",
            "width", "height", "min-width", "min-height", "max-width", "max-height", "display", "inline", "block",
            "inline-block", "flex", "grid", "none", "margin", "border", "padding", "content", "border-color",
            "border-style", "border-width", "border-top", "border-bottom", "border-left", "border-right",
            "box-sizing", "border-box", "position", "static", "relative", "absolute", "sticky", "fixed", "top",
            "bottom", "left", "right", "z-index", "justify-content", "align-items", "border-radius", "auto",
            "float", "list-style", "text-align", "box-shadow"
        };

        for (String regla : reglas) {
            if (palabra.equals(regla)) {
                return true;
            }
        }

        return false;
    }

    private boolean esSelector(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return false;
        }

        palabra = palabra.trim();

        if (palabra.equals("*")) {
            return true;
        }

        String[] etiquetas = {"body", "header", "main", "nav", "aside", "div", "ul", "ol", "li", "a", 
                              "h1", "h2", "h3", "h4", "h5", "h6", "p", "span", "label", "textarea", 
                              "button", "section", "article", "footer"};

        for (String etiqueta : etiquetas) {
            if (palabra.equals(etiqueta)) {
                return true;
            }
        }

        if (palabra.startsWith(".")) {
            return esClaseValida(palabra.substring(1)); 
        }

        if (palabra.startsWith("#")) {
            return esIdValido(palabra.substring(1)); 
        }

        return false;
    }

    private boolean esClaseValida(String clase) {
        return esKebabCase(clase);
    }

    private boolean esIdValido(String id) {
        return esKebabCase(id);
    }

    private boolean esKebabCase(String palabra) {
        if (palabra.isEmpty() || !Character.isLowerCase(palabra.charAt(0))) {
            return false;
        }

        for (int i = 1; i < palabra.length(); i++) {
            char c = palabra.charAt(i);

            if (!(Character.isLowerCase(c) || Character.isDigit(c) || c == '-')) {
                return false;
            }

            if (c == '-' && (i == 0 || i == palabra.length() - 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean esEntero(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return false;
        }

        for (int i = 0; i < palabra.length(); i++) {
            if (!Character.isDigit(palabra.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private void identificador(StringBuilder buffer, ArrayList<Token> tokens) {
        if(buffer.toString().trim().length()>0){
                    tokens.add(new Token("IDENTIFICADOR", buffer.toString().trim(), buffer.toString().trim()));
                }
    }
    
    private boolean esCadenaCss(String palabra){
        if (palabra != null && palabra.length() >= 2) {
            return palabra.startsWith("'") && palabra.endsWith("'");
        }
        return false;
    }

}
