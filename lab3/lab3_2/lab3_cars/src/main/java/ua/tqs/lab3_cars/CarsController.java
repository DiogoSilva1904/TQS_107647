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

    @DeleteMapping("/{carId}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long carId) {
        Car car = carsService.getCarDetails(carId);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        carsService.deleteCar(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<Car> updateCar(@PathVariable Long carId, @RequestBody Car car) {
        Car carToUpdate = carsService.getCarDetails(carId);
        if (carToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        carToUpdate.setMaker(car.getMaker());
        carToUpdate.setModel(car.getModel());
        carsService.save(carToUpdate);
        return new ResponseEntity<>(carToUpdate, HttpStatus.OK);
    }

}
