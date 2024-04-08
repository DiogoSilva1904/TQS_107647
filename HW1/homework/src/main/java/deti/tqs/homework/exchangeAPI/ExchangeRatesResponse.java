package deti.tqs.homework.exchangeAPI;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRatesResponse {
    private double amount;
    private String base;
    private String date;
    private Map<String, Double> rates;

}