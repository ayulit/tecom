package app.service.impl;

import app.service.ConnectorService;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConnectorServiceImplTest {

    @Test
    void queryCarData() {
        ConnectorService connectorService = new ConnectorServiceImpl();
        int carsToImport = 5;

        JSONArray result = connectorService.queryCarData(carsToImport);
        assertEquals(carsToImport, result.length());
    }
}
