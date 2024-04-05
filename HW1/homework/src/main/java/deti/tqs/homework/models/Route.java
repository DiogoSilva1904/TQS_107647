package deti.tqs.homework.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;


    @OneToMany(mappedBy = "route")
    private List<Stop> stops = new ArrayList<>();

    @OneToMany(mappedBy = "route")
    private List<Trip> trips;
}