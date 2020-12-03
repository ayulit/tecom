package app.service;

import org.json.JSONArray;

public interface ConnectorService {
    JSONArray queryCarData(int carsNum);
}
