package deti.tqs.homework.repositories;

import java.util.List;
import java.util.Optional;

import deti.tqs.homework.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deti.tqs.homework.models.Stop;

@Repository
public interface StopRepository extends JpaRepository<Stop, Long>{
    List<Stop> findByName(String name);

    List<Stop> findByRoute(Route route);
}
