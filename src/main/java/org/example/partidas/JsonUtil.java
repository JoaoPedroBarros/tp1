package org.example.partidas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;

public class JsonUtil {

    private static final ObjectMapper mapper =
            new ObjectMapper();

    static {

        mapper.enable(
                SerializationFeature.INDENT_OUTPUT);
    }

    public static ObjectMapper getMapper() {

        return mapper;
    }

    public static void salvar(
            String arquivo,
            Object objeto) throws Exception {

        mapper.writeValue(
                new File(arquivo),
                objeto);
    }

    public static <T> T ler(
            String arquivo,
            Class<T> classe)
            throws Exception {

        return mapper.readValue(
                new File(arquivo),
                classe);
    }
}