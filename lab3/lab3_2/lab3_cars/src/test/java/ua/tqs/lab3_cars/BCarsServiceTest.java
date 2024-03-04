package ua.tqs.lab3_cars;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;
@ExtendWith(MockitoExtension.class)
public class BCarsServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarsService carsService;

    @BeforeEach
    public void startup(){
        Car car = new Car("opel", "corsa");
        Car car1 = new Car("renault", "clio");
        Car car2 = new Car("fiat", "punto");

        List<Car> allCars = Arrays.asList(car, car1, car2);

        when(carRepository.findByCarId(1L)).thenReturn(car);
        when(carRepository.findByCarId(2L)).thenReturn(car1);
        when(carRepository.findByCarId(3L)).thenReturn(car2);
        when(carRepository.findByMaker("opel")).thenReturn(car);
        when(carRepository.findByMaker("renault")).thenReturn(car1);
        when(carRepository.findByMaker("fiat")).thenReturn(car2);
        when(carRepository.findByModel("corsa")).thenReturn(car);
        when(carRepository.findByModel("clio")).thenReturn(car1);
        when(carRepository.findByModel("punto")).thenReturn(car2);
        when(carRepository.save(any(Car.class))).thenReturn(car);
        when(carRepository.save(any(Car.class))).thenReturn(car1);
        when(carRepository.save(any(Car.class))).thenReturn(car2);
        when(carRepository.findAll()).thenReturn(allCars);
    }

    @Test
    public void testGetCarDetails() {
        Car car = carsService.getCarDetails(1L);
        assert(car.getMaker()).equals("opel");
        assert(car.getModel()).equals("corsa");
    }

    @Test
    public void testNotExistingCarDetails() {
        Car car = carsService.getCarDetails(4L);
        assertThat(car).isNull();
    }

    @Test
    public void testGetAllCars() {
        List<Car> allCars = carsService.getAllCars();
        assertThat(allCars).hasSize(3);
    }

}
