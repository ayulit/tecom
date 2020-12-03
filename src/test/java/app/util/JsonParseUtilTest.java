package app.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import app.external.api.model.CarCategory;
import app.external.api.model.CarDataResponse;
import org.junit.jupiter.api.Test;

class JsonParseUtilTest {

    @Test
    void parseCarDataResponseSingleCategory() {
        String json = "{\"createdAt\":\"2020-01-27T20:44:17.665Z\",\"Category\":\"Sedan\",\"Year\":2020,\"Model\":\"RLX\",\"Make\":\"Acura\",\"objectId\":\"rDkHakOBKP\",\"updatedAt\":\"2020-01-27T20:44:17.665Z\"}";
        CarDataResponse expected = new CarDataResponse("rDkHakOBKP", "Acura", "RLX", 2020, CarCategory.SEDAN);

        CarDataResponse actual = JsonParseUtil.parseCarDataResponse(json);
        assertEquals(expected, actual);
    }


    @Test
    void parseCarDataResponseCommaCategory() {
        String json = "{\"createdAt\":\"2020-01-27T20:44:17.665Z\",\"Category\":\"Coupe, Convertible\",\"Year\":2020,\"Model\":\"RLX\",\"Make\":\"Acura\",\"objectId\":\"rDkHakOBKP\",\"updatedAt\":\"2020-01-27T20:44:17.665Z\"}";
        CarDataResponse expected = new CarDataResponse("rDkHakOBKP", "Acura", "RLX", 2020, CarCategory.COUPE);

        CarDataResponse actual = JsonParseUtil.parseCarDataResponse(json);
        assertEquals(expected, actual);
    }

    @Test
    void parseCarDataResponseSlashCategory() {
        String json = "{\"objectId\":\"4q7L9FAU2S\",\"Year\":2020,\"Make\":\"Chrysler\",\"Model\":\"Pacifica\",\"Category\":\"Van\\/Minivan\",\"createdAt\":\"2020-01-27T20:44:17.665Z\",\"updatedAt\":\"2020-01-27T20:44:17.665Z\"}";
        CarDataResponse expected = new CarDataResponse("4q7L9FAU2S", "Chrysler", "Pacifica", 2020, CarCategory.VAN);

        CarDataResponse actual = JsonParseUtil.parseCarDataResponse(json);
        assertEquals(expected, actual);
    }
}
