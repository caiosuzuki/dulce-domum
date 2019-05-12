package com.dulcedomum.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class DesserializadorElementoRest extends StdDeserializer<ElementoRest> {
    private Class classeDoObjetoQueRepresentaOConteudoDoElemento;

    public DesserializadorElementoRest(Class classeDoObjetoQueRepresentaOConteudoDoElemento) {
        super(ElementoRest.class);
        this.classeDoObjetoQueRepresentaOConteudoDoElemento = classeDoObjetoQueRepresentaOConteudoDoElemento;
    }

    public static DesserializadorElementoRest criar(Class classeDoObjetoQueRepresentaOConteudoDoElemento) {
        return new DesserializadorElementoRest(classeDoObjetoQueRepresentaOConteudoDoElemento);
    }

    public static ElementoRest desserializar(String json, Class classeDoObjetoQueRepresentaOConteudoDoElemento) {
        ObjectMapper objectMapper = (new MapeadorDeJsonJaxRs()).objectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(ElementoRest.class, new DesserializadorElementoRest(classeDoObjetoQueRepresentaOConteudoDoElemento));
        objectMapper.registerModules(new Module[]{simpleModule});

        try {
            return (ElementoRest)objectMapper.readValue(json, ElementoRest.class);
        } catch (IOException var5) {
            throw new IllegalArgumentException("Ocorreu um erro ao desserializar o json para o objeto ElementoRest.", var5);
        }
    }

    public ElementoRest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode elementoRestJsonNode = (JsonNode)codec.readTree(jsonParser);
        return this.obterElementoRest(elementoRestJsonNode);
    }

    private ElementoRest obterElementoRest(JsonNode elementoRestJsonNode) throws IOException {
        Object objetoQueRepresentaOConteudo = this.obterObjetoQueRepresentaOConteudo(elementoRestJsonNode);
        return new ElementoRest(objetoQueRepresentaOConteudo);
    }

    private Object obterObjetoQueRepresentaOConteudo(JsonNode elementoRestJsonNode) throws IOException {
        String jsonDoConteudoDoElemento = elementoRestJsonNode.get("conteudo").toString();
        ObjectMapper objectMapper = (new MapeadorDeJsonJaxRs()).objectMapper();
        objectMapper.registerModules(new Module[]{new JavaTimeModule()});
        return objectMapper.readValue(jsonDoConteudoDoElemento, this.classeDoObjetoQueRepresentaOConteudoDoElemento);
    }
}
