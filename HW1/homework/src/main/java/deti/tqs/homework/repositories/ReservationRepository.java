package deti.tqs.homework.repositories;

import deti.tqs.homework.models.Reservation;
import deti.tqs.homework.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findByName(String name);

    Reservation findByNif(int nif);

    Reservation findById(UUID id);

    List<Reservation> findByTrip(Trip trip);
}
