package deti.tqs.homework.services;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import deti.tqs.homework.models.Trip;

import org.springframework.beans.factory.annotation.Autowired;

import deti.tqs.homework.repositories.TripRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().getClass());

    public Trip saveTrip(Trip trip) {
        logger.info("Saving trip");
        return tripRepository.save(trip);
    }

    public Trip getTripById(Long id) {
        logger.info("Getting trip by id");
        return tripRepository.findById(id).orElse(null);
    }

    public void deleteTripById(Long id) {
        logger.info("Deleting trip by id");
        tripRepository.deleteById(id);
    }

    public List<Trip> getAllTrips() {
        logger.info("Getting all trips");
        return tripRepository.findAll();
    }

    public List<Trip> getTripsByOriginAndDestination(String origin, String destination) {
        logger.info("Getting trips by origin and destination");
        return tripRepository.findByOriginAndDestination(origin, destination);
    }

    public List<Trip> getTripsByOrigin(String origin) {
        logger.info("Getting trips by origin");
        return tripRepository.findByOrigin(origin);
    }

    public List<Trip> getTripsByDestination(String destination) {
        logger.info("Getting trips by destination");
        return tripRepository.findByDestination(destination);
    }

    public List<Trip> getTripsByOriginAndDestinationAndDepartureTime(String origin, String destination, LocalDateTime departureTime) {
        logger.info("Getting trips by origin, destination and departure time");
        return tripRepository.findByOriginAndDestinationAndDepartureTime(origin, destination, departureTime);
    }

    public List<Trip> getTripsByDepartureTime(LocalDateTime departureTime) {
        logger.info("Getting trips by departure time");
        return tripRepository.findByDepartureTime(departureTime);
    }

    public List<Trip> getTripsByAvailableSeats(int availableSeats) {
        logger.info("Getting trips by available seats");
        return tripRepository.findByAvailableSeats(availableSeats);
    }


}
