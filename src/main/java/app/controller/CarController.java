package app.controller;

import java.util.List;

import app.dto.CarDto;
import app.service.CarService;
import app.service.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CarController {
    private final CarService carService;
    private final ImportService importService;

    @GetMapping
    public List<CarDto> get(Pageable pageable) {
        return carService.get(pageable);
    }

    @GetMapping
    public List<CarDto> getAll() {
        return carService.getAll();
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
