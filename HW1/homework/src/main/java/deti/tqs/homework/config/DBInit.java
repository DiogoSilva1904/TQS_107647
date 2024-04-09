package deti.tqs.homework.config;

import deti.tqs.homework.models.*;
import deti.tqs.homework.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DBInit {
    private final StopRepository stopRepository;
    private final RouteRepository routeRepository;
    private final TripRepository tripRepository;

    private final ReservationRepository reservationRepository;

    @Autowired
    public DBInit(StopRepository stopRepository, RouteRepository routeRepository, TripRepository tripRepository,ReservationRepository reservationRepository) {
        this.stopRepository = stopRepository;
        this.routeRepository = routeRepository;
        this.tripRepository = tripRepository;
        this.reservationRepository = reservationRepository;
    }

    @PostConstruct
    public void initialize() throws Exception{
        if (stopRepository.count()>0 && routeRepository.count()>0 && tripRepository.count()>0) {
            return;
        }

        String aveiro ="Aveiro";
        String coimbra = "Coimbra";
        String braga = "Braga";
        String faro = "Faro";
        String guarda = "Guarda";
        String vilareal = "Vila Real";
        Route route1 = new Route();
        routeRepository.save(route1);

        Route route2 = new Route();
        routeRepository.save(route2);

        Route route3 = new Route();
        routeRepository.save(route3);

        Route route4 = new Route();
        routeRepository.save(route4);

        Route route5 = new Route();
        routeRepository.save(route5);

        Route route6 = new Route();
        routeRepository.save(route6);

        Stop stop1 = new Stop();
        stop1.setName(aveiro);
        stop1.setStopOrder(1);
        stop1.setRoute(route1);
        stopRepository.save(stop1);
        Stop stop2 = new Stop();
        stop2.setRoute(route1);
        stop2.setName(coimbra);
        stop2.setStopOrder(2);
        stopRepository.save(stop2);
        Stop stop3 = new Stop();
        stop3.setName("Lisboa");
        stop3.setStopOrder(3);
        stop3.setRoute(route1);
        stopRepository.save(stop3);
        Stop stop4 = new Stop();
        stop4.setName(faro);
        stop4.setRoute(route1);
        stop4.setStopOrder(4);
        stopRepository.save(stop4);
        Stop stop5 = new Stop();
        stop5.setName(braga);
        stop5.setStopOrder(1);
        stop5.setRoute(route2);
        stopRepository.save(stop5);
        Stop stop6 = new Stop();
        stop6.setName("Porto");
        stop6.setStopOrder(2);
        stop6.setRoute(route2);
        stopRepository.save(stop6);
        Stop stop7 = new Stop();
        stop7.setName("Viseu");
        stop7.setStopOrder(3);
        stop7.setRoute(route2);
        stopRepository.save(stop7);
        Stop stop8 = new Stop();
        stop8.setName(guarda);
        stop8.setStopOrder(4);
        stop8.setRoute(route2);
        stopRepository.save(stop8);
        Stop stop9 = new Stop();
        stop9.setName(vilareal);
        stop9.setStopOrder(1);
        stop9.setRoute(route3);
        stopRepository.save(stop9);
        Stop stop10 = new Stop();
        stop10.setName("Viana do Castelo");
        stop10.setStopOrder(2);
        stop10.setRoute(route3);
        stopRepository.save(stop10);
        Stop stop11 = new Stop();
        stop11.setName("Portalegre");
        stop11.setStopOrder(1);
        stop11.setRoute(route4);
        stopRepository.save(stop11);
        Stop stop12 = new Stop();
        stop12.setName("Évora");
        stop12.setStopOrder(2);
        stop12.setRoute(route4);
        stopRepository.save(stop12);
        Stop stop13 = new Stop();
        stop13.setName("Beja");
        stop13.setStopOrder(1);
        stop13.setRoute(route5);
        stopRepository.save(stop13);
        Stop stop14 = new Stop();
        stop14.setName("Setúbal");
        stop14.setStopOrder(2);
        stop14.setRoute(route5);
        stopRepository.save(stop14);
        Stop stop15 = new Stop();
        stop15.setName(aveiro);
        stop15.setStopOrder(1);
        stop15.setRoute(route6);
        stopRepository.save(stop15);
        Stop stop16 = new Stop();
        stop16.setName(coimbra);
        stop16.setStopOrder(2);
        stop16.setRoute(route6);
        stopRepository.save(stop16);

        String idavolta = "IDA/VOLTA";
        String ida = "IDA";
        String bus = "A6";
        Trip trip1 = new Trip();
        trip1.setRoute(route1);
        trip1.setDepartureTime(LocalDateTime.parse("2024-04-08T08:00:00"));
        trip1.setArrivalTime(LocalDateTime.parse("2024-04-08T19:45:00"));
        trip1.setOrigin(aveiro);
        trip1.setDestination(faro);
        trip1.setPrice(50.0);
        trip1.setAvailableSeats(50);
        trip1.setBus_number("A1");
        trip1.setTrip_type(ida);
        tripRepository.save(trip1);

        Trip trip2 = new Trip();
        trip2.setRoute(route2);
        trip2.setDepartureTime(LocalDateTime.parse("2024-04-08T08:00:00"));
        trip2.setArrivalTime(LocalDateTime.parse("2024-04-08T17:00:00"));
        trip2.setOrigin(braga);
        trip2.setDestination(guarda);
        trip2.setPrice(60.0);
        trip2.setAvailableSeats(30);
        trip2.setBus_number("A2");
        trip2.setTrip_type(idavolta);
        tripRepository.save(trip2);

        Trip trip3 = new Trip();
        trip3.setRoute(route3);
        trip3.setDepartureTime(LocalDateTime.parse("2024-04-08T07:30:00"));
        trip3.setArrivalTime(LocalDateTime.parse("2024-04-08T15:00:00"));
        trip3.setOrigin(vilareal);
        trip3.setDestination("Viana do Castelo");
        trip3.setPrice(30.0);
        trip3.setAvailableSeats(40);
        trip3.setBus_number("A3");
        trip3.setTrip_type(ida);
        tripRepository.save(trip3);

        Trip trip4 = new Trip();
        trip4.setRoute(route1);
        trip4.setDepartureTime(LocalDateTime.parse("2024-04-10T10:00:00"));
        trip4.setArrivalTime(LocalDateTime.parse("2024-04-10T21:50:00"));
        trip4.setOrigin(aveiro);
        trip4.setDestination("Faro");
        trip4.setPrice(80.0);
        trip4.setAvailableSeats(50);
        trip4.setBus_number("A1");
        trip4.setTrip_type(idavolta);
        tripRepository.save(trip4);

        Trip trip5 = new Trip();
        trip5.setRoute(route2);
        trip5.setDepartureTime(LocalDateTime.parse("2024-04-10T10:00:00"));
        trip5.setArrivalTime(LocalDateTime.parse("2024-04-10T17:35:00"));
        trip5.setOrigin(braga);
        trip5.setDestination(guarda);
        trip5.setPrice(90.0);
        trip5.setAvailableSeats(30);
        trip5.setBus_number("A2");
        trip5.setTrip_type(ida);
        tripRepository.save(trip5);

        Trip trip6 = new Trip();
        trip6.setRoute(route3);
        trip6.setDepartureTime(LocalDateTime.parse("2024-04-10T11:00:00"));
        trip6.setArrivalTime(LocalDateTime.parse("2024-04-10T15:00:00"));
        trip6.setOrigin("Vila Real");
        trip6.setDestination("Viana do Castelo");
        trip6.setPrice(40.0);
        trip6.setAvailableSeats(40);
        trip6.setBus_number("A3");
        trip6.setTrip_type(idavolta);
        tripRepository.save(trip6);

        Trip trip7 = new Trip();
        trip7.setRoute(route4);
        trip7.setDepartureTime(LocalDateTime.parse("2024-04-10T12:00:00"));
        trip7.setArrivalTime(LocalDateTime.parse("2024-04-10T19:00:00"));
        trip7.setOrigin("Portalegre");
        trip7.setDestination("Évora");
        trip7.setPrice(40.0);
        trip7.setAvailableSeats(40);
        trip7.setBus_number("A4");
        trip7.setTrip_type(idavolta);
        tripRepository.save(trip7);

        Trip trip8 = new Trip();
        trip8.setRoute(route5);
        trip8.setDepartureTime(LocalDateTime.parse("2024-04-10T16:00:00"));
        trip8.setArrivalTime(LocalDateTime.parse("2024-04-10T18:00:00"));
        trip8.setOrigin("Beja");
        trip8.setDestination("Setúbal");
        trip8.setPrice(40.0);
        trip8.setAvailableSeats(40);
        trip8.setBus_number("A5");
        trip8.setTrip_type(idavolta);
        tripRepository.save(trip8);

        Trip trip9 = new Trip();
        trip9.setRoute(route6);
        trip9.setDepartureTime(LocalDateTime.parse("2024-04-10T10:00:00"));
        trip9.setArrivalTime(LocalDateTime.parse("2024-04-10T12:00:00"));
        trip9.setOrigin(aveiro);
        trip9.setDestination(coimbra);
        trip9.setPrice(40.0);
        trip9.setAvailableSeats(40);
        trip9.setBus_number(bus);
        trip9.setTrip_type(idavolta);
        tripRepository.save(trip9);

        Trip trip10 = new Trip();
        trip10.setRoute(route6);
        trip10.setDepartureTime(LocalDateTime.parse("2024-04-10T08:00:00"));
        trip10.setArrivalTime(LocalDateTime.parse("2024-04-10T09:30:00"));
        trip10.setOrigin(aveiro);
        trip10.setDestination(coimbra);
        trip10.setPrice(40.0);
        trip10.setAvailableSeats(0);
        trip10.setBus_number(bus);
        trip10.setTrip_type(ida);
        tripRepository.save(trip10);

        Trip trip11 = new Trip();
        trip11.setRoute(route5);
        trip11.setDepartureTime(LocalDateTime.parse("2024-04-10T10:00:00"));
        trip11.setArrivalTime(LocalDateTime.parse("2024-04-10T14:00:00"));
        trip11.setOrigin("Beja");
        trip11.setDestination("Setúbal");
        trip11.setPrice(40.0);
        trip11.setAvailableSeats(40);
        trip11.setBus_number(bus);
        trip11.setTrip_type(ida);
        tripRepository.save(trip11);

        Trip trip12 = new Trip();
        trip12.setRoute(route4);
        trip12.setDepartureTime(LocalDateTime.parse("2024-04-10T11:00:00"));
        trip12.setArrivalTime(LocalDateTime.parse("2024-04-10T16:25:00"));
        trip12.setOrigin("Portalegre");
        trip12.setDestination("Évora");
        trip12.setPrice(40.0);
        trip12.setAvailableSeats(40);
        trip12.setBus_number(bus);
        trip12.setTrip_type(ida);
        tripRepository.save(trip12);

        Reservation reservation1 = new Reservation();
        reservation1.setTrip(trip1);
        reservation1.setEmail("diogo@gmail.com");
        reservation1.setName("Diogo");
        reservation1.setNif(123456789);
        reservation1.setSeat("1A");
        reservationRepository.save(reservation1);

        Reservation reservation2 = new Reservation();
        UUID id = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

        reservation2.setId(id);
        reservation2.setTrip(trip2);
        reservation2.setEmail("maria@gmail.com");
        reservation2.setName("Maria");
        reservation2.setNif(987654321);
        reservation2.setSeat("2B");
        reservationRepository.save(reservation2);

        Reservation reservation3 = new Reservation();
        reservation3.setTrip(trip3);
        reservation3.setEmail("joao@gmail.com");
        reservation3.setName("Joao");
        reservation3.setNif(123456989);
        reservation3.setSeat("3C");
        reservationRepository.save(reservation3);
    }

}
