package app.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import app.service.ConnectorService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(noRollbackFor = RuntimeException.class)
public class ConnectorServiceImpl implements ConnectorService {
    @Override
    public JSONArray queryCarData(int carsNum) {
        log.debug("Query cars data");
        JSONObject data = null;
        try {
            URL url = new URL("https://parseapi.back4app.com/classes/Car_Model_List?limit=" + carsNum);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("X-Parse-Application-Id", "hlhoNKjOvEhqzcVAJ1lxjicJLZNVv36GdbboZj3Z"); // This is the fake app's application id
            urlConnection.setRequestProperty("X-Parse-Master-Key", "SNMJJF0CZZhTPhLDIqGhTlUNV9r60M2Z5spyWfXW"); // This is the fake app's readonly master key
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                data = new JSONObject(stringBuilder.toString()); // Here you have the data that you need
//                System.out.println(data.toString(2));
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            log.error("Error: " + e.toString());
        }

        JSONArray result;
        if (data != null && data.has("results")) {
            result = data.getJSONArray("results");
        } else {
            result = new JSONArray();
        }

        log.debug("Query finished with {} results", result.length());
        return result;
    }
}
