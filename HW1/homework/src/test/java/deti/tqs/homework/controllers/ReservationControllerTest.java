package deti.tqs.homework.controllers;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.UUID;

import deti.tqs.homework.models.Trip;
import deti.tqs.homework.services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import deti.tqs.homework.models.Reservation;
import deti.tqs.homework.services.ReservationService;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {
    @Autowired
    MockMvc mockMvc;


    @MockBean
    ReservationService reservationService;

    @MockBean
    TripService tripService;

    @BeforeEach
    void setUp() {
        Trip trip = new Trip();
        trip.setId(1L);
        trip.setOrigin("Aveiro");
        trip.setDestination("Porto");
        Reservation reservation = new Reservation();
        reservation.setTrip(trip);
        reservation.setSeat("1A");
        reservation.setName("John Doe");
        reservation.setEmail("diogo@gmail.com");
        reservation.setNif(123456789);
        Reservation reservation2 = new Reservation();
        reservation2.setTrip(trip);
        reservation2.setSeat("2B");
        reservation2.setName("Jane Doe");
        reservation2.setEmail("fjgorj@gmail.com");
        reservation2.setNif(987654321);
        when(reservationService.getReservationById(UUID.randomUUID())).thenReturn(reservation);
        when(reservationService.getAllReservations()).thenReturn(Arrays.asList(reservation, reservation2));
    }

    @Test
    @Disabled
    public void testGetReservationById() throws Exception {
        mockMvc.perform(get("/reservations/"+UUID.randomUUID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.seat", is("1A")))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("diogo@gmail.com")))
                .andExpect(jsonPath("$.nif", is(123456789)));
    }

    @Test
    public void testGetAllReservations() throws Exception {
        mockMvc.perform(get("/reservations")).andExpectAll(
                status().isOk(),
                jsonPath("$", hasSize(2)));
    }



}
