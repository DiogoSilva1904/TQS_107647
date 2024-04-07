package deti.tqs.homework.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import deti.tqs.homework.models.Route;
import deti.tqs.homework.models.Stop;
import deti.tqs.homework.repositories.RouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import deti.tqs.homework.repositories.TripRepository;
import deti.tqs.homework.models.Trip;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    @Mock
    private TripRepository tripRepository;

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private TripService tripService;


    Trip trip1,trip2,trip3;
    Route route1,route2;

    @BeforeEach
    public void setUp() {
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
        route1.setStops(Arrays.asList(stop1, stop2, stop3));
        route2.setStops(Arrays.asList(stop6, stop4, stop5));
        trip1.setRoute(route1);
        trip1.setDepartureTime(LocalDateTime.parse("2024-05-06T12:30:00"));
        trip1.setTrip_type("IDA");
        trip1.setOrigin("Aveiro");
        trip1.setDestination("Lisboa");
        trip1.setAvailableSeats(0);
        trip2.setRoute(route1);
        trip2.setDepartureTime(LocalDateTime.parse("2024-05-06T08:00:00"));
        trip2.setTrip_type("IDA/VOLTA");
        trip2.setOrigin("Aveiro");
        trip2.setDestination("Porto");
        trip2.setAvailableSeats(15);
        trip3.setRoute(route2);
        trip3.setDepartureTime(LocalDateTime.parse("2024-05-09T12:00:00"));
        trip3.setTrip_type("IDA");
        trip3.setOrigin("Braga");
        trip3.setDestination("Porto");
        trip3.setAvailableSeats(0);

    }

    @Test
    public void testSaveTrip() {
        when(tripRepository.save(any(Trip.class))).thenReturn(trip1);
        Trip savedTrip = tripService.saveTrip(trip1);
        assertEquals(trip1, savedTrip);
    }

    @Test
    public void testGetTripById() {
        when(tripRepository.findById(anyLong())).thenReturn(Optional.of(trip1));
        Trip foundTrip = tripService.getTripById(1L);
        assertEquals(trip1, foundTrip);
    }

    @Test
    public void testGetAllTrips() {
        List<Trip> trips = Arrays.asList(trip1, trip2, trip3);
        when(tripRepository.findAll()).thenReturn(trips);
        List<Trip> foundTrips = tripService.getAllTrips();
        assertEquals(trips, foundTrips);
    }

    @Test
    public void testGetTripsByOriginAndDestination() {
        List<Trip> trips = Collections.singletonList(trip1);
        when(tripRepository.findByOriginAndDestination(anyString(), anyString())).thenReturn(trips);
        List<Trip> foundTrips = tripService.getTripsByOriginAndDestination("Aveiro", "Lisboa");
        assertEquals(trips, foundTrips);
    }

    @Test
    public void testGetTripsByOrigin() {
        List<Trip> trips = Arrays.asList(trip1, trip2);
        when(tripRepository.findByOrigin(anyString())).thenReturn(trips);
        List<Trip> foundTrips = tripService.getTripsByOrigin("Aveiro");
        assertEquals(trips, foundTrips);
    }

    @Test
    public void testGetTripsByDestination() {
        List<Trip> trips = Arrays.asList(trip2, trip3);
        when(tripRepository.findByDestination(anyString())).thenReturn(trips);
        List<Trip> foundTrips = tripService.getTripsByDestination("Porto");
        assertEquals(trips, foundTrips);
    }

    @Test
    public void testGetTripsByAvailableSeats(){
        List<Trip> trips = Arrays.asList(trip1, trip3);
        when(tripRepository.findByAvailableSeats(anyInt())).thenReturn(trips);
        List<Trip> foundTrips = tripService.getTripsByAvailableSeats(0);
        assertEquals(trips, foundTrips);
    }

}
