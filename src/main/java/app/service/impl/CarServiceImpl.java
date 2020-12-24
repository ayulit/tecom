package app.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.dto.CarDto;
import app.entity.Car;
import app.exception.CarException;
import app.external.api.model.CarDataResponse;
import app.mapper.CarMapper;
import app.repository.CarRepository;
import app.repository.CarSpecificationsBuilder;
import app.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public Page<CarDto> get(String search, Pageable pageable) {
        CarSpecificationsBuilder builder = new CarSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Car> spec = builder.build();

        return carRepository.findAll(spec, pageable).map(carMapper::carToCarDto);
    }

    @Override
    public CarDto getCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarException("Car not found."));
        return carMapper.carToCarDto(car);
    }

    @Override
    public CarDto addCar(CarDto carDto) {
        Car car = carMapper.carDtoToCar(carDto);
        return carMapper.carToCarDto(carRepository.save(car));
    }

    @Override
    public void importCar(CarDataResponse carDataResponse) {
        if (carRepository.existsByExtId(carDataResponse.getObjectId())) {
            log.warn(
                    "Car #{} already exists in DB. Import of this car will be aborted due to potential conflict",
                    carDataResponse.getObjectId());
            return;
        }

        CarDto carDto = carMapper.carDataResponseToCarDto(carDataResponse);
        Car car = carMapper.carDtoToCar(carDto);
        carRepository.save(car);
    }

    //    @Override
//    public CarDto updateCar(Long id, CarDto carDto) {
//        Car car = carRepository.findById(id).orElseThrow(() -> new CarException("Car not found."));
//
//        car.setBrand(carDto.getBrand());
//        car.setModel(carDto.getModel());
//        car.setYear(carDto.getYear());
//        car.setMonth(carDto.getMonth());
//        car.setEngine(carDto.getEngine());
//        car.setHasTurbo(carDto.getHasTurbo());
//        car.setPower(carDto.getPower());
//        car.setTransmissionType(carDto.getTransmissionType());
//        car.setDriveType(carDto.getDriveType());
//        car.setBodyType(carDto.getBodyType());
//        car.setColor(carDto.getColor());
//
//        return carMapper.carToCarDto(carRepository.save(car));
//    }



}
