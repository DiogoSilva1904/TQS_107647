package deti.tqs.homework.controllers;

import deti.tqs.homework.models.Trip;
import deti.tqs.homework.services.RouteService;
import deti.tqs.homework.services.TripService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/trip/{id}")
    public ModelAndView getTripById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trip", tripService.getTripById(id));
        //ordenar stops aqui(ver depois)
        modelAndView.setViewName("details");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripsById(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.getTripById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTripById(@PathVariable Long id) {
        tripService.deleteTripById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ModelAndView getAllTrips() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trips", tripService.getAllTrips());
        modelAndView.setViewName("tripList"); // assuming you have a "trips.html" Thymeleaf template
        return modelAndView;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Trip>> getTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Trip>> getTrips(
            @RequestParam(name = "from") Optional<String> from,
            @RequestParam(name = "to") Optional<String> to,
            @RequestParam(name = "departureTime") Optional<LocalDateTime> departureTime) {

        if (from.isPresent() && to.isPresent() && departureTime.isPresent()) {
            return ResponseEntity.ok(tripService.getTripsByOriginAndDestinationAndDepartureTime(from.get(), to.get(), departureTime.get()));
        } else if (from.isPresent() && to.isPresent()) {
            return ResponseEntity.ok(tripService.getTripsByOriginAndDestination(from.get(), to.get()));
        } else if (from.isPresent()) {
            return ResponseEntity.ok(tripService.getTripsByOrigin(from.get()));
        } else if (to.isPresent()) {
            return ResponseEntity.ok(tripService.getTripsByDestination(to.get()));
        } else if (departureTime.isPresent()) {
            return ResponseEntity.ok(tripService.getTripsByDepartureTime(departureTime.get()));
        } else {
            return ResponseEntity.ok(tripService.getAllTrips());
        }

    }




    }
