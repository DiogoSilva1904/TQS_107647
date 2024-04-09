package deti.tqs.homework.services;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import deti.tqs.homework.models.Route;
import org.springframework.stereotype.Service;

import deti.tqs.homework.models.Stop;

import org.springframework.beans.factory.annotation.Autowired;

import deti.tqs.homework.repositories.StopRepository;

import java.util.List;

@Service
public class StopService {
    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private RouteService routeRepository;

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().getClass());

    public Stop saveStop(Stop stop) {
        logger.info("Saving stop");
        return stopRepository.save(stop);
    }

    public Stop getStopById(Long id) {
        logger.info("Getting stop by id");
        return stopRepository.findById(id).orElse(null);
    }

    public void deleteStopById(Long id) {
        logger.info("Deleting stop by id");
        stopRepository.deleteById(id);
    }

    public List<Stop> getAllStops() {
        logger.info("Getting all stops");
        return stopRepository.findAll();
    }

    public List<Stop> getStopsByRoute(Route route) {
        logger.info("Getting stops by route");
        return stopRepository.findByRoute(route);
    }
}
