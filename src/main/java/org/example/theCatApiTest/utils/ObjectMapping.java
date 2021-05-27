package org.example.theCatApiTest.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapping {

    private final ObjectMapper om;

    public ObjectMapping() {
        om = new ObjectMapper();
    }

    public JsonNode mapObjectToJsonNode(Object object) {
        JsonNode body = om.convertValue(object, JsonNode.class);
        return body;
    }
}
