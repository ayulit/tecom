package app.service;

import app.dto.CarDto;
import app.external.api.model.CarDataResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    Page<CarDto> get(String search, Pageable pageable);
    CarDto getCarById(Long id);
    CarDto addCar(CarDto carDto);
//    CarDto updateCar(Long id, CarDto carDto);
    void importCar(CarDataResponse carDataResponse);
}
