package app.mapper;


import app.dto.CarDto;
import app.entity.Car;
import app.external.api.model.CarDataResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(CarMapperDecorator.class)
public interface CarMapper {
    Car carDtoToCar(CarDto carDto);
    CarDto carToCarDto(Car car);

    @Mapping(target = "extId", source = "objectId")
    @Mapping(target = "brand", source = "make")
    @Mapping(target = "year", ignore = true)
    CarDto carDataResponseToCarDto(CarDataResponse carDataResponse);
}




