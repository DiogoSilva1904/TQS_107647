package deti.tqs.homework.controllers;

import deti.tqs.homework.models.Reservation;
import deti.tqs.homework.models.Trip;
import deti.tqs.homework.services.ReservationService;
import deti.tqs.homework.services.TripService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

            private final ReservationService reservationService;

            private final TripService tripService;

            @PostMapping
            public ModelAndView saveReservation(@RequestParam("tripId") Long tripId,
                                                @RequestParam("seat") String seat,
                                                @RequestParam("userEmail") String userEmail,
                                                @RequestParam("userName") String userName,
                                                @RequestParam("nif") Integer nif) {
                Reservation reservation = new Reservation();
                Trip trip = tripService.getTripById(tripId);
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
            }

            @GetMapping("/confirmation/{reservation_id}")
            public ModelAndView confirmationPage(@PathVariable UUID reservation_id) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("reservation", reservationService.getReservationById(reservation_id));
                modelAndView.setViewName("confirmation");
                return modelAndView;
            }

            @GetMapping("/createReservation/{tripId}")
            public ModelAndView createReservationPage(@PathVariable Long tripId) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("tripId", tripId);
                modelAndView.setViewName("createReservation");
                return modelAndView;
            }

            @GetMapping("/{id}")
            public ResponseEntity<Reservation> getReservationById(@PathVariable UUID id) {
                Reservation reservation = reservationService.getReservationById(id);
                return reservation != null ? ResponseEntity.ok(reservation) : ResponseEntity.notFound().build();
            }

            @GetMapping
            public ResponseEntity<List<Reservation>> getAllReservations() {
                return ResponseEntity.ok(reservationService.getAllReservations());
            }
}
