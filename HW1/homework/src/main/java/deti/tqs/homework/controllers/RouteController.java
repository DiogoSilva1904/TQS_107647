package deti.tqs.homework.controllers;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().getClass());

        @PostMapping
        public ResponseEntity<Route> saveRoute(@RequestBody Route route) {
            logger.info("Saving route");
            return ResponseEntity.status(HttpStatus.CREATED).body(routeService.saveRoute(route));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
            logger.info("Getting route by id");
            Route route = routeService.getRouteById(id);
            return route != null ? ResponseEntity.ok(route) : ResponseEntity.notFound().build();
        }

        @GetMapping
        public ResponseEntity<List<Route>> getAllRoutes() {
            logger.info("Getting all routes");
            return ResponseEntity.ok(routeService.getAllRoutes());
        }
}
