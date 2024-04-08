package deti.tqs.homework.services;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import deti.tqs.homework.models.Route;

import org.springframework.beans.factory.annotation.Autowired;

import deti.tqs.homework.repositories.RouteRepository;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().getClass());

    public Route saveRoute(Route route) {
        logger.info("Saving route");
        return routeRepository.save(route);
    }

    public Route getRouteById(Long id) {
        logger.info("Getting route by id");
        return routeRepository.findById(id).orElse(null);
    }

    public List<Route> getAllRoutes() {
        logger.info("Getting all routes");
        return routeRepository.findAll();
    }

    public void deleteRouteById(Long id) {
        logger.info("Deleting route by id");
        routeRepository.deleteById(id);
    }



}
