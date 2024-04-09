package deti.tqs.homework.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import deti.tqs.homework.models.Reservation;
import deti.tqs.homework.models.Trip;
import deti.tqs.homework.services.ReservationService;
import deti.tqs.homework.services.TripService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/reservations")
@Tag(name = "Reservation API", description = "Endpoints for managing reservations")
public class ReservationController {

            private final ReservationService reservationService;

            private final TripService tripService;

            private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().getClass());

            @PostMapping
            @Operation(summary = "Save a reservation", description = "Save a reservation with check if trip has any available seats and redirects to the confirmation page")
            public ModelAndView saveReservation(@RequestParam("tripId") Long tripId,
                                                @RequestParam("seat") String seat,
                                                @RequestParam("userEmail") String userEmail,
                                                @RequestParam("userName") String userName,
                                                @RequestParam("nif") Integer nif) {
                try{
                    logger.info("Saving reservation and checking if trip as any available seats");
                    Trip trip = tripService.getTripById(tripId);
                    Reservation reservation = new Reservation();
                    reservation.setTrip(trip);
                    reservation.setSeat(seat);
                    reservation.setName(userName);
                    reservation.setEmail(userEmail);
                    reservation.setNif(nif);
                    reservationService.saveReservation(reservation);
                    ModelAndView modelAndView = new ModelAndView();
                    modelAndView.setViewName("redirect:/reservations/confirmation/"+ reservation.getId());
                    modelAndView.addObject("reservation", reservation);
                    System.out.println("Reservation saved" + reservation);
                    return modelAndView;
                } catch (IllegalArgumentException e) {
                    logger.error("Error saving reservation");
                    return null;
                }
            }

            @PostMapping("/save")
            @Operation(summary = "Save a reservation", description = "Save a reservation with check if trip has any available seats")
            public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation) {
                try {
                    logger.info("Saving reservation and checking if trip as any available seats");
                    Trip trip = tripService.getTripById(reservation.getTrip().getId());
                    reservation.setTrip(trip);
                    return new ResponseEntity<>(reservationService.saveReservation(reservation), HttpStatus.CREATED);
                } catch (IllegalArgumentException e) {
                    logger.error("Error saving reservation");
                    return ResponseEntity.badRequest().build();
                }
            }

            @GetMapping("/confirmation/{reservation_id}")
            @Operation(summary = "Show confirmation page", description = "Show confirmation page for a reservation")
            public ModelAndView confirmationPage(@PathVariable UUID reservation_id) {
                logger.info("Showing confirmation page");
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("reservation", reservationService.getReservationById(reservation_id));
                modelAndView.setViewName("confirmation");
                return modelAndView;
            }

            @GetMapping("/createReservation/{tripId}")
            @Operation(summary = "Show create reservation page", description = "Show create reservation page on which the user can create a reservation for a trip")
            public ModelAndView createReservationPage(@PathVariable Long tripId) {
                logger.info("Showing create reservation page");
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("tripId", tripId);
                modelAndView.setViewName("createReservation");
                return modelAndView;
            }

            @GetMapping("/{id}")
            @Operation(summary = "Get reservation by id", description = "Get reservation by id")
            public ResponseEntity<Reservation> getReservationById(@PathVariable UUID id) {
                logger.info("Getting reservation by id");
                Reservation reservation = reservationService.getReservationById(id);
                return reservation != null ? ResponseEntity.ok(reservation) : ResponseEntity.notFound().build();
            }

            @GetMapping
            @Operation(summary = "Get all reservations", description = "Get all reservations")
            public ResponseEntity<List<Reservation>> getAllReservations() {
                logger.info("Getting all reservations");
                return ResponseEntity.ok(reservationService.getAllReservations());
            }
}
