package app.mapper;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import app.dto.CarDto;
import app.enums.BodyType;
import app.enums.DriveType;
import app.enums.TransmissionType;
import app.external.api.model.CarDataResponse;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CarMapperDecorator implements CarMapper {

    @Autowired
    CarMapper delegate;

    @Override
    public CarDto carDataResponseToCarDto(CarDataResponse carDataResponse) {
        CarDto carDto = delegate.carDataResponseToCarDto(carDataResponse);

        if (carDataResponse.getCategory() != null) {
            BodyType bodyType = BodyType.getByTestType(carDataResponse.getCategory());

            if (bodyType != null) {
                carDto.setBodyType(bodyType);
            }
        }

        // Random generation next
        Random random = new Random(System.currentTimeMillis());

        carDto.setYear((short)(random.nextInt(2020 - 1980) + 1980));
        carDto.setMonth((short)(random.nextInt(12) + 1));
        carDto.setEngine((short)(Math.random() * 5000 + 1000 + Math.random() * 900));
        carDto.setHasTurbo(random.nextBoolean());
        carDto.setPower((short)(Math.random() * 500 + 100));
        carDto.setTransmissionType(TransmissionType.values()[random.nextInt(TransmissionType.values().length)]);
        carDto.setDriveType(DriveType.values()[random.nextInt(DriveType.values().length)]);

        // sheesh...
        Arrays.stream(Color.class.getDeclaredFields())
                .filter(field -> field.getType().equals(Color.class))
                .map(field -> field.getName().toLowerCase())
                .sorted((o1, o2) -> ThreadLocalRandom.current().nextInt(-1, 2))
                .findAny()
                .ifPresent(carDto::setColor);


        return carDto;

    }
}
