package deti.tqs.homework.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import deti.tqs.homework.models.Route;
import deti.tqs.homework.models.Trip;

@DataJpaTest
public class TripRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TripRepository tripRepository;

    Route route1;
    Trip trip1;

    @BeforeEach
    public void setUp() {
        route1 = new Route();
        route1.setDepartureTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(0), ZoneOffset.UTC));
        route1.setArrivalTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(3600), ZoneOffset.UTC));
        entityManager.persist(route1);
        trip1 = new Trip();
        trip1.setRoute(route1);
        trip1.setDepartureTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(0), ZoneOffset.UTC));
        trip1.setArrivalTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(3600), ZoneOffset.UTC));
        trip1.setPrice(100L);
        trip1.setAvailable_seats(10);
        trip1.setBus_number("bus1");
        trip1.setTrip_type("type1");
        entityManager.persist(trip1);
    }

    @Test
    public void whenFindById_thenReturnTrip() {
        Trip trip = tripRepository.findById(1L).orElse(null);
        assertEquals(1L, trip.getId());
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochSecond(0), ZoneOffset.UTC), trip.getDepartureTime());
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochSecond(3600), ZoneOffset.UTC), trip.getArrivalTime());
        assertEquals(100L, trip.getPrice());
        assertEquals(10, trip.getAvailable_seats());
        assertEquals("bus1", trip.getBus_number());
        assertEquals("type1", trip.getTrip_type());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Trip trip = tripRepository.findById(5L).orElse(null);
        assertEquals(null, trip);
    }

    @Test
    public void findTripsRoute1(){
        List<Trip> trips = tripRepository.findByRoute(route1);
        assertEquals(1, trips.size());
        Trip trip = trips.get(0);
        assertEquals(3L, trip.getId());
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochSecond(0), ZoneOffset.UTC), trip.getDepartureTime());
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochSecond(3600), ZoneOffset.UTC), trip.getArrivalTime());
        assertEquals(100L, trip.getPrice());
        assertEquals(10, trip.getAvailable_seats());
        assertEquals("bus1", trip.getBus_number());
        assertEquals("type1", trip.getTrip_type());
    }
}
