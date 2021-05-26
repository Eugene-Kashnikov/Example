package utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.ResponsePojo;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class GetterCollector {
    private ObjectMapper om;

    public GetterCollector() {
    }
    public <T> List<T> getEntitiesFromArray(ResponsePojo response, Class<T> tClass) {
        List<T> targetEntities = null;
        om = new ObjectMapper();
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        try {
            var targetArray = Parsing.stringToJson(response.getResponseBody()).toString();
            targetEntities = om.readValue(targetArray, om.getTypeFactory().constructCollectionType(List.class, tClass));
        } catch (IOException e) {
            throw new RuntimeException(e);
//            log.error("Can't parse/mapping response: {}", response.getResponseBody());
        }
        return targetEntities;
    }

    public <T> T getEntity(ResponsePojo response, Class<T> tClass) {
        T targetEntity = null;
        om = new ObjectMapper();
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        try {
            var targetArray = Parsing.stringToJson(response.getResponseBody()).toString();
            targetEntity = om.readValue(targetArray, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);        }
        return targetEntity;
    }
    public <T> List<T> getEntitiesFromArray(ResponsePojo response, String responseKey, Class<T> tClass) {
        List<T> targetEntities = null;
        om = new ObjectMapper();
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        try {
            var targetArray = Parsing.stringToJson(response.getResponseBody()).get(responseKey).toString();
            targetEntities = om.readValue(targetArray, om.getTypeFactory().constructCollectionType(List.class, tClass));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return targetEntities;
    }
}