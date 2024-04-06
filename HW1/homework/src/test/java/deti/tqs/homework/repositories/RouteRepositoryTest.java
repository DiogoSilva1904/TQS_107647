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

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RouteRepositoryTest {

            @Autowired
            TestEntityManager entityManager;

            @Autowired
            RouteRepository routeRepository;

            @Autowired
            StopRepository stopRepository;

            @Autowired
            TripRepository tripRepository;


            Route route1, route2, route3;
            Trip trip1, trip2;

            @BeforeEach
            void setUp() {
                Stop stop1 = new Stop();
                stop1.setName("Aveiro");
                stop1.setStopOrder(1);
                Stop stop2 = new Stop();
                stop2.setName("Coimbra");
                stop2.setStopOrder(2);
                Trip trip1 = new Trip();
                trip1.setTrip_type("IDA");
                Trip trip2 = new Trip();
                trip2.setTrip_type("IDA/VOLTA");
                entityManager.persistAndFlush(stop1);
                entityManager.persistAndFlush(stop2);
                entityManager.persistAndFlush(trip1);
                entityManager.persistAndFlush(trip2);
                route1 = new Route();
                route1.setArrivalTime(LocalDateTime.parse("2021-05-01T08:00:00"));
                route1.setDepartureTime(LocalDateTime.parse("2021-05-01T07:00:00"));
                route1.setStops(List.of(stop1, stop2));
                route1.setTrips(List.of(trip1, trip2));
                route2 = new Route();
                route2.setArrivalTime(LocalDateTime.parse("2021-05-01T09:00:00"));
                route2.setDepartureTime(LocalDateTime.parse("2021-05-01T08:00:00"));
                route2.setStops(List.of( stop2));
                route2.setTrips(List.of(trip1));
                route3 = new Route();
                route3.setArrivalTime(LocalDateTime.parse("2021-05-01T10:00:00"));
                route3.setDepartureTime(LocalDateTime.parse("2021-05-01T09:00:00"));
                route3.setStops(List.of(stop1, stop2));
                route3.setTrips(List.of(trip2));
                entityManager.persistAndFlush(route1);
                entityManager.persistAndFlush(route2);
                entityManager.persistAndFlush(route3);

            }

            @Test
            public void whenFindById_thenReturnRoute(){
                Route found = routeRepository.findById(route1.getId()).orElse(null);
                assert(found.getId()).equals(route1.getId());
            }

            @Test
            public void whenInvalidID_thenReturnNUll(){
                Route found = routeRepository.findById(-1L).orElse(null);
                assertEquals(found, null);
            }



}
