package deti.tqs.homework.services;

import org.springframework.stereotype.Service;

import deti.tqs.homework.models.Stop;

import org.springframework.beans.factory.annotation.Autowired;

import deti.tqs.homework.repositories.StopRepository;

import java.util.List;

@Service
public class StopService {
    @Autowired
    private StopRepository stopRepository;

    public Stop saveStop(Stop stop) {
        return stopRepository.save(stop);
    }

    public Stop getStopById(Long id) {
        return stopRepository.findById(id).orElse(null);
    }

    public void deleteStopById(Long id) {
        stopRepository.deleteById(id);
    }

    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }
}
