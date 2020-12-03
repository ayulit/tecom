package app.service;

import java.util.List;

import app.dto.CarDto;
import app.external.api.model.CarDataResponse;

public interface CarService {
    List<CarDto> getAll();
    CarDto getCarById(Long id);
    CarDto addCar(CarDto carDto);
//    CarDto updateCar(Long id, CarDto carDto);
    void importCar(CarDataResponse carDataResponse);
}
