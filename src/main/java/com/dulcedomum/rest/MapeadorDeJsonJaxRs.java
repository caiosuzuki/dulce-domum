package com.dulcedomum.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;

public class MapeadorDeJsonJaxRs implements ContextResolver<ObjectMapper> {
    private ObjectMapper objectMapper = MapeadorRestDeObjetos.criar();

    public MapeadorDeJsonJaxRs() {
    }

    public ObjectMapper getContext(Class<?> aClass) {
        return this.objectMapper;
    }

    public ObjectMapper objectMapper() {
        return this.objectMapper;
    }
}
