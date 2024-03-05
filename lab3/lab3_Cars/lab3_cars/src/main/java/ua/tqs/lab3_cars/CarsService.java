package ua.tqs.lab3_cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CarsService {

    private final CarRepository carRepository;

    public CarsService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save (Car car) {
        return this.carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }

    public Car getCarDetails(Long carId) {
       return this.carRepository.findByCarId(carId);
    }

    public void deleteCar(Long carId) {
        this.carRepository.deleteById(carId);
    }

}
