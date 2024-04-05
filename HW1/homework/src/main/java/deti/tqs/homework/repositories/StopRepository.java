package deti.tqs.homework.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deti.tqs.homework.models.Stop;

@Repository
public interface StopRepository extends JpaRepository<Stop, Long>{
}
