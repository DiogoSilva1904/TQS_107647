package deti.tqs.homework.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Route API", description = "Endpoints for managing routes")
public class RouteController {

        private final RouteService routeService;

        private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().getClass());

        @PostMapping
        @Operation(summary = "Save a route", description = "Save a route")
        public ResponseEntity<Route> saveRoute(@RequestBody Route route) {
            logger.info("Saving route");
            return ResponseEntity.status(HttpStatus.CREATED).body(routeService.saveRoute(route));
        }

        @GetMapping("/{id}")
        @Operation(summary = "Get a route by id", description = "Get a route by id")
        public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
            logger.info("Getting route by id");
            Route route = routeService.getRouteById(id);
            return route != null ? ResponseEntity.ok(route) : ResponseEntity.notFound().build();
        }

        @GetMapping
        @Operation(summary = "Get all routes", description = "Get all routes")
        public ResponseEntity<List<Route>> getAllRoutes() {
            logger.info("Getting all routes");
            return ResponseEntity.ok(routeService.getAllRoutes());
        }
}
