package deti.tqs.homework.services;

import org.springframework.stereotype.Service;

import deti.tqs.homework.models.Reservation;

import org.springframework.beans.factory.annotation.Autowired;

import deti.tqs.homework.repositories.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }
}
