package app.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.dto.CarDto;
import app.exception.CarException;
import app.repository.CarSpecificationsBuilder;
import app.service.CarService;
import app.service.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CarController {
    private final CarService carService;
    private final ImportService importService;

    @GetMapping
    public List<CarDto> get(@RequestParam(value = "search", required = false) String search, Pageable pageable) {

        Page<CarDto> resultPage = carService.get(search, pageable);
//        if (pageable.getPageSize() > resultPage.getNumberOfElements()) {
//            throw new CarException("Cars not found");
//        }
        return resultPage.getContent();
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public CarDto addCar(@RequestBody CarDto cardDto) {
        return carService.addCar(cardDto);
    }

//    @PatchMapping("/{id}")
//    public CarDto updateCar(@PathVariable Long id, RequestBody CarDto carDto) {
//        return carService.updateCar(id, carDto);
//    }

    @GetMapping("/import")
    public ResponseEntity<?> importCars() {
        importService.importCars();
        return ResponseEntity.ok().build();
    }
}
