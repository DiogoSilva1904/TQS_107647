package deti.tqs.homework.repositories;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import deti.tqs.homework.models.Route;
import deti.tqs.homework.models.Stop;
import deti.tqs.homework.models.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class TripRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    StopRepository stopRepository;


    Route route1,route2;
    Trip trip1,trip2,trip3,trip4;
    @BeforeEach
    void setUp() {
        Stop stop1 = new Stop();
        stop1.setName("Aveiro");
        stop1.setStopOrder(1);
        Stop stop2 = new Stop();
        stop2.setName("Coimbra");
        stop2.setStopOrder(2);
        Stop stop3 = new Stop();
        stop3.setName("Lisboa");
        stop3.setStopOrder(3);
        Stop stop4 = new Stop();
        stop4.setName("Porto");
        stop4.setStopOrder(2);
        Stop stop5 = new Stop();
        stop5.setName("Faro");
        stop5.setStopOrder(3);
        Stop stop6 = new Stop();
        stop6.setName("Braga");
        stop6.setStopOrder(1);
        route1 = new Route();
        route2 = new Route();
        trip1 = new Trip();
        trip2 = new Trip();
        trip3 = new Trip();
        trip4 = new Trip();
        stopRepository.save(stop1);
        route1.setStops(new ArrayList<>(Arrays.asList(stop1, stop2, stop3)));
        route2.setStops(new ArrayList<>(Arrays.asList(stop6, stop5,stop4)));
        entityManager.persistAndFlush(route1);
        entityManager.persistAndFlush(route2);
        trip1.setArrivalTime(LocalDateTime.parse("2021-12-12T12:00:00"));
        trip1.setDepartureTime(LocalDateTime.parse("2021-12-12T12:00:00"));
        trip1.setRoute(route1);
        trip1.setTrip_type("IDA");
        trip1.setAvailableSeats(0);
        trip2.setArrivalTime(LocalDateTime.parse("2021-12-12T12:00:00"));
        trip2.setDepartureTime(LocalDateTime.parse("2021-12-12T12:00:00"));
        trip2.setRoute(route1);
        trip2.setTrip_type("IDA/VOLTA");
        trip2.setAvailableSeats(30);
        trip3.setArrivalTime(LocalDateTime.parse("2021-12-12T12:00:00"));
        trip3.setDepartureTime(LocalDateTime.parse("2021-12-12T12:00:00"));
        trip3.setRoute(route2);
        trip3.setTrip_type("IDA");
        trip3.setAvailableSeats(0);
        trip4.setArrivalTime(LocalDateTime.parse("2021-12-12T12:00:00"));
        trip4.setDepartureTime(LocalDateTime.parse("2021-12-12T12:00:00"));
        trip4.setRoute(route2);
        trip4.setOrigin("Aveiro");
        trip4.setDestination("Lisboa");
        trip4.setTrip_type("IDA/VOLTA");
        trip4.setAvailableSeats(10);
        entityManager.persist(trip1);
        entityManager.persist(trip2);
        entityManager.persist(trip3);
        entityManager.persist(trip4);
        entityManager.flush();

    }

    @Test
    void whenFindById_thenReturnTrip() {
        Trip found = tripRepository.findById(trip1.getId()).orElse(null);
        assert found != null;
        assert(found.getId()).equals(trip1.getId());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Trip fromDb = tripRepository.findById(-11L).orElse(null);
        assert fromDb == null;
    }

    @Test
    void whenFindAll_thenReturnAllTrips() {
        assert tripRepository.findAll().size() == 4;
    }

    @Test
    void whenFindByRoute_thenReturnTrips() {
        assert tripRepository.findByRoute(route1).size() == 2;
    }

    @Test
    void whenFindByOriginAndDestination_thenReturnTrips() {
        assert tripRepository.findByOriginAndDestination("Aveiro", "Lisboa").size() == 1;
    }

    @Test
    void whenFindByAvailableSears_thenReturnTrips(){
        assert tripRepository.findByAvailableSeats(0).size() == 2;
    }

}
