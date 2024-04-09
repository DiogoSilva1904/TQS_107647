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

import deti.tqs.homework.models.Trip;
import deti.tqs.homework.services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import deti.tqs.homework.models.Reservation;
import deti.tqs.homework.services.ReservationService;
import deti.tqs.homework.controllers.TestUtils;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {
    @Autowired
    MockMvc mockMvc;


    @MockBean
    ReservationService reservationService;

    @MockBean
    TripService tripService;

    Trip trip,trip1;
    Reservation reservation;

    @BeforeEach
    void setUp() {
        trip = new Trip();
        trip.setOrigin("Braga");
        trip.setDestination("Porto");
        trip.setAvailableSeats(0);
        trip.setTrip_type("IDA/VOLTA");
        trip1 = new Trip();
        trip1.setOrigin("Aveiro");
        trip1.setDestination("Porto");
        trip1.setAvailableSeats(10);
        trip1.setTrip_type("IDA");
        reservation = new Reservation();
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
    public void testGetReservationById() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setSeat("1A");
        reservation.setName("Rafael");
        reservation.setEmail("rafa@gmail.com");
        reservation.setNif(123456789);
        UUID id = UUID.randomUUID();
        reservation.setId(id);
        when(reservationService.getReservationById(id)).thenReturn(reservation);
        mockMvc.perform(get("/reservations/"+ id))
                .andExpect(jsonPath("$.id", is(id.toString())))
                .andExpect(jsonPath("$.seat", is("1A")))
                .andExpect(jsonPath("$.name", is("Rafael")))
                .andExpect(jsonPath("$.email", is("rafa@gmail.com")))
                .andExpect(jsonPath("$.nif", is(123456789)));
    }

    @Test
    public void testGetAllReservations() throws Exception {
        mockMvc.perform(get("/reservations")).andExpectAll(
                status().isOk(),
                jsonPath("$", hasSize(2)));
    }

    @Test
    public void testSaveReservation_withNoAvailableSeats() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setSeat("1A");
        reservation.setName("Rafa");
        reservation.setEmail("rafa@gmail.com");
        reservation.setNif(123456789);
        reservation.setTrip(trip);
        when(reservationService.saveReservation(any(Reservation.class))).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(post("/reservations/save").contentType(MediaType.APPLICATION_JSON).content(TestUtils.toJson(reservation)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSaveReservation_withAvailableSeats() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setSeat("1A");
        reservation.setName("Rafa");
        reservation.setEmail("rafa@gmail.com");
        reservation.setNif(123456789);
        reservation.setTrip(trip1);
        when(reservationService.saveReservation(any(Reservation.class))).thenReturn(reservation);
        mockMvc.perform(post("/reservations/save").contentType(MediaType.APPLICATION_JSON).content(TestUtils.toJson(reservation)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.seat", is("1A")))
                .andExpect(jsonPath("$.name", is("Rafa")))
                .andExpect(jsonPath("$.email", is("rafa@gmail.com")));

    }

    @Test
    public void testSaveReservation() throws Exception {
        when(reservationService.saveReservation(any(Reservation.class))).thenReturn(reservation);
        mockMvc.perform(post("/reservations/save").contentType(MediaType.APPLICATION_JSON).content(TestUtils.toJson(reservation)))
                .andExpect(status().isCreated());
    }

}
