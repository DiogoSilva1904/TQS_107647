package ua.tqs.lab3_cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class CCarsRepositoryPersistenceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenInvalid_ReturnNull() {
        Car fromDb = carRepository.findByCarId(-11L);
        assert(fromDb == null);
    }

    @Test
    public void whenValid_returnCar() {
        Car car = new Car("opel", "corsa");
        entityManager.persistAndFlush(car);
        Car fromDb = carRepository.findByCarId(car.getCarId());
        assert(fromDb.getMaker().equals(car.getMaker()));
        assert(fromDb.getModel().equals(car.getModel()));
    }

    @Test
    public void whenFindAll_returnAllCars() {
        Car car = new Car("opel", "corsa");
        Car car1 = new Car("renault", "clio");
        Car car2 = new Car("fiat", "punto");
        entityManager.persist(car);
        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.flush();
        List<Car> allCars = carRepository.findAll();
        assertThat(allCars).hasSize(3).extracting(Car::getModel).containsOnly(car.getModel(), car1.getModel(), car2.getModel());
    }
}
