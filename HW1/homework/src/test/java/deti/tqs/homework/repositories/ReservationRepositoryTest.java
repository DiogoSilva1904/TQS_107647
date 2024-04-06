package deti.tqs.homework.repositories;

import deti.tqs.homework.models.Reservation;
import deti.tqs.homework.models.Route;
import deti.tqs.homework.models.Stop;
import deti.tqs.homework.models.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class ReservationRepositoryTest {

        @Autowired
        TestEntityManager entityManager;

        @Autowired
        ReservationRepository reservationRepository;

        @Autowired
        TripRepository tripRepository;

        Reservation reservation1, reservation2, reservation3;
        Trip trip1, trip2;

        @BeforeEach
        void setUp(){
            reservation1 = new Reservation();
            reservation2 = new Reservation();
            reservation3 = new Reservation();
            trip1 = new Trip();
            trip1.setTrip_type("IDA");
            trip2 = new Trip();
            trip2.setTrip_type("IDA/VOLTA");
            entityManager.persistAndFlush(trip1);
            entityManager.persistAndFlush(trip2);
            reservation1.setName("Joao");
            reservation1.setNif(123456789);
            reservation1.setTrip(trip1);
            reservation2.setName("Maria");
            reservation2.setNif(987654321);
            reservation2.setTrip(trip2);
            reservation3.setName("Diogo");
            reservation3.setNif(123456799);
            reservation3.setTrip(trip1);
            entityManager.persistAndFlush(reservation1);
            entityManager.persistAndFlush(reservation2);
            entityManager.persistAndFlush(reservation3);
        }

        @Test
        public void whenFindByName_thenReturnReservation(){
            Reservation found = reservationRepository.findByName(reservation1.getName());
            assert(found.getName()).equals(reservation1.getName());
        }

        @Test
        public void whenFindByNif_thenReturnReservation(){
            Reservation found = reservationRepository.findByNif(reservation2.getNif());
            assert(found.getNif()).equals(reservation2.getNif());
        }

        @Test
        public void whenFindByTrip_thenReturnReservation(){
            List<Reservation> found = reservationRepository.findByTrip(trip1);
            assert(found.get(0).getTrip()).equals(trip1);
        }

        @Test
        public void whenFindById_thenReturnReservation() {
            Reservation found = reservationRepository.findById(reservation1.getId());
            assert (found.getId()).equals(reservation1.getId());
        }

}
