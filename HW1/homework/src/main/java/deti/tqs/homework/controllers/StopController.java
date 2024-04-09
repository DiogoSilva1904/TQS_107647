package deti.tqs.homework.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import deti.tqs.homework.models.Route;
import deti.tqs.homework.models.Stop;
import deti.tqs.homework.services.RouteService;
import deti.tqs.homework.services.StopService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/stops")
@Tag(name = "Stop API", description = "Endpoints for managing stops")
public class StopController {

            private final StopService stopService;

            private final RouteService routeService;

            private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().getClass());

            @PostMapping
            @Operation(summary = "Save a stop", description = "Save a stop")
            public ResponseEntity<Stop> saveStop(@RequestBody Stop stop) {
                logger.info("Saving stop");
                Route route = routeService.getRouteById(stop.getRoute().getId());
                stop.setRoute(route);
                return ResponseEntity.status(HttpStatus.CREATED).body(stopService.saveStop(stop));
            }

            @GetMapping("/{id}")
            @Operation(summary = "Get a stop by id", description = "Get a stop by id")
            public ResponseEntity<Stop> getStopById(@PathVariable Long id) {
                logger.info("Getting stop by id");
                Stop stop = stopService.getStopById(id);
                return stop != null ? ResponseEntity.ok(stop) : ResponseEntity.notFound().build();
            }


            @GetMapping
            @Operation(summary = "Get all stops", description = "Get all stops")
            public ResponseEntity<List<Stop>> getAllStops() {
                logger.info("Getting all stops");
                return ResponseEntity.ok(stopService.getAllStops());
            }
}
