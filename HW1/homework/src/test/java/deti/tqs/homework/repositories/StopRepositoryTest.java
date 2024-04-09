package deti.tqs.homework.repositories;

import deti.tqs.homework.models.Reservation;
import deti.tqs.homework.models.Route;
import deti.tqs.homework.models.Stop;
import deti.tqs.homework.models.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class StopRepositoryTest {
        @Autowired
        TestEntityManager entityManager;
        @Autowired
        StopRepository stopRepository;
        @Autowired
        RouteRepository routeRepository;

        Stop stop1, stop2, stop3;
        Route route1,route2;
        @BeforeEach
        void setUp() {
            route1 = new Route();
            route2 = new Route();
            entityManager.persistAndFlush(route1);
            entityManager.persistAndFlush(route2);
            stop1 = new Stop();
            stop1.setName("Aveiro");
            stop1.setStopOrder(1);
            stop1.setRoute(route1);
            stop2 = new Stop();
            stop2.setName("Coimbra");
            stop2.setStopOrder(2);
            stop2.setRoute(route1);
            stop3 = new Stop();
            stop3.setName("Lisboa");
            stop3.setStopOrder(3);

            entityManager.persistAndFlush(stop1);
            entityManager.persistAndFlush(stop2);
            entityManager.persistAndFlush(stop3);
        }

        @Test
        public void whenFindByName_thenReturnStop(){
            List<Stop> found = stopRepository.findByName(stop1.getName());
            assert(found.get(0).getName()).equals(stop1.getName());
        }

        @Test
        public void whenFindByRoute_thenReturnStop(){
            List<Stop> found = stopRepository.findByRoute(route1);
            assertEquals(found.size(),2);
        }

        @Test
        public void whenFindByRoute_thenReturnEmpty(){
            Route route3 = new Route();
            entityManager.persistAndFlush(route3);
            List<Stop> found = stopRepository.findByRoute(route3);
            assertEquals(found.size(),0);
        }
}
