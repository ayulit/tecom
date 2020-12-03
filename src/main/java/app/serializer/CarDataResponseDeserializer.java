package app.serializer;

import java.io.IOException;

import app.external.api.model.CarCategory;
import app.external.api.model.CarDataResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CarDataResponseDeserializer extends StdDeserializer<CarDataResponse> {
    public CarDataResponseDeserializer() {
        this(null);
    }

    public CarDataResponseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CarDataResponse deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode node = objectCodec.readTree(jsonParser);

        String objectId = node.get("objectId").asText();
        String make = node.get("Make").asText();
        String model = node.get("Model").asText();
        Integer year = (Integer) node.get("Year").numberValue();

        // hack
        String categories = node.get("Category").asText();
        CarCategory carCategory = CarCategory.valueOf((categories.split("[,/]")[0]).toUpperCase());

        return new CarDataResponse(objectId, make, model, year, carCategory);
    }
}
