package deti.tqs.homework.controllers;

import deti.tqs.homework.models.Trip;
import deti.tqs.homework.services.RouteService;
import deti.tqs.homework.services.TripService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/trips")
public class TripController {

        private final TripService tripService;

        private final RouteService routeService;


        @PostMapping
        public ResponseEntity<Trip> saveTrip(@RequestBody Trip trip) {
            trip.setRoute(routeService.getRouteById(trip.getRoute().getId()));
            return ResponseEntity.status(HttpStatus.CREATED).body(tripService.saveTrip(trip));

        }

        @GetMapping("/{id}")
        public ModelAndView getTripById(@PathVariable Long id) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("trip", tripService.getTripById(id));
            //ordenar stops aqui(ver depois)
            modelAndView.setViewName("details");
            return modelAndView;
        }

        @GetMapping
        public ModelAndView getAllTrips() {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("trips", tripService.getAllTrips());
            modelAndView.setViewName("tripList"); // assuming you have a "trips.html" Thymeleaf template
            return modelAndView;
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTripById(@PathVariable Long id) {
            tripService.deleteTripById(id);
            return ResponseEntity.noContent().build();
        }
}
