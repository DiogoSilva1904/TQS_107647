package deti.tqs.homework.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.lang.invoke.MethodHandles;

import deti.tqs.homework.models.Route;
import deti.tqs.homework.models.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import deti.tqs.homework.models.Trip;
import deti.tqs.homework.services.RouteService;
import deti.tqs.homework.services.TripService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/trips")
@Tag(name = "Trip API", description = "Endpoints for managing trips")
public class TripController {

    private final TripService tripService;

    private final RouteService routeService;

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().getClass());


    @PostMapping
    @Operation(summary = "Save a trip", description = "Save a trip")
    public ResponseEntity<Trip> saveTrip(@RequestBody Trip trip) {
        logger.info("Saving trip");
        trip.setRoute(routeService.getRouteById(trip.getRoute().getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(tripService.saveTrip(trip));

    }

    @GetMapping("/trip/{id}")
    @Operation(summary = "Get a trip by id", description = "Get a trip by id and load details page of the trip")
    public ModelAndView getTripById(@PathVariable Long id) {
        logger.info("Getting trip by id and showing the page with the details of the trip");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trip", tripService.getTripById(id));
        Trip trip = tripService.getTripById(id);
        Route route = trip.getRoute();
        List<Stop> stops = route.getStops();
        List<Stop> orderedStops = stops.stream()
                .sorted(Comparator.comparingInt(Stop::getStopOrder))
                .toList();
        modelAndView.addObject("stops", orderedStops);
        modelAndView.setViewName("details");
        return modelAndView;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a trip by id", description = "Get a trip by id")
    public ResponseEntity<Trip> getTripsById(@PathVariable Long id) {
        logger.info("Getting trip by id");
        return ResponseEntity.ok(tripService.getTripById(id));
    }

    @GetMapping
    @Operation(summary = "Get all trips", description = "Get all trips and load page with all trips")
    public ModelAndView getAllTrips() {
        logger.info("Getting all trips and showing page with all trips");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trips", tripService.getAllTrips());
        modelAndView.setViewName("tripList");
        return modelAndView;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all trips", description = "Get all trips")
    public ResponseEntity<List<Trip>> getTrips() {
        logger.info("Getting all trips");
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @GetMapping("/search")
    @Operation(summary = "Get trips by different parameters", description = "Get trips by origin, destination and departure time")
    public ResponseEntity<List<Trip>> getTrips(
            @RequestParam(name = "from") Optional<String> from,
            @RequestParam(name = "to") Optional<String> to,
            @RequestParam(name = "departureTime") Optional<LocalDateTime> departureTime) {
        logger.info("Getting trips by origin, destination and departure time");
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
