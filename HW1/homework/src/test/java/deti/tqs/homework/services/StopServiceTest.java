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
import deti.tqs.homework.repositories.RouteRepository;
import deti.tqs.homework.repositories.StopRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StopServiceTest {
    @Mock
    private StopRepository stopRepository;

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private StopService stopService;

    Stop stop1, stop2, stop3, stop4,stop5;
    Route route1, route2;

    @BeforeEach
    public void setUp() {
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
        stop3.setRoute(route1);
        stop4 = new Stop();
        stop4.setName("Porto");
        stop4.setStopOrder(2);
        stop4.setRoute(route2);
        stop5 = new Stop();
        stop5.setName("Faro");
        stop5.setStopOrder(1);
        stop5.setRoute(route2);
    }

    @Test
    public void whenGetStopsByRoute_thenReturnStops() {
        List<Stop> stops = new ArrayList<>();
        stops.add(stop1);
        stops.add(stop2);
        stops.add(stop3);
        when(stopRepository.findByRoute(route1)).thenReturn(stops);
        List<Stop> found = stopService.getStopsByRoute(route1);
        assertEquals(3, found.size());
        verify(stopRepository, times(1)).findByRoute(route1);
    }

    @Test
    public void whenGetAllStops_thenReturnAllStops() {
        List<Stop> stops = new ArrayList<>();
        stops.add(stop1);
        stops.add(stop2);
        stops.add(stop3);
        stops.add(stop4);
        stops.add(stop5);
        when(stopRepository.findAll()).thenReturn(stops);
        List<Stop> found = stopService.getAllStops();
        assertEquals(5, found.size());
        verify(stopRepository, times(1)).findAll();
    }

    @Test
    public void whenGetStopById_thenReturnStop() {
        when(stopRepository.findById(anyLong())).thenReturn(Optional.of(stop1));
        Stop found = stopService.getStopById(1L);
        assertEquals(stop1, found);
        verify(stopRepository, times(1)).findById(1L);
    }

}

