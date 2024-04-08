package deti.tqs.homework.services;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().getClass());

    public Reservation saveReservation(Reservation reservation) {
        logger.info("Saving reservation and checking if trip as any available seats");
        if (reservation.getTrip().getAvailableSeats()==0) {
            throw new IllegalArgumentException("No available seats for this trip");
        }
        else{
            return reservationRepository.save(reservation);
        }
    }

    public Reservation getReservationById(UUID id) {
        logger.info("Getting reservation by id");
        return reservationRepository.findById(id);
    }

    public List<Reservation> getAllReservations() {
        logger.info("Getting all reservations");
        return reservationRepository.findAll();
    }

}
