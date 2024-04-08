package deti.tqs.homework.controllers;

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
public class StopController {

            private final StopService stopService;

            private final RouteService routeService;

            private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().getClass());

            @PostMapping
            public ResponseEntity<Stop> saveStop(@RequestBody Stop stop) {
                logger.info("Saving stop");
                Route route = routeService.getRouteById(stop.getRoute().getId());
                stop.setRoute(route);
                return ResponseEntity.status(HttpStatus.CREATED).body(stopService.saveStop(stop));
            }

            @GetMapping("/{id}")
            public ResponseEntity<Stop> getStopById(@PathVariable Long id) {
                logger.info("Getting stop by id");
                Stop stop = stopService.getStopById(id);
                return stop != null ? ResponseEntity.ok(stop) : ResponseEntity.notFound().build();
            }


            @DeleteMapping("/{id}")
            public ResponseEntity<Void> deleteStopById(@PathVariable Long id) {
                logger.info("Deleting stop by id");
                stopService.deleteStopById(id);
                return ResponseEntity.noContent().build();
            }

            @GetMapping
            public ResponseEntity<List<Stop>> getAllStops() {
                logger.info("Getting all stops");
                return ResponseEntity.ok(stopService.getAllStops());
            }
}
