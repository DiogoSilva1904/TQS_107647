package deti.tqs.homework.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import deti.tqs.homework.models.Route;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String origin;

    private String destination;

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    private Double price;

    @ManyToOne
    @JsonIgnoreProperties({"stops","trips"})
    private Route route;

    private int available_seats;


    private String bus_number;

    @OneToMany(mappedBy = "trip")
    private List<Reservation> reservations;

    @Column(nullable = false)
    private String trip_type;

}
