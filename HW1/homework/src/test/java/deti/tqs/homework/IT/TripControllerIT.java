package deti.tqs.homework.IT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

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
public class TripControllerIT {

        @LocalServerPort
        int randomServerPort;

        @Autowired
        private TestRestTemplate restTemplate;



        @Test
        public void testGetTrips() {
            ResponseEntity<List<Trip>> response = restTemplate.exchange("http://localhost:" + randomServerPort +"/trips/all",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Trip>>() {
                    });

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertFalse(response.getBody().isEmpty());
        }

       @Test
       public void testGetTripById() {
           ResponseEntity<Trip> response = restTemplate.getForEntity("http://localhost:"+ randomServerPort +"/trips/2", Trip.class);

           assertEquals(HttpStatus.OK, response.getStatusCode());
           assertEquals(2L, response.getBody().getId());
       }

       @Test
       public void testGetTripByIdNotFound() {
           ResponseEntity<Trip> response = restTemplate.getForEntity("http://localhost:"+ randomServerPort +"/trips/1100", Trip.class);
           assertNull(response.getBody());
       }

       @Test
       public void testGetTripByOrigin() {
           ResponseEntity<List<Trip>> response = restTemplate.exchange("http://localhost:"+randomServerPort +"/trips/search?from=Aveiro",
                   HttpMethod.GET, null, new ParameterizedTypeReference<List<Trip>>() {
                   });

           assertEquals(HttpStatus.OK, response.getStatusCode());
           assertTrue(response.getBody().stream().allMatch(t -> t.getOrigin().equals("Aveiro")));
       }

       @Test
       public void testGetTripByDestination() {
            ResponseEntity<List<Trip>> response = restTemplate.exchange("http://localhost:"+randomServerPort+"/trips/search?to=Destination City",
                 HttpMethod.GET, null, new ParameterizedTypeReference<List<Trip>>() {
                 });

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.getBody().stream().allMatch(t -> t.getDestination().equals("Destination City")));
       }

       @Test
       public void testGetTripByDepartureTime() {
           ResponseEntity<List<Trip>> response = restTemplate.exchange("http://localhost:"+randomServerPort+"/trips/search?departureTime=2024-04-05T08:00:00",
                   HttpMethod.GET, null, new ParameterizedTypeReference<List<Trip>>() {
                   });

           assertEquals(HttpStatus.OK, response.getStatusCode());
           assertTrue(response.getBody().stream().allMatch(t -> t.getDepartureTime().equals(LocalDateTime.parse("2024-04-05T08:00:00"))));
       }

       @Test
       public void testGetTripByOriginAndDestination() {
           ResponseEntity<List<Trip>> response = restTemplate.exchange("http://localhost:"+randomServerPort+"/trips/search?from=Aveiro&to=Destination City",
                   HttpMethod.GET, null, new ParameterizedTypeReference<List<Trip>>() {
                   });

           assertEquals(HttpStatus.OK, response.getStatusCode());
           assertTrue(response.getBody().stream().allMatch(t -> t.getOrigin().equals("Aveiro") && t.getDestination().equals("Destination City")));
       }


}
