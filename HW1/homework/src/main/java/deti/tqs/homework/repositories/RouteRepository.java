package deti.tqs.homework.repositories;

import java.util.List;
import java.util.Optional;

import deti.tqs.homework.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deti.tqs.homework.models.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>{
}
