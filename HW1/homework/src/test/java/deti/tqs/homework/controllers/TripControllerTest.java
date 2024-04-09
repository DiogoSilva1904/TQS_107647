package deti.tqs.homework.controllers;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collections;

import deti.tqs.homework.models.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import deti.tqs.homework.models.Trip;
import deti.tqs.homework.services.RouteService;
import deti.tqs.homework.services.TripService;


@WebMvcTest(TripController.class)
public class TripControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TripService tripService;

    @MockBean
    RouteService routeService;


    Trip trip;

    @BeforeEach
    void setUp() {
        Route route = new Route();
        trip = new Trip();
        trip.setId(1L);
        trip.setOrigin("Aveiro");
        trip.setDestination("Porto");
        //trip.setDepartureTime(LocalDateTime.parse("2024-04-05T10:00:00"));
        //trip.setArrivalTime(LocalDateTime.parse("2024-04-05T12:00:00"));
        trip.setPrice(10.00);
        trip.setRoute(route);
        when(tripService.getTripById(1L)).thenReturn(trip);
        when(tripService.getAllTrips()).thenReturn(Arrays.asList(trip));
    }

    @Test
    public void testGetTripsById() throws Exception {
        mockMvc.perform(get("/trips/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origin", is("Aveiro")))
                .andExpect(jsonPath("$.destination", is("Porto")))
                .andExpect(jsonPath("$.price", is(10.0)));
    }

    @Test
    public void testGetTrips() throws Exception {
        mockMvc.perform(get("/trips"))
                .andExpect(status().isOk())
                .andExpect(view().name("tripList"));
    }

    @Test
    public void testGetAllTrips() throws Exception {
        mockMvc.perform(get("/trips/all")).andExpectAll(
                status().isOk(),
                jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetTripByDestination() throws Exception {
        when(tripService.getTripsByDestination("Porto"))
                .thenReturn(Collections.singletonList(trip));
        mockMvc.perform(get("/trips/search")
                        .param("to", "Porto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetTripsWithDepartureTime() throws Exception {
        when(tripService.getTripsByDepartureTime(LocalDateTime.parse("2024-04-05T10:00:00")))
                .thenReturn(Collections.singletonList(trip));
        mockMvc.perform(get("/trips/search")
                        .param("departureTime", "2024-04-05T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetTripsWithToAndDepartureTime() throws Exception {
        when(tripService.getAllTrips()).thenReturn(Collections.singletonList(trip));
        mockMvc.perform(get("/trips/search"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetTripsWithFromToAndDepartureTime() throws Exception {

        Trip trip1 = new Trip();
        trip1.setId(1L);
        trip1.setOrigin("Aveiro");
        trip1.setDestination("Porto");
        trip1.setDepartureTime(LocalDateTime.parse("2024-04-05T10:00:00"));
        trip1.setArrivalTime(LocalDateTime.parse("2024-04-05T10:00:00"));
        trip1.setPrice(10.00);
        trip1.setRoute(null);

        when(tripService.getTripsByOriginAndDestinationAndDepartureTime("Aveiro", "Porto", LocalDateTime.parse("2024-04-05T10:00:00")))
                .thenReturn(Arrays.asList(trip1));

        mockMvc.perform(get("/trips/search")
                        .param("from", "Aveiro")
                        .param("to", "Porto")
                        .param("departureTime", "2024-04-05T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetTripsWithFromAndTo() throws Exception {

        Trip trip1 = new Trip();
        trip1.setId(1L);
        trip1.setOrigin("Aveiro");
        trip1.setDestination("Porto");
        trip1.setDepartureTime(LocalDateTime.parse("2024-04-05T10:00:00"));
        trip1.setArrivalTime(LocalDateTime.parse("2024-04-05T10:00:00"));
        trip1.setPrice(10.00);
        trip1.setRoute(null);

        when(tripService.getTripsByOriginAndDestination("Aveiro", "Porto"))
                .thenReturn(Arrays.asList(trip1));

        mockMvc.perform(get("/trips/search")
                        .param("from", "Aveiro")
                        .param("to", "Porto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetTripsWithFrom() throws Exception {

        Trip trip1 = new Trip();
        trip1.setId(1L);
        trip1.setOrigin("Aveiro");
        trip1.setDestination("Porto");
        trip1.setDepartureTime(LocalDateTime.parse("2024-04-05T10:00:00"));
        trip1.setArrivalTime(LocalDateTime.parse("2024-04-05T10:00:00"));
        trip1.setPrice(10.00);
        trip1.setRoute(null);

        when(tripService.getTripsByOrigin("Aveiro"))
                .thenReturn(Arrays.asList(trip1));

        mockMvc.perform(get("/trips/search")
                        .param("from", "Aveiro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testSaveTrip() throws Exception {
        when(tripService.saveTrip(any(Trip.class))).thenReturn(trip);
        mockMvc.perform(post("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.toJson(trip)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.destination", is("Porto")));
    }
}
