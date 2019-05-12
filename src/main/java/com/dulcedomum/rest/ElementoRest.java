package com.dulcedomum.rest;

public class ElementoRest {
    private Object conteudo;

    private ElementoRest() {
    }

    public ElementoRest(Object conteudo) {
        this.conteudo = conteudo;
    }

    public Object getConteudo() {
        return this.conteudo;
    }
}
