package deti.tqs.homework.services;

import deti.tqs.homework.models.Stop;
import deti.tqs.homework.models.Trip;
import org.springframework.stereotype.Service;

import deti.tqs.homework.models.Route;

import org.springframework.beans.factory.annotation.Autowired;

import deti.tqs.homework.repositories.RouteRepository;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    public Route getRouteById(Long id) {
        return routeRepository.findById(id).orElse(null);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public void deleteRouteById(Long id) {
        routeRepository.deleteById(id);
    }



}
