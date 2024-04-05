package deti.tqs.homework.controllers;

import deti.tqs.homework.models.Route;
import deti.tqs.homework.services.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/routes")
public class RouteController {

        private final RouteService routeService;

        @PostMapping
        public ResponseEntity<Route> saveRoute(@RequestBody Route route) {
            return ResponseEntity.status(HttpStatus.CREATED).body(routeService.saveRoute(route));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
            Route route = routeService.getRouteById(id);
            return route != null ? ResponseEntity.ok(route) : ResponseEntity.notFound().build();
        }

        @GetMapping
        public ResponseEntity<List<Route>> getAllRoutes() {
            return ResponseEntity.ok(routeService.getAllRoutes());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteRouteById(@PathVariable Long id) {
            routeService.deleteRouteById(id);
            return ResponseEntity.noContent().build();
        }
}
