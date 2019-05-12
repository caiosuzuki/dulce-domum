package com.dulcedomum.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MapeadorRestDeObjetos {

    public MapeadorRestDeObjetos() {
    }

    public static ObjectMapper criar() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new ISO8601DateFormat());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModules(new Module[]{new JavaTimeModule()});
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
}
