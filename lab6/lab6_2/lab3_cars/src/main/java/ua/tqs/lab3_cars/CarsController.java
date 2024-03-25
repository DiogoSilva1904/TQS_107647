package ua.tqs.lab3_cars;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarsController {


    private final CarsService carsService;

    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carsService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable Long carId) {
        Car car = carsService.getCarDetails(carId);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carsService.save(car);
        return new ResponseEntity<>(saved, status);
    }

}
