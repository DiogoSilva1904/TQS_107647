package deti.tqs.homework.repositories;

import java.util.List;
import java.util.Optional;

import deti.tqs.homework.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deti.tqs.homework.models.Trip;


@Repository
public interface TripRepository extends JpaRepository<Trip, Long>{

    List<Trip> findByRoute(Route route);

}
