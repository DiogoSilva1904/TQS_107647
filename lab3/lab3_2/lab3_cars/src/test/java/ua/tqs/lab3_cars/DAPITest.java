package ua.tqs.lab3_cars;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class DAPITest {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateCar() {
        Car car = new Car("opel", "corsa");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/cars", car, Car.class);

        List<Car> found = carRepository.findAll();
        assertThat(found).extracting(Car::getModel).containsOnly("corsa");
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200() {
        Car car = new Car("opel", "corsa");
        Car car1 = new Car("renault", "clio");
        Car car2 = new Car("fiat", "500");
        carRepository.saveAndFlush(car);
        carRepository.saveAndFlush(car1);
        carRepository.saveAndFlush(car2);
        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsOnly("corsa","clio", "500");
    }

    @Test
    public void whenNonExistentCar_thenStatus404() {
        ResponseEntity<Car> responseEntity = restTemplate.getForEntity("/cars/1", Car.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
