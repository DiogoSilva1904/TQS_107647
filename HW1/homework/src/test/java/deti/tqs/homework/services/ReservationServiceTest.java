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

import deti.tqs.homework.models.Reservation;
import deti.tqs.homework.models.Trip;
import deti.tqs.homework.repositories.ReservationRepository;
import deti.tqs.homework.repositories.RouteRepository;
import deti.tqs.homework.repositories.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private ReservationService reservationService;

    Reservation reservation1, reservation2, reservation3;
    Trip trip1, trip2;
    @BeforeEach
    public void setUp() {
        trip1 = new Trip();
        trip1.setTrip_type("IDA");
        trip1.setAvailableSeats(0);
        trip2 = new Trip();
        trip2.setTrip_type("IDA/VOLTA");
        trip2.setAvailableSeats(10);
        reservation1 = new Reservation();
        reservation1.setName("Joao");
        reservation1.setNif(123456789);
        reservation1.setSeat("1A");
        reservation1.setEmail("joao@gmail.com");
        reservation2 = new Reservation();
        reservation2.setName("Maria");
        reservation2.setNif(987654321);
        reservation2.setSeat("2B");
        reservation2.setEmail("maria@gmail.com");
        reservation2.setTrip(trip2);
        reservation3 = new Reservation();
        reservation3.setName("Diogo");
        reservation3.setNif(123456799);
        reservation3.setSeat("3C");
        reservation3.setEmail("diogo@gmail.com");
        reservation3.setTrip(trip1);
    }

    @Test
    public void whenValidReservation_thenReservationShouldBeSaved() {
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation2);
        Reservation savedReservation = reservationService.saveReservation(reservation2);
        assertEquals(reservation2, savedReservation);
    }

    @Test
    public void whenValidReservation_thenReservationShouldBeFoundById() {
        when(reservationRepository.findById(any(UUID.class))).thenReturn(reservation2);
        Reservation found = reservationService.getReservationById(UUID.randomUUID());
        assertEquals(reservation2, found);
    }

    @Test
    public void whenNoSeatsAvailable_thenReservationShouldNotBeSaved() {
        reservation1.setTrip(trip1);
        assertThrows(IllegalArgumentException.class, () -> reservationService.saveReservation(reservation1));
    }

}
