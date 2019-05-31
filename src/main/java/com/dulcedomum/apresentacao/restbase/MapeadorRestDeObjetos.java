package com.dulcedomum.apresentacao.restbase;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MapeadorRestDeObjetos {

    public MapeadorRestDeObjetos() {
    }

    public static ObjectMapper criar() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(new Module[]{new JavaTimeModule()});
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
}
