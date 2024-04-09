package deti.tqs.homework.IT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

import deti.tqs.homework.models.Reservation;
import deti.tqs.homework.models.Trip;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:app_it.properties")
public class ReservationControllerIT {

            @LocalServerPort
            int randomServerPort;

            @Autowired
            private TestRestTemplate restTemplate;

            @Test
            public void testGetReservations() {
                ResponseEntity<List<Reservation>> response = restTemplate.exchange("http://localhost:" + randomServerPort +"/reservations",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Reservation>>() {
                        });

                assertEquals(HttpStatus.OK, response.getStatusCode());
            }

            @Test
            public void testGetReservationById() {
                UUID id = UUID.fromString("03db8c2d-01c5-4abd-865a-9485b08256e0");
                ResponseEntity<Reservation> response = restTemplate.exchange(String.format("http://localhost:%s/reservations/%s", randomServerPort, id),
                        HttpMethod.GET, null, Reservation.class);

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(id, response.getBody().getId());
            }
}
