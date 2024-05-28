package com.example.dlykserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {

    /*
    ObjectMapper is a core class in the Jackson library that is
    used to convert between Java objects and JSON data.
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * transfer Java bean to json string
     */
    public static String toJSON(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * transfer json string to Java bean
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        try {
            //eg: User user = OBJECT_MAPPER.readValue(jsonString, User.class);
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
