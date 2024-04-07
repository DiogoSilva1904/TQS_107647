package deti.tqs.homework.services;

import org.springframework.stereotype.Service;

import deti.tqs.homework.models.Reservation;

import org.springframework.beans.factory.annotation.Autowired;

import deti.tqs.homework.repositories.ReservationRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation saveReservation(Reservation reservation) {
        if (reservation.getTrip().getAvailableSeats()==0) {
            throw new IllegalArgumentException("No available seats for this trip");
        }
        else{
            return reservationRepository.save(reservation);
        }
    }

    public Reservation getReservationById(UUID id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

}
