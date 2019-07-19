package com.dulcedomum.apresentacao.restbase;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DesserializadorColecaoRest extends StdDeserializer<ColecaoRest> {

    private Class classeDoObjetoQueRepresentaOConteudoDoElemento;

    private DesserializadorColecaoRest(Class classeDoObjetoQueRepresentaOConteudoDoElemento) {
        super(classeDoObjetoQueRepresentaOConteudoDoElemento);
        this.classeDoObjetoQueRepresentaOConteudoDoElemento = classeDoObjetoQueRepresentaOConteudoDoElemento;
    }

    public static DesserializadorColecaoRest criar(Class classeDoObjetoQueRepresentaOConteudoDoElemento) {
        return new DesserializadorColecaoRest(classeDoObjetoQueRepresentaOConteudoDoElemento);
    }

    public ColecaoRest desserializar(String json) {
        ObjectMapper objectMapper = new MapeadorDeJsonJaxRs().objectMapper();
        SimpleModule simpleModule = new SimpleModule();

        simpleModule.addDeserializer(ColecaoRest.class, new DesserializadorColecaoRest(classeDoObjetoQueRepresentaOConteudoDoElemento));
        objectMapper.registerModules(simpleModule);
        try {
            return objectMapper.readValue(json, ColecaoRest.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Ocorreu um erro ao desserializar o json para o objeto ColecaoRest.", e);
        }
    }

    @Override
    public ColecaoRest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode colecaoRestJsonNode = codec.readTree(jsonParser);
        List<ElementoRest> elementos = obterOsElementosRest(colecaoRestJsonNode);
        return new ColecaoRest(elementos);
    }

    private List<ElementoRest> obterOsElementosRest(JsonNode colecaoRestJsonNode) {
        JsonNode elementosRestJsonNode = colecaoRestJsonNode.get("elementos");
        List<ElementoRest> elementosRest = new ArrayList<>();
        for(JsonNode elementoRestJsonNode : elementosRestJsonNode) {
            elementosRest.add(DesserializadorElementoRest.desserializar(elementoRestJsonNode.toString(), classeDoObjetoQueRepresentaOConteudoDoElemento));
        }
        return elementosRest;
    }
}
