package deti.tqs.homework.exchangeAPI;

import jakarta.persistence.Access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;
import java.util.Map;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "exchangeRates", key = "#root.methodName")
    public Map<String,Object> getExchangeRates() {
        Map<String,Object> exchangeRates = restTemplate.getForObject("https://api.frankfurter.app/latest", Map.class);
        return exchangeRates;
    }
}
