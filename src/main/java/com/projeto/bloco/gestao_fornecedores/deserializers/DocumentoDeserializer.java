package com.projeto.bloco.gestao_fornecedores.deserializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.bloco.gestao_fornecedores.models.events.GestaoDocumentosEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class DocumentoDeserializer implements Deserializer<GestaoDocumentosEvent> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public GestaoDocumentosEvent deserialize(String s, byte[] bytes) {
        if(bytes == null || bytes.length == 0){
            throw new SerializationException("Nenhum documento encontrado, para ser deserializado.");
        }

        try {
            return objectMapper.readValue(bytes, GestaoDocumentosEvent.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}