package deti.tqs.homework.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;


import deti.tqs.homework.models.Stop;
import deti.tqs.homework.models.Route;
import deti.tqs.homework.models.Trip;
import deti.tqs.homework.repositories.RouteRepository;
import deti.tqs.homework.repositories.StopRepository;
import deti.tqs.homework.repositories.TripRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RouteServiceTest {
    @Mock
    RouteRepository routeRepository;

    @Mock
    StopRepository stopRepository;

    @Mock
    TripRepository tripRepository;

    @InjectMocks
    RouteService routeService;

    Route route1, route2;
    Stop stop1, stop2, stop3, stop4, stop5;
    Trip trip1, trip2,trip3;

    @BeforeEach
    public void setUp() {
        stop1 = new Stop();
        stop1.setName("Aveiro");
        stop1.setStopOrder(1);
        stop2 = new Stop();
        stop2.setName("Coimbra");
        stop2.setStopOrder(2);
        stop3 = new Stop();
        stop3.setName("Lisboa");
        stop3.setStopOrder(3);
        stop4 = new Stop();
        stop4.setName("Porto");
        stop4.setStopOrder(2);
        stop5 = new Stop();
        stop5.setName("Faro");
        stop5.setStopOrder(3);

        trip1 = new Trip();
        trip1.setTrip_type("IDA");
        trip1.setAvailableSeats(0);
        trip2 = new Trip();
        trip2.setTrip_type("IDA/VOLTA");
        trip2.setAvailableSeats(10);
        trip3 = new Trip();
        trip3.setTrip_type("IDA");
        trip3.setAvailableSeats(30);

        route1 = new Route();
        route1.setStops(Arrays.asList(stop1, stop2, stop3));
        route1.setTrips(Arrays.asList(trip1, trip2));
        route2 = new Route();
        route2.setStops(Arrays.asList(stop4, stop5));
        route2.setTrips(Collections.singletonList(trip3));
    }

    @Test
    public void whenGetRouteById_thenReturnRoute() {
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route1));
        Route found = routeService.getRouteById(1L);
        assertEquals(route1, found);
        verify(routeRepository, times(1)).findById(1L);
    }

    @Test
    public void whenGetAllRoutes_thenReturnAllRoutes() {
        List<Route> routes = new ArrayList<>();
        routes.add(route1);
        routes.add(route2);
        when(routeRepository.findAll()).thenReturn(routes);
        List<Route> found = routeService.getAllRoutes();
        assertEquals(2, found.size());
        verify(routeRepository, times(1)).findAll();
    }

    @Test
    public void whenSaveRoute_thenReturnRoute() {
        when(routeRepository.save(any(Route.class))).thenReturn(route1);
        Route savedRoute = routeService.saveRoute(route1);
        assertEquals(route1, savedRoute);
    }

}
