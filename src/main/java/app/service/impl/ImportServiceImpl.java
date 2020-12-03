package app.service.impl;

import java.util.Objects;
import java.util.stream.StreamSupport;

import app.external.api.model.CarDataResponse;
import app.service.CarService;
import app.service.ConnectorService;
import app.service.ImportService;
import app.util.JsonParseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class ImportServiceImpl implements ImportService {

    private static final int CARS_NUM = 100;

    private final ConnectorService connectorService;
    private final CarService carService;

    @Override
    public void importCars() {
        log.info("Started automatic car import job.");
        JSONArray externalCarData;
        try {
            externalCarData = connectorService.queryCarData(CARS_NUM);
        } catch (RuntimeException e) {
            log.error("Request for car data to import ended with error: {}", e.getClass(), e);
            return;
        }
        if ((externalCarData == null || externalCarData.length() == 0)) {
            log.info("No car data found to import");
            return;
        }
        log.info("Found {} cars to import", externalCarData.length());

        StreamSupport.stream(externalCarData.spliterator(), false)
//                .flatMap(i -> i)
                .filter(Objects::nonNull)
                .map(jsonObject -> JsonParseUtil.parseCarDataResponse(jsonObject.toString()))
                .filter(Objects::nonNull)
                .forEach((CarDataResponse carDataResponse) -> {
                    try {
                        carService.importCar(carDataResponse);
                    } catch (Exception e) {
                        log.info("Import car #{} ended with an error", carDataResponse.getObjectId());
                        log.error(e.getMessage());
                    }
                });

        log.info("Cars import has been completed successfully.");
    }


}
