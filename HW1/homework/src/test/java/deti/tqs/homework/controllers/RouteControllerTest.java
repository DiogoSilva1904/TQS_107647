package deti.tqs.homework.controllers;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.UUID;

import deti.tqs.homework.models.Stop;
import deti.tqs.homework.models.Route;
import deti.tqs.homework.models.Trip;
import deti.tqs.homework.services.RouteService;
import deti.tqs.homework.services.StopService;
import deti.tqs.homework.services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import deti.tqs.homework.controllers.TestUtils;

@WebMvcTest(RouteController.class)
public class RouteControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    RouteService routeService;

    @MockBean
    TripService tripService;

    @MockBean
    StopService stopService;

    Route route1,route2;
    Trip trip1,trip2;
    Stop stop1,stop2,stop3,stop4,stop5,stop6,stop7,stop8,stop9,stop10;

    @BeforeEach
    void setUp() {
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
        stop4.setStopOrder(4);
        stop5 = new Stop();
        stop5.setName("Braga");
        stop5.setStopOrder(5);
        stop6 = new Stop();
        stop6.setName("Faro");
        stop6.setStopOrder(1);
        stop7 = new Stop();
        stop7.setName("Viseu");
        stop7.setStopOrder(2);
        stop8 = new Stop();
        stop8.setName("Guarda");
        stop8.setStopOrder(3);
        stop9 = new Stop();
        stop9.setName("Vila Real");
        stop9.setStopOrder(4);
        stop10 = new Stop();
        stop10.setName("Viana do Castelo");
        stop10.setStopOrder(5);
        trip1 = new Trip();
        trip1.setTrip_type("IDA");
        trip2 = new Trip();
        trip2.setTrip_type("IDA/VOLTA");
        route1 = new Route();
        route1.setStops(Arrays.asList(stop1, stop2, stop3, stop4, stop5));
        route1.setTrips(Arrays.asList(trip1, trip2));
        route2 = new Route();
        route2.setStops(Arrays.asList(stop6, stop7, stop8, stop9, stop10));
        route2.setTrips(Arrays.asList(trip1, trip2));
    }
    
    @Test
    void whenGetAllRoutes_thenReturnAllRoutes() throws Exception {
        when(routeService.getAllRoutes()).thenReturn(Arrays.asList(route1, route2));

        mockMvc.perform(get("/routes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void whenGetRouteById_thenReturnRoute() throws Exception {
        when(routeService.getRouteById(1L)).thenReturn(route1);

        mockMvc.perform(get("/routes/1"))
                .andExpect(status().isOk());
    }

}
