package app.util;

import java.io.IOException;

import app.external.api.model.CarDataResponse;
import app.serializer.CarDataResponseDeserializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonParseUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new SimpleModule().addDeserializer(CarDataResponse.class, new CarDataResponseDeserializer()));

    public static CarDataResponse parseCarDataResponse(String json) {
        try {
            return objectMapper.readValue(json, CarDataResponse.class);
        } catch (IOException e) {
            log.error("Cannot read value as CarDataResponse\nValue:{}", json, e);
            return null;
        }
    }
}
