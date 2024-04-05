package deti.tqs.homework.services;

import org.springframework.stereotype.Service;

import deti.tqs.homework.models.Trip;

import org.springframework.beans.factory.annotation.Autowired;

import deti.tqs.homework.repositories.TripRepository;

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
}
