package deti.tqs.homework.controllers;

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
@RequestMapping("/stop")
public class StopController {

            private final StopService stopService;

            private final RouteService routeService;

            @PostMapping
            public ResponseEntity<Stop> saveStop(@RequestBody Stop stop) {
                Route route = routeService.getRouteById(stop.getRoute().getId());
                stop.setRoute(route);
                return ResponseEntity.status(HttpStatus.CREATED).body(stopService.saveStop(stop));
            }

            @GetMapping("/{id}")
            public ResponseEntity<Stop> getStopById(@PathVariable Long id) {
                Stop stop = stopService.getStopById(id);
                return stop != null ? ResponseEntity.ok(stop) : ResponseEntity.notFound().build();
            }

            @DeleteMapping("/{id}")
            public ResponseEntity<Void> deleteStopById(@PathVariable Long id) {
                stopService.deleteStopById(id);
                return ResponseEntity.noContent().build();
            }

            @GetMapping
            public ResponseEntity<List<Stop>> getAllStops() {
                return ResponseEntity.ok(stopService.getAllStops());
            }
}
