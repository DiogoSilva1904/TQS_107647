package deti.tqs.homework.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import deti.tqs.homework.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deti.tqs.homework.models.Trip;


@Repository
public interface TripRepository extends JpaRepository<Trip, Long>{

    List<Trip> findByRoute(Route route);

    List<Trip> findByOriginAndDestination(String origin, String destination);

    List<Trip> findByOrigin(String origin);

    List<Trip> findByDestination(String destination);

    List<Trip> findByOriginAndDestinationAndDepartureTime(String origin, String destination, LocalDateTime departureTime);

    List<Trip> findByOriginAndDepartureTime(String origin, LocalDateTime departureTime);

    List<Trip> findByDestinationAndDepartureTime(String destination, LocalDateTime departureTime);

    List<Trip> findByDepartureTime(LocalDateTime departureTime);

    List<Trip> findByAvailableSeats(int availableSeats);
}
