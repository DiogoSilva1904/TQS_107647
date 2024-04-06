package deti.tqs.homework.services;

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

    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    public void deleteTripById(Long id) {
        tripRepository.deleteById(id);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public List<Trip> getTripsByOriginAndDestination(String origin, String destination) {
        return tripRepository.findByOriginAndDestination(origin, destination);
    }

    public List<Trip> getTripsByOrigin(String origin) {
        return tripRepository.findByOrigin(origin);
    }

    public List<Trip> getTripsByDestination(String destination) {
        return tripRepository.findByDestination(destination);
    }

    public List<Trip> getTripsByOriginAndDestinationAndDepartureTime(String origin, String destination, LocalDateTime departureTime) {
        return tripRepository.findByOriginAndDestinationAndDepartureTime(origin, destination, departureTime);
    }

    public List<Trip> getTripsByOriginAndDepartureTime(String origin, LocalDateTime departureTime) {
        return tripRepository.findByOriginAndDepartureTime(origin, departureTime);
    }

    public List<Trip> getTripsByDestinationAndDepartureTime(String destination, LocalDateTime departureTime) {
        return tripRepository.findByDestinationAndDepartureTime(destination, departureTime);
    }

    public List<Trip> getTripsByDepartureTime(LocalDateTime departureTime) {
        return tripRepository.findByDepartureTime(departureTime);
    }


}
