package org.example.thecatapitest.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.thecatapitest.core.ResponsePojo;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class JsonConverter {
    private ObjectMapper om;

    public JsonConverter() {
        om = new ObjectMapper();
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
    }

    public <T> List<T> getEntitiesFromArray(ResponsePojo response, Class<T> tClass) {
        List<T> targetEntities = null;

        try {
            String targetArray = stringToJson(response.getResponseBody()).toString();
            targetEntities = om.readValue(targetArray, om.getTypeFactory().constructCollectionType(List.class, tClass));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return targetEntities;
    }

    public <T> T getEntity(ResponsePojo response, Class<T> tClass) {
        T targetEntity = null;
        try {
            String targetArray = stringToJson(response.getResponseBody()).toString();
            targetEntity = om.readValue(targetArray, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return targetEntity;
    }

    private static JsonNode stringToJson(String str) {
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonObj = null;
        try {
            jsonObj = om.readTree(str);
            return jsonObj;
        } catch (JsonProcessingException e) {
            log.error("Can't parse string: " + str);
        }
        return jsonObj;
    }
}