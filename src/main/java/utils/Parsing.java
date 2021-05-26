package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;


import java.io.IOException;

@Slf4j
public final class Parsing {

    private Parsing() {}

    public static JsonNode stringToJson(String str) {
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

    public static JsonNode stringToJson(ResponseBody body) {
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonObj = null;
        try {
            String bodyStr = body.string();
            jsonObj = om.readTree(bodyStr);
            return jsonObj;
        } catch (IOException e) {
            log.error("Can't parse string: " + jsonObj.asText());
        }
        return jsonObj;
    }
}